package Posting.src;

public class Main {
    public static void main(String[] args) {

        ConsoleUI ui = new ConsoleUI();

        String mockAuthor = "rares0208";
        int mockId = 2;

        System.out.println("-- CREATE A NEW POST --");

        // Requesting info
        String title = ui.askForTitle();
        String baseContent = ui.askForContent();
        String attachment = ui.askForAttachment();

        String finalContent = baseContent + attachment;

        Post myNewPost = new Post(mockId, title, finalContent, mockAuthor);

        ui.displayPost(myNewPost);

        ui.closeScanner();
    }
}