package menu_commands;

import logger.Logger; // 💡 Importul corect pentru a recunoaște tipul Logger
import logger.logger_commands.BacktoMain;
import logger.logger_commands.ShowLogOptions;
import posting.ConsoleUI;
import java.util.ArrayList;
import java.util.List;

public class LoggerCommand implements MenuCommand {

    private final ConsoleUI ui;
    private final List<LoggerSubCommand> options = new ArrayList<>();

    // 💡 Toată magia de configurare a sub-meniului se întâmplă direct aici:
    public LoggerCommand(Logger logger, ConsoleUI ui) {
        this.ui = ui;
        this.options.add(new ShowLogOptions(logger));
        this.options.add(new BacktoMain(ui));
    }

    @Override
    public void execute() {
        boolean stayInMenu = true;

        while (stayInMenu) {
            ui.showMessage("\n--- LOGGER SETTINGS ---");

            for (int i = 0; i < options.size(); i++) {
                ui.showMessage((i + 1) + ". " + options.get(i).getNotificationText());
            }
            ui.showMessage("-----------------------");

            String choice = ui.getInput("Select option: ");

            try {
                int numarSelectat = Integer.parseInt(choice);
                int indexInLista = numarSelectat - 1;

                if (indexInLista >= 0 && indexInLista < options.size()) {
                    stayInMenu = options.get(indexInLista).execute();
                } else {
                    ui.showMessage("Invalid option.");
                }
            } catch (NumberFormatException e) {
                ui.showMessage("Please enter a valid number.");
            }
        }
    }
}