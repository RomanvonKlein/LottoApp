package lottoapp.data;

import org.junit.Test;

import lottoapp.exception.BadCommandSyntaxException;

import static org.junit.Assert.assertThrows;

public class PredictionTest {
    @Test
    public void constructorTest_countTooLarge() {
        assertThrows(BadCommandSyntaxException.class, () -> {Prediction pred = new Prediction(1, 10, 11);});
    }
    @Test
    public void constructorTest_countNegative(){
        assertThrows (BadCommandSyntaxException.class,()->{ Prediction pred= new Prediction(1,10,-1);});
    }
    @Test
    public void constructorTest_countZero(){
        assertThrows (BadCommandSyntaxException.class,()->{ Prediction pred= new Prediction(1,10,0);});
    }
    @Test
    public void constructorTest_smallestPossibleCount(){
        Prediction pred_min= new Prediction(1,10,1);
    }
    @Test
    public void constructorTest_largestPossibleCount(){
        Prediction pred_min= new Prediction(1,10,10);
    }
}
