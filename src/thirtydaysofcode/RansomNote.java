package thirtydaysofcode;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by wendy on 6/5/17.
 */
public class RansomNote {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();

        HashMap<String, Integer> mapMagazine = new HashMap<>();
//        System.out.println();

        String magazine[] = new String[m];
        for(int magazine_i=0; magazine_i < m; magazine_i++){
            magazine[magazine_i] = in.next();
            String key = magazine[magazine_i];
            Integer oldValue = mapMagazine.get(key);
            if(oldValue == null) {
                mapMagazine.put(key, 1);
            } else {
                mapMagazine.replace(key, oldValue+1);
            }
//            if(key.equals("efwph")) System.out.println(key + " " + mapMagazine.get(key));
        }


        String ransom[] = new String[n];
        for(int ransom_i=0; ransom_i < n; ransom_i++){
            ransom[ransom_i] = in.next();
        }

//        System.out.println(magazine.length);

        boolean isRansomNoteReplicated = true;
        for(int ransom_i=0; ransom_i < n; ransom_i++){
            if(!mapMagazine.containsKey(ransom[ransom_i])) {
                System.out.println("No");
                isRansomNoteReplicated = false;
                break;
            }
            else {
                int oldValue = mapMagazine.get(ransom[ransom_i]);
                if(oldValue == 1) {
                    mapMagazine.remove(ransom[ransom_i]);
                } else {
                    mapMagazine.replace(ransom[ransom_i], oldValue-1);
                }
            }
        }
        if(isRansomNoteReplicated) {
            System.out.println("Yes");
        }
    }

}
