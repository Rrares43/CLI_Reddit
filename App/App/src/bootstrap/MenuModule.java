package bootstrap;

import account_manager.AccountQuery;
import community.SubredditQuery;
import interaction.repository.InteractionQuery;
import logger.Logger;
import menu_commands.AccountCommand;
import menu_commands.InterractionCommand;
import menu_commands.LoggerCommand;
import menu_commands.MenuDispatcher;
import menu_commands.PostCommand;
import menu_commands.SubredditCommand;
import posting.OutputWriter;
import posting.StringReader;
import posting.commands.CreatePostCommand;

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
