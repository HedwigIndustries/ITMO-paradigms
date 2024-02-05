package expression.generic;


import expression.exceptions.ArgumentException;
import expression.exceptions.DBZException;
import expression.exceptions.OverflowException;

public class GenericTabulator implements Tabulator {
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        Object[][][] result = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        Types<?> type = switch (mode) {
            case ("i") -> new TypeInteger(true);
            case ("d") -> new TypeDouble();
            case ("bi") -> new TypeBigInteger();
            case ("u") -> new TypeInteger();
            case ("f") -> new TypeFloat();
            case ("s") -> new TypeShort();
            default -> throw new ArgumentException("No such mode.");
        };
        return calculate(type, expression, x1, x2, y1, y2, z1, z2, result);
    }

    private static <T> Object[][][] calculate(Types<T> type, String expression, int x1, int x2, int y1, int y2, int z1, int z2, Object[][][] result) {
        GenericExpressionParser<T> parser = new GenericExpressionParser<>(type);
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (int k = z1; k <= z2; k++) {
                    try {
                        result[i - x1][j - y1][k - z1] = parser.parse(expression)
                                .evaluate(type.castToCurrentGeneric(Integer.toString(i)),
                                        type.castToCurrentGeneric(String.valueOf(j)),
                                        type.castToCurrentGeneric(String.valueOf(k)));
                    } catch (DBZException | OverflowException  e) {
                        result[i - x1][j - y1][k - z1] = null;
                    }
                }
            }
        }
        return result;
    }

}
