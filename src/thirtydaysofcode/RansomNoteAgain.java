package thirtydaysofcode;

import util.HashTable;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by wendy on 9/15/18.
 */
public class RansomNoteAgain {

    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {
        HashTable mapWords = new HashTable();

        for (int i = 0; i < magazine.length; i++) {
            String key = magazine[i];
            Integer wordCount = mapWords.get(key);
            if(wordCount == null) {
                mapWords.put(key, 1);
            } else {
                mapWords.replace(key, wordCount+=1);
            }
        }

        for (int i = 0; i < note.length; i++) {
            String key = note[i];
            Integer wordCount = mapWords.get(key);
            System.out.print(key + " " + wordCount);
            if(wordCount == null || wordCount == 0) {
                System.out.print("No");
                return;
            } else {
                mapWords.replace(key, wordCount-1);
            }
        }
        System.out.print("Yes");
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);

        scanner.close();
    }
}
