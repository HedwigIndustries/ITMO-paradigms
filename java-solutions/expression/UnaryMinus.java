package expression;

public class UnaryMinus extends UnaryOperations {
    public UnaryMinus(SomeExpressions value) {
        super(value, "-");
    }

    @Override
    public int evaluate(int x) {
        return -value.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -value.evaluate(x, y, z);
    }

}
