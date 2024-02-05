package expression.generic;

import java.util.Objects;

abstract public class BinaryOperation<T> implements GenericSomeExpression<T> {
    private final GenericSomeExpression<T> first;
    private final GenericSomeExpression<T> second;
    protected final Types<T> type;
    protected String operation;

    protected BinaryOperation(GenericSomeExpression<T> first, GenericSomeExpression<T> second, Types<T> type, String operation) {
        this.first = first;
        this.second = second;
        this.type = type;
        this.operation = operation;
    }

    protected abstract T apply(T first, T second);

    @Override
    public T evaluate(T x) {
        return apply(first.evaluate(x), second.evaluate(x));
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return apply(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "(" + first.toString() + " " + operation + " " + second.toString() + ")";
    }

    /*@Override
    public boolean equals(Object expression) {
        if (expression instanceof final BinaryOperation operation1) {
            return this.first.equals(operation1.first) &&
                    this.operation.equals(operation1.operation) &&
                    this.second.equals(operation1.second);
        } else {
            return false;
        }
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(first, second, operation);
    }

}
