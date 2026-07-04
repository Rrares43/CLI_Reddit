
public class InteractionService {

    public Post findPostbyId(int postId) {
        for (Post p : dataBase.mockPosts){
            if (p.getId() == postId) {
                return p;
            }
        }
        return null;
    }

    public void upvote(int postId, int choice) {
        Post post = findPostbyId(postId);
        if (post == null) {
            System.out.println("Postarea nu exista");
            return;
        }

        if (choice == 1) {
            post.increment_upvotes();
            System.out.println("Upvote adaugat");
        } else if (choice == 2) {
            post.decrement_upvotes();
            System.out.println("Upvote sters");
        }
    }

    public void downvote(int postId, int choice) {
        Post post = findPostbyId(postId);
        if (post == null) {
            System.out.println("Postarea nu exista");
            return;
        }

        if (choice == 1) {
            post.increment_downvotes();
            System.out.println("Downvote adaugat");
        } else if (choice == 2) {
            post.decrement_downvotes();
            System.out.println("Downvote sters");
        }
    }

    public void comment(int postId, String text) {
        Post post = findPostbyId(postId);
        if (post != null) {
            int commentId = dataBase.nextCommentId++;
            Comment newComment = new Comment(commentId, text, dataBase.currentLoggedInUser);

            post.addComment(newComment);
            System.out.println("Comentariu adaugat ID: " + commentId);
        } else {
            System.out.println("Postarea nu exista");
        }
    }

    public void replyToComment(int postId, int parentCommentId, String text) {
        Post post = findPostbyId(postId);
        if (post == null) {
            System.out.println("Postarea nu exista");
            return;
        }

        Comment parentComment = null;
        for (Comment c : post.getComments()) {
            if (c.getId() == parentCommentId) {
                parentComment = c;
                break;
            }
        }

        if (parentComment != null) {
            int replyId = dataBase.nextCommentId++;
            Comment reply = new Comment(replyId, text, dataBase.currentLoggedInUser);

            parentComment.addreply(reply);
            System.out.println("Reply la comentariu " + parentCommentId);
        } else {
            System.out.println("Comentariul de baza nu exista.");
        }
    }

    public void editComment(int postId, int commentId, String newText) {
        Post post = findPostbyId(postId);
        if (post == null) {
            System.out.println("Postarea nu exista");
            return;
        }

        for (Comment c : post.getComments()) {
            if (c.getId() == commentId) {
                if (!c.getAuthor().equals(dataBase.currentLoggedInUser)) {
                    System.out.println("Nu poti edita comentariul");
                    return;
                }
                c.setText(newText);
                System.out.println("Comentariu modificat");
                return;
            }
        }
        System.out.println("Comentariul negasit.");
    }

    public void deleteComment(int postId, int commentId) {
        Post post = findPostbyId(postId);
        if (post == null) {
            System.out.println("Postarea nu exista");
            return;
        }

        for (int i = 0; i < post.getComments().size(); i++) {
            Comment c = post.getComments().get(i);
            if (c.getId() == commentId) {
                if (!c.getAuthor().equals(dataBase.currentLoggedInUser)) {
                    System.out.println("Nu poti sterge comentariul");
                    return;
                }
                post.remove_comment(i);
                System.out.println("Comentariu șters");
                return;
            }
        }
        System.out.println("Comentariul negasit.");
    }
        public void printPostByAuthor(String authorName){
            boolean autorGasit = false;
            for(Post p : dataBase.mockPosts) {
                if (p.getAuthor() == authorName) {
                    System.out.println("Postarea are titlul:" + p.getTitle());
                    System.out.println("Postarea are upvotes" + p.getUpvotes());
                    System.out.println("Postarea are upvotes" + p.getDownvotes());

                    autorGasit = true;

                }
            }
                if(autorGasit == false)
                {
                    System.out.println("Autorul nu a fost gasit");
                    return;
                }


        }

        public void printPostsBySubreddit(String subredditName){
        boolean subredditGasit = false;
        for (Post p : dataBase.mockPosts){
            if(p.getSubreddit().equalsIgnoreCase(subredditName)){
                System.out.println("Subreddit: r/" + p.getSubreddit());
                System.out.println("Titlu: " + p.getTitle());
                System.out.println("Autor: " + p.getAuthor());
                System.out.println("Upvotes: " + p.getUpvotes() + " | Downvotes: " + p.getDownvotes());

                subredditGasit = true;
            }
        }
        if(subredditGasit == false){
            System.out.println("Subreddit-ul nu a fost gasit");
        }
        }
    }
