import java.util.ArrayList;
import java.util.List;

public class Comment {
    private int Id;
    private String text;
    private String author;
    private List<Comment> replies;

    public Comment(int Id,String text,String author)
    {
        this.Id=Id;
        this.text=text;
        this.author=author;
        this.replies=new ArrayList<>();
    }

    //functie pentru editarea comenatriilor
    public void setText(String newText){
        this.text = newText;

    }

    public int getId(){
        return Id;
    }

    public String getText(){
        return text;
    }

    public String getAuthor(){
        return author;
    }

    public List<Comment> getReplies(){
        return replies;
    }

    public void addreply(Comment reply){
        this.replies.add(reply);
    }

}


