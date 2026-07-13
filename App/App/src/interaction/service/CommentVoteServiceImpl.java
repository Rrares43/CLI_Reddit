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
    public void upvoteComment(int commentId, int choice) {
        Comment comment = postRepository.findCommentById(commentId);
        if(comment == null){
            logger.log(LogLevel.ERROR, "Comment not found");
            throw new IllegalArgumentException("Comment not found");
        }
        if(choice == 1){
            comment.getVoteTracker().addUpvotes();
            postRepository.saveToFile();
            logger.log(LogLevel.INFO, "Comment with id" + commentId + " has been upvoted");
        }
        else if(choice == 2){
            comment.getVoteTracker().removeUpvotes();
            postRepository.saveToFile();
            logger.log(LogLevel.INFO, "Comment with id" + commentId + "upovte deleted");
        }

    }

    @Override
    public void downvoteComment(int commentId, int choice) {
        Comment comment = postRepository.findCommentById(commentId);
        if(comment == null){
            logger.log(LogLevel.ERROR, "Comment not found");
            throw new IllegalArgumentException("Comment not found");
        }
        if(choice == 1){
            comment.getVoteTracker().addDownvotes();
            postRepository.saveToFile();
            logger.log(LogLevel.INFO, "Comment with id" + commentId + " has been dowvoted");
        }
        else if(choice == 2){
            comment.getVoteTracker().removeDownvotes();
            postRepository.saveToFile();
            logger.log(LogLevel.INFO, "Comment with id" + commentId + "downvote deleted");
        }

    }

}
