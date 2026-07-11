package posting;

import interaction.Post;

public class PostQuery {
    private final PostView view;
    public PostQuery(PostView view) {
        this.view = view;
    }

    public void postQuery() {
        String mockAuthor = "rares0208";
        int mockId = 2;

        System.out.println("CREATE A NEW POST");


        String title = view.askForTitle();
        String subreddit = view.askForSubreddit();
        String baseContent = view.askForContent();
        String attachment = view.askForAttachment();

        String finalContent = baseContent + attachment;

        Post myNewPost = new Post(mockId, title, finalContent, mockAuthor, subreddit);

        view.displayPost(myNewPost);


    }
}