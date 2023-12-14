package lottoapp.commands;

public class BlacklistCommandProcessor implements ICommandProcessor {
    private static BlacklistCommandProcessor singletonInstance = null;

    private BlacklistCommandProcessor() {
    }

    @Override
    public void execute(String[] args) {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    public static BlacklistCommandProcessor getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new BlacklistCommandProcessor();
        }
        return singletonInstance;
    }

}
