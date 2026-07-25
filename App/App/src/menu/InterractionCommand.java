package menu;

import interaction.repository.InteractionQuery;

public class InterractionCommand implements MenuCommand {
    private final InteractionQuery interactionQuery;

    public InterractionCommand(InteractionQuery interactionQuery) {
        this.interactionQuery = interactionQuery;
    }
    @Override
    public void execute(){
        interactionQuery.interactionQuery();
    }
}
