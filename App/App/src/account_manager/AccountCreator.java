package account_manager;

import java.util.Scanner;

public class AccountCreator {
    public static void AccountCreate(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Username: ");
        String username = sc.nextLine();
        System.out.println("Enter Email: ");
        String email = sc.nextLine();
        System.out.println("Enter Password: ");
        System.out.println("Password must be at least 8 characters long and contain at least one number, one uppercase letter, one lowercase letter, and one special character.");
        String password = sc.nextLine();
        Account account = new Account(username, email, password);
        AccountOperations.saveAccount(account);
    }
}
