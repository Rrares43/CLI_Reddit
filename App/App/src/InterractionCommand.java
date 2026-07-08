import interaction.InteractionQuery;

public class InterractionCommand implements MenuCommand{
    private InteractionQuery interactionQuery;

    InterractionCommand(InteractionQuery interactionQuery) {
        this.interactionQuery = interactionQuery;
    }
    @Override
    public void execute(){
        InteractionQuery.interactionQuery();
    }
}
