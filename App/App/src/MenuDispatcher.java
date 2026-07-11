import menu_commands.MenuCommand;
import posting.ConsoleIO;
import posting.StringReader;
import posting.OutputWriter;
import java.util.HashMap;
import java.util.Map;

public class MenuDispatcher {
    private Map<String, MenuCommand> commands = new HashMap<>();
    private final StringReader stringReader;
    private final OutputWriter output;

    public MenuDispatcher(StringReader stringReader, OutputWriter output) {
        this.stringReader = stringReader;
        this.output = output;
    }

    public void registerCommand(String choice, MenuCommand command) {
        commands.put(choice, command);
    }

    public void execute(String choice) {
        MenuCommand command = commands.get(choice);
        if (command != null) {
            command.execute();
        } else {
            output.write("Invalid Input");
        }
    }
}

