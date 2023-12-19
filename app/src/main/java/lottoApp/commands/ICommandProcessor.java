package lottoapp.commands;

import lottoapp.exception.BadCommandSyntaxException;

public interface ICommandProcessor {
    public void execute(String[] args) throws BadCommandSyntaxException, IllegalArgumentException;
}
