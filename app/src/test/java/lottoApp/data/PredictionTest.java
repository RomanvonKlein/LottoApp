package lottoapp.data;

import org.junit.Test;

import lottoapp.exception.BadCommandSyntaxException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class PredictionTest {
    @Test
    public void constructorTest_countTooLarge() {
        assertThrows(BadCommandSyntaxException.class, () -> {
            Prediction pred = new Prediction(1, 10, 11);
        });
    }

    @Test
    public void constructorTest_countNegative() {
        assertThrows(BadCommandSyntaxException.class, () -> {
            Prediction pred = new Prediction(1, 10, -1);
        });
    }

    @Test
    public void constructorTest_countZero() {
        assertThrows(BadCommandSyntaxException.class, () -> {
            Prediction pred = new Prediction(1, 10, 0);
        });
    }

    @Test
    public void constructorTest_smallestPossibleCount() {
        Prediction pred_min = new Prediction(1, 10, 1);
    }

    @Test
    public void constructorTest_largestPossibleCount() {
        Prediction pred_min = new Prediction(1, 10, 10);
    }

    @Test
    public void predicitonTest_shouldBeInRange() {
        Prediction pred = new Prediction(1, 10, 1);
        int result = pred.generateNext(new ArrayList<Integer>());
        assertTrue(result > 0 && result < 10);
    }

    @Test
    public void predicitonTest_shouldBeUnique() {
        int predictionCount = 10;
        Prediction pred = new Prediction(1, 10, 10);
        ArrayList<Integer> predictions = new ArrayList<>();
        for (int i = 1; i < predictionCount; i++) {
            int prediction = pred.generateNext(predictions);
            assertFalse(predictions.contains(prediction));
            predictions.add(prediction);
        }
    }
}
