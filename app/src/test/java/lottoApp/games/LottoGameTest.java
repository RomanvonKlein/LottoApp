package lottoapp.games;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LottoGameTest {
    @Test
    public void testValidNumbers(){
        //Test edge cases
        LottoGame lottoGame =  new LottoGame();
        assertFalse(lottoGame.isNumberValid(0));
        assertTrue(lottoGame.isNumberValid(1));
        assertFalse(lottoGame.isNumberValid(50));
        assertTrue(lottoGame.isNumberValid(49));
        //Test mid range cases
        assertTrue(lottoGame.isNumberValid(12));
        assertTrue(lottoGame.isNumberValid(23));
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
        //test predicted values are in range
        int lastval = -1;
        for (int candidate:predictions.get(0)) {
            assertTrue(candidate > lastval);
            lastval = candidate;
        }
    }
}
