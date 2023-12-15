package lottoapp.games;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EurojackpotGameTest {
    @Test
    public void testIsNumberValid() {
        EurojackpotGame eurojackpotGame = new EurojackpotGame();

        // Test mid range cases
        assertTrue("20 should be valid for this game.", eurojackpotGame.isNumberValid(20));
        assertTrue("25 should be valid for this game.", eurojackpotGame.isNumberValid(25));
        // Test edge cases
        assertFalse("0 should not be valid for this game", eurojackpotGame.isNumberValid(0));
        assertTrue("1 should be just in valid range for this game.", eurojackpotGame.isNumberValid(1));
        assertFalse("51 should not too large a number for this game", eurojackpotGame.isNumberValid(51));
        assertTrue("50 should be the largest valid number for this game.", eurojackpotGame.isNumberValid(50));
    }

    @Test
    public void testPredictions_counts() {
        EurojackpotGame eurojackpotGame = new EurojackpotGame();
        List<List<Integer>> predictions = eurojackpotGame.getPredictions();
        // test number of predictions
        assertEquals("There should be two sets of predictions",2, predictions.size());
        assertEquals("the first set of predictions should consist of 5 numbers",5, predictions.get(0).size());
        assertEquals("The second set of predictions should consist of 2 numbers",2, predictions.get(1).size());
    }

    @Test
    public void testPredictions_firstPart() {
        EurojackpotGame eurojackpotGame = new EurojackpotGame();
        List<List<Integer>> predictions = eurojackpotGame.getPredictions();
        // test predicted values are in range
        for (int candidate : predictions.get(0)) {
            assertTrue(String.format("Candidate %d should be in range 1 to 50", candidate),
                    candidate > 0 && candidate < 51);
        }
    }
    @Test
    public void testPredictions_secondPart() {
        EurojackpotGame eurojackpotGame = new EurojackpotGame();
        List<List<Integer>> predictions = eurojackpotGame.getPredictions();
        for (int candidate : predictions.get(1)) {
            assertTrue(String.format("Candidate %d should be in range 1 to 10", candidate),
                    candidate > 0 && candidate < 11);
        }
    }
    @Test
    public void testPredictions_orderPredictions_firstPart(){
        EurojackpotGame eurojackpotGame = new EurojackpotGame();
        List<List<Integer>> predictions = eurojackpotGame.getPredictions();
        //test predicted values are in range
        int lastval = -1;
        for (int candidate:predictions.get(0)) {
            assertTrue(candidate > lastval);
            lastval = candidate;
        }
    }
    @Test
    public void testPredictions_orderPredictions_secondPart(){
        EurojackpotGame eurojackpotGame = new EurojackpotGame();
        List<List<Integer>> predictions = eurojackpotGame.getPredictions();
        //test predicted values are in range
        int lastval = -1;
        for (int candidate:predictions.get(1)) {
            assertTrue(candidate > lastval);
            lastval = candidate;
        }
    }
}
