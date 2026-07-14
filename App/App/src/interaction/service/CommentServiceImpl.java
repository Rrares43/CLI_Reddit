package interaction.service;

import interaction.model.Comment;
import interaction.model.Post;
import interaction.repository.PostRepo;
import logger.Logger;
import logger.LogLevel;
import java.util.List;

public class CommentServiceImpl implements CommentService {

    private final PostRepo postRepo;
    private final Logger logger;

    public CommentServiceImpl(PostRepo postRepository, Logger logger) {
        this.postRepo = postRepository;
        this.logger = logger;
    }

    @Override
    public void comment(int postId, String text) {
        Post post = postRepo.findPostById(postId);
        if (post == null) {
            logger.log(LogLevel.ERROR, "Post not found for comment: " + postId);
            throw new IllegalArgumentException("Post not found");
        }

        int commentId = postRepo.getNextCommentId();
        Comment newComment = new Comment(commentId, text, postRepo.getCurrentUser());
        post.addComment(newComment);
        postRepo.saveToFile();

        logger.log(LogLevel.INFO, "Comment added ID: " + commentId + " by " + postRepo.getCurrentUser());
    }

    @Override
    public void replyToComment(int postId, int parentCommentId, String text) {
        Post post = postRepo.findPostById(postId);
        if (post == null) {
            logger.log(LogLevel.ERROR, "Post not found for reply: " + postId);
            throw new IllegalArgumentException("Post not found");
        }

        Comment parentComment = null;
        for (Comment c : post.getComments()) {
            if (c.getId() == parentCommentId) {
                parentComment = c;
                break;
            }
        }

        if (parentComment == null) {
            logger.log(LogLevel.ERROR, "Base comment not found: " + parentCommentId);
            throw new IllegalArgumentException("Base comment not found.");
        }

        int replyId = postRepo.getNextCommentId();
        Comment reply = new Comment(replyId, text, postRepo.getCurrentUser());
        parentComment.addreply(reply);
        postRepo.saveToFile();

        logger.log(LogLevel.INFO, "Reply ID " + replyId + " added at comment " + parentCommentId);
    }

    @Override
    public void editComment(int postId, int commentId, String newText) {
        Post post = postRepo.findPostById(postId);
        if (post == null) {
            logger.log(LogLevel.ERROR, "Post not found for edit: " + postId);
            throw new IllegalArgumentException("Post not found");
        }

        for (Comment c : post.getComments()) {
            if (c.getId() == commentId) {
                if (!c.getAuthor().equals(postRepo.getCurrentUser())) {
                    logger.log(LogLevel.ERROR, "Comment " + commentId + " cannot be edited by " + postRepo.getCurrentUser());
                    throw new SecurityException("Comment cannot be edited");
                }
                c.setText(newText);
                postRepo.saveToFile();
                logger.log(LogLevel.INFO, "Comment edited: " + commentId);
                return;
            }
        }

        throw new IllegalArgumentException("Comment not found.");
    }

    @Override
    public void deleteComment(int postId, int commentId) {
        Post post = postRepo.findPostById(postId);
        if (post == null) {
            logger.log(LogLevel.ERROR, "Post not found for delete: " + postId);
            throw new IllegalArgumentException("Post not found");
        }

        for (int i = 0; i < post.getComments().size(); i++) {
            Comment c = post.getComments().get(i);
            if (c.getId() == commentId) {
                if (!c.getAuthor().equals(postRepo.getCurrentUser())) {
                    logger.log(LogLevel.ERROR, "Comment " + commentId + " cannot be deleted by " + postRepo.getCurrentUser());
                    throw new SecurityException("Comment cannot be deleted");
                }
                post.removeComment(i);
                postRepo.saveToFile();
                logger.log(LogLevel.INFO, "Comment deleted: " + commentId);
                return;
            }
        }

        throw new IllegalArgumentException("Comment not found.");
    }

    @Override
    public Comment findCommentById(int commentId) {
        for (Post post : postRepo.findAllPosts()) {
            Comment found = searchInComments(post.getComments(), commentId);
            if (found != null) {
                return found;
            }
        }
        return null;
    }


    private Comment searchInComments(List<Comment> comments, int commentId) {
        for (Comment c : comments) {
            if (c.getId() == commentId) {
                return c;
            }
            Comment foundInReplies = searchInComments(c.getReplies(), commentId);
            if (foundInReplies != null) {
                return foundInReplies;
            }
        }
        return null;
    }
}