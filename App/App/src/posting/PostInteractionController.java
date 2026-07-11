package posting;

import interaction.Comment;
import interaction.Post;
import interaction.VoteService;
import interaction.CommentService;
import interaction.PostRepo;
import posting.IntReader;
import posting.OutputWriter;
import posting.StringReader;
import posting.PostView;

public class PostInteractionController {
    private final StringReader stringReader;
    private final IntReader intReader;
    private final OutputWriter output;
    private final PostView postView;
    private final VoteService voteService;
    private final CommentService commentService;
    private final PostRepo postRepo;

    public PostInteractionController(StringReader stringReader, IntReader intReader,
                                     OutputWriter output, PostView postView,
                                     VoteService voteService, CommentService commentService,
                                     PostRepo postRepo) {
        this.stringReader = stringReader;
        this.intReader = intReader;
        this.output = output;
        this.postView = postView;
        this.voteService = voteService;
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

        try {
            switch (choice) {
                case "1" -> handleVote(postID, true);
                case "2" -> handleVote(postID, false);
                case "3" -> handleAddComment(postID);
                case "4" -> manageCommentInteraction(postID);
                case "0" -> output.write("Action cancelled.");
                default -> output.write("Invalid choice! Returning to menu.");
            }
        } catch (IllegalArgumentException | SecurityException e) {
            output.write("Error: " + e.getMessage());
        }
    }

    private void handleVote(int postID, boolean isUpvote) {
        String type = isUpvote ? "Upvote" : "Downvote";
        int voteChoice = intReader.readInt("Select: 1 for Add " + type + " | 2 for Remove " + type);

        if (isUpvote) {
            voteService.upvote(postID, voteChoice);
        } else {
            voteService.downvote(postID, voteChoice);
        }
        output.write("Vote updated successfully.");
    }

    private void handleAddComment(int postID) {
        String text = stringReader.readString("Enter your comment text:");
        commentService.comment(postID, text);
        output.write("Comment processed.");
    }

    private void manageCommentInteraction(int postID) {
        int commentID = intReader.readInt("Insert Comment ID to interact with:");
        Comment foundComment = commentService.findCommentById(commentID);

        if (foundComment == null) {
            output.write("Error: Comment with ID " + commentID + " does not exist.");
            return;
        }

        output.write("Selected comment by: " + foundComment.getAuthor());
        output.write("1. Reply to comment\n2. Edit comment\n3. Delete comment");
        String commentChoice = stringReader.readString("Select option (1-3): ");

        try {
            switch (commentChoice) {
                case "1" -> {
                    String text = stringReader.readString("Enter your reply text:");
                    commentService.replyToComment(postID, commentID, text);
                    output.write("Reply added successfully.");
                }
                case "2" -> {
                    String text = stringReader.readString("Enter new text for your comment:");
                    commentService.editComment(postID, commentID, text);
                    output.write("Comment updated successfully.");
                }
                case "3" -> {
                    commentService.deleteComment(postID, commentID);
                    output.write("Comment deleted successfully.");
                }
                default -> output.write("Invalid choice! Action cancelled.");
            }
        } catch (IllegalArgumentException | SecurityException e) {
            output.write("Error: " + e.getMessage());
        }
    }
}