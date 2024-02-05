package expression.generic;

public interface GenericParser<T> {
    GenericSomeExpression<T> parse(String string);

}
