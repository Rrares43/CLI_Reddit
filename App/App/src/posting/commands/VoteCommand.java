package posting.commands;

import interaction.VoteService;
import posting.IntReader;
import posting.OutputWriter;
import posting.commands.PostActionCommand;

public class VoteCommand implements PostActionCommand {
    private final VoteService voteService;
    private final IntReader intReader;
    private final OutputWriter output;
    private final boolean isUpvote;

    public VoteCommand(VoteService voteService, IntReader intReader, OutputWriter output, boolean isUpvote) {
        this.voteService = voteService;
        this.intReader = intReader;
        this.output = output;
        this.isUpvote = isUpvote;
    }

    @Override
    public void execute(int postId) {
        String type = isUpvote ? "Upvote" : "Downvote";
        int voteChoice = intReader.readInt("Select: 1 for Add " + type + " | 2 for Remove " + type);

        if (isUpvote) voteService.upvote(postId, voteChoice);
        else voteService.downvote(postId, voteChoice);

        output.write("Vote updated successfully.");
    }
}