package bootstrap;

import account.SessionService;
import subreddit.SubredditMenu;
import subreddit.command.CreateSubredditCommand;
import subreddit.command.DeleteSubredditCommand;
import subreddit.command.EditSubredditCommand;
import subreddit.command.ViewSubredditCommand;
import io.OutputWriter;
import io.StringReader;

final class SubredditModule {
    private SubredditModule() {
    }

    static SubredditMenu create(SessionService sessionService, StringReader stringReader, OutputWriter output) {
        SubredditMenu subredditMenu = new SubredditMenu(sessionService, stringReader, output);
        subredditMenu.registerCommand("1", new CreateSubredditCommand(sessionService));
        subredditMenu.registerCommand("2", new ViewSubredditCommand(stringReader, output));
        subredditMenu.registerCommand("3", new EditSubredditCommand(sessionService, stringReader));
        subredditMenu.registerCommand("4", new DeleteSubredditCommand(stringReader, sessionService));
        return subredditMenu;
    }
}
