package expression.generic;


import expression.exceptions.InvalidOperationException;
import expression.exceptions.InvalidSymbolException;

public class GenericExpressionParser<T> extends BaseParser implements GenericParser<T> {
    // private String flag;
    private final Types<T> type;

    public GenericExpressionParser(Types<T> type) {
        this.type = type;
    }


    public GenericSomeExpression<T> parse(String string) {
        //flag = "null";
        GenericSomeExpression<T> result;
        /*int countBrackets = 0;
        int i = 0;
        while (i < string.length()) {
            char ch = string.charAt(i);
            if (ch == '(') {
                countBrackets++;
            } else if (ch == ')') {
                countBrackets--;
            }
            i++;
        }
        if (countBrackets > 0) {
            throw new BracketException("No closing parenthesis");
        } else if (countBrackets < 0) {
            throw new BracketException("No opening parenthesis");
        }
        */
        result = parse(new StringCharSource(string));
        return result;
    }

    public GenericSomeExpression<T> parse(CharSource source) {
        super.source = source;
        take();
        return parseExpression();

    }

    public GenericSomeExpression<T> parseExpression() {
        //flag = "null";
        return thirdPriorityParse();
    }

    private GenericSomeExpression<T> thirdPriorityParse() {
        GenericSomeExpression<T> result = secondPriorityParse();
        if (!eofTest() && !test(')')) {
            throw new InvalidSymbolException("Invalid symbol: " + take());
        }
        return result;
    }

    private GenericSomeExpression<T> secondPriorityParse() {
        GenericSomeExpression<T> result = firstPriorityParse();
        while (!eof()) {
            if (take('+')) {
                //flag = "binary_operation";
                GenericSomeExpression<T> next = firstPriorityParse();
                result = new Add<>(result, next, type);
            } else if (take('-')) {
                // flag = "binary_operation";
                GenericSomeExpression<T> next = firstPriorityParse();
                result = new Subtract<>(result, next, type);
            } else {
                return result;
            }
        }
        return result;
    }

    private GenericSomeExpression<T> firstPriorityParse() {
        GenericSomeExpression<T> result = unaryOperationParse();
        while (!eof()) {
            skipWhitespace();
            if (take('*')) {
                //flag = "binary_operation";
                GenericSomeExpression<T> next = unaryOperationParse();
                result = new Multiply<>(result, next, type);
            } else if (take('/')) {
                //flag = "binary_operation";
                GenericSomeExpression<T> next = unaryOperationParse();
                result = new Divide<>(result, next, type);
            } else {
                return result;
            }
        }
        return result;
    }

    private GenericSomeExpression<T> unaryOperationParse() {
        skipWhitespace();
        if (take('-')) {
            if (between('0', '9')) {
                //flag = "argument";
                return parseConst('-');
            } else {
                //flag = "unary_operation";
                GenericSomeExpression<T> next = unaryOperationParse();
                return new UnaryMinus<>(next, type);
            }
        }
        return parseArgument();
    }

    private GenericSomeExpression<T> parseArgument() {
        if (take('(')) {
            //flag = "null";
            GenericSomeExpression<T> result = parseExpression();
            expect(')');
            return result;
        }
        if (between('0', '9')) {
            //flag = "argument";
            return parseConst('+');
        }
        if (test('x') || test('y') || test('z')) {
            //flag = "argument";
            return new Variable<>(Character.toString(take()));
        } else {
            throw new IllegalArgumentException();
            /*skipWhitespace();
            if (flag.equals("null")) {
                throw new ArgumentException("Expected correct first argument. Received: " + "\"" + take() + "\"");
            } else if (flag.equals("argument")) {
                throw new ArgumentException("Expected operation after argument. Received: " + "\"" + take() + "\"");
            } else if (flag.equals("binary_operation") && take() != '\0') {
                throw new ArgumentException("Expected second argument after binary operation. Received: " + "\"" + take() + "\"");
            } else if (flag.equals("unary_operation") && take() != '\0') {
                throw new ArgumentException("Expected argument after unary operation. Received: " + "\"" + take() + "\"");
            } else if (take() == '\0') {
                throw new ArgumentException("Expected last argument. Received: " + "\"" + take() + "\"");
            } else
                throw new ArgumentException("Received: " + "\"" + take() + "\"");

            */
        }
    }

    private Const<T> parseConst(char op) {
        if (take('0')) {
            return new Const<>(type.castToCurrentGeneric("0"));
        } else {
            final StringBuilder sb = new StringBuilder();
            if (op == '-') {
                sb.append(op);
            }
            sb.append(takeDigits());
            if (test('s') || test('c')) {
                //flag = "null";
                throw new InvalidOperationException("Expected correct operation with two arguments with space. Received:" + take());
            }
            return new Const<>(type.castToCurrentGeneric(sb.toString()));
        }
    }

    private String takeDigits() {
        StringBuilder sb = new StringBuilder();
        while (between('0', '9')) {
            sb.append(take());
        }
        return (sb.toString());
    }

    private void skipWhitespace() {
        while (!eof() && Character.isWhitespace(testChar())) {
            take();
        }
    }

}

