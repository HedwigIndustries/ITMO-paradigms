package expression.generic;


public class UnaryMinus<T> extends UnaryOperation<T> {
    public UnaryMinus(GenericSomeExpression<T> value, Types<T> type) {
        super(value, type, "-");
    }

    @Override
    protected T apply(T value) {
        return tabulator.unaryMinus(value);
    }

}
