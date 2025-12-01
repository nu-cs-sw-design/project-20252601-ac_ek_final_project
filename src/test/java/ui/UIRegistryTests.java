package ui;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UIRegistryTests {

    @Test
    public void testCreate_console() {
        Optional<GameUI> result = UIRegistry.create("console");
        assertTrue(result.isPresent());
        assertTrue(result.get() instanceof ConsoleUI);
    }

    @Test
    public void testCreate_gui() {
        Optional<GameUI> result = UIRegistry.create("gui");
        assertTrue(result.isPresent());
        assertTrue(result.get() instanceof SwingGUI);
    }

    @Test
    public void testCreate_caseInsensitive_console() {
        Optional<GameUI> result = UIRegistry.create("CONSOLE");
        assertTrue(result.isPresent());
    }

    @Test
    public void testCreate_caseInsensitive_gui() {
        Optional<GameUI> result = UIRegistry.create("GUI");
        assertTrue(result.isPresent());
    }

    @Test
    public void testCreate_mixedCase() {
        Optional<GameUI> result = UIRegistry.create("Console");
        assertTrue(result.isPresent());
    }

    @Test
    public void testCreate_nonExistent() {
        Optional<GameUI> result = UIRegistry.create("nonexistent");
        assertFalse(result.isPresent());
    }

    @Test
    public void testCreate_emptyString() {
        Optional<GameUI> result = UIRegistry.create("");
        assertFalse(result.isPresent());
    }

    @Test
    public void testCreateDefault_returnsConsoleUI() {
        GameUI result = UIRegistry.createDefault();
        assertNotNull(result);
        assertTrue(result instanceof ConsoleUI);
    }

    @Test
    public void testIsRegistered_console() {
        assertTrue(UIRegistry.isRegistered("console"));
    }

    @Test
    public void testIsRegistered_gui() {
        assertTrue(UIRegistry.isRegistered("gui"));
    }

    @Test
    public void testIsRegistered_caseInsensitive() {
        assertTrue(UIRegistry.isRegistered("CONSOLE"));
        assertTrue(UIRegistry.isRegistered("GUI"));
    }

    @Test
    public void testIsRegistered_nonExistent() {
        assertFalse(UIRegistry.isRegistered("nonexistent"));
    }

    @Test
    public void testIsRegistered_emptyString() {
        assertFalse(UIRegistry.isRegistered(""));
    }

    @Test
    public void testGetAvailableTypes_containsConsole() {
        Collection<String> types = UIRegistry.getAvailableTypes();
        assertTrue(types.contains("console"));
    }

    @Test
    public void testGetAvailableTypes_containsGui() {
        Collection<String> types = UIRegistry.getAvailableTypes();
        assertTrue(types.contains("gui"));
    }

    @Test
    public void testGetAvailableTypes_notEmpty() {
        Collection<String> types = UIRegistry.getAvailableTypes();
        assertFalse(types.isEmpty());
    }

    @Test
    public void testGetAvailableTypes_size() {
        Collection<String> types = UIRegistry.getAvailableTypes();
        assertTrue(types.size() >= 2);
    }

    @Test
    public void testRegister_newType() {
        UIRegistry.register("test", "Test UI", ConsoleUI::new);
        
        assertTrue(UIRegistry.isRegistered("test"));
        Optional<GameUI> result = UIRegistry.create("test");
        assertTrue(result.isPresent());
    }

    @Test
    public void testRegister_caseInsensitiveAccess() {
        UIRegistry.register("custom", "Custom UI", ConsoleUI::new);
        
        assertTrue(UIRegistry.isRegistered("CUSTOM"));
        assertTrue(UIRegistry.isRegistered("Custom"));
    }

    @Test
    public void testCreateAndIsRegistered_consistent() {
        Collection<String> types = UIRegistry.getAvailableTypes();
        
        for (String type : types) {
            assertTrue(UIRegistry.isRegistered(type));
            assertTrue(UIRegistry.create(type).isPresent());
        }
    }
}