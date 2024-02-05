package expression.generic;

import expression.exceptions.DBZException;

public class TypeShort implements Types<Short> {

    public TypeShort() {
    }

    public Short add(Short first, Short second) {
        return (short) (first + second);
    }

    public Short subtract(Short first, Short second) {
        return (short) (first - second);
    }

    public Short multiply(Short first, Short second) {
        return (short) (first * second);
    }

    public Short divide(Short first, Short second) {
        if (second == 0) throw new DBZException();
        return (short) (first / second);
    }

    public Short unaryMinus(Short value) {
        return (short) (-value);

    }

    public Short castToCurrentGeneric(String string) {
        return (short) Integer.parseInt(string);
    }

}