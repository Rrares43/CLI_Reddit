package interaction;

import community.Subreddit;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    public static List<Post> mockPosts = new ArrayList<>();
    public static List<Subreddit> mockSubreddits = new ArrayList<>();
    public static String currentLoggedInUser = "FlorinUser";
    public static int nextCommentId = 1;

    static {
        mockSubreddits.add(new Subreddit("r/test", "testing for subs"));

        mockPosts.add(new Post(1, "Prima mea postare", "Acesta este continutul postarii de test.", "FlorinUser", "r/test"));
        mockPosts.add(new Post(2, "Intrebare Java", "Imi da o eroare la Scanner, ma poate ajuta cineva?", "Alex", "r/test"));
        mockPosts.add(new Post(99, "Regulament", "Va rugam sa pastrati un limbaj decent pe acest subreddit.", "Admin", "r/test"));
    }

    static{ mockSubreddits.add(new Subreddit("r/test", "testing for subs")); }

}
