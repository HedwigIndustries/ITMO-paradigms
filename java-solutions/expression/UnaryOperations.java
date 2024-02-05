package expression;


abstract public class UnaryOperations implements SomeExpressions {

    protected SomeExpressions value;
    protected String operation;

    protected UnaryOperations(SomeExpressions value, String operation) {
        this.value = value;
        this.operation = operation;
    }

    @Override
    public String toString() {
        return operation + "(" + value.toString() + ")";
    }

    @Override
    public boolean equals(Object expression) {
        if (expression instanceof UnaryOperations)
            return this.toString().equals(expression.toString());
        else
            return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode() * 19 + operation.hashCode();
    }

}
