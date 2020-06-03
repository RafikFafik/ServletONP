package pl.rafjas;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.sum;

public class RpnCalculator {
    private static Stack<Integer> stack = new Stack<Integer>();
    private static Scanner scanner = new Scanner(System.in);
    StringTokenizer token;

    public static int gcd(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        while (x != y) {
            if (x > y)
                x -= y;
            else
                y -= x;
        }
        return x;
    }

    private static int lcm(int x, int y) {
        return (x * y) / gcd(x, y);
    }

    static boolean convert(String token) throws Exception {
        try {
            Pattern pattern = Pattern.compile("[0-9]+(\\|[0-9]+)?");
            Matcher matcher = pattern.matcher(token);
            matcher.matches();
            String result = matcher.group();
            StringTokenizer fraction = new StringTokenizer(result, "|");
            stack.push(Integer.parseInt(fraction.nextToken()));
            int tokenCount = fraction.countTokens();
            if (tokenCount == 0)
                stack.push(1);
            else
                stack.push(Integer.parseInt(fraction.nextToken()));
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    private static void calc(String operator) {
        int denominator_a = stack.pop();
        int nominator_a = stack.pop();
        int denominator_b = stack.pop();
        int nominator_b = stack.pop();
        int denominator_c = lcm(denominator_a, denominator_b);
        int nominator_c;
        switch (operator) {
            case "+":
                nominator_a *= denominator_b;
                nominator_b *= denominator_a;
                nominator_c = nominator_b + nominator_a;
                break;
            case "-":
                nominator_a *= denominator_b;
                nominator_b *= denominator_a;
                nominator_c = nominator_b - nominator_a;
                break;
            case "*":
                nominator_c = nominator_b * nominator_a;
                denominator_c = denominator_b * denominator_a;
                break;
            case "/":
                nominator_c = nominator_b * denominator_a;
                denominator_c = denominator_b * nominator_a;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + operator);
        }
        stack.push(nominator_c);
        stack.push(denominator_c);
    }
    private static boolean validateExpression(String expression)throws Exception {
        try {
            Pattern pattern = Pattern.compile("^([0-9]+(\\|[0-9]+)?)*$");
            Matcher matcher = pattern.matcher(expression);
            matcher.matches();
            String result = matcher.group();
            return false;
        }
        catch (Exception e) {
            return true;
        }
    }
    public static String driver(String number) throws Exception {
        //if(validateExpression(number))
       //     return "Wrong expression";
        StringTokenizer stringTokenizer = new StringTokenizer (number, " ");
        while (stringTokenizer.hasMoreTokens()) {
            String currentToken = stringTokenizer.nextToken();
            boolean isOperator = convert(currentToken);
            if(isOperator) {
                calc(currentToken);
            }
        }
        int denominator = stack.pop();
        int nominator = stack.pop();
        if (nominator == 0) {
            return "Result: " + 0;
        } else {
            nominator = nominator / gcd(nominator, denominator);
            denominator = denominator / gcd(nominator, denominator);
            if (denominator != 1)
                return "Result: " + nominator + "|" + denominator;
            else
                return "Result: " + nominator;
        }
    }
}
