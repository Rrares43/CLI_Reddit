package Posting;

import interaction.Post;

public class PostQuery {
    public static void postQuery() {

        ConsoleUI ui = new ConsoleUI();

        String mockAuthor = "rares0208";
        int mockId = 2;

        System.out.println("-- CREATE A NEW POST --");

        // Requesting info
        String title = ui.askForTitle();
        String subreddit = ui.askForSubreddit();
        String baseContent = ui.askForContent();
        String attachment = ui.askForAttachment();

        String finalContent = baseContent + attachment;

        Post myNewPost = new Post(mockId, title, finalContent, mockAuthor, subreddit);

        ui.displayPost(myNewPost);

        ui.closeScanner();
    }
}