package posting.commands;

import interaction.service.CommentService;
import posting.OutputWriter;
import posting.StringReader;

public class EditCommentCommand implements CommentActionCommand {
    private final CommentService commentService;
    private final StringReader stringReader;
    private final OutputWriter output;

    public EditCommentCommand(CommentService commentService, StringReader stringReader, OutputWriter output) {
        this.commentService = commentService;
        this.stringReader = stringReader;
        this.output = output;
    }

    @Override
    public void execute(int postId, int commentId) {
        String text = stringReader.readString("Edit comment:");
        commentService.editComment(postId, commentId, text);
        output.write("Comment edited successfully.");
    }
}
