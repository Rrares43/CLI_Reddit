package interaction;

import java.util.List;

public class PostRepo implements PostRepository {
    public Post findPostById(int postId){
        for(Post p : DataBase.mockPosts){
            if(p.getId() == postId){
                return p;
            }
        }
        return null;
    }
    public List<Post> findAllPosts(){
        return DataBase.mockPosts;
    }
    public int getNextCommentId(){
        return DataBase.nextCommentId++;
    }
    public String getCurrentUser(){
        return DataBase.currentLoggedInUser;
    }



}
