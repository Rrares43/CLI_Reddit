package bootstrap;

import account.AccountQuery;
import community.SubredditQuery;
import interaction.repository.InteractionQuery;
import logger.Logger;
import menu.AccountCommand;
import menu.InterractionCommand;
import menu.LoggerCommand;
import menu.MenuDispatcher;
import menu.PostCommand;
import menu.SubredditCommand;
import util.OutputWriter;
import util.StringReader;
import post.command.CreatePostCommand;

final class MenuModule {
    private MenuModule() {
    }

    static MenuDispatcher create(OutputWriter output,
                                 StringReader stringReader,
                                 Logger logger,
                                 AccountQuery accountQuery,
                                 CreatePostCommand createPostCommand,
                                 InteractionQuery interactionQuery,
                                 SubredditQuery subredditQuery) {
        MenuDispatcher dispatcher = new MenuDispatcher(output);
        dispatcher.registerCommand("1", new AccountCommand(accountQuery));
        dispatcher.registerCommand("2", new PostCommand(createPostCommand));
        dispatcher.registerCommand("3", new InterractionCommand(interactionQuery));
        dispatcher.registerCommand("4", new SubredditCommand(subredditQuery));
        dispatcher.registerCommand("5", new LoggerCommand(logger, stringReader, output));
        return dispatcher;
    }
}
