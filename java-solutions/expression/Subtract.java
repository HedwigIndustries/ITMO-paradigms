package expression;

public class Subtract extends BinaryOperations {
    public Subtract(SomeExpressions first, SomeExpressions second) {
        super(first, second, "-");
    }

    @Override
    public int evaluate(int x) {
        return first.evaluate(x) - second.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return first.evaluate(x, y, z) - second.evaluate(x, y, z);
    }

}
