package expression.exceptions;

import expression.SomeExpressions;

public class CheckedAdd extends CheckedBinaryOperations {

    public CheckedAdd(SomeExpressions first, SomeExpressions second) {
        super(first, second, "+");
    }

    @Override
    protected int check(int first, int second) {
        if ((second > 0) && (first > Integer.MAX_VALUE - second)) {
            throw new OverflowException();
        }
        if ((second < 0) && (first < Integer.MIN_VALUE - second)) {
            throw new OverflowException();
        }
        return first + second;
    }
}
