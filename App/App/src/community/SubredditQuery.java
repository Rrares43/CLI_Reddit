package community;

public class SubredditQuery {
    public void subredditQuery() {
        String subredditName = SubName.ask();
        String subredditDescription = SubDescription.ask();

        Subreddit subreddit = new Subreddit(subredditName, subredditDescription);
        SubredditCreator.saveSubreddit(subreddit);
    }

}
