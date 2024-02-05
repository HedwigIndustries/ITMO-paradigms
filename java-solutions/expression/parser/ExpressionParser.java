///*
//package expression.parser;
//
//import expression.*;
//import expression.exceptions.TripleParser;
//
//public final class ExpressionParser implements TripleParser {
//    public ExpressionParser() {
//    }
//
//    public TripleExpression parse(final String string) {
//        return parse(new StringCharSource(string));
//    }
//
//    public static TripleExpression parse(final CharSource source) {
//        return new ExprParser(source).parseExpression();
//    }
//
//    private static class ExprParser extends BaseParser {
//
//        public ExprParser(final CharSource source) {
//            super(source);
//        }
//
//        public SomeExpressions parseExpression() {
//            skipWhitespace();
//            return thirdPriorityParse();
//        }
//
//        private SomeExpressions thirdPriorityParse() {
//            SomeExpressions result = secondPriorityParse();
//            while (!eof()) {
//                if (take('s')) {
//                    expect("et");
//                    skipWhitespace();
//                    result = new Set(result, secondPriorityParse());
//                } else if (take('c')) {
//                    if (test('l')) {
//                        expect("lear");
//                        result = new Clear(result, secondPriorityParse());
//                    } else if (test('o')) {
//                        expect("ount");
//                        return new UnaryCount(unaryOperationParse());
//                    }
//                } else {
//                    return result;
//                }
//            }
//            return result;
//        }
//
//        private SomeExpressions secondPriorityParse() {
//            SomeExpressions result = firstPriorityParse();
//            while (!eof()) {
//                if (take('+')) {
//                    result = new Add(result, firstPriorityParse());
//                } else if (take('-')) {
//                    result = new Subtract(result, firstPriorityParse());
//                } else {
//                    return result;
//                }
//            }
//            return result;
//        }
//
//        private SomeExpressions firstPriorityParse() {
//            SomeExpressions result = unaryOperationParse();
//            while (!eof()) {
//                skipWhitespace();
//                if (take('*')) {
//                    result = new Multiply(result, unaryOperationParse());
//                } else if (take('/')) {
//                    result = new Divide(result, unaryOperationParse());
//                } else {
//                    return result;
//                }
//            }
//            return result;
//        }
//
//        private SomeExpressions unaryOperationParse() {
//            skipWhitespace();
//            if (take('c')) {
//                expect("ount");
//                skipWhitespace();
//                return new UnaryCount(unaryOperationParse());
//            }
//            if (take('-')) {
//                if (between('0', '9')) {
//                    return parseConst('-');
//                } else {
//                    return new UnaryMinus(unaryOperationParse());
//                }
//            }
//            return parseArgument();
//        }
//
//        private SomeExpressions parseArgument() {
//            skipWhitespace();
//            if (take('(')) {
//                SomeExpressions result = parseExpression();
//                expect(')');
//                return result;
//            }
//            if (between('0', '9')) {
//                return parseConst('+');
//            }
//            if (test('x') || test('y') || test('z')) {
//                return new Variable(Character.toString(take()));
//            } else {
//                throw error("Invalid argument" + testChar());
//            }
//        }
//
//        private Const parseConst(char op) {
//            if (take('0')) {
//                return new Const(Integer.parseInt("0"));
//            } else {
//                final StringBuilder sb = new StringBuilder();
//                if (op == '-') {
//                    sb.append(op);
//                }
//                sb.append(takeDigits());
//                return new Const(Integer.parseInt(sb.toString()));
//            }
//        }
//
//        private String takeDigits() {
//            StringBuilder sb = new StringBuilder();
//            while (between('0', '9')) {
//                sb.append(take());
//            }
//            return (sb.toString());
//
//        }
//
//        private void skipWhitespace() {
//            while (!eof() && Character.isWhitespace(testChar())) {
//                take();
//            }
//        }
//
//    }
//
//}
//*/
