package posting;
import interaction.Comment;
import interaction.Post;
import posting.post_validators.Validator;

public class PostView {
    private final StringReader stringReader;
    private final OutputWriter output;
    private final Validator<String> notBlankValidator;
    private final Validator<String> linkValidator;

    public PostView(StringReader stringReader, OutputWriter output,
                    Validator<String> notBlankValidator, Validator<String> linkValidator) {
        this.stringReader = stringReader;
        this.output = output;
        this.notBlankValidator = notBlankValidator;
        this.linkValidator = linkValidator;
    }

    public String askForTitle() {
        int limit = 300;
        while (true) {
            String title = stringReader.readString("Enter post title (max " + limit + " characters):");
            if (!notBlankValidator.isValid(title)) {
                output.write("Error: Title cannot be empty!");
            } else if (title.length() > limit) {
                output.write("Error: Title is too long! You entered " + title.length() + " characters.");
            } else {
                return title;
            }
        }
    }

    public String askForContent() {
        int limit = 3000;
        while (true) {
            String content = stringReader.readString("Enter post content (max " + limit + " characters):");
            if (!notBlankValidator.isValid(content)) {
                output.write("Error: Content cannot be empty!");
            } else if (content.length() > limit) {
                output.write("Error: Content is too long!");
            } else {
                return content;
            }
        }
    }

    public String askForAttachment() {
        while (true) {
            String type = stringReader.readString("Do you want to add an attachment? (photo/link/no)");
            if (type.equalsIgnoreCase("photo")) return askForPhoto();
            if (type.equalsIgnoreCase("link")) return askForLink();
            if (type.equalsIgnoreCase("no")) {
                output.write("Proceeding text-only post");
                return "";
            }
            output.write("Invalid Input. Please type 'photo', 'link', or 'no'.");
        }
    }

    private String askForPhoto() {
        while (true) {
            String photo = stringReader.readString("Enter the photo name:");
            if (notBlankValidator.isValid(photo)) return "\n[Image: " + photo + "]";
            output.write("Error: Photo name cannot be empty!");
        }
    }

    private String askForLink() {
        while (true) {
            String link = stringReader.readString("Enter the link (e.g. www.google.com):");
            if (!linkValidator.isValid(link)) {
                output.write("Error: Invalid link!");
            } else {
                return "\n[Link: " + link + "]";
            }
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