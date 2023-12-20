package lottoapp.games;

import org.junit.Test;

import lottoapp_temp.games.LottoGame;

import java.util.List;

import static org.junit.Assert.*;

public class LottoGameTest {

    @Test
    public void testIsNumberValid_justInBounds() {
        LottoGame lottoGame =  new LottoGame();

        assertTrue("1 should be just in valid range for this game.", lottoGame.isNumberValid(1));
        assertTrue("49 should be the largest valid number for this game.", lottoGame.isNumberValid(49));
    }

    @Test
    public void testIsNumberValid_outOfBounds() {
        LottoGame lottoGame =  new LottoGame();

        assertFalse("0 should not be valid for this game", lottoGame.isNumberValid(0));
        assertFalse("50 should not too large a number for this game", lottoGame.isNumberValid(50));
    }

    @Test
    public void testIsNumberValid_validMiddle() {
        LottoGame lottoGame =  new LottoGame();

        assertTrue("20 should be valid for this game.", lottoGame.isNumberValid(12));
        assertTrue("25 should be valid for this game.", lottoGame.isNumberValid(23));
    }

    @Test
    public void testPredictions_size() {
        LottoGame lottoGame = new LottoGame();
        List<List<Integer>> predictions = lottoGame.getPredictions();
        //test number of predictions
        assertEquals("There should be exactly one set of predictions.",1, predictions.size());
        assertEquals("There should be 6 number in the prediction.",6, predictions.get(0).size());
    }

    @Test
    public void testPredictions_numberRange(){
        LottoGame lottoGame = new LottoGame();
        List<List<Integer>> predictions = lottoGame.getPredictions();
        //test predicted values are in range
        for (int candidate:predictions.get(0)) {
            assertTrue(candidate > 0 && candidate < 50);
        }
    }

    @Test
    public void testPredictions_orderPredictions(){
        LottoGame lottoGame = new LottoGame();
        List<List<Integer>> predictions = lottoGame.getPredictions();
        //test predicted values are in order
        int lastval = -1;
        for (int candidate:predictions.get(0)) {
            assertTrue(candidate > lastval);
            lastval = candidate;
        }
    }
}
