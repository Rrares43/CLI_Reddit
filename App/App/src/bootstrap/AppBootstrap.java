package bootstrap;

import account.AccountQuery;
import account.SessionService;
import community.SubredditQuery;
import interaction.repository.InteractionQuery;
import interaction.repository.PostRepo;
import logger.LogLevel;
import logger.Logger;
import menu.MenuDispatcher;
import util.ConsoleIO;
import util.IntReader;
import util.OutputWriter;
import post.PostView;
import util.StringReader;
import post.command.CreatePostCommand;

public final class AppBootstrap {

    public static AppContext wire() {
        ConsoleIO console = new ConsoleIO();
        StringReader stringReader = console;
        IntReader intReader = console;
        OutputWriter output = console;

        Logger logger = Logger.getInstance();
        logger.log(LogLevel.INFO, "Application Started");

        SessionService sessionService = new SessionService();
        PostRepo postRepo = new PostRepo(sessionService);

        AccountQuery accountQuery = AccountModule.create(stringReader, output, sessionService);
        PostView postView = PostingModule.createPostView(stringReader, output);
        CreatePostCommand createPostCommand = PostingModule.createCreatePostCommand(
                postView, postRepo, sessionService
        );

        InteractionQuery interactionQuery = InteractionModule.create(
                stringReader, intReader, output, postView, postRepo, logger
        );

        SubredditQuery subredditQuery = SubredditModule.create(sessionService, stringReader, output);

        MenuDispatcher dispatcher = MenuModule.create(
                output,
                stringReader,
                logger,
                accountQuery,
                createPostCommand,
                interactionQuery,
                subredditQuery
        );

        return new AppContext(sessionService, stringReader, output, dispatcher);
    }
}
