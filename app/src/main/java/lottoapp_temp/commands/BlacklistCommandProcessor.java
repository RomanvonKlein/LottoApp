package lottoapp_temp.commands;

import static lottoapp_temp.logging.Logging.LOGGER;

import java.util.Arrays;

import lottoapp_temp.App;
import lottoapp_temp.data.Storage;
import lottoapp_temp.exception.BadCommandSyntaxException;

/**
 * Command processor for the 'blacklist' command
 */
public class BlacklistCommandProcessor implements ICommandProcessor {
    private static BlacklistCommandProcessor singletonInstance = null;

    private BlacklistCommandProcessor() {
    }

    @Override
    public void execute(String[] args) throws BadCommandSyntaxException {
        if (args.length >= 2) {
            String method = args[0].toLowerCase();
            if (method.equals("add")) {
                tryParseAddNumbers(Arrays.stream(args, 1, args.length).toArray(String[]::new));
            } else if (method.equals("remove")) {
                tryRemoveNumbers(Arrays.stream(args, 1, args.length).toArray(String[]::new));
            } else {
                throw new IllegalArgumentException(String.format(
                        "Unknown blacklist operation '%s'. Use either 'add number1 , number2, ...' or 'remove number1 , number2, ...'. ",
                        method));
            }
        } else {
            throw new BadCommandSyntaxException(String.format(
                    "Illegal number of arguments for blacklisting: found %d expected at least 2.", args.length));
        }
    }

    /**
     * Singleton provider
     */
    public static BlacklistCommandProcessor getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new BlacklistCommandProcessor();
        }
        return singletonInstance;
    }

    /**
     * Tries to add the givenn number to the blacklist. Checks for bounds and
     * blacklist restrictions. Throws an IllegalArgumentException if the given
     * number conflicts with any rules.
     */
    private boolean tryAddNumber(int number) throws IllegalArgumentException {
        if (App.BLACKLIST.contains(number)) {
            throw new IllegalArgumentException(String
                    .format("Number '%d' is already in the blacklist. Current blacklist content: %s",
                            number, Arrays.toString(App.BLACKLIST.toArray())));
        } else if (!GameCommandProcessor.isNumberValidForAnyGame(number)) {
            throw new IllegalArgumentException(String
                    .format("Number '%d' is not valid for any game.",
                            number));
        } else {
            App.BLACKLIST.add(number);
            return true;
        }
    }

    /**
     * Tries to parse numbers from the strings in the provided array. Then tries to
     * add them one-by-one to the blacklist.
     * <p>
     * Will throw if either the string cannot be parsed to a number or the parsed
     * number conflicts with any rules.
     */
    private void tryParseAddNumbers(String[] args) throws BadCommandSyntaxException, IllegalArgumentException {
        boolean blacklistChanged = false;
        if (App.BLACKLIST.size() >= App.MAX_BLACKLIST) {
            throw new IllegalArgumentException(
                    "Blacklist is already full. Try to remove numbers with the command 'blacklist remove number1, number2,...' first.");
        } else if (App.BLACKLIST.size() + args.length > App.MAX_BLACKLIST) {
            throw new BadCommandSyntaxException(
                    String.format(
                            "Too many arguments for blacklist. Current blacklist size: %d, maximum blacklist size: %d.",
                            App.BLACKLIST.size(), App.MAX_BLACKLIST));
        } else {
            for (String numberCandidate : args) {
                int candidate;
                try {
                    candidate = Integer.parseInt(numberCandidate);
                    if (tryAddNumber(candidate)) {
                        blacklistChanged = true;
                        String successMsg = String.format("Added %d to blacklist.", candidate);
                        LOGGER.info(successMsg);
                        System.out.println(successMsg);
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(
                            String.format("Entry '%s' cannot be parsed to a valid number.", numberCandidate));
                }
            }
        }
        if (blacklistChanged) {
            Storage.saveBlacklist();
        }
    }

    /**
     * Tries to parse the given array of strings to numbers, then to remove them
     * from the blacklist. In case any string cannot be parsed or any parsed number violates the rules, will throw an IllegalArgumentException.
     */
    private void tryRemoveNumbers(String[] args) throws IllegalArgumentException {
        boolean blacklistChanged = false;
        if (App.BLACKLIST.isEmpty()) {
            throw new IllegalArgumentException(
                    "Blacklist is already empty. Try to add numbers with the command 'blacklist add number1, number2,...' first.");
        } else if (App.BLACKLIST.size() - args.length < 0) {
            throw new IllegalArgumentException(
                    String.format(
                            "Too many numbers given to remove from the blacklist. Current blacklist size: %d.",
                            App.BLACKLIST.size()));
        } else {
            for (String numberCandidate : args) {
                try {
                    int candidate = Integer.parseInt(numberCandidate);
                    if (App.BLACKLIST.contains(candidate)) {
                        blacklistChanged = true;
                        App.BLACKLIST.remove(App.BLACKLIST.indexOf(candidate));
                        String successMsg = String.format("Removed number %d from blacklist.", candidate);
                        LOGGER.info(successMsg);
                        System.out.println(successMsg);
                    } else {
                        throw new IllegalArgumentException(String
                                .format("Number '%d' is not in the blacklist. Current blacklist content: %s",
                                        candidate, Arrays.toString(App.BLACKLIST.toArray())));
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(
                            String.format("Entry '%s' cannot be parsed to a valid number.", numberCandidate));
                }
            }
        }
        if (blacklistChanged) {
            Storage.saveBlacklist();
        }
    }

}
