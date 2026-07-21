package posting.post_validators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IsValidLengthTest {

    @Test
    void testValidLength() {
        IsValidLength validator = new IsValidLength(10);
        assertTrue(validator.isValid("valid"));
    }

    @Test
    void testExactMaxLength() {
        IsValidLength validator = new IsValidLength(5);
        assertTrue(validator.isValid("valid"));
    }

    @Test
    void testExceedsMaxLength() {
        IsValidLength validator = new IsValidLength(5);
        assertFalse(validator.isValid("toolong"));
    }

    @Test
    void testNullString() {
        IsValidLength validator = new IsValidLength(10);
        assertFalse(validator.isValid(null));
    }

    @Test
    void testEmptyString() {
        IsValidLength validator = new IsValidLength(10);
        assertTrue(validator.isValid(""));
    }

    @Test
    void testZeroMaxLength() {
        IsValidLength validator = new IsValidLength(0);
        assertTrue(validator.isValid(""));
        assertFalse(validator.isValid("a"));
    }

    @Test
    void testNegativeMaxLength() {
        IsValidLength validator = new IsValidLength(-1);
        assertFalse(validator.isValid("any"));
    }

    @Test
    void testVeryLongString() {
        IsValidLength validator = new IsValidLength(100);
        StringBuilder longString = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            longString.append("a");
        }
        assertTrue(validator.isValid(longString.toString()));
    }

    @Test
    void testStringWithSpaces() {
        IsValidLength validator = new IsValidLength(10);
        assertTrue(validator.isValid("valid text"));
    }

    @Test
    void testStringWithSpecialCharacters() {
        IsValidLength validator = new IsValidLength(10);
        assertTrue(validator.isValid("!@#$%^&*()"));
    }

    @Test
    void testLargeMaxLength() {
        IsValidLength validator = new IsValidLength(10000);
        assertTrue(validator.isValid("short"));
    }

    @Test
    void testSingleCharacterAtLimit() {
        IsValidLength validator = new IsValidLength(1);
        assertTrue(validator.isValid("a"));
    }
}
