package logger;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Logger {
    private static Logger instance;
    private String logMode = "CONSOLE";
    private static final String FILE_NAME = "app_log.txt";
    private Logger() {}

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void setLogMode(String mode) {
        if (mode.equalsIgnoreCase("FILE") || mode.equalsIgnoreCase("CONSOLE")) {
            this.logMode = mode.toUpperCase();
        }
    }


    public void log(LogLevel level, String message) {
        // Generează automat timpul curent: format "YYYY-MM-DD HH:MM:SS"
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String formattedMessage = "[" + timestamp + "] [" + level + "] " + message;

        if (logMode.equals("FILE")) {

            try (FileWriter fw = new FileWriter(FILE_NAME, true);
                 PrintWriter pw = new PrintWriter(fw)) {
                pw.println(formattedMessage);
            } catch (IOException e) {
                System.out.println("Eroare Logger: Scriere in fisier esuata scriem in consola");
                System.out.println(formattedMessage);
            }
        } else {

            System.out.println(formattedMessage);
        }
    }
}