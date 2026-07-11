package menu_commands;

import logger.Logger;
import logger.logger_commands.BacktoMain;
import logger.logger_commands.ShowLogOptions;
import posting.ConsoleIO;
import posting.OutputWriter;
import posting.StringReader;

import java.util.ArrayList;
import java.util.List;

public class LoggerCommand implements MenuCommand {

    private final StringReader stringReader;
    private final OutputWriter output;
    private final List<LoggerSubCommand> options = new ArrayList<>();

    public LoggerCommand(Logger logger, StringReader stringReader, OutputWriter output) {
        this.stringReader = stringReader;
        this.output = output;

        this.options.add(new ShowLogOptions(logger));

        this.options.add(new BacktoMain(output));
    }

    @Override
    public void execute() {
        boolean stayInMenu = true;

        while (stayInMenu) {
            output.write("\n--- LOGGER SETTINGS ---");

            for (int i = 0; i < options.size(); i++) {
                output.write((i + 1) + ". " + options.get(i).getNotificationText());
            }
            output.write("-----------------------");

            String choice = stringReader.readString("Select option: ");

            try {
                int numarSelectat = Integer.parseInt(choice);
                int indexInLista = numarSelectat - 1;

                if (indexInLista >= 0 && indexInLista < options.size()) {
                    stayInMenu = options.get(indexInLista).execute();
                } else {
                    output.write("Invalid option.");
                }
            } catch (NumberFormatException e) {
                output.write("Please enter a valid number.");
            }
        }
    }
}