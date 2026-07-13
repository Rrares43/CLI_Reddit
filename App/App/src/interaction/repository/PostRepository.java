package interaction.repository;

import interaction.model.Post;

import java.util.List;

public interface PostRepository {
        Post findPostById(int postId);
        List<Post> findAllPosts();
        int getNextCommentId();
        String getCurrentUser();
        void saveToFile();
        void addPost(Post post);
    }