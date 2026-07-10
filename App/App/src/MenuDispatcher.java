import menu_commands.MenuCommand;
import posting.ConsoleUI;

import java.util.HashMap;
import java.util.Map;

public class MenuDispatcher {
    private Map<String, MenuCommand> commands = new HashMap<>();
    private ConsoleUI ui;

    public MenuDispatcher(ConsoleUI ui) {
        this.ui = ui;
    }

    public void registerCommand(String choice, MenuCommand command) {
        commands.put(choice, command);
    }

    public void execute(String choice) {
        MenuCommand command = commands.get(choice);
        if (command != null) {
            command.execute();
        } else {
            ui.showMessage("Invalid Input");
        }
    }
}

