package interaction;

import community.Subreddit;

import java.util.ArrayList;
import java.util.List;

public class dataBase {

    public static List<Post> mockPosts = new ArrayList<>();
    public static List<Subreddit> mockSubreddits = new ArrayList<>();
    public static String currentLoggedInUser = "FlorinUser";
    public static int nextCommentId = 1;

    static{ mockSubreddits.add(new Subreddit("r/test", "testing for subs")); }

}
