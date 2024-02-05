package expression;

abstract public class BinaryOperations implements SomeExpressions {
    protected SomeExpressions first;
    protected SomeExpressions second;
    protected String operation;

    protected BinaryOperations(SomeExpressions first, SomeExpressions second, String operation) {
        this.first = first;
        this.second = second;
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "(" + first.toString() + " " + operation + " " + second.toString() + ")";
    }

    @Override
    public boolean equals(Object expression) {
        if (expression instanceof final BinaryOperations operation1) {
            return this.first.equals(operation1.first) &&
                    this.operation.equals(operation1.operation) &&
                    this.second.equals(operation1.second);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (first.hashCode() * 19 + second.hashCode()) * 19 + operation.hashCode();
    }

}
