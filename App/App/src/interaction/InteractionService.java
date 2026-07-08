package interaction;
import logger.Logger;
import logger.LogLevel;
public class InteractionService {

    public Post findPostbyId(int postId) {
        for (Post p : dataBase.mockPosts){
            if (p.getId() == postId) {
                return p;
            }
        }
        return null;
    }

    public void upvote(int postId, int choice) {
        Post post = findPostbyId(postId);
        Logger logger = Logger.getInstance();
        if (post == null) {
            System.out.println("Post not found");
            logger.log(LogLevel.ERROR,"Post not found");
            return;
        }

        if (choice == 1) {
            post.increment_upvotes();
            System.out.println("Upvote added");
            logger.log(LogLevel.INFO,"Upvote added");
        } else if (choice == 2) {
            post.decrement_upvotes();
            System.out.println("Upvote deleted");
            logger.log(LogLevel.INFO,"Upvote deleted");
        }
    }

    public void downvote(int postId, int choice) {
        Post post = findPostbyId(postId);
        Logger logger = Logger.getInstance();
        if (post == null) {
            System.out.println("Post does not exist");
            logger.log(LogLevel.ERROR,"Post does not exist");
            return;
        }

        if (choice == 1) {
            post.increment_downvotes();
            System.out.println("Downvote added");
            logger.log(LogLevel.INFO,"Downvote added");
        } else if (choice == 2) {
            post.decrement_downvotes();
            System.out.println("Downvote deleted");
            logger.log(LogLevel.INFO,"Downvote deleted");
        }
    }

    public void comment(int postId, String text) {
        Post post = findPostbyId(postId);
        Logger logger = Logger.getInstance();
        if (post != null) {
            int commentId = dataBase.nextCommentId++;
            Comment newComment = new Comment(commentId, text, dataBase.currentLoggedInUser);

            post.addComment(newComment);
            System.out.println("Comment added ID: " + commentId);
            logger.log(LogLevel.INFO,"Comment added ID: " + commentId);
        } else {
            System.out.println("Post does not exist");
            logger.log(LogLevel.ERROR,"Post does not exist");
        }
    }

    public void replyToComment(int postId, int parentCommentId, String text) {
        Post post = findPostbyId(postId);
        Logger logger = Logger.getInstance();
        if (post == null) {
            System.out.println("Postarea nu exista");
            logger.log(LogLevel.ERROR,"Postarea nu exista");
            return;
        }

        Comment parentComment = null;
        for (Comment c : post.getComments()) {
            if (c.getId() == parentCommentId) {
                parentComment = c;
                break;
            }
        }

        if (parentComment != null) {
            int replyId = dataBase.nextCommentId++;
            Comment reply = new Comment(replyId, text, dataBase.currentLoggedInUser);

            parentComment.addreply(reply);
            System.out.println("Reply " + parentCommentId);
            logger.log(LogLevel.INFO,"Reply " + parentCommentId);
        } else {
            System.out.println("Base comment does not exist.");
            logger.log(LogLevel.ERROR,"Base comment does not exist.");
        }
    }

    public void editComment(int postId, int commentId, String newText) {
        Post post = findPostbyId(postId);
        Logger logger = Logger.getInstance();
        if (post == null) {
            System.out.println("Post does not exist");
            logger.log(LogLevel.ERROR,"Post does not exist");

            return;
        }

        for (Comment c : post.getComments()) {
            if (c.getId() == commentId) {
                if (!c.getAuthor().equals(dataBase.currentLoggedInUser)) {
                    System.out.println("Cannot edit comment");
                    logger.log(LogLevel.ERROR,"Cannot edit comment");
                    return;
                }
                c.setText(newText);
                System.out.println("The comment was edited");
                logger.log(LogLevel.INFO,"The comment was edited");
                return;
            }
        }
        System.out.println("Comment not found.");
        logger.log(LogLevel.ERROR,"Comment not found.");
    }

    public void deleteComment(int postId, int commentId) {
        Post post = findPostbyId(postId);
        Logger logger = Logger.getInstance();
        if (post == null) {
            System.out.println("Post does not exist");
            logger.log(LogLevel.ERROR,"Post does not exist");
            return;
        }

        for (int i = 0; i < post.getComments().size(); i++) {
            Comment c = post.getComments().get(i);
            if (c.getId() == commentId) {
                if (!c.getAuthor().equals(dataBase.currentLoggedInUser)) {
                    System.out.println("Comment cannot be deleted");
                    logger.log(LogLevel.ERROR,"Comment cannot be deleted");
                    return;
                }
                post.remove_comment(i);
                System.out.println("Comment removed");
                logger.log(LogLevel.INFO,"Comment removed");
                return;
            }
        }
        System.out.println("Comment not found.");
        logger.log(LogLevel.ERROR,"Comment not found.");
    }

    }
