package bootstrap;

import interaction.repository.InteractionQuery;
import interaction.repository.PostRepo;
import interaction.service.CommentService;
import interaction.service.CommentServiceImpl;
import interaction.service.CommentVoteService;
import interaction.service.CommentVoteServiceImpl;
import interaction.service.PostDeleteService;
import interaction.service.PostDeleteServiceImpl;
import interaction.service.PostEditServiceImpl;
import interaction.service.PostVoteService;
import interaction.service.PostVoteServiceImpl;
import logger.Logger;
import util.IntReader;
import util.OutputWriter;
import post.PostInteractionController;
import post.PostView;
import util.StringReader;
import post.command.AddCommentCommand;
import post.command.CommentActionCommand;
import post.command.DeleteCommentCommand;
import post.command.DeletePostCommand;
import post.command.DownvoteCommentCommand;
import post.command.EditCommentCommand;
import post.command.EditPostCommand;
import post.command.ReplyCommentCommand;
import post.command.UpvoteCommentCommand;
import post.command.VoteCommand;

final class InteractionModule {

    static InteractionQuery create(StringReader stringReader,
                                   IntReader intReader,
                                   OutputWriter output,
                                   PostView postView,
                                   PostRepo postRepo,
                                   Logger logger) {
        PostVoteService postVoteService = new PostVoteServiceImpl(postRepo, logger);
        PostEditServiceImpl postEditService = new PostEditServiceImpl(postRepo);
        PostDeleteService postDeleteService = new PostDeleteServiceImpl(postRepo);
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
        interactionController.registerPostCommand("4", new EditPostCommand(stringReader, postEditService, postRepo));
        interactionController.registerPostCommand("5", new DeletePostCommand(postDeleteService, output));
        interactionController.registerPostCommand("6", interactionController::manageCommentInteraction);

        interactionController.registerCommentCommand("1", upvoteComm);
        interactionController.registerCommentCommand("2", downvoteComm);
        interactionController.registerCommentCommand("3", new ReplyCommentCommand(commentService, stringReader, output));
        interactionController.registerCommentCommand("4", new EditCommentCommand(commentService, stringReader, output));
        interactionController.registerCommentCommand("5", new DeleteCommentCommand(commentService, output));

        return new InteractionQuery(interactionController);
    }
}
