package interaction;

public interface VoteService {
    void upvote(int postId,int choice);
    void downvote(int postId,int choice);
}

