package lottoapp.commands;

public interface ICommandProcessor {

    public static ICommandProcessor getInstance() {
        throw new UnsupportedOperationException(
                "The method 'getInstance' in class " + ICommandProcessor.class.getName() + " must be overwritten.");
    }

    public void execute(String[] args);

}
