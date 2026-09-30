package training.leetcode.algorithm.calculate;

public class BasicCalculator {

    public int calculate(String expression) {
        int length = expression.length();
        int current = 0;
        int prev = 0;
        int accum = 0;
        char prevOp = '+';

        for (int i = 0; i < length; i++) {
            char c = expression.charAt(i);

            if (c >= '0' && c <= '9') {
                current = current * 10 + (c - '0');
            }

            if (c == '+' || c == '-' || c == '*' || i == length - 1) {
                if (prevOp == '+' || prevOp == '-') {
                    accum = accum + prev;
                    prev = (prevOp == '+') ? current : -current;
                } else if (prevOp == '*') {
                    prev = prev * current;
                }

                prevOp = c;
                current = 0;
            }
        }

        accum += prev;

        return accum;
    }

    public static void main(String[] args) {
        BasicCalculator calculator = new BasicCalculator();

        int result =  calculator.calculate("12+2*3+44+3*3");

        if(result != 71) throw new RuntimeException("Value is not 71: "+result);

        result = calculator.calculate("12+2*3-44+3*3");

        if(result != -17) throw new RuntimeException("Value is not -17: "+result);

        result = calculator.calculate("12+44+3*3");

        if(result != 65) throw new RuntimeException("Value is not 65: "+result);

        result = calculator.calculate("4*3*3");

        if(result != 36) throw new RuntimeException("Value is not 36: "+result);
    }
}
