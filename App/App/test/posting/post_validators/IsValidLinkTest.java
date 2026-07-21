package posting.post_validators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IsValidLinkTest {

    @Test
    void testValidHttpLink() {
        IsValidLink validator = new IsValidLink();
        assertTrue(validator.isValid("http://example.com"));
    }

    @Test
    void testValidHttpsLink() {
        IsValidLink validator = new IsValidLink();
        assertTrue(validator.isValid("https://example.com"));
    }

    @Test
    void testValidWwwLink() {
        IsValidLink validator = new IsValidLink();
        assertTrue(validator.isValid("www.example.com"));
    }

    @Test
    void testNullLink() {
        IsValidLink validator = new IsValidLink();
        assertFalse(validator.isValid(null));
    }

    @Test
    void testEmptyLink() {
        IsValidLink validator = new IsValidLink();
        assertFalse(validator.isValid(""));
    }

    @Test
    void testBlankLink() {
        IsValidLink validator = new IsValidLink();
        assertFalse(validator.isValid("   "));
    }

    @Test
    void testLinkWithSpaces() {
        IsValidLink validator = new IsValidLink();
        assertFalse(validator.isValid("http://example.com/path with spaces"));
    }

    @Test
    void testLinkWithoutProtocolOrWww() {
        IsValidLink validator = new IsValidLink();
        assertFalse(validator.isValid("example.com"));
    }

    @Test
    void testLinkWithoutDot() {
        IsValidLink validator = new IsValidLink();
        assertFalse(validator.isValid("http://example"));
    }

    @Test
    void testLinkWithSubdomain() {
        IsValidLink validator = new IsValidLink();
        assertTrue(validator.isValid("https://sub.example.com"));
    }

    @Test
    void testLinkWithPath() {
        IsValidLink validator = new IsValidLink();
        assertTrue(validator.isValid("https://example.com/path/to/resource"));
    }

    @Test
    void testLinkWithQueryParams() {
        IsValidLink validator = new IsValidLink();
        assertTrue(validator.isValid("https://example.com?param=value"));
    }

    @Test
    void testLinkWithFragment() {
        IsValidLink validator = new IsValidLink();
        assertTrue(validator.isValid("https://example.com#section"));
    }

    @Test
    void testLinkWithPort() {
        IsValidLink validator = new IsValidLink();
        assertTrue(validator.isValid("https://example.com:8080"));
    }

    @Test
    void testLinkWithWwwAndHttps() {
        IsValidLink validator = new IsValidLink();
        assertTrue(validator.isValid("https://www.example.com"));
    }

    @Test
    void testLinkWithWwwAndHttp() {
        IsValidLink validator = new IsValidLink();
        assertTrue(validator.isValid("http://www.example.com"));
    }

    @Test
    void testLinkWithTrailingSlash() {
        IsValidLink validator = new IsValidLink();
        assertTrue(validator.isValid("https://example.com/"));
    }

    @Test
    void testLinkWithMultipleDots() {
        IsValidLink validator = new IsValidLink();
        assertTrue(validator.isValid("https://example.co.uk"));
    }

    @Test
    void testLinkWithHyphen() {
        IsValidLink validator = new IsValidLink();
        assertTrue(validator.isValid("https://my-example.com"));
    }

    @Test
    void testLinkWithUnderscore() {
        IsValidLink validator = new IsValidLink();
        assertTrue(validator.isValid("https://my_example.com"));
    }

    @Test
    void testLinkWithNumbers() {
        IsValidLink validator = new IsValidLink();
        assertTrue(validator.isValid("https://example123.com"));
    }

    @Test
    void testLinkWithOnlyProtocol() {
        IsValidLink validator = new IsValidLink();
        assertFalse(validator.isValid("http://"));
    }

    @Test
    void testLinkWithFtpProtocol() {
        IsValidLink validator = new IsValidLink();
        assertFalse(validator.isValid("ftp://example.com"));
    }

    @Test
    void testLinkWithSpacesInProtocol() {
        IsValidLink validator = new IsValidLink();
        assertFalse(validator.isValid("http ://example.com"));
    }
}
