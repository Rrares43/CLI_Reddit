package interaction.service;

public interface CommentVoteService {
    void upvoteComment(int postId,int commentId,int choice);
    void downvoteComment(int postId,int commentId, int choice);
}
