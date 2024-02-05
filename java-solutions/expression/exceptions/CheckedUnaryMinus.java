package expression.exceptions;

import expression.SomeExpressions;

public class CheckedUnaryMinus extends CheckedUnaryOperations {
    public CheckedUnaryMinus(SomeExpressions value) {
        super(value, "-");
    }

    @Override
    protected int check(int arg) {
        if (arg <= Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return -arg;
    }
}
