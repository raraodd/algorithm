package testing;

import util.HashMap;

/**
 * Created by SRIN on 7/18/2017.
 */
public class TestMap {

    public static void main(String[] args) {

        Integer[] arr = {1, 2, 3, 1, 2};
        String[] val = {"1val", "2val", "3val", "4val", "5val"};

        HashMap<Integer, String> map = new HashMap<>();

        for(int i=0; i<5; i++) {
            map.put(arr[i], val[i]);
            System.out.println("--get " + map.get(arr[i]));
        }
    }
}
