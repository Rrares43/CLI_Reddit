package account_manager;

import account_manager.account_commands.AccountCommand;
import account_manager.account_commands.ChangePasswordCommand;
import account_manager.account_commands.CreateAccountCommand;
import account_manager.account_commands.LoginCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AccountQuery {
  private final Map<String, AccountCommand> Commands;
  private boolean running;

  public AccountQuery(){
    this.Commands = new HashMap<>();
    this.running = true;

    Commands.put("1" , new CreateAccountCommand());
    Commands.put("2" , new LoginCommand());
    Commands.put("3" , new ChangePasswordCommand());
    Commands.put("4", () -> this.running = false);
  }
  public void accountQuery() {
    Scanner sc = new Scanner(System.in);
    while (running) {
      System.out.println("Select an option(1/2/3/4):");
      System.out.println("1. Account creation");
      System.out.println("2. Logging in");
      System.out.println("3. Change password");
      System.out.println("4. End program");
      String choice = sc.nextLine();

      AccountCommand command = Commands.get(choice);

      if(command != null) {
        command.execute();
      }
      else {
        System.out.println("Invalid Input");
      }
    }
  }
}