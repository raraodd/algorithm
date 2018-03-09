package util;

import java.util.Scanner;

/**
 * Created by SRIN on 2/28/2017.
 */
public class StringUtil {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine();
        String s2 = sc.nextLine();

        System.out.println(s1 + " " + s2 + " " + s1.compareTo(s2));
        System.out.println(s1 + " " + s2 + " " + stringCompareTo(s1, s2));

        char a[] = s1.toCharArray();
        char b[] = s2.toCharArray();

        for(int i = 0; i<a.length; i++) {
            System.out.println(a[i] + " " + (int) a[i]);
            System.out.println(b[i] + " " + (int) b[i]);
            System.out.println("---");
        }

    }

    public static int stringCompareTo(String s1, String s2) {
        char a[] = s1.toCharArray();
        char b[] = s2.toCharArray();

        int len = a.length < b.length ? a.length : b.length;

        for(int i = 0; i < len; i++) {
            int charDiff = (int) a[i] - (int) b[i];
            if(charDiff == 0 && i != len-1) continue;
            if(charDiff != 0) return charDiff;
        }
        return a.length - b.length;
    }
}
