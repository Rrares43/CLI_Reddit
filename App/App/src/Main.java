import account_manager.Account;
import account_manager.AccountOperations;
import account_manager.AccountQuery;
import Posting.ConsoleUI;
import Posting.PostQuery;
import community.SubredditQuery;
import interaction.InteractionQuery;

import java.util.Scanner;

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Input 1 for account option, 2 to make a post, 3 for interaction or 4 to create a subreddit:");
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
    else if(choice.equals("4")){
        SubredditQuery.subredditQuery();
    }
    else{
        System.out.println("Invalid Input");
    }
}
