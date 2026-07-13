package interaction;
import logger.Logger;
import logger.LogLevel;


public class VoteServiceImpl implements VoteService{
    private final PostRepo postRepo;
    private final Logger logger;

    public VoteServiceImpl(PostRepo postRepo,Logger logger){
        this.logger = logger;
        this.postRepo = postRepo;
    }

    @Override
    public void upvote(int postId,int choice) {
        Post post = postRepo.findPostById(postId);
        if(post==null){
            logger.log(LogLevel.ERROR,"Post with id "+postId+" does not exist");
            throw new IllegalArgumentException("Post with id "+postId+" does not exist");
        }

        if(choice==1){
            post.increment_upvotes();
            logger.log(LogLevel.INFO,"Vote for post with id "+postId+" has been upvoted");
        }
        else if(choice==2){
            post.decrement_upvotes();
            logger.log(LogLevel.INFO,"Vote for post with id "+postId+" has been decremented");
        }
    }
@Override
    public void downvote(int postId,int choice) {
    Post post = postRepo.findPostById(postId);
        if(post==null){
            logger.log(LogLevel.ERROR,"Post with id "+postId+" does not exist");
            throw new IllegalArgumentException("Post with id "+postId+" does not exist");
        }
        if(choice==1){
            post.increment_downvotes();
            logger.log(LogLevel.INFO,"Vote for post with id "+postId+" has been downvoted");
        }
        else if(choice==2){
            post.decrement_downvotes();
            logger.log(LogLevel.INFO,"Vote for post with id "+postId+" has been decremented");
        }
}

}
