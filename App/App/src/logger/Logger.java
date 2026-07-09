package logger;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Logger {
    private static Logger instance;
    private static final String FILE_NAME = "app_log.txt";
    private Logger() {}

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(LogLevel level, String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String formattedMessage = "[" + timestamp + "] [" + level + "] " + message;

        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(formattedMessage);
        } catch (IOException e) {
            System.out.println("Error: Could not write to file");
            System.out.println(formattedMessage);
        }
    }

    public void printLogsToConsole() {
        File logFile = new File(FILE_NAME);
        if (!logFile.exists() || logFile.length() == 0) {
            System.out.println("\n Log file does not exist or is empty.");
            return;
        }

        System.out.println("\n Log file content (`app_log.txt`)");

        try (Scanner fileReader = new Scanner(logFile)) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

    }
}