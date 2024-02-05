package expression.generic;

public interface Types<T> {
    T add(T first, T second);

    T subtract(T first, T second);

    T multiply(T first, T second);

    T divide(T first, T second);

    T unaryMinus(T value);

    T castToCurrentGeneric(String string);
}
