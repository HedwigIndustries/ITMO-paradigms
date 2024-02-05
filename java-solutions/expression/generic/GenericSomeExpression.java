package expression.generic;

public interface GenericSomeExpression<T> {
    T evaluate(T x);

    T evaluate(T x, T y, T z);

}
