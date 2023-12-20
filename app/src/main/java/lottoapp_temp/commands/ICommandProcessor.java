package lottoapp_temp.commands;

import lottoapp_temp.exception.BadCommandSyntaxException;

/**
 * Interface to be implemented for all used commands besides 'exit'.
 */
public interface ICommandProcessor {
    /**
     * Should be implemented for all commands. Expects as arguments all user input after the command name.
     * <p>
     * Should throw BadCommandSyntaxException if the command was malformatted, and IllegalArgumentException if any of the provided values does not match the expected range or type.
     */
    public void execute(String[] args) throws BadCommandSyntaxException, IllegalArgumentException;
}
