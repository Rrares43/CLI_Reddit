package bootstrap;

import account_manager.SessionService;
import menu_commands.MenuDispatcher;
import posting.OutputWriter;
import posting.StringReader;

public class ApplicationLoop {
    private final SessionService sessionService;
    private final StringReader stringReader;
    private final OutputWriter output;
    private final MenuDispatcher dispatcher;

    public ApplicationLoop(AppContext context) {
        this.sessionService = context.getSessionService();
        this.stringReader = context.getStringReader();
        this.output = context.getOutput();
        this.dispatcher = context.getDispatcher();
    }

    public void run() {
        while (true) {
            if (!sessionService.isLoggedIn()) {
                dispatcher.execute("1");
                continue;
            }

            output.write("\n--- MAIN MENU ---");
            output.write("0. Exit");
            output.write("1. Account menu");
            output.write("2. Post Options");
            output.write("3. Interaction");
            output.write("4. Subreddit menu");
            output.write("5. Logger");
            output.write("-----------------------");

            String choice = stringReader.readString("Select your choice (0/1/2/3/4/5): ");
            if ("0".equals(choice)) {
                output.write("Application is closing");
                break;
            }
            dispatcher.execute(choice);
        }
    }
}
