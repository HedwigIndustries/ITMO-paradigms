package expression.generic;

import java.util.Objects;

public class Const<T> implements GenericSomeExpression<T> {
    private final T value;

    public Const(T value) {
        this.value = value;

    }

    @Override
    public T evaluate(T x) {
        return value;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    /*@Override
    public boolean equals(Object expression) {
        if (expression instanceof final Const const1) {
            return value == const1.value;
        } else {
            return false;
        }
    }*/

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
