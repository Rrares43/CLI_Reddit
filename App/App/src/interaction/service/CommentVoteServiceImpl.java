package interaction.service;

import interaction.model.Comment;
import interaction.model.CommentVote;
import interaction.repository.PostRepo;
import logger.Logger;
import logger.LogLevel;
import persistence.DatabaseSync;

import java.util.Optional;

public class CommentVoteServiceImpl implements CommentVoteService {
    private final PostRepo postRepo;
    private final Logger logger;

    public CommentVoteServiceImpl(PostRepo postRepo, Logger logger) {
        this.logger = logger;
        this.postRepo = postRepo;
    }

    @Override
    public void upvoteComment(int postId, int commentId) {
        handleVote(postId, commentId, true);
    }

    @Override
    public void downvoteComment(int postId, int commentId) {
        handleVote(postId, commentId, false);
    }

    private void handleVote(int postId, int commentId, boolean isUpvote) {
        Comment comment = postRepo.findCommentById(postId, commentId);

        if (comment == null) {
            logger.log(LogLevel.ERROR, "Comment with id " + commentId + " does not exist in post " + postId);
            throw new IllegalArgumentException("Comment does not exist");
        }

        String currentUsername = postRepo.getCurrentUser();
        if (currentUsername == null) {
            logger.log(LogLevel.ERROR, "No user is currently logged in.");
            return;
        }

        Optional<CommentVote> existingVoteOpt = comment.getUserVote(currentUsername);

        if (existingVoteOpt.isPresent()) {
            CommentVote existingVote = existingVoteOpt.get();

            if (existingVote.isUpvote() == isUpvote) {

                comment.getVotes().remove(existingVote);
                DatabaseSync.removeCommentVote(currentUsername, commentId, isUpvote ? 1 : -1);
                logger.log(LogLevel.INFO, "Vote removed for comment " + commentId);
            } else {

                existingVote.setUpvote(isUpvote);
                DatabaseSync.upsertCommentVote(currentUsername, commentId, isUpvote ? 1 : -1);
                logger.log(LogLevel.INFO, "Vote direction changed for comment " + commentId);
            }
        } else {

            comment.getVotes().add(new CommentVote(currentUsername, commentId, isUpvote));
            DatabaseSync.upsertCommentVote(currentUsername, commentId, isUpvote ? 1 : -1);
            logger.log(LogLevel.INFO, "New vote added for comment " + commentId);
        }
        postRepo.saveToFile();
    }
}