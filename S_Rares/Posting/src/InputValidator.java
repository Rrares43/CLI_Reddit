public class InputValidator {

        public static boolean isNotBlank(String input) {
            return input != null && !input.isBlank();
        }

    public static boolean isValidLink(String link) {
        return isNotBlank(link)
                && !link.contains(" ")
                && (link.startsWith("www.") || link.startsWith("http://") || link.startsWith("https://"))
                && link.contains(".");
    }
}