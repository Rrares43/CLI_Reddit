package bootstrap;

import interaction.repository.InteractionQuery;
import interaction.repository.PostRepo;
import interaction.service.CommentService;
import interaction.service.CommentServiceImpl;
import interaction.service.CommentVoteService;
import interaction.service.CommentVoteServiceImpl;
import interaction.service.PostEditServiceImpl;
import interaction.service.PostVoteService;
import interaction.service.PostVoteServiceImpl;
import logger.Logger;
import posting.IntReader;
import posting.OutputWriter;
import posting.PostInteractionController;
import posting.PostView;
import posting.StringReader;
import posting.commands.AddCommentCommand;
import posting.commands.CommentActionCommand;
import posting.commands.DeleteCommentCommand;
import posting.commands.DownvoteCommentCommand;
import posting.commands.EditCommentCommand;
import posting.commands.EditPostCommand;
import posting.commands.ReplyCommentCommand;
import posting.commands.UpvoteCommentCommand;
import posting.commands.VoteCommand;

final class InteractionModule {

    static InteractionQuery create(StringReader stringReader,
                                   IntReader intReader,
                                   OutputWriter output,
                                   PostView postView,
                                   PostRepo postRepo,
                                   Logger logger) {
        PostVoteService postVoteService = new PostVoteServiceImpl(postRepo, logger);
        PostEditServiceImpl postEditService = new PostEditServiceImpl(postRepo);
        CommentService commentService = new CommentServiceImpl(postRepo, logger);
        CommentVoteService commentVoteService = new CommentVoteServiceImpl(postRepo, logger);

        PostInteractionController interactionController = new PostInteractionController(
                stringReader, intReader, output, postView, commentService, commentVoteService, postRepo
        );

        CommentActionCommand upvoteComm = new UpvoteCommentCommand(commentVoteService, stringReader, output);
        CommentActionCommand downvoteComm = new DownvoteCommentCommand(commentVoteService, stringReader, output);

        interactionController.registerPostCommand("1", new VoteCommand(postVoteService, intReader, output, true));
        interactionController.registerPostCommand("2", new VoteCommand(postVoteService, intReader, output, false));
        interactionController.registerPostCommand("3", new AddCommentCommand(commentService, stringReader, output));
        interactionController.registerPostCommand("4", new EditPostCommand(stringReader, postEditService));
        interactionController.registerPostCommand("5", interactionController::manageCommentInteraction);

        interactionController.registerCommentCommand("1", upvoteComm);
        interactionController.registerCommentCommand("2", downvoteComm);
        interactionController.registerCommentCommand("3", new ReplyCommentCommand(commentService, stringReader, output));
        interactionController.registerCommentCommand("4", new EditCommentCommand(commentService, stringReader, output));
        interactionController.registerCommentCommand("5", new DeleteCommentCommand(commentService, output));

        return new InteractionQuery(interactionController);
    }
}
