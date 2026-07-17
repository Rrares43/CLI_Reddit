package community;

import account_manager.SessionService;
import posting.StringReader;

import java.util.List;

import static community.SubredditOperations.loadSubreddits;

public class SubredditEditor {
    private final StringReader stringReader;

    public SubredditEditor(StringReader stringReader) {
        this.stringReader = stringReader;
    }

    public void editSubreddit(){
        List<Subreddit> subreddits = loadSubreddits();
        String targetSub = chooseSubreddit();
        for(Subreddit sub : subreddits){
            if(sub.getName().equals(targetSub)){
                String newDesc = stringReader.readString("Enter new description: ");
                sub.setDescription(newDesc);
            }
        }
    }

    public String chooseSubreddit(){
        SessionService sessionService = new SessionService();
        SubredditOperations.listSubsMadebyUser(sessionService.getCurrentUsername());
        return stringReader.readString("Choose subreddit to edit: ");
    }
}
