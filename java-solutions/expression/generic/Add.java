package expression.generic;

public class Add<T> extends BinaryOperation<T> {
    public Add(GenericSomeExpression<T> first, GenericSomeExpression<T> second, Types<T> type) {
        super(first, second, type, "+");
    }

    @Override
    protected T apply(T first, T second) {
        return type.add(first, second);
    }

}
