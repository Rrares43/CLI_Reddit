package posting.commands;

import interaction.service.CommentVoteService;
import posting.StringReader;
import posting.OutputWriter;

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
        output.write("Select: 1 to ADD Upvote | 2 to REMOVE Upvote\n");
        String choiceStr = stringReader.readString("Enter choice (1-2): ");
        try {
            int voteChoice = Integer.parseInt(choiceStr);
            commentVoteService.upvoteComment(postId,commentId, voteChoice);
            output.write("Upvote processed successfully\n");
        }
        catch (NumberFormatException e) {
            output.write("Invalid choice");
        }
    }
}