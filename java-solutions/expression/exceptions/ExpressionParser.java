package expression.exceptions;

import expression.*;
import expression.parser.BaseParser;
import expression.parser.CharSource;
import expression.parser.StringCharSource;

public final class ExpressionParser implements TripleParser {
    private static String flag;

    public ExpressionParser() {
    }


    public TripleExpression parse(final String string) {
        flag = "null";
        int countBrackets = 0;
        int i = 0;
        TripleExpression result;
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
        result = parse(new StringCharSource(string));
        return result;
    }

    public static TripleExpression parse(final CharSource source) {
        return new ExprParser(source).parseExpression();
    }

    private static class ExprParser extends BaseParser {

        public ExprParser(final CharSource source) {
            super(source);
        }

        public SomeExpressions parseExpression() {
            flag = "null";
            return thirdPriorityParse();
        }

        private SomeExpressions thirdPriorityParse() {
            SomeExpressions result = secondPriorityParse();
            while (!eof()) {
                if (take('s')) {
                    expect("et");
                    flag = "binary_operation";
                    SomeExpressions next = secondPriorityParse();
                    result = new Set(result, next);
                } else if (take('c')) {
                    if (test('l')) {
                        expect("lear");
                        flag = "binary_operation";
                        SomeExpressions next = secondPriorityParse();
                        result = new Clear(result, next);
                    } else if (test('o')) {
                        expect("ount");
                        flag = "unary_operation";
                        SomeExpressions next = unaryOperationParse();
                        result = new UnaryCount(next);
                    }
                } else {
                    if (!eofTest() && (!test(')'))) {
                        throw new InvalidSymbolException("Invalid symbol: " + take());
                    }
                    return result;
                }
            }
            return result;
        }

        private SomeExpressions secondPriorityParse() {
            SomeExpressions result = firstPriorityParse();
            while (!eof()) {
                if (take('+')) {
                    flag = "binary_operation";
                    SomeExpressions next = firstPriorityParse();
                    result = new CheckedAdd(result, next);
                } else if (take('-')) {
                    flag = "binary_operation";
                    SomeExpressions next = firstPriorityParse();
                    result = new CheckedSubtract(result, next);
                } else {
                    return result;
                }
            }
            return result;
        }

        private SomeExpressions firstPriorityParse() {
            SomeExpressions result = unaryOperationParse();
            while (!eof()) {
                skipWhitespace();
                if (take('*')) {
                    flag = "binary_operation";
                    SomeExpressions next = unaryOperationParse();
                    result = new CheckedMultiply(result, next);
                } else if (take('/')) {
                    flag = "binary_operation";
                    SomeExpressions next = unaryOperationParse();
                    result = new CheckedDivide(result, next);
                } else {
                    return result;
                }
            }
            return result;
        }

        private SomeExpressions unaryOperationParse() {
            skipWhitespace();
            if (take('c')) {
                expect("ount");
                if (!test('(') && !Character.isWhitespace(testChar())) {
                    throw new InvalidSymbolException("Invalid symbol: " + take());
                }
                flag = "unary_operation";
                SomeExpressions next = unaryOperationParse();
                return new UnaryCount(next);
            }
            if (take('-')) {
                if (between('0', '9')) {
                    flag = "argument";
                    return parseConst('-');
                } else {
                    flag = "unary_operation";
                    SomeExpressions next = unaryOperationParse();
                    return new CheckedUnaryMinus(next);
                }
            }
            return parseArgument();
        }

        private SomeExpressions parseArgument() {
            if (take('(')) {
                flag = "null";
                SomeExpressions result = parseExpression();
                expect(')');
                return result;
            }
            if (between('0', '9')) {
                flag = "argument";
                return parseConst('+');
            }
            if (test('x') || test('y') || test('z')) {
                flag = "argument";
                return new Variable(Character.toString(take()));
            } else {
                skipWhitespace();
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
            }
        }

        private Const parseConst(char op) {
            if (take('0')) {
                return new Const(Integer.parseInt("0"));
            } else {
                final StringBuilder sb = new StringBuilder();
                if (op == '-') {
                    sb.append(op);
                }
                sb.append(takeDigits());
                if (test('s') || test('c')) {
                    flag = "null";
                    throw new InvalidOperationException("Expected correct operation with two arguments with space. Received:" + Character.toString(take()));
                }
                return new Const(Integer.parseInt(sb.toString()));
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

}
