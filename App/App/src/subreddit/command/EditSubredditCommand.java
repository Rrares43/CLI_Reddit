package subreddit.command;

import account.SessionService;
import subreddit.Subreddit;
import subreddit.repository.SubredditRepository;
import io.StringReader;

import java.util.List;

import static subreddit.repository.SubredditRepository.loadSubreddits;

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
        List<Subreddit> subsMadeByUser = SubredditRepository.listSubsMadebyUser(sessionService.getCurrentUsername());
        if(subsMadeByUser.isEmpty()){
            System.out.println("No subreddits made by this user");
            return;
        }
        boolean found = false;
        String targetSub = chooseSubreddit();
        for(Subreddit sub : subsMadeByUser){
            if(sub.getName().equals(targetSub)){
                found = true;
                break;
            }
        }

        if(found){
            List<Subreddit> subreddits = SubredditRepository.loadSubreddits();

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
                }
            }
            SubredditRepository.writeSubreddits(subreddits);
            System.out.println("Subreddit edited successfully!");
        }
        else{
            System.out.println("Subreddit not found");
        }
    }

    public String chooseSubreddit(){
        System.out.println("Subreddits this user has made: ");
        List<Subreddit> subsMadeByUser = SubredditRepository.listSubsMadebyUser(sessionService.getCurrentUsername());
        if(subsMadeByUser.isEmpty()){
            System.out.println("No subreddits made by this user");
            return null;
        }
        for(Subreddit sub : subsMadeByUser){
            System.out.println(sub.getName());
        }
        return stringReader.readString("Choose subreddit to edit: ");
    }
}
