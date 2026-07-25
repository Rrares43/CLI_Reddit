package subreddit.command;

import subreddit.Subreddit;
import subreddit.repository.SubredditRepository;
import io.OutputWriter;
import io.StringReader;

import java.util.List;

public class ViewSubredditCommand implements SubredditCommand {
    private final StringReader stringReader;
    private final OutputWriter output;

    public ViewSubredditCommand(StringReader stringReader, OutputWriter output) {
        this.stringReader = stringReader;
        this.output = output;
    }

    @Override
    public void execute(){
        System.out.println("Choose a subreddit to view:");
        List<Subreddit> subreddits = SubredditRepository.loadSubreddits();
        String sub = stringReader.readString("Enter subreddit name: ");
        boolean found = false;
        if(!sub.startsWith("r/")){
            sub = "r/" + sub;
        }
        for(Subreddit s : subreddits){
            if(s.getName().equals(sub)){
                found = true;
                output.write("Subreddit: " + s.getName());
                output.write(s.getDescription());
                output.write("Owner: " + s.getOwner());
            }
        }
        if(!found){
            output.write("Subreddit doesn't exist");
        }
    }
}
