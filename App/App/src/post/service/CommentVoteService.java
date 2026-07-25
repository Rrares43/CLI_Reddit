package post.service;

public interface CommentVoteService {
    void upvoteComment(int postId,int commentId);
    void downvoteComment(int postId,int commentId);
}
