import account_manager.Account;
import account_manager.AccountOperations;
import account_manager.AccountQuery;
import Posting.ConsoleUI;
import Posting.PostQuery;
import interaction.InteractionQuery;

import java.util.Scanner;

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input 1 for account options or 2 for post options:");
    String choice = sc.nextLine();

    if(choice.equals("1")) {
        AccountQuery.accountQuery();
    }
    else if(choice.equals("2")) {
        PostQuery.postQuery();
    }
    // doesnt do anything for now
    else if(choice.equals("3")) {
        InteractionQuery.interactionQuery();
    }
    else{
        System.out.println("Invalid Input");
    }
}
