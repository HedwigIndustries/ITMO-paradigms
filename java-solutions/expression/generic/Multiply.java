package expression.generic;

public class Multiply<T> extends BinaryOperation<T> {
    public Multiply(GenericSomeExpression<T> first, GenericSomeExpression<T> second, Types<T> type) {
        super(first, second, type, "*");
    }

    @Override
    protected T apply(T first, T second) {
        return type.multiply(first, second);
    }

}
