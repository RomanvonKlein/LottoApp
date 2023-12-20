/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package lottoapp;

import org.junit.Test;

import lottoapp.App;
import lottoapp.logging.Logging;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AppTest {
    @Test
    public void appHasALogger() {
        App app = new App();
        assertNotNull("app should have a logger", Logging.LOGGER);
    }

    private Method getParseCommandMethod() throws NoSuchMethodException {
        Method parseCommandMethod = App.class.getDeclaredMethod("parseCommand", String[].class);
        parseCommandMethod.setAccessible(true);
        return parseCommandMethod;
    }

    @Test
    public void appRecognizesExitCommand()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        App app = new App();
        assertTrue((Boolean) (getParseCommandMethod().invoke(app,
                new Object[]{new String[] { "exit" }})));
    }
}
