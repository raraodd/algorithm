package thirtydaysofcode;

import java.util.Scanner;

/**
 * Created by SRIN on 2/7/2017.
 */
public class ArrayLeftRotation {

    public static int n;

    public static void printArr(int start, int end, int a[]){
        int i = start;
        while(i!=end){
            System.out.print(a[i] + " ");

            if(i == n-1){
                i = 0;
                if(end == 0) {
                    System.out.print(a[end] + " ");
                    break;
                }
            }
            else if (i+1 == end) {
                System.out.print(a[i+1] + " ");
                break;
            }
            else {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        int k = in.nextInt(); // rotate
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }

        if(k == n){
            for (int i = 0; i < n; i++){
                System.out.print(a[i] + " ");
            }
        } else if(k < n){
            printArr(k, k-1, a);
        } else {
            int mod = k % n;
            printArr(mod, mod-1, a);
        }
    }

}
