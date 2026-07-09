import logger.Logger;
import posting.ConsoleUI;

public class LoggerCommand implements MenuCommand {

    private final Logger logger;
    private final ConsoleUI ui;
    public LoggerCommand(Logger logger, ConsoleUI ui) {
        this.logger = logger;
        this.ui = ui;
    }
    @Override
    public void execute() {
        String prompt = """
                --- LOGGER SETTINGS ---
                1. Show all logs
                2. Back to Main Menu
                -----------------------
                Select mode (1/2): """;

        String choice = ui.getInput(prompt);

        if (choice.equals("1")) {
            logger.printLogsToConsole();
        } else if (choice.equals("2")) {
            ui.showMessage("Going back to Main Menu");
        } else {
            ui.showMessage("Invalid option.");
        }
    }
}
