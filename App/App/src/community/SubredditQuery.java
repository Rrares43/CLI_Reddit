package community;

import java.util.Objects;

public class SubredditQuery {
    public void subredditQuery() {
        boolean running = true;
        while(running) {
            System.out.println("Enter data or 0 to end the program");
            String subredditName = SubName.ask();
            String subredditDescription = SubDescription.ask();
            if(Objects.equals(subredditName, "0") || Objects.equals(subredditDescription, "0")){
                running = false;
                System.out.println("Program ended");
                continue;
            }
            Subreddit subreddit = new Subreddit(subredditName, subredditDescription);
            SubredditCreator.saveSubreddit(subreddit);
        }
    }

}
