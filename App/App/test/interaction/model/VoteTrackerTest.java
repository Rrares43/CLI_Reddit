package interaction.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VoteTrackerTest {

    @Test
    void testInitialVotes() {
        VoteTracker voteTracker = new VoteTracker();
        assertEquals(0, voteTracker.getUpvotes());
        assertEquals(0, voteTracker.getDownvotes());
    }

    @Test
    void testAddUpvotes() {
        VoteTracker voteTracker = new VoteTracker();
        voteTracker.addUpvotes();
        assertEquals(1, voteTracker.getUpvotes());
        assertEquals(0, voteTracker.getDownvotes());
    }

    @Test
    void testAddMultipleUpvotes() {
        VoteTracker voteTracker = new VoteTracker();
        voteTracker.addUpvotes();
        voteTracker.addUpvotes();
        voteTracker.addUpvotes();
        assertEquals(3, voteTracker.getUpvotes());
    }

    @Test
    void testAddDownvotes() {
        VoteTracker voteTracker = new VoteTracker();
        voteTracker.addDownvotes();
        assertEquals(0, voteTracker.getUpvotes());
        assertEquals(1, voteTracker.getDownvotes());
    }

    @Test
    void testAddMultipleDownvotes() {
        VoteTracker voteTracker = new VoteTracker();
        voteTracker.addDownvotes();
        voteTracker.addDownvotes();
        voteTracker.addDownvotes();
        assertEquals(3, voteTracker.getDownvotes());
    }

    @Test
    void testRemoveUpvotes() {
        VoteTracker voteTracker = new VoteTracker();
        voteTracker.addUpvotes();
        voteTracker.addUpvotes();
        voteTracker.removeUpvotes();
        assertEquals(1, voteTracker.getUpvotes());
    }

    @Test
    void testRemoveDownvotes() {
        VoteTracker voteTracker = new VoteTracker();
        voteTracker.addDownvotes();
        voteTracker.addDownvotes();
        voteTracker.removeDownvotes();
        assertEquals(1, voteTracker.getDownvotes());
    }

    @Test
    void testMixedVotes() {
        VoteTracker voteTracker = new VoteTracker();
        voteTracker.addUpvotes();
        voteTracker.addUpvotes();
        voteTracker.addDownvotes();
        voteTracker.addUpvotes();
        voteTracker.removeDownvotes();
        assertEquals(3, voteTracker.getUpvotes());
        assertEquals(0, voteTracker.getDownvotes());
    }

    @Test
    void testRemoveUpvotesBelowZero() {
        VoteTracker voteTracker = new VoteTracker();
        voteTracker.removeUpvotes();
        assertEquals(-1, voteTracker.getUpvotes());
    }

    @Test
    void testRemoveDownvotesBelowZero() {
        VoteTracker voteTracker = new VoteTracker();
        voteTracker.removeDownvotes();
        assertEquals(-1, voteTracker.getDownvotes());
    }
}
