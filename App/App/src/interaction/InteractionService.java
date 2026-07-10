package interaction;
import logger.Logger;
import logger.LogLevel;
import java.util.List;


public class InteractionService {

    public Post findPostbyId(int postId) {
        for (Post p : DataBase.mockPosts){
            if (p.getId() == postId) {
                return p;
            }
        }
        return null;
    }

    public void upvote(int postId, int choice) {
        Logger logger = Logger.getInstance();
        Post post = findPostbyId(postId);
        if (post == null) {
            System.out.println("Post not found");
            logger.log(LogLevel.ERROR, "Post not found");
            return;
        }

        if (choice == 1) {
            post.increment_upvotes();
            System.out.println("Upvote added");
            logger.log(LogLevel.INFO, "Upvote added");
        } else if (choice == 2) {
            post.decrement_upvotes();
            System.out.println("Upvote removed");
            logger.log(LogLevel.INFO, "Upvote removed");
        }
    }

    public void downvote(int postId, int choice) {
        Post post = findPostbyId(postId);
        Logger logger = Logger.getInstance();
        if (post == null) {
            System.out.println("Post not found");
            logger.log(LogLevel.ERROR, "Post not found");

            return;
        }

        if (choice == 1) {
            post.increment_downvotes();
            System.out.println("Downvote added");
            logger.log(LogLevel.INFO, "Downvote added");
        } else if (choice == 2) {
            post.decrement_downvotes();
            System.out.println("Downvote removed");
            logger.log(LogLevel.INFO, "Downvote removed");
        }
    }

    public void comment(int postId, String text) {
        Post post = findPostbyId(postId);
        Logger logger = Logger.getInstance();
        if (post != null) {
            int commentId = DataBase.nextCommentId++;
            Comment newComment = new Comment(commentId, text, DataBase.currentLoggedInUser);

            post.addComment(newComment);
            System.out.println("Comment added ID: " + commentId);
            logger.log(LogLevel.INFO, "Comment added ID: " + commentId);
        } else {
            System.out.println("Post not found");
            logger.log(LogLevel.ERROR, "Post not found");
        }
    }

    public Comment findCommentById(int commentId) {
        for (Post post : DataBase.mockPosts) {
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

    public void replyToComment(int postId, int parentCommentId, String text) {
        Logger logger = Logger.getInstance();
        Post post = findPostbyId(postId);
        if (post == null) {
            System.out.println("Post not found");
            logger.log(LogLevel.ERROR, "Post not found");
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
            int replyId = DataBase.nextCommentId++;
            Comment reply = new Comment(replyId, text, DataBase.currentLoggedInUser);

            parentComment.addreply(reply);
            System.out.println("Reply at comment " + parentCommentId);
            logger.log(LogLevel.INFO, "Reply at comment ");

        } else {
            System.out.println("Base comment not found.");
            logger.log(LogLevel.ERROR, "Base comment not found.");
        }
    }

    public void editComment(int postId, int commentId, String newText) {
        Post post = findPostbyId(postId);
        Logger logger = Logger.getInstance();
        if (post == null) {
            System.out.println("Post not found");
            logger.log(LogLevel.ERROR, "Post not found");

            return;
        }

        for (Comment c : post.getComments()) {
            if (c.getId() == commentId) {
                if (!c.getAuthor().equals(DataBase.currentLoggedInUser)) {
                    System.out.println("Comment cannot be edited");
                    logger.log(LogLevel.ERROR, "Comment cannot be edited");
                    return;
                }
                c.setText(newText);
                System.out.println("Comment edited");
                logger.log(LogLevel.INFO, "Comment edited");
                return;
            }
        }
        System.out.println("Comment not found.");
        logger.log(LogLevel.ERROR, "Comment not found.");
    }

    public void deleteComment(int postId, int commentId) {
        Logger logger = Logger.getInstance();
        Post post = findPostbyId(postId);
        if (post == null) {
            System.out.println("Post not found");
            logger.log(LogLevel.ERROR, "Post not found");

            return;
        }

        for (int i = 0; i < post.getComments().size(); i++) {
            Comment c = post.getComments().get(i);
            if (c.getId() == commentId) {
                if (!c.getAuthor().equals(DataBase.currentLoggedInUser)) {
                    System.out.println("Comment cannot be deleted");
                    logger.log(LogLevel.ERROR, "Comment cannot be deleted");
                    return;
                }
                post.remove_comment(i);
                System.out.println("Comment deleted");
                logger.log(LogLevel.INFO, "Comment deleted");
                return;
            }
        }
        System.out.println("Comment not found.");
        logger.log(LogLevel.ERROR, "Comment not found.");
    }

    }
