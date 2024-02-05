package expression;

public class Add extends BinaryOperations {

    public Add(SomeExpressions first, SomeExpressions second) {
        super(first, second, "+");
    }

    @Override
    public int evaluate(int x) {
        return first.evaluate(x) + second.evaluate(x);
    }


    @Override
    public int evaluate(int x, int y, int z) {
        //int res = first.evaluate(x, y, z) + second.evaluate(x, y, z);
        return first.evaluate(x, y, z) + second.evaluate(x, y, z);
    }
}
