package expression.exceptions;

public class InvalidOperationException extends ParserException {
    public InvalidOperationException(String str) {
        super(str);
    }
}
