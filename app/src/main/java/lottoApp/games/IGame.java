package lottoapp.games;

import java.util.ArrayList;
import java.util.List;

import lottoapp.data.Prediction;

public interface IGame {
    public List<Prediction> getRanges();

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

    public default boolean isNumberValid(int number) {
        for (Prediction pred : this.getRanges()) {
            if (number >= pred.getMin() && number <= pred.getMax()) {
                return true;
            }
        }
        return false;
    }
}