package lottoapp.commands;

import lottoapp.App;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameCommandProcessorTest {
    @Before
    public void clearBlacklist(){
        App.BLACKLIST.clear();
    }
    @Test
    public void testValidNumbersJustOutOfBounds(){
        assertFalse(GameCommandProcessor.isNumberValidForAnyGame(0));
        assertFalse(GameCommandProcessor.isNumberValidForAnyGame(51));
    }

    @Test
    public void testValidNumbersJustInBounds(){
        assertTrue(GameCommandProcessor.isNumberValidForAnyGame(1));
        assertTrue(GameCommandProcessor.isNumberValidForAnyGame(50));
    }
}
