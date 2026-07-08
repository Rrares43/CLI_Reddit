import account_manager.AccountQuery;
import community.SubredditQuery;
import posting.ConsoleUI;
import posting.PostQuery;
import interaction.InteractionQuery;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ConsoleUI ui = new ConsoleUI();

        AccountQuery accountQuery = new AccountQuery();
        PostQuery postQuery = new PostQuery();
        InteractionQuery interactionQuery = new InteractionQuery();
        SubredditQuery subredditQuery = new SubredditQuery();

        MenuDispatcher dispatcher = new MenuDispatcher(ui);

        dispatcher.registerCommand("1", new AccountCommand(accountQuery));
        dispatcher.registerCommand("2", new PostCommand(postQuery));
        dispatcher.registerCommand("3", new InterractionCommand(interactionQuery));
        dispatcher.registerCommand("4", new SubredditCommand(subredditQuery));

        String prompt = "Input 1 for account options, 2 for post options, 3 for interaction or 4 for subreddit creation: ";
        String choice = ui.getInput(prompt);

        dispatcher.execute(choice);

    }
}
