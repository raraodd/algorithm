package testing;

import util.Stack;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by SRIN on 7/24/2017.
 */
public class TestStack {

    public static void main(String[] args) throws Exception {

        try {
            String s = "10000000000000.5";
            System.out.println(new BigDecimal(s).toBigInteger());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }




        Integer[] arr = {1, 2, 3, 1, 2};

        Stack<Integer> stack = new Stack<>();

        for (int i=0; i<arr.length; i++) {
            stack.push(arr[i]);
        }

        stack.print();
        stack.pop();
        stack.pop();
        stack.print();
    }
}
