package interaction.service;

import interaction.model.Post;
import interaction.repository.PostRepository;

public class PostEditServiceImpl implements PostEditService{
    private final PostRepository postRepository;

    public PostEditServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void editPost(int postId, String newTitle, String newContent) {
        Post postToEdit = postRepository.findPostById(postId);
        postToEdit.setTitle(newTitle);
        postToEdit.setContent(newContent);
        postRepository.saveToFile();
        System.out.println("Post edited successfully");
    }
}
