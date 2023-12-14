package lottoapp.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lottoapp.games.EurojackpotGame;
import lottoapp.games.IGame;
import lottoapp.games.LottoGame;

public class GameCommandProcessor implements ICommandProcessor {

    private static GameCommandProcessor singletonInstance = null;
    private static HashMap<String, IGame> gameMap = new HashMap<>(
            Map.of("lotto", new LottoGame(), "eurojackpot", new EurojackpotGame()));

    private GameCommandProcessor() {
    }

    public static GameCommandProcessor getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new GameCommandProcessor();
        }
        return singletonInstance;
    }

    @Override
    public void execute(String[] args) {
        String gameName = args[0].toLowerCase();
        if (!gameMap.containsKey(gameName)) {
            throw new IllegalArgumentException(
                    String.format("'%s' is not a valid game name. Registered games: '%s'", gameName,
                            gameMap.keySet().toArray()));
        }
        System.out.print("Tips: ");
        List<List<Integer>> allPredictions = gameMap.get(gameName).getPredictions();

        for (int i = 0; i < allPredictions.size(); i++) {
            List<Integer> predictions = allPredictions.get(i);
            for (int number : predictions) {
                System.out.print(String.format("%d ", number));
            }
            if (i < allPredictions.size() - 1) {
                System.out.print("- ");
            }
        }
        System.out.println(".");
    }
}