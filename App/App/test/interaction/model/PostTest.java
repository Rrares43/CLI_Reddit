package interaction.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class PostTest {

    @Test
    void shouldCreatePostWithInitialDefaults() {
        Post post = new Post(1, "Test Title", "Test content", "testuser", "testsubreddit");

        assertEquals(1, post.getId());
        assertEquals("Test Title", post.getTitle());
        assertEquals("Test content", post.getContent());
        assertEquals("testuser", post.getAuthor());
        assertEquals("testsubreddit", post.getSubredditName());
        assertNotNull(post.getComments());
        assertTrue(post.getComments().isEmpty());
        assertEquals(0, post.getUpvotes());
        assertEquals(0, post.getDownvotes());
    }

    @Test
    void shouldAddAndMaintainMultipleComments() {
        Post post = new Post(1, "Title", "Content", "user", "subreddit");
        Comment comment1 = new Comment(1, "Comment 1", "user1");
        Comment comment2 = new Comment(2, "Comment 2", "user2");

        post.addComment(comment1);
        post.addComment(comment2);

        List<Comment> comments = post.getComments();
        assertEquals(2, comments.size());
        assertEquals("Comment 1", comments.get(0).getText());
        assertEquals("Comment 2", comments.get(1).getText());
    }

    @Test
    void shouldRemoveCommentByValidIndex() {
        Post post = new Post(1, "Title", "Content", "user", "subreddit");
        Comment comment1 = new Comment(1, "Comment 1", "user1");
        Comment comment2 = new Comment(2, "Comment 2", "user2");
        Comment comment3 = new Comment(3, "Comment 3", "user3");

        post.addComment(comment1);
        post.addComment(comment2);
        post.addComment(comment3);

        post.removeComment(1);

        List<Comment> comments = post.getComments();
        assertEquals(2, comments.size());
        assertEquals("Comment 1", comments.get(0).getText());
        assertEquals("Comment 3", comments.get(1).getText());
    }

    @Test
    void shouldHandleInvalidIndexWhenRemovingComment() {
        Post post = new Post(1, "Title", "Content", "user", "subreddit");
        Comment comment = new Comment(1, "Comment", "user");
        post.addComment(comment);

        post.removeComment(-1);
        post.removeComment(5);

        assertEquals(1, post.getComments().size());

        Post emptyPost = new Post(2, "Title", "Content", "user", "subreddit");
        emptyPost.removeComment(0);

        assertEquals(0, emptyPost.getComments().size());
    }

    @Test
    void shouldUpdateVotesThroughVoteTracker() {
        Post post = new Post(1, "Title", "Content", "user", "subreddit");
        assertNotNull(post.getVoteTracker());

        post.getVoteTracker().addUpvotes();
        post.getVoteTracker().addDownvotes();

        assertEquals(1, post.getUpvotes());
        assertEquals(1, post.getDownvotes());
    }

    @Test
    void shouldHandleEdgeCaseInputsForFields() {
        Post emptyPost = new Post(-1, "", "", "user", "");
        Post nullPost = new Post(0, null, null, null, null);

        assertEquals(-1, emptyPost.getId());
        assertEquals("", emptyPost.getTitle());
        assertEquals("", emptyPost.getContent());
        assertEquals("", emptyPost.getSubredditName());

        assertEquals(0, nullPost.getId());
        assertNull(nullPost.getTitle());
        assertNull(nullPost.getContent());
        assertNull(nullPost.getAuthor());
        assertNull(nullPost.getSubredditName());
    }
}