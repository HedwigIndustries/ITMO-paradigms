package expression.generic;

public class TypeDouble implements Types<Double> {

    public Double add(Double first, Double second) {
        return first + second;
    }

    public Double subtract(Double first, Double second) {
        return first - second;
    }

    public Double multiply(Double first, Double second) {
        return first * second;
    }

    public Double divide(Double first, Double second) {
        return first / second;
    }

    public Double unaryMinus(Double value) {
        return -value;
    }

    public Double castToCurrentGeneric(String string) {
        return Double.parseDouble(string);
    }

}
