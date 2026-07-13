package posting.commands;

import interaction.service.CommentService;
import posting.StringReader;
import posting.OutputWriter;

public class ReplyCommentCommand implements CommentActionCommand {
    private final CommentService commentService;
    private final StringReader stringReader;
    private final OutputWriter output;

    public ReplyCommentCommand(CommentService commentService, StringReader stringReader, OutputWriter output) {
        this.commentService = commentService;
        this.stringReader = stringReader;
        this.output = output;
    }

    @Override
    public void execute(int postId, int commentId) {
        String text = stringReader.readString("Enter your reply text:");
        commentService.replyToComment(postId, commentId, text);
        output.write("Reply added successfully.");
    }
}