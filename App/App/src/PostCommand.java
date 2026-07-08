import posting.PostQuery;

public class PostCommand implements MenuCommand {

    private final PostQuery postQuery;

    public PostCommand(PostQuery postQuery) {
        this.postQuery = postQuery;
    }
    @Override
    public void execute(){
        postQuery.postQuery();
    }
}
