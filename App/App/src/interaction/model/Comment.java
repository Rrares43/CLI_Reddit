package interaction.model;

import java.util.ArrayList;
import java.util.List;

public class Comment {
    private int Id;
    private String text;
    private String author;
    private List<Comment> replies;
    private int upvotes;
    private int downvotes;

    private VoteTracker voteTracker;

    public Comment(int Id,String text,String author)
    {
        this.Id=Id;
        this.text=text;
        this.author=author;
        this.replies=new ArrayList<>();
        this.upvotes=0;
        this.downvotes=0;
        this.voteTracker=new VoteTracker();
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

    public int getUpvotes(){
        if(voteTracker==null){
            voteTracker=new VoteTracker();
        }
        return voteTracker.getUpvotes();
    }
    public int getDownvotes(){
        if(voteTracker==null){
            voteTracker=new VoteTracker();
        }
        return voteTracker.getDownvotes();
    }
    public VoteTracker getVoteTracker() {
        if (this.voteTracker == null) {
            this.voteTracker = new VoteTracker();
        }
        return this.voteTracker;
    }


}


