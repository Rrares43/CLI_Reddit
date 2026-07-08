package posting;

import interaction.Comment;
import interaction.InteractionService;
import interaction.Post;

import java.util.Scanner;

public class ConsoleUI {
    private Scanner sc;

    public ConsoleUI() {
        this.sc = new Scanner(System.in);
    }

    public String askForTitle() {
        int limit = 300;
        while (true) {
            System.out.println("Enter post title (max " + limit + " characters):");
            String title = sc.nextLine();

            if (!InputValidator.isNotBlank(title)) {
                System.out.println("Error: Title cannot be empty!");

            } else if (!posting.InputValidator.isValidLength(title, limit)) {
                System.out.println("Error: Title is too long! You entered " + title.length() + " characters.");

            } else {
                return title;
            }
        }
    }

    public String askForContent() {
        int limit = 3000;
        while (true) {
            System.out.println("Enter post content (max " + limit + " characters):");
            String content = sc.nextLine();

            if (!InputValidator.isNotBlank(content)) {
                System.out.println("Error: Content cannot be empty!");
            } else if (!InputValidator.isValidLength(content, limit)) {
                System.out.println("Error: Content is too long! You entered " + content.length() + " characters.");
            } else {
                return content;
            }
        }
    }

    public String askForAttachment() {
        while (true) {
            System.out.println("Do you want to add an attachment? (photo/link/no)");
            String type = sc.nextLine();

            if (type.equalsIgnoreCase("photo")) {
                return askForPhoto();
            } else if (type.equalsIgnoreCase("link")) {
                return askForLink();
            } else if (type.equalsIgnoreCase("no")) {
                System.out.println("Proceeding text-only post...");
                return "";
            } else {
                System.out.println("Invalid Input. Please type 'photo', 'link', or 'no'.");
            }
        }
    }

    private String askForPhoto() {
        while (true) {
            System.out.println("Enter the photo name:");
            String photo = sc.nextLine();
            if (InputValidator.isNotBlank(photo)) {
                return "\n[Image: " + photo + "]";
            }
            System.out.println("Error: Photo name cannot be empty!");
        }
    }

    private String askForLink() {
        while (true) {
            System.out.println("Enter the link (e.g. www.google.com):");
            String link = sc.nextLine();

            if (!InputValidator.isNotBlank(link)) {
                System.out.println("Error: Link cannot be empty!");
            } else if (!InputValidator.isValidLink(link)) {
                System.out.println("Error: Invalid link!");
            } else {
                return "\n[Link: " + link + "]";
            }
        }
    }

    public String askForSubreddit() {
        while (true) {
            System.out.println("Enter the subreddit name:");
            String subreddit = sc.nextLine();
            if(!InputValidator.isNotBlank(subreddit)){
                System.out.println("Error: Subreddit name cannot be empty!");
            }
            else{
                return subreddit;
            }
        }
    }

    private final InteractionService interactionService = new InteractionService();

    public void interactWithPost() {
        System.out.println("Enter post ID:");

        if (sc.hasNextInt()) {
            int postID = sc.nextInt();
            sc.nextLine();

            Post foundPost = interactionService.findPostbyId(postID);

            if (foundPost != null) {
                displayPost(foundPost);

                System.out.println("Choose an action:");
                System.out.println("1. Upvote");
                System.out.println("2. Downvote");
                System.out.println("3. Add Comment");
                System.out.println("4. Interact with a specific Comment");
                System.out.println("0. Cancel");
                System.out.print("Select option (0-4): ");

                String choice = sc.nextLine();

                switch (choice) {
                    case "1" -> {
                        System.out.println("Select: 1 for Add Upvote | 2 for Remove Upvote");
                        if (sc.hasNextInt()) {
                            int voteChoice = sc.nextInt();
                            sc.nextLine();
                            interactionService.upvote(postID, voteChoice);
                        } else {
                            System.out.println("Invalid number!");
                            sc.nextLine();
                        }
                    }
                    case "2" -> {
                        System.out.println("Select: 1 for Add Downvote | 2 for Remove Downvote");
                        if (sc.hasNextInt()) {
                            int voteChoice = sc.nextInt();
                            sc.nextLine();
                            interactionService.downvote(postID, voteChoice);
                        } else {
                            System.out.println("Invalid number!");
                            sc.nextLine();
                        }
                    }
                    case "3" -> {
                        System.out.println("Enter your comment text:");
                        String text = sc.nextLine();
                        interactionService.comment(postID, text);
                    }
                    case "4" -> {
                        manageCommentInteraction(postID);
                    }
                    case "0" -> System.out.println("Action cancelled.");
                    default -> System.out.println("Invalid choice! Returning to menu.");
                }

            } else {
                System.out.println("Error: Post with ID " + postID + " does not exist in the database.");
            }

        } else {
            System.out.println("Error: Post ID must be a number!");
            sc.nextLine();
        }
    }

    private void manageCommentInteraction(int postID) {
        System.out.println("Insert Comment ID to interact with:");

        if (sc.hasNextInt()) {
            int commentID = sc.nextInt();
            sc.nextLine();

            Comment foundComment = interactionService.findCommentById(commentID);

            if (foundComment != null) {
                System.out.println("Selected comment by: " + foundComment.getAuthor());
                System.out.println("1. Reply to comment");
                System.out.println("2. Edit comment");
                System.out.println("3. Delete comment");
                System.out.print("Select option (1-3): ");

                String commentChoice = sc.nextLine();

                switch (commentChoice) {
                    case "1" -> {
                        System.out.println("Enter your reply text:");
                        String text = sc.nextLine();
                        interactionService.replyToComment(postID, commentID, text);
                    }
                    case "2" -> {
                        System.out.println("Enter new text for your comment:");
                        String text = sc.nextLine();
                        interactionService.editComment(postID, commentID, text);
                    }
                    case "3" -> {
                        interactionService.deleteComment(postID, commentID);
                    }
                    default -> System.out.println("Invalid choice! Action cancelled.");
                }
            } else {
                System.out.println("Error: Comment with ID " + commentID + " does not exist.");
            }
        } else {
            System.out.println("Error: Comment ID must be a number!");
            sc.nextLine();
        }
    }


    public void displayPost(Post post) {
        System.out.println("\n==========================================");
        System.out.println("📌 [" + post.getSubredditName() + "] " + post.getTitle());
        System.out.println("👤 By: " + post.getAuthor() + " | ID: " + post.getId());
        System.out.println("🔼 Upvotes: " + post.getUpvotes() + " | 🔽 Downvotes: " + post.getDownvotes());
        System.out.println("------------------------------------------");
        System.out.println(post.getContent());
        System.out.println("==========================================");

        System.out.println("💬 COMMENTS:");
        if (post.getComments().isEmpty()) {
            System.out.println("   No comments yet. Be the first to share your thoughts!");
        } else {
            for (Comment c : post.getComments()) {
                displayCommentTree(c, "   ");
            }
        }
        System.out.println("==========================================\n");
    }


    private void displayCommentTree(Comment comment, String indent) {
        System.out.println(indent + "↳ [ID: " + comment.getId() + "] 👤 " + comment.getAuthor() + ": " + comment.getText());

        for (Comment reply : comment.getReplies()) {
            displayCommentTree(reply, indent + "      ");
        }
    }

    public String getInput(String prompt) {
        System.out.println(prompt);
        return sc.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
    public void closeScanner() {
        sc.close();
    }
}