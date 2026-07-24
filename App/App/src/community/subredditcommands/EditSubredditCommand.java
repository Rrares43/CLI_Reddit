package community.subredditcommands;

import account_manager.SessionService;
import community.Subreddit;
import community.SubredditEditor;
import community.SubredditOperations;
import posting.OutputWriter;
import posting.StringReader;

import java.util.List;

import static community.SubredditOperations.loadSubreddits;

public class EditSubredditCommand implements SubredditCommand{
    private final SessionService sessionService;
    private final StringReader stringReader;

    public EditSubredditCommand(SessionService sessionService, StringReader stringReader) {
        this.sessionService = sessionService;
        this.stringReader = stringReader;
    }

    @Override
    public void execute(){
        if(sessionService.isLoggedIn()) {
            editSubreddit();
        }
    }

    public void editSubreddit(){
        List<Subreddit> subreddits = loadSubreddits();
        String targetSub = chooseSubreddit();
        boolean found = false;
        for(Subreddit sub : subreddits){
            if(sub.getName().equals(targetSub)){
                String newTitle = stringReader.readString("Enter new title: ");
                if(newTitle.startsWith("r/")){
                    sub.setName(newTitle);
                }
                else{
                    sub.setName("r/" + newTitle);
                }
                String newDesc = stringReader.readString("Enter new description: ");
                sub.setDescription(newDesc);
                found = true;
                break;
            }
        }
        if(found){
            SubredditOperations.writeSubreddits(subreddits);
            System.out.println("Subreddit edited successfully!");
        }
        else{
            System.out.println("Subreddit not found");
        }
    }

    public String chooseSubreddit(){
        System.out.println("Subreddits this user has made: ");
        SubredditOperations.listSubsMadebyUser(sessionService.getCurrentUsername());
        return stringReader.readString("Choose subreddit to edit: ");
    }
}
