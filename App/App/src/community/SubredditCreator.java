package community;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import logger.Logger;
import logger.LogLevel;

public class SubredditCreator {
    private static final String FILE_NAME = "App/data/subreddits.json";

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static List<Subreddit> loadSubreddits() {
        File file = new File(FILE_NAME);
        if(!file.exists()){
            return new ArrayList<>();
        }
        try(FileReader reader = new FileReader(file)){
            Type listType = new TypeToken<ArrayList<Subreddit>>(){}.getType();
            List<Subreddit> subreddits = gson.fromJson(reader, listType);
            return subreddits != null ? subreddits : new ArrayList<>();
        }
        catch(IOException e){
            System.out.println("Error");
            Logger.getInstance().log(LogLevel.ERROR,"Error");
            return new ArrayList<>();
        }
    }

    private static void writeSubreddits(List<Subreddit> subreddits){
        try(FileWriter fileWriter = new FileWriter(FILE_NAME)){
            gson.toJson(subreddits, fileWriter);
        }
        catch (IOException e){
            System.out.println("Error");
            Logger.getInstance().log(LogLevel.ERROR,"Error");
        }
    }

    public static void saveSubreddit(Subreddit subreddit){
        try{
            List<Subreddit> subreddits = loadSubreddits();
            subreddits.add(subreddit);
            writeSubreddits(subreddits);

            System.out.println("Subreddit saved successfully!");
            Logger.getInstance().log(LogLevel.INFO,"Subreddit saved successfully!");
        }
        catch(Exception e){
            System.out.println("Error");
            Logger.getInstance().log(LogLevel.ERROR,"Error");
        }
    }
}
