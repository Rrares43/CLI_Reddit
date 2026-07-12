package posting;
import interaction.Comment;
import interaction.Post;
import posting.attachment_handlers.AttachmentHandler;
import posting.post_validators.Validator;

import java.util.Map;

public class PostView {
    private final StringReader stringReader;
    private final OutputWriter output;
    private final Validator<String> notBlankValidator;

    private final Validator<String> titleLengthValidator;
    private final Validator<String> contentLengthValidator;
    private final Map<String, AttachmentHandler> attachmentHandlers;

    public PostView(StringReader stringReader, OutputWriter output,
                    Validator<String> notBlankValidator,
                    Validator<String> titleLengthValidator,
                    Validator<String> contentLengthValidator,
                    Map<String, AttachmentHandler> attachmentHandlers) {
        this.stringReader = stringReader;
        this.output = output;
        this.notBlankValidator = notBlankValidator;
        this.titleLengthValidator = titleLengthValidator;
        this.contentLengthValidator = contentLengthValidator;
        this.attachmentHandlers = attachmentHandlers;
    }

    public String askForTitle() {
        while (true) {
            String title = stringReader.readString("Enter post title:");
            if (!notBlankValidator.isValid(title)) {
                output.write("Error: Title cannot be empty!");
            } else if (!titleLengthValidator.isValid(title)) {
                output.write("Error: Title exceeds the maximum allowed length!");
            } else {
                return title;
            }
        }
    }

    public String askForContent() {
        while (true) {
            String content = stringReader.readString("Enter post content:");
            if (!notBlankValidator.isValid(content)) {
                output.write("Error: Content cannot be empty!");
            } else if (!contentLengthValidator.isValid(content)) {
                output.write("Error: Content exceeds the maximum allowed length!");
            } else {
                return content;
            }
        }
    }

    public String askForAttachment() {
        String options = String.join("/", attachmentHandlers.keySet());

        while (true) {
            String type = stringReader.readString("Do you want to add an attachment? (" + options + ")");

            AttachmentHandler handler = attachmentHandlers.get(type.toLowerCase());
            if (handler != null) {
                return handler.handle();
            }
            output.write("Invalid Input. Please type one of: " + options);
        }
    }

    public String askForSubreddit() {
        while (true) {
            String subreddit = stringReader.readString("Enter the subreddit name:");
            if (notBlankValidator.isValid(subreddit)) return subreddit;
            output.write("Error: Subreddit name cannot be empty!");
        }
    }

    public void displayPost(Post post) {
        output.write("[" + post.getSubredditName() + "] " + post.getTitle());
        output.write("By: " + post.getAuthor() + " | ID: " + post.getId());
        output.write("Upvotes: " + post.getUpvotes() + " | Downvotes: " + post.getDownvotes());
        output.write("------------------------------------------");
        output.write(post.getContent());
        output.write("COMMENTS:");

        if (post.getComments().isEmpty()) {
            output.write("No comments yet. Be the first to share your thoughts!");
        } else {
            for (Comment c : post.getComments()) {
                displayCommentTree(c, "   ");
            }
        }
    }

    private void displayCommentTree(Comment comment, String indent) {
        output.write(indent + "↳ [ID: " + comment.getId() + "]" + comment.getAuthor() + ": " + comment.getText());
        for (Comment reply : comment.getReplies()) {
            displayCommentTree(reply, indent + "      ");
        }
    }
}