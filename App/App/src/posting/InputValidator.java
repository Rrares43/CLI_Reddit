package posting;

public class InputValidator {

    public boolean isValidLength(String text, int maxLength) {
        return text.length() <= maxLength;
    }
}