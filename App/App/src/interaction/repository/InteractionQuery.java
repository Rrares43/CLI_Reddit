package interaction.repository;
import posting.PostInteractionController;

public class InteractionQuery {
    private final PostInteractionController controller;

    public InteractionQuery(PostInteractionController controller) {
        this.controller = controller;
    }
    public void interactionQuery() {
        controller.startInteraction();
    }
}