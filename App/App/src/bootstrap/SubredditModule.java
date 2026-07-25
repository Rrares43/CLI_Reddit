package bootstrap;

import account.SessionService;
import community.SubredditQuery;
import community.command.CreateSubredditCommand;
import community.command.EditSubredditCommand;
import community.command.ViewSubredditCommand;
import util.OutputWriter;
import util.StringReader;

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
