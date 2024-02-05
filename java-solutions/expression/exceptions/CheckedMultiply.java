package expression.exceptions;

import expression.SomeExpressions;

public class CheckedMultiply extends CheckedBinaryOperations {
    public CheckedMultiply(SomeExpressions first, SomeExpressions second) {
        super(first, second, "*");
    }

    @Override
    protected int check(int first, int second) {
        if (first > 0 && second > 0) {
            if (first > (Integer.MAX_VALUE / second)) {
                throw new OverflowException();
            }
        } else if (first < 0 && second < 0) {
            if (first < (Integer.MAX_VALUE / second)) {
                throw new OverflowException();
            }
        } else if (first > 0 && second < 0 && second != -1) {
            if (first > (Integer.MIN_VALUE / second)) {
                throw new OverflowException();
            }
        } else if (first < 0 && second > 0 && second != 1) {
            if (first < (Integer.MIN_VALUE / second)) {
                throw new OverflowException();
            }
        }
        return first * second;
    }
}


