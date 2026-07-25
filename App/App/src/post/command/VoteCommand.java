package post.command;

import interaction.service.PostVoteService;
import util.IntReader;
import util.OutputWriter;

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

        if (isUpvote) {
            postVoteService.upvote(postId);
        } else {
            postVoteService.downvote(postId);
        }

        output.write("Vote updated successfully.");
    }
}