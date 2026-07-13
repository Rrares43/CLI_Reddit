package posting;

import interaction.*;
import posting.commands.CommentActionCommand;
import posting.commands.PostActionCommand;

import java.util.HashMap;
import java.util.Map;

public class PostInteractionController {
    private final StringReader stringReader;
    private final IntReader intReader;
    private final OutputWriter output;
    private final PostView postView;
    private final CommentService commentService;
    private final PostRepository postRepo;

    private final Map<String, PostActionCommand> postCommands = new HashMap<>();
    private final Map<String, CommentActionCommand> commentCommands = new HashMap<>();

    public void registerPostCommand(String choice, PostActionCommand command) {
        postCommands.put(choice, command);
    }


    public void registerCommentCommand(String choice, CommentActionCommand command) {
        commentCommands.put(choice, command);
    }


    public PostInteractionController(StringReader stringReader, IntReader intReader,
                                     OutputWriter output, PostView postView,
                                     CommentService commentService,
                                     PostRepository postRepo) {
        this.stringReader = stringReader;
        this.intReader = intReader;
        this.output = output;
        this.postView = postView;
        this.commentService = commentService;
        this.postRepo = postRepo;
    }

    public void startInteraction() {
        int postID = intReader.readInt("Enter post ID:");
        Post foundPost = postRepo.findPostById(postID);

        if (foundPost == null) {
            output.write("Error: Post with ID " + postID + " does not exist in the database.");
            return;
        }

        postView.displayPost(foundPost);
        handlePostMenu(postID);
    }

    private void handlePostMenu(int postID) {
        output.write("Choose an action:\n1. Upvote\n2. Downvote\n3. Add Comment\n4. Interact with a specific Comment\n0. Cancel");
        String choice = stringReader.readString("Select option (0-4):");

        if (choice.equals("0")) {
            output.write("Action cancelled.");
            return;
        }

        PostActionCommand command = postCommands.get(choice);
        if (command != null) {
            try {
                command.execute(postID);
            } catch (Exception e) {
                output.write("Error: " + e.getMessage());
            }
        } else {
            output.write("Invalid choice! Returning to menu.");
        }
    }

    public void manageCommentInteraction(int postID) {
        int commentID = intReader.readInt("Insert Comment ID to interact with:");
        Comment foundComment = commentService.findCommentById(commentID);

        if (foundComment == null) {
            output.write("Error: Comment does not exist.");
            return;
        }

        output.write("Selected comment by: " + foundComment.getAuthor());
        output.write("1. Reply to comment\n2. Edit comment\n3. Delete comment");
        String commentChoice = stringReader.readString("Select option (1-3): ");

        CommentActionCommand command = commentCommands.get(commentChoice);
        if (command != null) {
            try {
                command.execute(postID, commentID);
            } catch (Exception e) {
                output.write("Error: " + e.getMessage());
            }
        } else {
            output.write("Invalid choice! Action cancelled.");
        }
    }
}