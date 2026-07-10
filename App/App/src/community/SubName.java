package community;

import logger.LogLevel;
import logger.Logger;
import posting.InputValidator;

import java.util.Scanner;

public class SubName implements SubredditData{
    public static String ask(){
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter subreddit name:");
            String subredditName = sc.nextLine();
            if (!InputValidator.isNotBlank(subredditName)) {
                System.out.println("Error: Subreddit name cannot be empty!");
                Logger.getInstance().log(LogLevel.ERROR,"Error: Subreddit name cannot be empty!");
            } else {
                return subredditName;
            }
        }
    }
}
