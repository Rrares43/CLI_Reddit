package menu_commands;

import posting.commands.CreatePostCommand;

public class PostCommand implements MenuCommand {

    private final CreatePostCommand createPostCommand;

    public PostCommand(CreatePostCommand createPostCommand) {
        this.createPostCommand = createPostCommand;
    }
    @Override
    public void execute(){
        createPostCommand.execute();
    }
}
