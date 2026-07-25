package post.command;

import post.service.CommentVoteService;
import io.StringReader;
import io.OutputWriter;

public class UpvoteCommentCommand implements CommentActionCommand {
    private final CommentVoteService commentVoteService;
    private final StringReader stringReader;
    private final OutputWriter output;

    public UpvoteCommentCommand(CommentVoteService commentVoteService, StringReader stringReader, OutputWriter output) {
        this.commentVoteService = commentVoteService;
        this.stringReader = stringReader;
        this.output = output;
    }

    @Override
    public void execute(int postId, int commentId) {
        commentVoteService.upvoteComment(postId, commentId);
        output.write("Upvote processed successfully\n");
    }
}