package bootstrap;

import account_manager.SessionService;
import community.SubredditQuery;
import community.subredditcommands.CreateSubredditCommand;
import community.subredditcommands.EditSubredditCommand;
import community.subredditcommands.ViewSubredditCommand;
import posting.OutputWriter;
import posting.StringReader;

final class SubredditModule {
    private SubredditModule() {
    }

    static SubredditQuery create(SessionService sessionService, StringReader stringReader, OutputWriter output) {
        SubredditQuery subredditQuery = new SubredditQuery(sessionService, stringReader, output);
        subredditQuery.registerCommand("1", new CreateSubredditCommand(sessionService));
        subredditQuery.registerCommand("2", new ViewSubredditCommand(stringReader, output));
        subredditQuery.registerCommand("3", new EditSubredditCommand(sessionService, stringReader));
        return subredditQuery;
    }
}
