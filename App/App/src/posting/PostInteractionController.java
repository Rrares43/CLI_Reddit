package posting;

import community.Subreddit;
import community.SubredditOperations;
import interaction.model.Comment;
import interaction.model.Post;
import interaction.repository.PostRepository;
import interaction.service.CommentService;
import interaction.service.CommentVoteService;
import posting.commands.CommentActionCommand;
import posting.commands.PostActionCommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostInteractionController {
    private final StringReader stringReader;
    private final IntReader intReader;
    private final OutputWriter output;
    private final PostView postView;
    private final CommentService commentService;
    private final PostRepository postRepo;
    private final CommentVoteService commentVoteService;
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
                                     CommentService commentService, CommentVoteService commentVoteService,
                                     PostRepository postRepo) {
        this.stringReader = stringReader;
        this.intReader = intReader;
        this.output = output;
        this.postView = postView;
        this.commentService = commentService;
        this.commentVoteService = commentVoteService;
        this.postRepo = postRepo;
    }

    public void startInteraction() {
        String subredditName = askForSubreddit();
        if (subredditName == null) {
            return;
        }

        List<Post> posts = postRepo.findPostsBySubreddit(subredditName);
        if (posts.isEmpty()) {
            output.write("No posts found in " + subredditName + ".");
            return;
        }

        output.write("\nPosts in " + subredditName + ":");
        for (Post post : posts) {
            output.write("ID: " + post.getId() + " | Title: " + post.getTitle());
        }

        int postID = intReader.readInt("Enter the ID of the post you want to interact with:");
        Post foundPost = null;
        for (Post post : posts) {
            if (post.getId() == postID) {
                foundPost = post;
                break;
            }
        }

        if (foundPost == null) {
            output.write("Error: Post with ID " + postID + " does not exist in " + subredditName + ".");
            return;
        }

        postView.displayPost(foundPost);
        handlePostMenu(postID);
    }

    private String askForSubreddit() {
        List<Subreddit> subreddits = SubredditOperations.loadSubreddits();
        if (subreddits.isEmpty()) {
            output.write("No subreddits available.");
            return null;
        }

        while (true) {
            output.write("\nWhich subreddit would you like to browse?");
            output.write("Available subreddits:");
            for (Subreddit subreddit : subreddits) {
                output.write("- " + subreddit.getName());
            }

            String input = stringReader.readString("Enter subreddit name (or 0 to cancel): ");
            if ("0".equals(input)) {
                output.write("Action cancelled.");
                return null;
            }

            String normalized = normalizeSubredditName(input);
            for (Subreddit subreddit : subreddits) {
                if (subreddit.getName().equals(normalized)) {
                    return subreddit.getName();
                }
            }

            output.write("Invalid subreddit. Please try again.");
        }
    }

    private String normalizeSubredditName(String name) {
        if (name == null) {
            return "";
        }
        if (!name.startsWith("r/")) {
            return "r/" + name;
        }
        return name;
    }

    private void handlePostMenu(int postID) {
        output.write("Choose an action:\n1. Upvote\n2. Downvote\n3. Add Comment\n4. Edit Post\n5. Interact with a specific Comment\n0. Cancel");
        String choice = stringReader.readString("Select option (0-5):");

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


        output.write("1. Upvote comment\n2. Downvote comment\n3. Reply to comment\n4. Edit comment\n5. Delete comment");
        String commentChoice = stringReader.readString("Select option (1-5): ");

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
