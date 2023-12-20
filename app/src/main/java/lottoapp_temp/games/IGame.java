package lottoapp_temp.games;

import java.util.ArrayList;
import java.util.List;

import lottoapp_temp.data.Prediction;

public interface IGame {
    public List<Prediction> getRanges();

    /**
     * Generates a list of prediction lists as configured in the implementations.
     * @return
     */
    public default List<List<Integer>> getPredictions() {
        List<List<Integer>> predictions = new ArrayList<>();
        for (Prediction pred : this.getRanges()) {
            List<Integer> predictionSet = new ArrayList<>();
            while (predictionSet.size() < pred.getCount()) {
                predictionSet.add(pred.generateNext(predictionSet));
            }
            predictionSet.sort(null);
            predictions.add(predictionSet);
        }
        return predictions;
    }

    /**
     * Checks wether a number in the the configured range of any prediciton range of the implementation.
     */
    public default boolean isNumberValid(int number) {
        for (Prediction pred : this.getRanges()) {
            if (number >= pred.getMin() && number <= pred.getMax()) {
                return true;
            }
        }
        return false;
    }

    public String getName();
}