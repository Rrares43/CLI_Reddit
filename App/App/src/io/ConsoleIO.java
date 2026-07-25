package io;

import java.util.Scanner;

public class ConsoleIO implements StringReader, IntReader, OutputWriter {
    private final Scanner scanner;

    public ConsoleIO() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Error: Must be a number. Try again:");
            scanner.nextLine();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    @Override
    public void write(String message) {
        if (message.contains("Error") || message.contains("Invalid") || message.contains("not found")) {
            System.out.println(TextFormatter.error("\n❌ " + message));
        }
        else if (message.contains("Success") || message.contains("created") || message.contains("deleted")) {
            System.out.println(TextFormatter.success("\n✓ " + message));
        }
        else if (message.contains("Warning") || message.contains("Are you sure")) {
            System.out.println(TextFormatter.warning("\n⚠ " + message));
        }

        else {
            System.out.println(message);
        }
    }
}
