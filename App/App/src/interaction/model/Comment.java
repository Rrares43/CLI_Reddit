package interaction.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Comment {
    private int Id;
    private String text;
    private String author;
    private List<Comment> replies;
    private int upvotes;
    private int downvotes;

    private List<CommentVote> votes;

    public Comment(int Id, String text, String author) {
        this.Id = Id;
        this.text = text;
        this.author = author;
        this.replies = new ArrayList<>();
        this.upvotes = 0;
        this.downvotes = 0;
        this.votes = new ArrayList<>();
    }

    //functie pentru editarea comenatriilor
    public void setText(String newText) {
        this.text = newText;

    }

    public int getId() {
        return Id;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public void addreply(Comment reply) {
        this.replies.add(reply);
    }

    public List<CommentVote> getVotes() {
        if (votes == null) {
            votes = new ArrayList<>();
        }
        return votes;
    }

    public int getUpvotes() {
        int count = 0;
        for (CommentVote vote : getVotes()) {
            if (vote.isUpvote()) {
                count++;
            }
        }
        return count;
    }

    public int getDownvotes() {
        int count = 0;
        for (CommentVote vote : getVotes()) {
            if (!vote.isUpvote()) {
                count++;
            }
        }
        return count;
    }

    public Optional<CommentVote> getUserVote(String username) {
        for (CommentVote vote : getVotes()) {
            if (vote.getUsername().equals(username)) {
                return Optional.of(vote);
            }
        }
        return Optional.empty();
    }
}

