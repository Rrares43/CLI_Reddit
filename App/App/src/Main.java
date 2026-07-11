import account_manager.AccountQuery;
import logger.Logger;
import community.SubredditQuery;
import menu_commands.*;
import posting.*;
import posting.attachment_handlers.AttachmentHandler;
import posting.attachment_handlers.LinkAttachmentHandler;
import posting.attachment_handlers.NoAttachmentHandler;
import posting.attachment_handlers.PhotoAttachmentHandler;
import posting.commands.AddCommentCommand;
import posting.commands.ReplyCommentCommand;
import posting.commands.VoteCommand;
import posting.post_validators.IsNotBlank;
import posting.post_validators.IsValidLink;
import posting.post_validators.Validator;
import posting.post_validators.IsValidLength;

import java.util.HashMap;
import java.util.Map;
import interaction.*;

public class Main {
    public static void main(String[] args) {

        ConsoleIO console = new ConsoleIO();
        StringReader stringReader = console;
        IntReader intReader = console;
        OutputWriter output = console;

        logger.Logger.getInstance().log(logger.LogLevel.INFO, "Application Started");

        AccountQuery accountQuery = new AccountQuery();
        Logger logger = Logger.getInstance();
        PostRepo postRepo = new PostRepo();
        VoteService voteService = new VoteServiceImpl(postRepo, logger);
        CommentService commentService = new CommentServiceImpl(postRepo, logger);

        Validator<String> notBlankValidator = new IsNotBlank();
        Validator<String> linkValidator = new IsValidLink();
        Validator<String> titleLengthValidator = new IsValidLength(300);
        Validator<String> contentLengthValidator = new IsValidLength(3000);

        Map<String, AttachmentHandler> attachmentHandlers = new HashMap<>();
        attachmentHandlers.put("photo", new PhotoAttachmentHandler(stringReader, output, notBlankValidator));
        attachmentHandlers.put("link", new LinkAttachmentHandler(stringReader, output, linkValidator));
        attachmentHandlers.put("no", new NoAttachmentHandler(output));

        PostView postView = new PostView(
                stringReader,
                output,
                notBlankValidator,
                linkValidator,
                titleLengthValidator,
                contentLengthValidator,
                attachmentHandlers
        );

        PostInteractionController interactionController = new PostInteractionController(
                stringReader, intReader, output, postView, voteService, commentService, postRepo
        );

        PostQuery postQuery = new PostQuery(postView);

        InteractionQuery interactionQuery = new InteractionQuery(interactionController);
        SubredditQuery subredditQuery = new SubredditQuery();

        MenuDispatcher dispatcher = new MenuDispatcher(stringReader, output);

        dispatcher.registerCommand("1", new AccountCommand(accountQuery));
        dispatcher.registerCommand("2", new PostCommand(postQuery));
        dispatcher.registerCommand("3", new InterractionCommand(interactionQuery));
        dispatcher.registerCommand("4", new SubredditCommand(subredditQuery));
        dispatcher.registerCommand("5", new LoggerCommand(logger, stringReader, output));

        interactionController.registerPostCommand("1", new VoteCommand(voteService, intReader, output, true));  // true = Upvote
        interactionController.registerPostCommand("2", new VoteCommand(voteService, intReader, output, false)); // false = Downvote
        interactionController.registerPostCommand("3", new AddCommentCommand(commentService, stringReader, output));
        interactionController.registerPostCommand("4", postId -> interactionController.manageCommentInteraction(postId));

        interactionController.registerCommentCommand("1", new ReplyCommentCommand(commentService, stringReader, output));

        while (true) {
            output.write("\n--- MENIU PRINCIPAL ---");
            output.write("0. Exit");
            output.write("1. Account Options");
            output.write("2. Post Options");
            output.write("3. Interaction");
            output.write("4. Subreddit Creation");
            output.write("5. Logger");
            output.write("-----------------------");

            String choice = stringReader.readString("Select your choice (0/1/2/3/4/5): ");
            if (choice.equals("0")) {
                output.write("Application is closing");
                break;
            }
            dispatcher.execute(choice);
        }
    }
}