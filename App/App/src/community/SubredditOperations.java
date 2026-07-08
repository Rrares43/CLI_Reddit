package community;

import posting.InputValidator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SubredditOperations {
    public static void saveSubreddit(Subreddit subreddit){
        try(FileWriter writer = new FileWriter("App/data/subreddits.txt", true)){
            writer.write(subreddit.getName() + "," + subreddit.getDescription() + "\n");
            System.out.println("Subreddit created successfully!");
        }
        catch(IOException e){
            System.out.println("Error");
        }
    }

    public static String askForName(){
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter subreddit name:");
            String subredditName = sc.nextLine();
            if (!InputValidator.isNotBlank(subredditName)) {
                System.out.println("Error: Subreddit name cannot be empty!");
            } else {
                return subredditName;
            }
        }
    }

    public static String askForDescription(){
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter subreddit description:");
            String subredditDescription = sc.nextLine();
            if (!InputValidator.isNotBlank(subredditDescription)) {
                System.out.println("Error: Subreddit description cannot be empty!");
            } else {
                return subredditDescription;
            }
        }
    }
}
