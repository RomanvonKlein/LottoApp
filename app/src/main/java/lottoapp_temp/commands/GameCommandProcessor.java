package lottoapp_temp.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.logging.Logger;

import lottoapp_temp.exception.BadCommandSyntaxException;
import lottoapp_temp.games.EurojackpotGame;
import lottoapp_temp.games.IGame;
import lottoapp_temp.games.LottoGame;
import lottoapp_temp.logging.Logging;

/**
 * Command processor for the 'game' command
 */
public class GameCommandProcessor implements ICommandProcessor {

    private static GameCommandProcessor singletonInstance = null;
    /**
     * Map for assigning user arguments from the console input to IGame - instances.
     */
    private static HashMap<String, IGame> gameMap = new HashMap<>(
            Map.of("lotto", new LottoGame(), "eurojackpot", new EurojackpotGame()));

    private GameCommandProcessor() {
    }

    /**
     * Singleton provider
     */
    public static GameCommandProcessor getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new GameCommandProcessor();
        }
        return singletonInstance;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            throw new BadCommandSyntaxException("Not enough parameters were passed. Usage: 'game [gamename]'.");
        }
        String gameName = args[0].toLowerCase();
        if (!gameMap.containsKey(gameName)) {
            throw new IllegalArgumentException(
                    String.format("'%s' is not a valid game name. Registered games: '%s'", gameName,
                            Arrays.toString(gameMap.keySet().toArray())));
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

    /**
     * Checks wether the given number is within any range of any Prediction of any registered IGame instance.
     */
    public static boolean isNumberValidForAnyGame(int number) {
        for (IGame game : gameMap.values()) {
            if (game.isNumberValid(number)) {
                Logging.LOGGER.info(String.format("Number %d is a valid number for game %s.",number,game.getName()));
                return true;
            }
        }
        Logging.LOGGER.info(String.format("Number %d is not a valid number in any game.",number));
        return false;
    }
}
