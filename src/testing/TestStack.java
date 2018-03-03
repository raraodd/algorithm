package testing;

import util.Stack;

/**
 * Created by SRIN on 7/24/2017.
 */
public class TestStack {

    public static void main(String[] args) throws Exception {

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
