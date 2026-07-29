package subreddit.command;

import account.SessionService;
import io.StringReader;
import subreddit.Subreddit;
import subreddit.repository.SubredditRepository;

import java.util.List;

public class DeleteSubredditCommand implements SubredditCommand{
    private final StringReader stringReader;
    private final SessionService sessionService;

    public DeleteSubredditCommand(StringReader stringReader, SessionService sessionService) {
        this.stringReader = stringReader;
        this.sessionService = sessionService;
    }

    @Override
    public void execute(){
        if(sessionService.isLoggedIn()) {
            deleteSubreddit();
        }
    }

    private void deleteSubreddit(){
        String targetSub = chooseSubreddit();

        if (targetSub == null) {
            return;
        }

        List<Subreddit> subsMadebyUser = SubredditRepository.listSubsMadebyUser(sessionService.getCurrentUsername());
        boolean found = false;

        for(Subreddit sub : subsMadebyUser){
            if(sub.getName().equals(targetSub)){
                found = true;
                break;
            }
        }

        if(found){
            List<Subreddit> subreddits = SubredditRepository.loadSubreddits();

            subreddits.removeIf(sub -> sub.getName().equals(targetSub));

            SubredditRepository.writeSubreddits(subreddits);
            System.out.println("Subreddit deleted successfully!");
        }
        else{
            System.out.println("Subreddit not found");
        }
    }

    public String chooseSubreddit(){
        System.out.println("Subreddits this user has made: ");
        List<Subreddit> subsMadebyUser = SubredditRepository.listSubsMadebyUser(sessionService.getCurrentUsername());
        if(subsMadebyUser.isEmpty()){
            System.out.println("No subreddits made by this user");
            return null;
        }

        for(Subreddit sub : subsMadebyUser){
            System.out.println(sub.getName());
        }
        return stringReader.readString("Choose subreddit to delete: ");
    }
}
