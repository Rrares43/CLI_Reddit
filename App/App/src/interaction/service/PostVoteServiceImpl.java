package interaction.service;

import interaction.model.Post;
import interaction.model.PostVote;
import interaction.repository.PostRepo;
import logger.Logger;
import logger.LogLevel;
import persistence.DatabaseSync;

import java.util.Optional;

public class PostVoteServiceImpl implements PostVoteService {
    private final PostRepo postRepo;
    private final Logger logger;

    public PostVoteServiceImpl(PostRepo postRepo, Logger logger) {
        this.logger = logger;
        this.postRepo = postRepo;
    }

    @Override
    public void upvote(int postId) {
        handleVote(postId, true);
    }

    @Override
    public void downvote(int postId) {
        handleVote(postId, false);
    }

    private void handleVote(int postId, boolean isUpvote) {
        Post post = postRepo.findPostById(postId);
        if (post == null) {
            logger.log(LogLevel.ERROR, "Post with id " + postId + " does not exist");
            throw new IllegalArgumentException("Post with id " + postId + " does not exist");
        }

        String currentUsername = postRepo.getCurrentUser();
        if (currentUsername == null) {
            logger.log(LogLevel.ERROR, "No user is currently logged in.");
            return;
        }

        Optional<PostVote> existingVoteOpt = post.getUserVote(currentUsername);

        if (existingVoteOpt.isPresent()) {
            PostVote existingVote = existingVoteOpt.get();

            if (existingVote.isUpvote() == isUpvote) {
                // Utilizatorul a apăsat din nou pe același buton (Toggle OFF / Ștergere)
                post.getVotes().remove(existingVote);
                DatabaseSync.removePostVote(currentUsername, postId, isUpvote ? 1 : -1);
                logger.log(LogLevel.INFO, "Vote removed for post " + postId);
            } else {
                // Utilizatorul a schimbat direcția votului (ex: din Downvote în Upvote)
                existingVote.setUpvote(isUpvote);
                DatabaseSync.upsertPostVote(currentUsername, postId, isUpvote ? 1 : -1);
                logger.log(LogLevel.INFO, "Vote direction changed for post " + postId);
            }
        } else {
            // Nu există un vot anterior - adăugăm unul nou
            post.getVotes().add(new PostVote(currentUsername, postId, isUpvote));
            DatabaseSync.upsertPostVote(currentUsername, postId, isUpvote ? 1 : -1);
            logger.log(LogLevel.INFO, "New vote added for post " + postId);
        }

        // Salvăm modificările local
        postRepo.saveToFile();
    }

    public String getVoteStatus(int postId) {
        Post post = postRepo.findPostById(postId);
        String currentUsername = postRepo.getCurrentUser();

        if (post == null || currentUsername == null) return "";

        Optional<PostVote> vote = post.getUserVote(currentUsername);
        if (vote.isEmpty()) {
            return "[ You have not voted on this post ]";
        }
        return vote.get().isUpvote() ? "[ Status: UPVOTED ]" : "[ Status: DOWNVOTED ]";
    }
}