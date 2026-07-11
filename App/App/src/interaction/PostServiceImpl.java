package interaction;

public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private int currentId = 1;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(String author, String title, String content, String subreddit) {
        int newId = currentId++;
        Post newPost = new Post(newId, title, content, author, subreddit);



        return newPost;
    }
}