package bootstrap;

import account_manager.SessionService;
import interaction.repository.PostRepo;
import interaction.service.PostService;
import interaction.service.PostServiceImpl;
import posting.OutputWriter;
import posting.PostView;
import posting.StringReader;
import posting.attachment_handlers.AttachmentHandler;
import posting.attachment_handlers.LinkAttachmentHandler;
import posting.attachment_handlers.NoAttachmentHandler;
import posting.attachment_handlers.PhotoAttachmentHandler;
import posting.commands.CreatePostCommand;
import posting.post_validators.IsNotBlank;
import posting.post_validators.IsValidLength;
import posting.post_validators.IsValidLink;
import posting.post_validators.Validator;

import java.util.HashMap;
import java.util.Map;

final class PostingModule {
    private PostingModule() {
    }

    static PostView createPostView(StringReader stringReader, OutputWriter output) {
        Validator<String> notBlankValidator = new IsNotBlank();
        Validator<String> linkValidator = new IsValidLink();
        Validator<String> titleLengthValidator = new IsValidLength(300);
        Validator<String> contentLengthValidator = new IsValidLength(3000);

        Map<String, AttachmentHandler> attachmentHandlers = new HashMap<>();
        attachmentHandlers.put("photo", new PhotoAttachmentHandler(stringReader, output, notBlankValidator));
        attachmentHandlers.put("link", new LinkAttachmentHandler(stringReader, output, linkValidator));
        attachmentHandlers.put("no", new NoAttachmentHandler(output));

        return new PostView(
                stringReader,
                output,
                notBlankValidator,
                titleLengthValidator,
                contentLengthValidator,
                attachmentHandlers
        );
    }

    static CreatePostCommand createCreatePostCommand(PostView postView,
                                                     PostRepo postRepo,
                                                     SessionService sessionService) {
        PostService postService = new PostServiceImpl(postRepo);
        return new CreatePostCommand(postView, postService, sessionService);
    }
}
