package hackerrank.arithmeticexpressions;


import java.io.*;

public class CSV {
    public static void main(String[]args) throws FileNotFoundException{
        String filename = "test.csv";
        StringBuilder sb = new StringBuilder();
        try
        {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
            StringBuilder sb = new StringBuilder();
            sb.app

            out.print("the text"); out.println();
            //more code
            out.print("more text"); out.println();
            //more code
            out.print(sb.toString());
            out.close();
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
            System.out.println(e.getMessage());
        }

        System.out.println("done!");
    }
}