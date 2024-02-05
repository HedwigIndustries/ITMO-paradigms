package expression.exceptions;

public class ParserException extends IllegalArgumentException{
    public ParserException(String expression) {
        super(expression);
    }

}
