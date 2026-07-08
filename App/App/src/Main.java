import account_manager.AccountQuery;
import logger.Logger;
import posting.ConsoleUI;
import posting.PostQuery;
import interaction.InteractionQuery;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ConsoleUI ui = new ConsoleUI();

        AccountQuery accountQuery = new AccountQuery();
        PostQuery postQuery = new PostQuery();
        InteractionQuery interactionQuery = new InteractionQuery();

        Logger logger = Logger.getInstance();

        MenuDispatcher dispatcher = new MenuDispatcher(ui);

        dispatcher.registerCommand("1", new AccountCommand(accountQuery));
        dispatcher.registerCommand("2", new PostCommand(postQuery));
        dispatcher.registerCommand("3", new InterractionCommand(interactionQuery));
        dispatcher.registerCommand("4", new LoggerCommand(logger, ui));

        ui.showMessage("--- MENIU PRINCIPAL ---");
        ui.showMessage("1. Account Options");
        ui.showMessage("2. Post Options");
        ui.showMessage("3. Interaction");
        ui.showMessage("4. Logger");
        ui.showMessage("-----------------------");

        String choice = ui.getInput("Select your choice (1/2/3/4): ");


        dispatcher.execute(choice);

    }
}
