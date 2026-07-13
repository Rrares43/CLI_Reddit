package posting.commands;

import interaction.Post;
import interaction.PostService;
import posting.PostView;

import java.util.Scanner;

public class CreatePostCommand {
    private final PostView view;
    private final PostService postService;

    public CreatePostCommand(PostView view, PostService postService) {
        this.view = view;
        this.postService = postService;
    }

    public void execute() {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are sure you want to create a new post? (y/n)");
        String answer = scanner.nextLine();
        if(answer.equals("n")){
            running = false;
        }
        if(running) {
            System.out.println("CREATE A NEW POST (or enter 0 in any field to return to previous menu):");
            String title = view.askForTitle();
            if (title.equals("0")) return;
            String subreddit = view.askForSubreddit();
            if (subreddit.equals("0")) return;
            String baseContent = view.askForContent();
            if (baseContent.equals("0")) return;
            String attachment = view.askForAttachment();

            String finalContent = baseContent + attachment;

            Post myNewPost = postService.createPost("rares0208", title, finalContent, subreddit);

            view.displayPost(myNewPost);
        }
    }
}