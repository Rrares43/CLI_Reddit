package interaction;

import java.util.List;

public interface PostRepository {
        Post findPostById(int postId);
        List<Post> findAllPosts();
        int getNextCommentId();
        String getCurrentUser();
        void saveToFile();
        void addPost(Post post);
    }