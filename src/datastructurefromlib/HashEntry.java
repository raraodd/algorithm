// very simple hashtable implementing array
// if there is collision, it will find next empty array to put data
// WARNING : bad performance if array is > 70% filled
// WARNING : no handling if array is full, this hash algorithm will loop forever to find empty slot!
package datastructurefromlib;

public class HashEntry {
    private int key;
    private int value;

    HashEntry(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }
}