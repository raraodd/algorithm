package util;

/**
 * Created by SRIN on 2/27/2017.
 */
public class HashMap<Key, Value> {
    private HashEntry[] table;
    private int HASH_SIZE = 5831;
    private int currentSize;
    private int count;

    public HashMap() {
        this.currentSize = HASH_SIZE;
        table = new HashEntry[currentSize];
    }

    public HashEntry[] getHashValue() {
        HashEntry entries[] = new HashEntry[count];
        int index = 0;
        for(int i=0; i < this.currentSize; i++){
            HashEntry<Key, Value> entry = table[i];
            if(entry != null) {
                entries[index] = entry;
                index++;
            }
        }
        return entries;
    }

    public Value get(Key key) {
        int hash = (key.hashCode() & 0x7fffffff) % currentSize;
        if (table[hash] == null) {
            return null;
        }
        return (Value)table[hash].getValue();
    }

    private void rehash() {
        HashEntry newHash[] = new HashEntry[this.currentSize+HASH_SIZE];
        int newSize = this.currentSize + HASH_SIZE;
        for(int i=0; i<this.currentSize; i++) {
            HashEntry<Key, Value> entry = table[i];
            if(entry != null) {
                int hash = (entry.key.hashCode() & 0x7fffffff) % newSize;
                newHash[hash] = entry;
            }
            this.currentSize = newSize;
            this.table = newHash;
        }
    }

    public void put(Key key, Value value) {
        int hash = (key.hashCode() & 0x7fffffff) % currentSize;
        if(count >= this.currentSize) {
            while (this.table[hash] != null) {
                rehash();
                hash = (key.hashCode() & 0x7fffffff) % currentSize;
            }
        }
        if(table[hash] == null) {
            count++;
        }
        table[hash] = new HashEntry<Key, Value>(key, value);
    }

    public void remove(Key key) {
        int hash = (key.hashCode() & 0x7fffffff) % currentSize;
        table[hash] = null;
        count--;
    }

    public int size() {
        return count;
    }
}
