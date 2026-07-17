package account_manager;

import account_manager.account_commands.AccountCommand;
import posting.StringReader;
import posting.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class AccountQuery {
  private final Map<String, AccountCommand> commands;
  private final StringReader stringReader;
  private final OutputWriter output;
  private boolean running;

  public AccountQuery(StringReader stringReader, OutputWriter output) {
    this.commands = new HashMap<>();
    this.stringReader = stringReader;
    this.output = output;

    this.commands.put("6", () -> this.running = false);
  }

  public void registerCommand(String key, AccountCommand command) {
    commands.put(key, command);
  }

  public void startAccountMenu() {
    running = true;

    while (running) {
      output.write("\n--- ACCOUNT MENU ---");
      output.write("1. Create Account");
      output.write("2. Login");
      output.write("3. Change Password");
      output.write("4. Check Current User");
      output.write("5. Logout");
      output.write("6. Back to Main Menu");


      String choice = stringReader.readString("Select an option (1/2/3/4/5/6): ");

      AccountCommand command = commands.get(choice);
      if (command != null) {
        try {
          command.execute();
        } catch (Exception e) {
          output.write("Error: " + e.getMessage());
        }
      } else {
        output.write("Invalid option! Please try again.");
      }
    }
  }
}