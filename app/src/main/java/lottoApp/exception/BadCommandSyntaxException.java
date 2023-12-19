package lottoapp.exception;

public class BadCommandSyntaxException extends IllegalArgumentException {
    private String additionalMessage = "";

    public BadCommandSyntaxException(String message) {
        this.additionalMessage = message;
    }

    @Override
    public String getMessage() {
        return String.format("The input command had invalid Syntax.%n%s", this.additionalMessage);
    }

}
