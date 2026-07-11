package posting;
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
                System.out.println(message);
            }
}
