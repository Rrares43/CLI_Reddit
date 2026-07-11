package logger.logger_commands;

import menu_commands.LoggerSubCommand;
import posting.OutputWriter;

public class BacktoMain implements LoggerSubCommand {
    private final OutputWriter output;

    public BacktoMain(OutputWriter output) {
        this.output = output;
    }
    @Override
    public String getNotificationText() {
        return "Back to Main Menu";
    }

    @Override
    public boolean execute() {
        output.write("Going back to Main Menu");
        return false;
    }
}