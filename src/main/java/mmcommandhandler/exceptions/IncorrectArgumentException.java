package mmcommandhandler.exceptions;

public class IncorrectArgumentException extends CommandExecutionException {
    public IncorrectArgumentException(String message) {
        super(message);
    }
}
