package util;

/**
 * Created by SRIN on 2/27/2017.
 */
public class Vect<Item> {

    private Item items[];
    private int size;
    private int temp_size;
    private int SIZE_VECTOR = 100000;
    private int index = 0;

    public Vect() {
        temp_size = 1000;
        items = (Item[]) new Object[temp_size];
    }

    public void addElement(Item node) {
        size++;
        if(index >= temp_size) {
            Item newItem[] = (Item[]) new Object[temp_size+SIZE_VECTOR];
            System.arraycopy(items, 0, newItem, 0, temp_size);
            temp_size+=SIZE_VECTOR;
        }
        items[index] = node;
        index++;
    }

    public Item elementAt(int index) {
        if(index < 0 || index >= size) {
            return null;
        }
        Item item = items[index];
        return item;
    }

    public Item removeElement(int index) {
        if(index < 0 || index > size) {
            return null;
        }
        Item item = items[index];
        System.arraycopy(items, index+1, items, index, items.length-1-index);
        items[items.length-1] = null;
        size--;
        return item;
    }

    public int size() {
        return this.size;
    }
}
