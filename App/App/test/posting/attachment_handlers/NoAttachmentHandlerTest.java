package posting.attachment_handlers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import posting.OutputWriter;

import java.io.ByteArrayOutputStream;

class NoAttachmentHandlerTest {

    private NoAttachmentHandler handler;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        OutputWriter outputWriter = new OutputWriter() {
            @Override
            public void write(String message) {
                try {
                    outputStream.write(message.getBytes());
                } catch (java.io.IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        handler = new NoAttachmentHandler(outputWriter);
    }

    @Test
    void testHandleReturnsEmptyString() {
        String result = handler.handle();
        assertEquals("", result);
    }

    @Test
    void testHandleWritesMessage() {
        handler.handle();
        String output = outputStream.toString();
        assertEquals("Proceeding text-only post", output.trim());
    }

    @Test
    void testHandleWithNullOutput() {
        NoAttachmentHandler handlerWithNull = new NoAttachmentHandler(null);
        assertThrows(Exception.class, () -> handlerWithNull.handle());
    }

    @Test
    void testMultipleHandleCalls() {
        handler.handle();
        handler.handle();
        String output = outputStream.toString();
        assertTrue(output.contains("Proceeding text-only post"));
    }
}
