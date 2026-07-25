package post.service;

public interface PostVoteService {
    void upvote(int postId);
    void downvote(int postId);
}

