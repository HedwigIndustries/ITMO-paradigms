package expression.exceptions;

public class InvalidSymbolException extends ParserException {
    public InvalidSymbolException(String str) {
        super(str);
    }
}
