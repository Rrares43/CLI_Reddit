package posting;

import interaction.Post;

public class PostQuery {
    private final ConsoleUI ui;
    public PostQuery(ConsoleUI ui) {
        this.ui = ui;
    }

    public void postQuery() {
        String mockAuthor = "rares0208";
        int mockId = 2;

        System.out.println("CREATE A NEW POST");


        String title = ui.askForTitle();
        String subreddit = ui.askForSubreddit();
        String baseContent = ui.askForContent();
        String attachment = ui.askForAttachment();

        String finalContent = baseContent + attachment;

        Post myNewPost = new Post(mockId, title, finalContent, mockAuthor, subreddit);

        ui.displayPost(myNewPost);


    }
}