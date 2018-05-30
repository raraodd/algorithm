package hackerrank.arithmeticexpressions;

import java.io.*;
import java.util.*;

public class ArithmeticExpressions {

    private static final int MULTIPLICATION = 1;
    private static final int ADDITION = 2;
    private static final int SUBTRACTION = 3;
    static String finalExpression = "";

    static long doArithmetic(long[] arr, int pos, int expression, long result) {
        System.out.println(arr.length + " " + expression + " " + pos + " " + result);
        long res;
        if(pos == arr.length) return result;

        switch (expression) {
            case MULTIPLICATION:
                result *= arr[pos];
                break;
            case ADDITION:
                result += arr[pos];
                break;
            case SUBTRACTION:
                result -= arr[pos];
                break;
        }

        res = doArithmetic(arr, pos+1, 2, result);
        if(res != -99999999 && res % 101 == 0) {
            System.out.println(pos + " " + expression + " " + res + " " + res % 101);
            finalExpression = getExpression(expression) + String.valueOf(arr[pos]) + finalExpression;
            return res;
        }

        res = doArithmetic(arr, pos+1, 3, result);
        if(res != -99999999 && res % 101 == 0) {
            System.out.println(pos + " " + expression + " " + res + " " + res % 101);
            finalExpression = getExpression(expression) + String.valueOf(arr[pos]) + finalExpression;
            return res;
        }

//        res = doArithmetic(arr, pos+1, 1, result);
//        if(res != -99999999 && res % 101 == 0) {
//            System.out.println(pos + " " + expression + " " + res + " " + res % 101);
//            finalExpression = getExpression(expression) + String.valueOf(arr[pos]) + finalExpression;
//            return res;
//        }

        return -99999999;
    }

    static String getExpression(int expression) {
        switch (expression) {
            case MULTIPLICATION: return "*";
            case ADDITION: return "+";
            case SUBTRACTION: return "-";
        }
        return "";
    }

    // Complete the arithmeticExpressions function below.
    static String arithmeticExpressions(long[] arr) {
        long res;
        res = doArithmetic(arr, 1, 2, arr[0]);
        System.out.println("1 " + res + " " + res%101);
        if(!finalExpression.equals("") && res % 101 == 0) return finalExpression = arr[0] + finalExpression;
        res = doArithmetic(arr, 1, 3, arr[0]);
        System.out.println("2 " + res);
        if(!finalExpression.equals("") && res % 101 == 0) return finalExpression = arr[0] + finalExpression;
        res = doArithmetic(arr, 1, 1, arr[0]);
        System.out.println("3 " + res);
        return finalExpression = arr[0] + finalExpression;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new java.io.FileInputStream("/Users/wendyp/Documents/9_Project/Algorithms/src/hackerrank/arithmeticexpressions/input16.txt"));
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long[] arr = new long[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        String result = arithmeticExpressions(arr);
        System.out.println(result);

        scanner.close();
    }

}
