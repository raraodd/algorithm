package thirtydaysofcode;

public class Main {

    private static final int INVALID = -9999999;

    public static int compare(char[] x1, char[] x2){
        int i = 0;
        int res = INVALID;
        if(x1.length < x2.length) {
            while (i < x1.length && x1[i] - x2[i] == 0){
                i++;
            }
            if(i == x1.length && res == INVALID){
                return x1.length - x2.length;
            } else {
                return x1[i] - x2[i];
            }
        } else if (x2.length < x1.length) {
            while (i < x2.length && x1[i] - x2[i] == 0){
                i++;
            }
            if(i == x2.length && res == INVALID){
                return x1.length - x2.length;
            } else {
                return x1[i] - x2[i];
            }
        }
        for(i = 0; i < x1.length; i++){
            if(x1[i] - x2[i] != 0) {
                return x1[i] - x2[i];
            }
        }
        return 0;
    }

    public static boolean equals;

    public static void main(String[] args) {

        String a = "111124 ";
        String b = new String("111124 ");


        System.out.println(a.compareTo(b));
        System.out.println(compare(a.toCharArray(), b.toCharArray()));
        System.out.println(a.toString() == b);

    }
}
