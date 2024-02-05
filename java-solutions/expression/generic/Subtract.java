package expression.generic;

public class Subtract<T> extends BinaryOperation<T> {
    public Subtract(GenericSomeExpression<T> first, GenericSomeExpression<T> second, Types<T> types) {
        super(first, second, types, "-");
    }

    @Override
    protected T apply(T first, T second) {
        return type.subtract(first, second);
    }

}
