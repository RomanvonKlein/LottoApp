package lottoapp_temp.games;

import java.util.ArrayList;
import java.util.List;

import lottoapp_temp.data.Prediction;

/**
 * Game configuration for the '5 aus 50 und 2 aus 10' game type.
 */
public class EurojackpotGame implements IGame {

    private static ArrayList<Prediction> ranges = new ArrayList<>() {
        {
            add(new Prediction(1, 50, 5));
            add(new Prediction(1, 10, 2));
        }
    };

    @Override
    public List<Prediction> getRanges() {
        return ranges;
    }

    @Override
    public String getName() {
        return "Eurojackpot";
    }

}
