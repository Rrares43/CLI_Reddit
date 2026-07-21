import menu_commands.MenuCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import posting.OutputWriter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class MenuDispatcherTest {

    private MenuDispatcher dispatcher;
    private ByteArrayOutputStream outputStream;
    private OutputWriter outputWriter;
    private boolean commandExecuted;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        outputWriter = new OutputWriter() {
            @Override
            public void write(String message) {
                try {
                    outputStream.write(message.getBytes());
                } catch (java.io.IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        dispatcher = new MenuDispatcher(outputWriter);
        commandExecuted = false;
    }

    @Test
    void testRegisterCommand() {
        MenuCommand command = () -> commandExecuted = true;
        dispatcher.registerCommand("1", command);
        // Command registered successfully if no exception thrown
    }

    @Test
    void testExecuteRegisteredCommand() {
        MenuCommand command = () -> commandExecuted = true;
        dispatcher.registerCommand("1", command);
        dispatcher.execute("1");
        assertTrue(commandExecuted);
    }

    @Test
    void testExecuteUnregisteredCommand() {
        dispatcher.execute("999");
        assertEquals("Invalid Input", outputStream.toString().trim());
    }

    @Test
    void testExecuteWithNullChoice() {
        dispatcher.execute(null);
        assertEquals("Invalid Input", outputStream.toString().trim());
    }

    @Test
    void testExecuteWithEmptyChoice() {
        dispatcher.execute("");
        assertEquals("Invalid Input", outputStream.toString().trim());
    }

    @Test
    void testMultipleCommands() {
        boolean[] firstExecuted = {false};
        boolean[] secondExecuted = {false};
        
        MenuCommand firstCommand = () -> firstExecuted[0] = true;
        MenuCommand secondCommand = () -> secondExecuted[0] = true;
        
        dispatcher.registerCommand("1", firstCommand);
        dispatcher.registerCommand("2", secondCommand);
        
        dispatcher.execute("1");
        assertTrue(firstExecuted[0]);
        assertFalse(secondExecuted[0]);
        
        dispatcher.execute("2");
        assertTrue(secondExecuted[0]);
    }

    @Test
    void testOverwriteExistingCommand() {
        boolean[] firstExecuted = {false};
        boolean[] secondExecuted = {false};
        
        MenuCommand firstCommand = () -> firstExecuted[0] = true;
        MenuCommand secondCommand = () -> secondExecuted[0] = true;
        
        dispatcher.registerCommand("1", firstCommand);
        dispatcher.registerCommand("1", secondCommand);
        
        dispatcher.execute("1");
        assertFalse(firstExecuted[0]);
        assertTrue(secondExecuted[0]);
    }

    @Test
    void testExecuteWithNumericString() {
        MenuCommand command = () -> commandExecuted = true;
        dispatcher.registerCommand("123", command);
        dispatcher.execute("123");
        assertTrue(commandExecuted);
    }

    @Test
    void testExecuteWithAlphabeticChoice() {
        MenuCommand command = () -> commandExecuted = true;
        dispatcher.registerCommand("a", command);
        dispatcher.execute("a");
        assertTrue(commandExecuted);
    }

    @Test
    void testExecuteWithMixedChoice() {
        MenuCommand command = () -> commandExecuted = true;
        dispatcher.registerCommand("cmd1", command);
        dispatcher.execute("cmd1");
        assertTrue(commandExecuted);
    }

    @Test
    void testCommandExecutionDoesNotThrowException() {
        MenuCommand command = () -> {
            // Command that doesn't throw
            int result = 1 + 1;
        };
        dispatcher.registerCommand("1", command);
        assertDoesNotThrow(() -> dispatcher.execute("1"));
    }
}
