package interaction.model;

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
        this.voteTracker = new VoteTracker();
    }

    private VoteTracker voteTracker;

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
        if(voteTracker==null){
            voteTracker=new VoteTracker();
        }
        return voteTracker.getUpvotes();
    }

    public int getDownvotes() {
        if(voteTracker==null){
            voteTracker=new VoteTracker();
        }
        return voteTracker.getDownvotes();
    }

    public VoteTracker getVoteTracker(){
        return voteTracker;
    }

    public List<Comment> getComments(){
        return comments;
    }

    public String getSubredditName(){
        return subredditName;
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public void removeComment(int index){
        if(index>=0 && index<this.comments.size()){
            this.comments.remove(index);
        }
    }
}
