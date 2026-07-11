package posting.commands;
import interaction.CommentService;
import posting.StringReader;
import posting.OutputWriter;
import posting.commands.PostActionCommand;

public class AddCommentCommand implements PostActionCommand {
    private final CommentService commentService;
    private final StringReader stringReader;
    private final OutputWriter output;

    public AddCommentCommand(CommentService commentService, StringReader stringReader, OutputWriter output) {
        this.commentService = commentService;
        this.stringReader = stringReader;
        this.output = output;
    }

    @Override
    public void execute(int postId) {
        String text = stringReader.readString("Enter your comment text:");
        commentService.comment(postId, text);
        output.write("Comment processed.");
    }
}