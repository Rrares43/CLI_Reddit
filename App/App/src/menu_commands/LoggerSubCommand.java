package menu_commands;

public interface LoggerSubCommand {
    String getNotificationText();
    boolean execute();
}