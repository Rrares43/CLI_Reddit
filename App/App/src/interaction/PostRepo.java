package interaction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PostRepo implements PostRepository {
    private final List<Post> posts;
    private static final Path DB_FILE = Paths.get("reddit_database.json");
    private final Gson gson;

    public PostRepo() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        this.posts = loadFromFile();

        if (this.posts.isEmpty()) {
            this.posts.addAll(DataBase.mockPosts);
            saveToFile();
        }
    }

    private List<Post> loadFromFile() {
        if (!Files.exists(DB_FILE)) {
            return new ArrayList<>();
        }

        try (Reader reader = Files.newBufferedReader(DB_FILE)) {
            Type listType = new TypeToken<ArrayList<Post>>(){}.getType();
            List<Post> loadedData = gson.fromJson(reader, listType);
            return loadedData != null ? loadedData : new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error reading JSON database: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveToFile() {
        try (Writer writer = Files.newBufferedWriter(DB_FILE)) {
            gson.toJson(this.posts, writer);
        } catch (Exception e) {
            System.err.println("Error saving to JSON: " + e.getMessage());
        }
    }

    @Override
    public Post findPostById(int postId) {
        for (Post p : this.posts) {
            if (p.getId() == postId) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Post> findAllPosts() {
        return this.posts;
    }

    @Override
    public int getNextCommentId() {
        return DataBase.nextCommentId++;
    }

    @Override
    public String getCurrentUser() {
        return DataBase.currentLoggedInUser;
    }
    @Override
    public void addPost(Post post) {
        this.posts.add(post);
        saveToFile();
    }
}