package expression.generic;

import expression.exceptions.DBZException;
import expression.exceptions.OverflowException;

public class TypeInteger implements Types<Integer> {
    private boolean checked = false;

    public TypeInteger(boolean flag) {
        this.checked = flag;
    }


    public TypeInteger() {
    }

    public Integer add(Integer first, Integer second) {
        if (checked) {
            checkAdd(first, second);
        }
        return first + second;
    }

    public Integer subtract(Integer first, Integer second) {
        if (checked) {
            checkSubtract(first, second);
        }
        return first - second;
    }

    public Integer multiply(Integer first, Integer second) {
        if (checked) {
            checkMultiply(first, second);
        }
        return first * second;
    }

    public Integer divide(Integer first, Integer second) {
        if (checked) {
            checkDivide(first, second);
        }
        if (second == 0) throw new DBZException();
        return first / second;
    }

    public Integer unaryMinus(Integer value) {
        if (checked) {
            checkNegate(value);
        }
        return -value;
    }

    public Integer castToCurrentGeneric(String string) {
        return Integer.parseInt(string);
    }

    private void checkAdd(int first, int second) {
        if ((second > 0) && (first > Integer.MAX_VALUE - second)) {
            throw new OverflowException();
        }
        if ((second < 0) && (first < Integer.MIN_VALUE - second)) {
            throw new OverflowException();
        }
    }

    private void checkMultiply(int first, int second) {
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
    }

    private void checkDivide(int first, int second) {
        if (first == Integer.MIN_VALUE && second == -1) {
            throw new OverflowException();
        }
    }

    private void checkNegate(int value) {
        if (value == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
    }

    private void checkSubtract(int first, int second) {
        if ((second < 0) && (first > Integer.MAX_VALUE + second)) {
            throw new OverflowException();
        }
        if ((second > 0) && (first < Integer.MIN_VALUE + second)) {
            throw new OverflowException();
        }
    }

}
