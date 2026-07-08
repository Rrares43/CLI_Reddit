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
                1. Set to CONSOLE
                2. Set to FILE
                -----------------------
                Select mode (1/2): """;

        String choice = ui.getInput(prompt);

        if (choice.equals("1")) {
            logger.setLogMode("CONSOLE");
            ui.showMessage("Logger mode successfully set to CONSOLE.");
        } else if (choice.equals("2")) {
            logger.setLogMode("FILE");
            ui.showMessage("Logger mode successfully set to FILE.");
        } else {
            ui.showMessage("Invalid input. Logger mode remains unchanged.");
        }
    }
}
