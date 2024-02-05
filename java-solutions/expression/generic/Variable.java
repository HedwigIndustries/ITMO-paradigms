package expression.generic;

import java.util.Objects;

public class Variable<T> implements GenericSomeExpression<T> {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public T evaluate(T x) {
        return x;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        switch (this.name) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
        }
        throw new IllegalArgumentException("Invalid args");
    }

    @Override
    public String toString() {
        return name;
    }

    /*@Override
    public boolean equals(Object expression) {
        if (expression instanceof final Variable variable) {
            return name.equals(variable.name);
        } else {
            return false;
        }
    }*/

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
