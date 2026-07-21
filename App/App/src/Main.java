import account_manager.*;
import account_manager.account_commands.*;
import interaction.repository.InteractionQuery;
import interaction.repository.PostRepo;
import interaction.service.*;
import logger.Logger;
import community.SubredditQuery;
import menu_commands.*;
import menu_commands.AccountCommand;
import posting.*;
import posting.attachment_handlers.AttachmentHandler;
import posting.attachment_handlers.LinkAttachmentHandler;
import posting.attachment_handlers.NoAttachmentHandler;
import posting.attachment_handlers.PhotoAttachmentHandler;
import posting.commands.*;
import posting.post_validators.IsNotBlank;
import posting.post_validators.IsValidLink;
import posting.post_validators.Validator;
import posting.post_validators.IsValidLength;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        //comanda pentru migrarea bazei de date, nu mai trebuie apelata
        //DataMigrator.runMigration();

        // Test for the database connection

        /*
        System.out.println("Pornire Reddit CLI...");
        try (Connection conn = DataBaseConnection.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Conexiunea la baza de date a fost realizată");

            }
        } catch (SQLException e) {
            System.err.println("Eroare la conectarea bazei de date");
            e.printStackTrace();
        }

         */

        ConsoleIO console = new ConsoleIO();
        StringReader stringReader = console;
        IntReader intReader = console;
        OutputWriter output = console;

        logger.Logger.getInstance().log(logger.LogLevel.INFO, "Application Started");

        SessionService sessionService = new SessionService();

        AccountQuery accountQuery = new AccountQuery(stringReader, output, sessionService);
        AccountCreator accountCreator = new AccountCreator(stringReader, output);
        AccountLogin accountLogin = new AccountLogin(stringReader, sessionService, output);
        PasswordChanger passwordChanger = new PasswordChanger();
        AccountInfo accountInfo = new AccountInfo();
        AccountLogout accountLogout = new AccountLogout(sessionService);

        accountQuery.registerCommand("1" , new CreateAccountCommand(accountCreator));
        accountQuery.registerCommand("2", new LoginCommand(accountLogin));
        accountQuery.registerCommand("3", new ChangePasswordCommand(passwordChanger));
        accountQuery.registerCommand("4", new CheckCurrentUserCommand(accountInfo, sessionService));
        accountQuery.registerCommand("5", new LogoutCommand(accountLogout));

        Logger logger = Logger.getInstance();
        PostRepo postRepo = new PostRepo(sessionService);
        PostVoteService postVoteService = new PostVoteServiceImpl(postRepo, logger);
        PostEditServiceImpl postEditService = new PostEditServiceImpl(postRepo);
        CommentService commentService = new CommentServiceImpl(postRepo, logger);
        CommentVoteService commentVoteService = new CommentVoteServiceImpl(postRepo, logger);
        CommentActionCommand upvoteComm = new UpvoteCommentCommand(commentVoteService,stringReader,output);
        CommentActionCommand downvoteComm = new DownvoteCommentCommand(commentVoteService,stringReader,output);


        PostService postService = new PostServiceImpl(postRepo);

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
                titleLengthValidator,
                contentLengthValidator,
                attachmentHandlers
        );

        PostInteractionController interactionController = new PostInteractionController(
                stringReader, intReader, output, postView, commentService,commentVoteService,postRepo
        );

        CreatePostCommand createPostCommand = new CreatePostCommand(postView, postService, sessionService);

        InteractionQuery interactionQuery = new InteractionQuery(interactionController);
        SubredditQuery subredditQuery = new SubredditQuery();

        MenuDispatcher dispatcher = new MenuDispatcher(output);

        dispatcher.registerCommand("1", new AccountCommand(accountQuery));
        dispatcher.registerCommand("2", new PostCommand(createPostCommand));
        dispatcher.registerCommand("3", new InterractionCommand(interactionQuery));
        dispatcher.registerCommand("4", new SubredditCommand(subredditQuery));
        dispatcher.registerCommand("5", new LoggerCommand(logger, stringReader, output));

        interactionController.registerPostCommand("1", new VoteCommand(postVoteService, intReader, output, true));  // true = Upvote
        interactionController.registerPostCommand("2", new VoteCommand(postVoteService, intReader, output, false)); // false = Downvote
        interactionController.registerPostCommand("3", new AddCommentCommand(commentService, stringReader, output));
        interactionController.registerPostCommand("4", new EditPostCommand(stringReader, postEditService));
        interactionController.registerPostCommand("5", interactionController::manageCommentInteraction);

        interactionController.registerCommentCommand("1",upvoteComm);
        interactionController.registerCommentCommand("2",downvoteComm);
        interactionController.registerCommentCommand("3",new ReplyCommentCommand(commentService, stringReader, output));
        interactionController.registerCommentCommand("4",new EditCommentCommand(commentService, stringReader, output));
        interactionController.registerCommentCommand("5",new DeleteCommentCommand(commentService, output));


        while (true) {
            String choice;
            if(!sessionService.isLoggedIn()){
                choice = "1";
                dispatcher.execute(choice);
            }
            else {
                output.write("\n--- MAIN MENU ---");
                output.write("0. Exit");
                output.write("1. Account Options");
                output.write("2. Post Options");
                output.write("3. Interaction");
                output.write("4. Subreddit Creation");
                output.write("5. Logger");
                output.write("-----------------------");
                choice = stringReader.readString("Select your choice (0/1/2/3/4/5): ");
                if (choice.equals("0")) {
                    output.write("Application is closing");
                    break;
                }
                dispatcher.execute(choice);
            }
        }
    }

}