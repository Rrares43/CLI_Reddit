package posting.post_validators;

public interface Validator<T> {
    boolean isValid(T input);
}
