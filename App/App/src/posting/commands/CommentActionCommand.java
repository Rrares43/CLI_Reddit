package posting.commands;

public interface CommentActionCommand {
    void execute(int postId, int commentId);
}