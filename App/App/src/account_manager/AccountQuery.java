package account_manager;

import java.util.Scanner;

public class AccountQuery {
  public void accountQuery() {
    Scanner sc = new Scanner(System.in);
    boolean running = true;
    while (running) {
      System.out.println("Input 1 for account creation, 2 for loggin in or 3 to end the program:");
      String choice = sc.nextLine();

      if(choice.equals("1")) {
        System.out.println("Enter Username: ");
        String username = sc.nextLine();
        System.out.println("Enter Email: ");
        String email = sc.nextLine();
        System.out.println("Enter Password: ");
        System.out.println("Password must be at least 8 characters long and contain at least one number, one uppercase letter, one lowercase letter, and one special character.");
        String password = sc.nextLine();
        Account account = new Account(username, email, password);
        AccountOperations.saveAccount(account);

      } else if (choice.equals("2")) {
        System.out.println("Enter Username: ");
        String username = sc.nextLine();
        System.out.println("Enter Password: (or enter 0 to change password)");
        String password = sc.nextLine();
        if (password.equals("0")) {
          System.out.println("Enter Email:");
          String email = sc.nextLine();
          if (AccountOperations.checkEmail(email)) {
            System.out.println("Enter New Password: ");
            password = sc.nextLine();
            AccountOperations.changePassword(username, password);
          } else {
            System.out.println("Email not found");
          }
        } else if (AccountOperations.verifyAccount(new Account(username, "", password))) {
          System.out.println("Login Successful");
        } else {
          System.out.println("Login Failed");
        }
      }
      else if (choice.equals("3")) {
        running = false;
      }
      else {
        System.out.println("Invalid Input");
      }
    }
  }
}