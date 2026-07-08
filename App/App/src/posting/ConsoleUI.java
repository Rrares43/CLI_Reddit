package posting;

import interaction.Post;

import java.util.Scanner;

public class ConsoleUI {
    private Scanner sc;

    public ConsoleUI() {
        this.sc = new Scanner(System.in);
    }

    public String askForTitle() {
        int limit = 300;
        while (true) {
            System.out.println("Enter post title (max " + limit + " characters):");
            String title = sc.nextLine();

            if (!InputValidator.isNotBlank(title)) {
                System.out.println("Error: Title cannot be empty!");

            } else if (!InputValidator.isValidLength(title, limit)) {
                System.out.println("Error: Title is too long! You entered " + title.length() + " characters.");

            } else {
                return title;
            }
        }
    }

    public String askForContent() {
        int limit = 3000;
        while (true) {
            System.out.println("Enter post content (max " + limit + " characters):");
            String content = sc.nextLine();

            if (!InputValidator.isNotBlank(content)) {
                System.out.println("Error: Content cannot be empty!");
            } else if (!InputValidator.isValidLength(content, limit)) {
                System.out.println("Error: Content is too long! You entered " + content.length() + " characters.");
            } else {
                return content;
            }
        }
    }

    public String askForAttachment() {
        while (true) {
            System.out.println("Do you want to add an attachment? (photo/link/no)");
            String type = sc.nextLine();

            if (type.equalsIgnoreCase("photo")) {
                return askForPhoto();
            } else if (type.equalsIgnoreCase("link")) {
                return askForLink();
            } else if (type.equalsIgnoreCase("no")) {
                System.out.println("Proceeding text-only post...");
                return "";
            } else {
                System.out.println("Invalid Input. Please type 'photo', 'link', or 'no'.");
            }
        }
    }

    private String askForPhoto() {
        while (true) {
            System.out.println("Enter the photo name:");
            String photo = sc.nextLine();
            if (InputValidator.isNotBlank(photo)) {
                return "\n[Image: " + photo + "]";
            }
            System.out.println("Error: Photo name cannot be empty!");
        }
    }

    private String askForLink() {
        while (true) {
            System.out.println("Enter the link (e.g. www.google.com):");
            String link = sc.nextLine();

            if (!InputValidator.isNotBlank(link)) {
                System.out.println("Error: Link cannot be empty!");
            } else if (!InputValidator.isValidLink(link)) {
                System.out.println("Error: Invalid link!");
            } else {
                return "\n[Link: " + link + "]";
            }
        }
    }

    public void displayPost(Post post) {
        System.out.println("\n-- POST SUCCESSFULLY CREATED --");
        System.out.println("ID: " + post.getId());
        System.out.println("Author: " + post.getAuthor());
        System.out.println("Title: " + post.getTitle());
        System.out.println("Content: " + post.getContent());
    }

    public void closeScanner() {
        sc.close();
    }
}