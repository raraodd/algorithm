package util;

import thirtydaysofcode.LL;

public class HashTable {

    public static class Data {
        String key;
        int value;

        public Data(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    static final int HASH_SIZE = 2;

    static LL[] table;

    public static int hash (String s) {
        long hash = 5381;

        int i = 0;
        while (i < s.length() && s.charAt(i) != '\0') {
            hash = ((hash << 5) + hash) + s.charAt(i);
            i++;
        }
        return (int) (hash % HASH_SIZE);
    }

    public HashTable() {
        table = new LL[HASH_SIZE];
    }

    // insert
    public static void put(String key, int value) {
        int hash_key = hash(key);
        LL list = table[hash_key];
        Data data = new Data(key, value);
        if(list == null) {
            list = new LL();
        }
        list.insertNode(data);
        table[hash_key] = list;
    }

    // update
    public static void replace(String key, int value) {
        int hash_key = hash(key);
        LL list = table[hash_key];
        list.insertNode(value);
        table[hash_key] = list;
    }

    // get
    public static int get(String key) {
        LL list = table[hash(key)];

        return ;
    }

    public static void main(String args[])
    {
        System.out.println(hash("Wendyyase"));

    }
}
