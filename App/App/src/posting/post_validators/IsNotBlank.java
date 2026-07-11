package posting.post_validators;

public class IsNotBlank implements Validator<String> {
    @Override
    public boolean isValid(String input) {
        return input != null && !input.isBlank();
    }
}
