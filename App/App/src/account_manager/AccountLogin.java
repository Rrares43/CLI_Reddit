package account_manager;

import posting.OutputWriter;
import posting.StringReader;

import java.util.Scanner;

public class AccountLogin {
    private final StringReader stringReader;

    public AccountLogin(StringReader stringReader) {
        this.stringReader = stringReader;
    }

    public void Login(){
        String username = stringReader.readString("Enter username: ");
        String password = stringReader.readString("Enter password: ");

        if (AccountOperations.verifyAccount(new Account(username, "", password))) {
            System.out.println("Login Successful");
        }
        else {
            System.out.println("Login Failed");
        }
    }
}
