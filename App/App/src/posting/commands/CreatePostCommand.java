package posting.commands;

import interaction.Post;
import interaction.PostService;
import posting.PostView;

public class CreatePostCommand {
    private final PostView view;
    private final PostService postService;

    public CreatePostCommand(PostView view, PostService postService) {
        this.view = view;
        this.postService = postService;
    }

    public void execute() {
        System.out.println("CREATE A NEW POST");

        String title = view.askForTitle();
        String subreddit = view.askForSubreddit();
        String baseContent = view.askForContent();
        String attachment = view.askForAttachment();

        String finalContent = baseContent + attachment;

        Post myNewPost = postService.createPost("rares0208", title, finalContent, subreddit);

        view.displayPost(myNewPost);
    }
}