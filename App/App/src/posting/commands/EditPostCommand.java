package posting.commands;

import interaction.service.PostEditService;
import interaction.service.PostEditServiceImpl;
import posting.StringReader;

public class EditPostCommand implements PostActionCommand{
    private final StringReader stringReader;
    private final PostEditServiceImpl postEditService;

    public EditPostCommand(StringReader stringReader, PostEditServiceImpl postEditService) {
        this.stringReader = stringReader;
        this.postEditService = postEditService;
    }

    @Override
    public void execute(int postId) {
        String newTitle = stringReader.readString("Enter new title: ");
        String newContent = stringReader.readString("Enter new content: ");
        postEditService.editPost(postId, newTitle, newContent);
    }
}
