package expression.generic;

import java.util.Objects;

abstract public class UnaryOperation<T> implements GenericSomeExpression<T> {

    private final GenericSomeExpression<T> value;
    protected Types<T> tabulator;
    protected String operation;

    protected UnaryOperation(GenericSomeExpression<T> value, Types<T> type, String operation) {
        this.value = value;
        this.tabulator = type;
        this.operation = operation;
    }

    protected abstract T apply(T value);

    @Override
    public T evaluate(T x) {
        return apply(value.evaluate(x));
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return apply(value.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return operation + "(" + value.toString() + ")";
    }

   /* @Override
    public boolean equals(Object expression) {
        if (expression instanceof UnaryOperation)
            return this.toString().equals(expression.toString());
        else
            return false;
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(value, operation);
    }

}
