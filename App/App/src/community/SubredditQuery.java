package community;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SubredditQuery {
    public static void subredditQuery() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input subreddit name:");
        String subredditName = sc.nextLine();
        System.out.println("Input subreddit description:");
        String subredditDescription = sc.nextLine();
        Subreddit subreddit = new Subreddit(subredditName, subredditDescription);
        saveSubreddit(subreddit);
    }

    public static void saveSubreddit(Subreddit subreddit){
        try(FileWriter writer = new FileWriter("App/data/subreddits.txt", true)){
            writer.write(subreddit.getName() + "," + subreddit.getDescription() + "\n");
            System.out.println("Subreddit created successfully!");
        }
        catch(IOException e){
            System.out.println("Error");
        }
    }
}
