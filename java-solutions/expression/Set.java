package expression;


public class Set extends BinaryOperations {

    public Set(SomeExpressions first, SomeExpressions second) {
        super(first, second, "set");
    }

    @Override
    public int evaluate(int x) {
        return (first.evaluate(x)) | (1 << second.evaluate(x));
    }


    @Override
    public int evaluate(int x, int y, int z) {
        return (first.evaluate(x, y, z)) | (1 << second.evaluate(x, y, z));
    }
}
