package expression;

public class Const implements SomeExpressions {
    private final int value;

    public Const(int value) {
        this.value = value;

    }

    @Override
    public int evaluate(int x) {
        return this.value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return this.value;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

    @Override
    public boolean equals(Object expression) {
        if (expression instanceof final Const const1) {
            return value == const1.value;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return value;
    }
}
