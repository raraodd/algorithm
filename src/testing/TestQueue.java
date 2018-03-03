package testing;

import util.Queue;

/**
 * Created by SRIN on 7/24/2017.
 */
public class TestQueue {

    public static void main(String[] args) throws Exception {

        Integer[] arr = {1, 2, 3, 1, 2, 4, 5, 6, 7, 8, 10};

        Queue<Integer> queue = new Queue<>();

        for (int i=0; i<arr.length; i++) {
            queue.enqueue(arr[i]);
        }

        queue.print();
        queue.dequeue();
        queue.print();

    }
}
