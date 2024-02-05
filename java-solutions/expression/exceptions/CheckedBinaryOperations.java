package expression.exceptions;

import expression.SomeExpressions;

abstract public class CheckedBinaryOperations implements SomeExpressions {
    protected SomeExpressions first;
    protected SomeExpressions second;
    protected String operation;

    public CheckedBinaryOperations(SomeExpressions first, SomeExpressions second, String operation) {
        this.first = first;
        this.second = second;
        this.operation = operation;
    }

    public int evaluate(int x) {
        int first = this.first.evaluate(x);
        int second = this.second.evaluate(x);
        return check(first, second);
    }

    public int evaluate(int x, int y, int z) {
        int first = this.first.evaluate(x, y, z);
        int second = this.second.evaluate(x, y, z);
        return check(first, second);
    }
   /* private int invokeOperation(int first, int second) {
        switch (operation) {
            case ("+") -> {
                return first + second;
            }
            case ("-") -> {
                return first - second;
            }
            case ("*") -> {
                return first * second;
            }
            case ("/") -> {
                return first / second;
            }
            default -> {
                System.err.println("Invalid operation" + " " + operation);
                return 0;
            }
        }
    }*/
    protected abstract int check(int first, int second);

    public String toString() {
        return "(" + first.toString() + " " + operation + " " + second.toString() + ")";
    }

    public boolean equals(Object expression) {
        if (expression instanceof final CheckedBinaryOperations operation1) {
            return this.first.equals(operation1.first) &&
                    this.operation.equals(operation1.operation) &&
                    this.second.equals(operation1.second);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (first.hashCode() * 19 + second.hashCode()) * 19 + operation.hashCode();
    }
}
