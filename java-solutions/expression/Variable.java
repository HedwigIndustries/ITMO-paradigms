package expression;

public class Variable implements SomeExpressions {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (this.name) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
        }
        throw new IllegalArgumentException("Invalid args");
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object expression) {
        if (expression instanceof final Variable variable) {
            return name.equals(variable.name);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
