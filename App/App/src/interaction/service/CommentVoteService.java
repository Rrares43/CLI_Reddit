package interaction.service;

public interface CommentVoteService {
    void upvoteComment(int commentId,int choice);
    void downvoteComment(int commentId, int choice);
}
