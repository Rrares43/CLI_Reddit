import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String mockAuthor=("rares0208");
        int mockId=2;

        System.out.println("-- CREATE A NEW POST --");

        System.out.println("Enter post title:");
        String title = sc.nextLine();

        System.out.println("Enter post content:");
        String content = sc.nextLine();

        while (true) {
            System.out.println("Do you want to add a photo?");
            String withPhoto = sc.nextLine();

            if (withPhoto.equalsIgnoreCase("yes")) {
                System.out.println("Enter the photo name:");
                String photoName = sc.nextLine();
                content = content + "\n[Attachment: " + photoName + "]";
                break;

            } else if (withPhoto.equalsIgnoreCase("no")) {
                System.out.println("Proceeding text-only post...");
                break;

            } else {
                System.out.println("Invalid Input. Please type 'yes' or 'no'.");
            }
        }
        Post myNewPost = new Post(mockId, title, content, mockAuthor);
        System.out.println("\n-- POST SUCCESSFULLY CREATED --");
        System.out.println("ID: " + myNewPost.getId());
        System.out.println("Author: " + myNewPost.getAuthor());
        System.out.println("Title: " + myNewPost.getTitle());
        System.out.println("Content: " + myNewPost.getContent());

        sc.close();
    }
}
