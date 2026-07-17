package interaction.repository;

import community.Subreddit;
import interaction.model.Comment;
import interaction.model.Post;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    public static List<Post> mockPosts = new ArrayList<>();
    public static List<Subreddit> mockSubreddits = new ArrayList<>();

    static {
        mockSubreddits.add(new Subreddit("r/test", "testing for subs", "tester"));

        mockPosts.add(new Post(1, "Prima mea postare", "Acesta este continutul postarii de test.", "FlorinUser", "r/test"));
        mockPosts.add(new Post(2, "Intrebare Java", "Imi da o eroare la Scanner, ma poate ajuta cineva?", "Alex", "r/test"));
        mockPosts.add(new Post(3, "Regulament", "Va rugam sa pastrati un limbaj decent pe acest subreddit.", "Admin", "r/test"));

        Post primaPostare = mockPosts.get(0);
        Comment comentariuDeTest = new Comment(1, "Comentariu!", "Marcel2");
        primaPostare.getComments().add(comentariuDeTest);
    }
}
