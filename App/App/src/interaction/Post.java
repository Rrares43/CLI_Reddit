package interaction;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private int Id;
    private String title;
    private String content;
    private String author;
    private int upvotes;
    private int downvotes;
    private List<Comment> comments;
    private String subredditName;

    public Post(int Id,String title,String content,String author, String subredditName){
        this.Id = Id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.subredditName = subredditName;
        this.upvotes = 0;
        this.downvotes = 0;
        this.comments = new ArrayList<>();
    }

    public int getId(){
        return Id;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public String getAuthor(){
        return author;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public List<Comment> getComments(){
        return comments;
    }

    public String getSubredditName(){
        return subredditName;
    }

    public void increment_upvotes(){
        this.upvotes++;
    }

    public void increment_downvotes(){
        this.downvotes++;
    }

    public void decrement_upvotes(){
        this.upvotes--;
    }

    public void decrement_downvotes(){
        this.downvotes--;
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public void remove_comment(int index){
        if(index>=0 && index<this.comments.size()){
            this.comments.remove(index);
        }
    }
}
