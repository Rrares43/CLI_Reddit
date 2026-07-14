package interaction.service;

import interaction.model.Comment;
import interaction.repository.PostRepo;
import logger.Logger;
import logger.LogLevel;

public class CommentVoteServiceImpl implements CommentVoteService {
    private final PostRepo postRepository;
    private final Logger logger;

    public CommentVoteServiceImpl(PostRepo postRepository, Logger logger) {
        this.postRepository = postRepository;
        this.logger = logger;
    }

    @Override
    public void upvoteComment(int postId, int commentId, int choice) {
        Comment comment = postRepository.findCommentById(postId, commentId);
        if (comment == null) {
            logger.log(LogLevel.ERROR, "Comment not found");
            throw new IllegalArgumentException("Comment not found");
        }
        if (choice == 1) {
            comment.getVoteTracker().addUpvotes(); // 💡 Corectat la singular
            postRepository.saveToFile();
            logger.log(LogLevel.INFO, "Comment with id " + commentId + " has been upvoted");
        } else if (choice == 2) {
            comment.getVoteTracker().removeUpvotes(); // 💡 Corectat la singular
            postRepository.saveToFile();
            logger.log(LogLevel.INFO, "Comment with id " + commentId + " upvote deleted");
        }
    }

    @Override
    public void downvoteComment(int postId, int commentId, int choice) {
        Comment comment = postRepository.findCommentById(postId, commentId);
        if (comment == null) {
            logger.log(LogLevel.ERROR, "Comment not found");
            throw new IllegalArgumentException("Comment not found");
        }
        if (choice == 1) {
            comment.getVoteTracker().addDownvotes();
            postRepository.saveToFile();
            logger.log(LogLevel.INFO, "Comment with id " + commentId + " has been downvoted");
        } else if (choice == 2) {
            comment.getVoteTracker().removeDownvotes();
            postRepository.saveToFile();
            logger.log(LogLevel.INFO, "Comment with id " + commentId + " downvote deleted");
        }
    }
}