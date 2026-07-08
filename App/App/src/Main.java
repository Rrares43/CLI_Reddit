import account_manager.AccountQuery;
import posting.PostQuery;
import interaction.InteractionQuery;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        AccountQuery accountQuery = new AccountQuery();
        PostQuery postQuery = new PostQuery();
        InteractionQuery interactionQuery = new InteractionQuery();

        System.out.println("Input 1 for account options or 2 for post options:");
        String choice = sc.nextLine();

        MenuCommand selectedCommand = switch(choice){
            case "1" -> new AccountCommand(accountQuery);
            case "2" -> new PostCommand(postQuery);
            case "3" -> new InterractionCommand(interactionQuery);
            default -> null;
        };

        if (selectedCommand != null) {
            selectedCommand.execute();
        } else {
            System.out.println("Invalid Input");
        }

    }
}
