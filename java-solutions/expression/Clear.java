package expression;

public class Clear extends BinaryOperations {

    public Clear(SomeExpressions first, SomeExpressions second) {
        super(first, second, "clear");
    }

    @Override
    public int evaluate(int x) {
        return (first.evaluate(x)) & (~(1 << second.evaluate(x)));
    }


    @Override
    public int evaluate(int x, int y, int z) {
        return (first.evaluate(x, y, z)) & (~(1 << second.evaluate(x, y, z)));
    }
}
