package posting.post_validators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IsNotBlankTest {

    @Test
    void testValidString() {
        IsNotBlank validator = new IsNotBlank();
        assertTrue(validator.isValid("Valid string"));
    }

    @Test
    void testNullString() {
        IsNotBlank validator = new IsNotBlank();
        assertFalse(validator.isValid(null));
    }

    @Test
    void testEmptyString() {
        IsNotBlank validator = new IsNotBlank();
        assertFalse(validator.isValid(""));
    }

    @Test
    void testBlankStringWithSpaces() {
        IsNotBlank validator = new IsNotBlank();
        assertFalse(validator.isValid("   "));
    }

    @Test
    void testBlankStringWithTabs() {
        IsNotBlank validator = new IsNotBlank();
        assertFalse(validator.isValid("\t\t"));
    }

    @Test
    void testStringWithLeadingWhitespace() {
        IsNotBlank validator = new IsNotBlank();
        assertTrue(validator.isValid("  valid"));
    }

    @Test
    void testStringWithTrailingWhitespace() {
        IsNotBlank validator = new IsNotBlank();
        assertTrue(validator.isValid("valid  "));
    }

    @Test
    void testStringWithSingleCharacter() {
        IsNotBlank validator = new IsNotBlank();
        assertTrue(validator.isValid("a"));
    }

    @Test
    void testStringWithNumbers() {
        IsNotBlank validator = new IsNotBlank();
        assertTrue(validator.isValid("123"));
    }

    @Test
    void testStringWithSpecialCharacters() {
        IsNotBlank validator = new IsNotBlank();
        assertTrue(validator.isValid("!@#$%"));
    }

    @Test
    void testStringWithNewlines() {
        IsNotBlank validator = new IsNotBlank();
        assertFalse(validator.isValid("\n\n"));
    }

    @Test
    void testStringWithMixedWhitespace() {
        IsNotBlank validator = new IsNotBlank();
        assertFalse(validator.isValid(" \t\n "));
    }
}
