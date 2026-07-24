package menu_commands;

import community.SubredditQuery;

public class SubredditCommand implements MenuCommand {
    private final SubredditQuery subredditQuery;
    public SubredditCommand(SubredditQuery subredditQuery){
        this.subredditQuery = subredditQuery;
    }
    @Override
    public void execute(){
        subredditQuery.startSubredditMenu();
    }
}
