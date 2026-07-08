import community.SubredditQuery;

public class SubredditCommand implements MenuCommand{
    private SubredditQuery subredditQuery;
    public SubredditCommand(SubredditQuery subredditQuery){
        this.subredditQuery = subredditQuery;
    }
    @Override
    public void execute(){
        subredditQuery.subredditQuery();
    }
}
