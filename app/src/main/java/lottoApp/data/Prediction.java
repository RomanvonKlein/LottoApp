package lottoapp.data;

import java.util.List;
import java.util.Random;

import lottoapp.App;
import lottoapp.exception.BadCommandSyntaxException;

public class Prediction {
    private int min;
    private int max;
    private int count;
    private static Random rand = new Random();

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public Prediction(int min, int max, int count) {
        this.min = min;
        this.max = max;
        this.count = count;
        if (max + 1 - min < count) {
            throw new BadCommandSyntaxException(
                    String.format("Cannot generate %s unique numbers in the range from %d to %d.", count, min, max));
        }else if (count <1){
            throw new BadCommandSyntaxException("Cannot create prediciton series with less than 1 expected prediction.");
        }
    }

    public int generateNext(List<Integer> predictionSet) {
        int prediction = rand.nextInt(this.min, this.max + 1);
        while (predictionSet.contains(prediction) || App.BLACKLIST.contains(prediction)) {
            prediction = rand.nextInt(this.min, this.max + 1);
        }
        return prediction;
    }

}