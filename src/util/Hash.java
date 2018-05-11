package util;

public class Hash {

    public static long hash (String s) {
        long hash = 5381;

        int i = 0;
        while (i < s.length() && s.charAt(i) != '\0') {
            hash = ((hash << 5) + hash) + s.charAt(i);
            i++;
        }
        return (int) (hash%1000);
    }

    public static void main(String args[])
    {
        System.out.println(hash("wendy"));

    }
}
