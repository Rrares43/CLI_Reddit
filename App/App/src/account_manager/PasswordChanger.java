package account_manager;

import java.util.Scanner;

public class PasswordChanger {
    public void ChangePassword(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Email:");
        String email = sc.nextLine();
        if (AccountOperations.checkEmail(email)) {
            System.out.println("Enter New Password: ");
            String password = sc.nextLine();
            AccountOperations.changePassword(email, password);
        }
        else {
            System.out.println("Email not found");
        }
    }
}
