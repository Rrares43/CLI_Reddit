import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String mockAuthor=("rares0208");
        int mockId=2;

        System.out.println("-- CREATE A NEW POST --");

        String title = "";
        while (title.isBlank()) {
            System.out.println("Enter post title:");
            title = sc.nextLine();
            if (title.isBlank()) {
                System.out.println("Error: Title cannot be empty!");
            }
        }

        String content = "";
        while (content.isBlank()) {
            System.out.println("Enter post content:");
            content = sc.nextLine();
            if (content.isBlank()) {
                System.out.println("Error: Content cannot be empty!");
            }
        }

        while (true) {
            System.out.println("Do you want to add an attachment? (photo/link/no)");
            String attachmentType = sc.nextLine();

            if (attachmentType.equalsIgnoreCase("photo")) {
                String photoName = "";
                while (photoName.isBlank()) {
                    System.out.println("Enter the photo name:");
                    photoName = sc.nextLine();
                    if (photoName.isBlank()) {
                        System.out.println("Error: Photo name cannot be empty!");
                    }
                }
                content = content + "\n[Image: " + photoName + "]";
                break;

            } else if (attachmentType.equalsIgnoreCase("link")) {
                String linkUrl = "";
                while (true) {
                    System.out.println("Enter the link (e.g. www.google.com):");
                    linkUrl = sc.nextLine();

                    if (linkUrl.isBlank()) {
                        System.out.println("Error: Link cannot be empty!");
                    }
                    //validate the link
                    else if (!linkUrl.contains(".") || linkUrl.contains(" ")) {
                        System.out.println("Error: Invalid link! A link must contain a dot ('.') and no spaces.");
                    }
                    else {
                        break;
                    }
                }
                content = content + "\n[Link: " + linkUrl + "]";
                break;

            } else if (attachmentType.equalsIgnoreCase("no")) {
                System.out.println("Proceeding text-only post...");
                break;

            } else {
                System.out.println("Invalid Input. Please type 'photo', 'link', or 'no'.");
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
