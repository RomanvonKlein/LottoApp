package lottoapp.commands;

import lottoapp.App;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertThrows;

/**
 * Not unit testing functionality with io operiations on the file system.
 */
public class BlacklistCommandProcessorTest {
    @Test
    public void testAddToFull(){
        App.BLACKLIST.clear();
        App.BLACKLIST.addAll(Arrays.asList(1,2,3,4,5,6));
        BlacklistCommandProcessor proc = BlacklistCommandProcessor.getInstance();
        assertThrows("Trying to add a number to a full list should result in an IllegalArgumentException",IllegalArgumentException.class,()->proc.execute(new String[]{"add","7"}));
    }
    @Test
    public void testRemoveFromEmpty(){
        App.BLACKLIST.clear();
        BlacklistCommandProcessor proc = BlacklistCommandProcessor.getInstance();
        assertThrows("Trying to remove a number from an empty blacklist should result in an IllegalArgumentException",IllegalArgumentException.class,()->proc.execute(new String[]{"remove","7"}));
    }
}
