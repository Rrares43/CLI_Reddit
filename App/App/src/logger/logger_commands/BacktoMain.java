package logger.logger_commands;

import menu_commands.LoggerSubCommand;
import posting.ConsoleUI;

public class BacktoMain implements LoggerSubCommand {
    private final ConsoleUI ui;

    public BacktoMain(ConsoleUI ui) {
        this.ui = ui;
    }

    @Override
    public String getNotificationText() {
        return "Back to Main Menu";
    }

    @Override
    public boolean execute() {
        ui.showMessage("Going back to Main Menu...");
        return false;
    }
}