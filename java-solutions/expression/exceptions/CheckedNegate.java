package expression.exceptions;

import expression.SomeExpressions;

public class CheckedNegate extends CheckedUnaryOperations {
    public CheckedNegate(SomeExpressions value) {
        super(value, "-");
    }

    @Override
    protected int check(int value) {
        if (value == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return -value;
    }
}
