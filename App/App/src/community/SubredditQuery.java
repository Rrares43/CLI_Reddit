package community;

import account_manager.SessionService;
import community.subredditcommands.SubredditCommand;
import posting.OutputWriter;
import posting.StringReader;

import java.util.HashMap;
import java.util.Map;

public class SubredditQuery {
    Map<String, SubredditCommand> commands;
    private final SessionService sessionService;
    private final StringReader stringReader;
    private final OutputWriter output;
    private boolean running;

    public SubredditQuery(SessionService sessionService, StringReader stringReader, OutputWriter output) {
        this.sessionService = sessionService;
        this.stringReader = stringReader;
        this.output = output;
        this.commands = new HashMap<>();

        commands.put("0", () -> this.running = false);
    }

    public void registerCommand(String key, SubredditCommand command) {
        commands.put(key, command);
    }

    public void startSubredditMenu() {
        running = true;
        while (running){
            String choice;
            System.out.println("--- SUBREDDIT MENU ---");
            System.out.println("0. Exit");
            System.out.println("1. Create Subreddit");
            System.out.println("2. View Subreddit");
            System.out.println("3. Edit Subreddit");

            choice = stringReader.readString("Select an option (0/1/2/3): ");
            if(choice.equals("0")){
                running = false;
            }
            else if(!(choice.equals("1") ||choice.equals("2") || choice.equals("3"))){
                output.write("Invalid option! Please try again.");
            }
            else{
                SubredditCommand command = commands.get(choice);
                if (command != null){
                    try {
                        command.execute();
                    }
                    catch (Exception e){
                        output.write("Error: " + e.getMessage());
                    }
                }
            }
        }
    }

}
