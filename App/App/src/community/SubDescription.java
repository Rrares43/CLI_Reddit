package community;

import logger.LogLevel;
import logger.Logger;
import posting.InputValidator;

import java.util.Scanner;

public class SubDescription implements SubredditData{
    public static String ask(){
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter subreddit description:");
            String subredditDescription = sc.nextLine();
            if (!InputValidator.isNotBlank(subredditDescription)) {
                System.out.println("Error: Subreddit description cannot be empty!");
                Logger.getInstance().log(LogLevel.ERROR,"Error: Subreddit description cannot be empty!");
            } else {
                return subredditDescription;
            }
        }
    }
}
