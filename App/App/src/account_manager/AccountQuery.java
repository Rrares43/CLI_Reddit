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
  private final SessionService sessionService;
  private boolean running;

  public AccountQuery(StringReader stringReader, OutputWriter output, SessionService sessionService) {
    this.sessionService = sessionService;
    this.commands = new HashMap<>();
    this.stringReader = stringReader;
    this.output = output;

    this.commands.put("0", () -> this.running = false);
  }

  public void registerCommand(String key, AccountCommand command) {
    commands.put(key, command);
  }

  public void startAccountMenu() {
    running = true;

    while (running) {
      String choice;
      if(!sessionService.isLoggedIn()){
        output.write("\n--- ACCOUNT MENU ---");
        output.write("0. End Application");
        output.write("1. Create Account");
        output.write("2. Login");

        choice = stringReader.readString("Select an option (0/1/2): ");
        if(choice.equals("1") || choice.equals("2")) {
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
        else if(choice.equals("0")){
          output.write("App closed");
          System.exit(0);
        }
        else{
          output.write("Choice invalid!");
        }
      }
      else {
        output.write("\n--- ACCOUNT MENU ---");
        output.write("0. Back to Main Menu");
        output.write("1. Add account");
        output.write("2. Login into another account");
        output.write("3. Change Password");
        output.write("4. Check Current User");
        output.write("5. Logout");

        choice = stringReader.readString("Select an option (0/1/2/3/4/5): ");

        AccountCommand command = commands.get(choice);
        if (command != null) {
          try {
            command.execute();
          } catch (Exception e) {
            output.write("Error: " + e.getMessage());
          }
        }
        else {
          output.write("Invalid option! Please try again.");
        }
      }
    }
  }
}