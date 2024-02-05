package expression.generic;

import expression.exceptions.DBZException;

import java.math.BigInteger;

public class TypeBigInteger implements Types<BigInteger> {

    public TypeBigInteger() {
    }

    public BigInteger add(BigInteger first, BigInteger second) {
        return first.add(second);
    }

    public BigInteger subtract(BigInteger first, BigInteger second) {
        return first.subtract(second);
    }

    public BigInteger multiply(BigInteger first, BigInteger second) {
        return first.multiply(second);
    }

    public BigInteger divide(BigInteger first, BigInteger second) {
        if (second.equals(new BigInteger(String.valueOf(0))))
            throw new DBZException();
        return first.divide(second);
    }

    public BigInteger unaryMinus(BigInteger value) {
        return value.multiply(new BigInteger(String.valueOf(-1)));
    }

    public BigInteger castToCurrentGeneric(String string) {
        return new BigInteger(string);
    }
}
