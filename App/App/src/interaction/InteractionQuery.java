package interaction;

import posting.ConsoleUI;

public class InteractionQuery {
    private final VoteService voteService;
    private final CommentService commentService;
    private final PostRepo postRepo;
    private final ConsoleUI ui;

    public InteractionQuery(VoteService voteService, CommentService commentService, PostRepo postRepo, ConsoleUI ui) {
        this.voteService = voteService;
        this.commentService = commentService;
        this.postRepo = postRepo;
        this.ui = ui;
    }

    public void interactionQuery() {
        ui.interactWithPost(voteService, commentService, postRepo);
    }
}