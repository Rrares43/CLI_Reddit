package community;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SubredditQuery {
    public void subredditQuery() {
        String subredditName = SubredditOperations.askForName();
        String subredditDescription = SubredditOperations.askForDescription();

        Subreddit subreddit = new Subreddit(subredditName, subredditDescription);
        SubredditOperations.saveSubreddit(subreddit);
    }

}
