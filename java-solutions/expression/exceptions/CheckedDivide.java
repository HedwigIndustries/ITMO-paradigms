package expression.exceptions;

import expression.SomeExpressions;

public class CheckedDivide extends CheckedBinaryOperations {
    public CheckedDivide(SomeExpressions first, SomeExpressions second) {
        super(first, second, "/");
    }

    @Override
    protected int check(int first, int second) {
        if (first == Integer.MIN_VALUE && second == -1){
            throw new OverflowException();
        }
        if (second == 0) {
            throw new DBZException();
        }
        return first / second;
    }
}
