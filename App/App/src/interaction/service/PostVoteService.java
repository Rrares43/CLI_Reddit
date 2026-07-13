package interaction.service;

public interface PostVoteService {
    void upvote(int postId,int choice);
    void downvote(int postId,int choice);
}

