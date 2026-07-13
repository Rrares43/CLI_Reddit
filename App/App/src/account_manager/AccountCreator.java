package account_manager;

import account_manager.account_verification.EmailVerification;
import account_manager.account_verification.PasswordVerification;

import java.util.IllegalFormatCodePointException;
import java.util.Scanner;

public class AccountCreator {
    public static void AccountCreate(){
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while(running) {
            System.out.println("Create an account? (y/n)");
            String answer = sc.nextLine().toLowerCase();
            if (!answer.equals("y")) {
                running = false;
                continue;
            }
            System.out.println("Enter Username: ");
            String username = sc.nextLine();
            System.out.println("Enter Email: ");
            String email = sc.nextLine();
            if (!EmailVerification.verify(email)) {
                continue;
            }
            System.out.println("Enter Password: ");
            System.out.println("Password must be at least 8 characters long and contain at least one number, one uppercase letter, one lowercase letter, and one special character.");
            String password = sc.nextLine();
            if (!PasswordVerification.verify(password)) {
                continue;
            }
            Account account = new Account(username, email, password);
            AccountOperations.saveAccount(account);
        }
    }
}
