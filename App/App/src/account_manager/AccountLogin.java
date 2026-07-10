package account_manager;

import java.util.Scanner;

public class AccountLogin {
    public static void Login(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Username: ");
        String username = sc.nextLine();
        System.out.println("Enter Password: ");
        String password = sc.nextLine();
        if (AccountOperations.verifyAccount(new Account(username, "", password))) {
            System.out.println("Login Successful");
        }
        else {
            System.out.println("Login Failed");
        }
    }
}
