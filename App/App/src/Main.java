import account_manager.AccountQuery;
import logger.Logger;
import community.SubredditQuery;
import menu_commands.*;
import posting.ConsoleUI;
import posting.PostQuery;
import interaction.InteractionQuery;


public class Main {
    public static void main(String[] args) {

        ConsoleUI ui = new ConsoleUI();
        logger.Logger.getInstance().log(logger.LogLevel.INFO, "Application Started");

        AccountQuery accountQuery = new AccountQuery();
        PostQuery postQuery = new PostQuery();
        InteractionQuery interactionQuery = new InteractionQuery();
        SubredditQuery subredditQuery = new SubredditQuery();

        Logger logger = Logger.getInstance();

        MenuDispatcher dispatcher = new MenuDispatcher(ui);

        dispatcher.registerCommand("1", new AccountCommand(accountQuery));
        dispatcher.registerCommand("2", new PostCommand(postQuery));
        dispatcher.registerCommand("3", new InterractionCommand(interactionQuery));
        dispatcher.registerCommand("4", new SubredditCommand(subredditQuery));
        dispatcher.registerCommand("5", new LoggerCommand(logger, ui));

        while (true) {
            ui.showMessage("--- MENIU PRINCIPAL ---");
            ui.showMessage("0. Exit");
            ui.showMessage("1. Account Options");
            ui.showMessage("2. Post Options");
            ui.showMessage("3. Interaction");
            ui.showMessage("4. Subreddit Creation");
            ui.showMessage("5. Logger");
            ui.showMessage("-----------------------");

            String choice = ui.getInput("Select your choice (0/1/2/3/4/5): ");
            if (choice.equals("0")) {
                ui.showMessage("Application is closing");
                break;
            }
            dispatcher.execute(choice);
        }
    }
}
