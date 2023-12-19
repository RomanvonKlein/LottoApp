package lottoapp.games;

import java.util.ArrayList;
import java.util.List;

import lottoapp.data.Prediction;

public class LottoGame implements IGame {
    private static ArrayList<Prediction> ranges = new ArrayList<>() {
        {
            add(new Prediction(1, 49, 6));
        }
    };

    @Override
    public List<Prediction> getRanges() {
        return ranges;
    }

    @Override
    public String getName(){
        return "Lotto";
    }
}
