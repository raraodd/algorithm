package thirtydaysofcode; /**
 * Created by SRIN on 1/26/2017.
 */
import java.util.*;

public class Sorting {

    public static int swap = 0;
    public static int[] arr;

    private static void printArray(String s, int[] x) {
        System.out.print(s + " Array: ");
        for(int i : x){
            System.out.print(i + " ");
        }
        System.out.println();
    }


    public static void bubbleSort() {
        printArray("Initial", arr);

        int endPosition = arr.length - 1;
        int swapPosition;

        while( endPosition > 0 ) {
            swapPosition = 0;

            for(int i = 0; i < endPosition; i++) {

                if( arr[i] > arr[i + 1] ){
                    // Swap elements 'i' and 'i + 1':
                    int tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;

                    swapPosition = i;
                    swap++;
                } // end if

//                printArray("Current", arr);
            } // end for

            endPosition = swapPosition;
        } // end while

//        printArray("Sorted", arr);
    } // end bubbleSort

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named thirtydaysofcode.Sorting. */

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        arr = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        bubbleSort();

        System.out.println("Array is sorted in " + swap + " swaps.");
        System.out.println("First element: " + arr[0]);
        System.out.println("Last element: " + arr[n-1]);
    }
}
