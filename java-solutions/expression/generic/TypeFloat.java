package expression.generic;

public class TypeFloat implements Types<Float> {

    public TypeFloat() {
    }

    public Float add(Float first, Float second) {
        return first + second;
    }

    public Float subtract(Float first, Float second) {
        return first - second;
    }

    public Float multiply(Float first, Float second) {
        return first * second;
    }

    public Float divide(Float first, Float second) {
        return first / second;
    }

    public Float unaryMinus(Float value) {
        return -value;
    }

    public Float castToCurrentGeneric(String string) {
        return Float.parseFloat(string);
    }

}