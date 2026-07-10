package community;

import java.io.FileWriter;
import java.io.IOException;

import logger.Logger;
import logger.LogLevel;

public class SubredditCreator {
    public static void saveSubreddit(Subreddit subreddit){
        try(FileWriter writer = new FileWriter("App/data/subreddits.txt", true)){
            writer.write(subreddit.getName() + "," + subreddit.getDescription() + "\n");
            System.out.println("Subreddit created successfully!");
            Logger.getInstance().log(LogLevel.INFO,"Subreddit created successfully");

        }
        catch(IOException e){
            System.out.println("Error");
            Logger.getInstance().log(LogLevel.ERROR,"Error");
        }
    }
}
