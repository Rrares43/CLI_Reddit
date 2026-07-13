package community;

import java.util.Objects;

public class SubredditQuery {
    public void subredditQuery() {
        boolean running = true;
        while(running) {
            System.out.println("Enter data or 0 to return to the previous menu:");
            String subredditName = SubName.ask();
            if(Objects.equals(subredditName, "0")){
                running = false;
                continue;
            }
            String subredditDescription = SubDescription.ask();
            if(Objects.equals(subredditDescription, "0")){
                running = false;
                continue;
            }
            Subreddit subreddit = new Subreddit(subredditName, subredditDescription);
            SubredditCreator.saveSubreddit(subreddit);
        }
    }

}
