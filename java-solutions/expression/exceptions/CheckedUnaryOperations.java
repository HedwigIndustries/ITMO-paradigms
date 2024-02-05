package expression.exceptions;


import expression.SomeExpressions;

abstract public class CheckedUnaryOperations implements SomeExpressions {

    protected SomeExpressions value;
    protected String operation;

    public CheckedUnaryOperations(SomeExpressions value, String operation) {
        this.value = value;
        this.operation = operation;
    }

    public int evaluate(int x) {
        int value = this.value.evaluate(x);
        return check(value);
    }

    public int evaluate(int x, int y, int z) {
        int value = this.value.evaluate(x, y, z);
        return check(value);
    }

    protected abstract int check(int first);

    public String toString() {
        return operation + "(" + value.toString() + ")";
    }

    public boolean equals(Object expression) {
        if (expression instanceof CheckedUnaryOperations)
            return this.toString().equals(expression.toString());
        else
            return false;
    }

    public int hashCode() {
        return value.hashCode() * 19 + operation.hashCode();
    }
}
