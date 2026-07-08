import posting.PostQuery;

public class PostCommand implements MenuCommand {

    private PostQuery postQuery;

    public PostCommand(PostQuery postQuery) {
        this.postQuery = postQuery;
    }
    @Override
    public void execute(){
        postQuery.postQuery();
    }
}
