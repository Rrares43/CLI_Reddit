package posting.commands;

import interaction.service.PostVoteService;
import posting.IntReader;
import posting.OutputWriter;

public class VoteCommand implements PostActionCommand {
    private final PostVoteService postVoteService;
    private final IntReader intReader;
    private final OutputWriter output;
    private final boolean isUpvote;

    public VoteCommand(PostVoteService postVoteService, IntReader intReader, OutputWriter output, boolean isUpvote) {
        this.postVoteService = postVoteService;
        this.intReader = intReader;
        this.output = output;
        this.isUpvote = isUpvote;
    }

    @Override
    public void execute(int postId) {
        String type = isUpvote ? "Upvote" : "Downvote";
        int voteChoice = intReader.readInt("Select: 1 for Add " + type + " | 2 for Remove " + type);

        if (isUpvote) postVoteService.upvote(postId, voteChoice);
        else postVoteService.downvote(postId, voteChoice);

        output.write("Vote updated successfully.");
    }
}