package expression;

public class UnaryCount extends UnaryOperations {

    public UnaryCount(SomeExpressions value) {
        super(value, "count");
    }

    @Override
    public int evaluate(int x) {

        return Integer.toBinaryString(value.evaluate(x)).length();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int count = 0;
        String str = Integer.toBinaryString(value.evaluate(x, y, z));
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }


}
