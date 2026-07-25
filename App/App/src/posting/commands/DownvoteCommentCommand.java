package posting.commands;

import interaction.service.CommentVoteService;
import posting.StringReader;
import posting.OutputWriter;

public class DownvoteCommentCommand implements CommentActionCommand {
    private final CommentVoteService commentVoteService;
    private final StringReader stringReader;
    private final OutputWriter output;

    public DownvoteCommentCommand(CommentVoteService commentVoteService, StringReader stringReader, OutputWriter output) {
        this.commentVoteService = commentVoteService;
        this.stringReader = stringReader;
        this.output = output;
    }

    @Override
    public void execute(int postId, int commentId) {
        commentVoteService.downvoteComment(postId, commentId);
        output.write("Downvote processed successfully\n");
    }
}