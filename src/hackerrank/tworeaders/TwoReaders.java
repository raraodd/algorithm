package hackerrank.tworeaders;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by wendy on 03/03/18.
 */
public class TwoReaders {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        HashMap<String, Boolean> mapBooks = new HashMap<>();

        for(int i = 0; i < N; i++) {
            String book = sc.next().toLowerCase();
//            System.out.println(book);
            mapBooks.put(book, true);
        }

        int count = 0;

        for(int i = 0; i < N; i++) {
            String book = sc.next().toLowerCase();
//            System.out.println(book + " " + mapBooks.get(book));

            if(mapBooks.get(book) != null) count += 1;
        }

        System.out.println(count) ;

    }
}
