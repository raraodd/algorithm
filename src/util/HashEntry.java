package util;

/**
 * Created by SRIN on 2/27/2017.
 */
public class HashEntry<Key, Value> {
    public Key key;
    public Value value;

    HashEntry(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public Key getKey() {
        return  key;
    }

    public Value getValue() {
        return value;
    }
}
