package community;

import account_manager.SessionService;

import java.util.Objects;

public class SubredditQuery {
    public void subredditQuery() {
        boolean running = true;
        SessionService sessionService = new SessionService();
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
            Subreddit subreddit = new Subreddit(subredditName, subredditDescription, sessionService.getCurrentUsername());
            SubredditOperations.saveSubreddit(subreddit);
        }
    }

}
