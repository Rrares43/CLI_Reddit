package interaction;

public interface PostService {
    Post createPost(String author, String title, String content, String subreddit);
}
