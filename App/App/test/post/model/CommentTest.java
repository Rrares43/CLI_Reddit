package post.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    @Test
    void shouldCreateCommentWithInitialDefaults() {
        Comment comment = new Comment(1, "Test comment", "testuser");

        assertEquals(1, comment.getId());
        assertEquals("Test comment", comment.getText());
        assertEquals("testuser", comment.getAuthor());
        assertNotNull(comment.getReplies());
        assertTrue(comment.getReplies().isEmpty());
        assertEquals(0, comment.getUpvotes());
        assertEquals(0, comment.getDownvotes());
        assertNotNull(comment.getVoteTracker());
        assertEquals(0, comment.getVoteTracker().getUpvotes());
        assertEquals(0, comment.getVoteTracker().getDownvotes());
    }

    @Test
    void shouldAddAndMaintainMultipleReplies() {
        Comment parent = new Comment(1, "Parent comment", "testuser");
        Comment reply1 = new Comment(2, "Reply 1", "user1");
        Comment reply2 = new Comment(3, "Reply 2", "user2");

        parent.addreply(reply1);
        parent.addreply(reply2);

        List<Comment> replies = parent.getReplies();
        assertEquals(2, replies.size());
        assertEquals("Reply 1", replies.get(0).getText());
        assertEquals("Reply 2", replies.get(1).getText());
    }

    @Test
    void shouldSupportNestedRepliesTree() {
        Comment parent = new Comment(1, "Parent", "user1");
        Comment reply = new Comment(2, "Reply", "user2");
        Comment nestedReply = new Comment(3, "Nested", "user3");

        parent.addreply(reply);
        reply.addreply(nestedReply);

        assertEquals(1, parent.getReplies().size());
        assertEquals(1, parent.getReplies().get(0).getReplies().size());
        assertEquals("Nested", parent.getReplies().get(0).getReplies().get(0).getText());
    }

    @Test
    void shouldUpdateVotesThroughVoteTracker() {
        Comment comment = new Comment(1, "Test comment", "testuser");
        assertNotNull(comment.getVoteTracker());

        comment.getVoteTracker().addUpvotes();
        comment.getVoteTracker().addDownvotes();

        // VoteTracker is independent; getUpvotes/getDownvotes count CommentVote entries
        assertEquals(1, comment.getVoteTracker().getUpvotes());
        assertEquals(1, comment.getVoteTracker().getDownvotes());
        assertEquals(0, comment.getUpvotes());
        assertEquals(0, comment.getDownvotes());
    }

    @Test
    void shouldCountVotesFromCommentVoteList() {
        Comment comment = new Comment(1, "Test comment", "testuser");

        comment.getVotes().add(new CommentVote("alice", 1, true));
        comment.getVotes().add(new CommentVote("bob", 1, false));
        comment.getVotes().add(new CommentVote("carol", 1, true));

        assertEquals(2, comment.getUpvotes());
        assertEquals(1, comment.getDownvotes());
        assertTrue(comment.getUserVote("alice").isPresent());
        assertTrue(comment.getUserVote("alice").get().isUpvote());
        assertTrue(comment.getUserVote("nobody").isEmpty());
    }
}
