package logger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LogLevelTest {

    @Test
    void testLogLevelValues() {
        LogLevel[] levels = LogLevel.values();
        assertEquals(3, levels.length);
    }

    @Test
    void testLogLevelInfo() {
        LogLevel level = LogLevel.INFO;
        assertEquals("INFO", level.name());
    }

    @Test
    void testLogLevelWarning() {
        LogLevel level = LogLevel.WARNING;
        assertEquals("WARNING", level.name());
    }

    @Test
    void testLogLevelError() {
        LogLevel level = LogLevel.ERROR;
        assertEquals("ERROR", level.name());
    }

    @Test
    void testLogLevelValueOf() {
        assertEquals(LogLevel.INFO, LogLevel.valueOf("INFO"));
        assertEquals(LogLevel.WARNING, LogLevel.valueOf("WARNING"));
        assertEquals(LogLevel.ERROR, LogLevel.valueOf("ERROR"));
    }

    @Test
    void testLogLevelOrdinal() {
        assertEquals(0, LogLevel.INFO.ordinal());
        assertEquals(1, LogLevel.WARNING.ordinal());
        assertEquals(2, LogLevel.ERROR.ordinal());
    }
}
