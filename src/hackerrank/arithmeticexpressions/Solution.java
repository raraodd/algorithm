package hackerrank.arithmeticexpressions;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class Solution {

    private static boolean solve(boolean [][] dp, long sumSoFar, int currentElement, long [] elements, List<Character> elementsUsed) {
        long modVal = 101L;
        sumSoFar %= modVal;

        if (currentElement == elements.length && sumSoFar == 0) {
            return true;
        } else if (currentElement == elements.length) {
            return false;
        }

        if (sumSoFar == 0) {
            elementsUsed.add('*');
            return solve(dp, sumSoFar, currentElement + 1, elements, elementsUsed);
        }

        if (dp[(int)sumSoFar + 101][currentElement]) {return false;}

        long moddedElement = (elements[currentElement]) % modVal;

        elementsUsed.add('+');
        if (solve(dp, (sumSoFar + moddedElement) % modVal, currentElement + 1, elements, elementsUsed)) {
            return true;
        }
        elementsUsed.remove(elementsUsed.size() - 1);

        elementsUsed.add('-');
        if (solve (dp, (sumSoFar - moddedElement) % modVal, currentElement + 1, elements, elementsUsed)) {
            return true;
        }
        elementsUsed.remove(elementsUsed.size() - 1);

        elementsUsed.add('*');
        if (solve(dp, (sumSoFar * moddedElement) % modVal, currentElement + 1, elements, elementsUsed)) {
            return true;
        }
        elementsUsed.remove(elementsUsed.size() - 1);

        dp[(int)sumSoFar + 101][currentElement] = true;

        return false;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new java.io.FileInputStream("/Users/wendyp/Documents/9_Project/Algorithms/src/hackerrank/arithmeticexpressions/input25.txt"));

        Scanner scn = new Scanner(System.in);

        int numberOfElements = scn.nextInt();
        long [] elements = new long [numberOfElements];

        for (int i = 0; i < numberOfElements; i++) {
            elements[i] = scn.nextLong();
        }

        List<Character> elementsUsed = new ArrayList<>();
        boolean [][] dp = new boolean[202][numberOfElements];
        solve(dp, elements[0], 1, elements, elementsUsed);

        int i = 1;
        System.out.print(elements[0]);
        for (Character elementUsed : elementsUsed) {
            System.out.print(elementUsed + "" + elements[i++]);
        }

        scn.close();
    }
}