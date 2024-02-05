package expression.generic;

public class Divide<T> extends BinaryOperation<T> {
    public Divide(GenericSomeExpression<T> first, GenericSomeExpression<T> second, Types<T> type) {
        super(first, second, type, "/");
    }

    @Override
    protected T apply(T first, T second) {
        return type.divide(first, second);
    }

}
