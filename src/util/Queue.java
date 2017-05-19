package util;

/**
 * Created by SRIN on 2/28/2017.
 */
public class Queue<Item> {
    private Vect<Item> items;
    private int index = 0;

    public Queue(Item item) {
        items.addElement(item);
        index++;
    }

    public Item get() {
        if(isEmpty()) return null;
        Item item = items.removeElement(index-1);
        index--;
        return item;
    }

    public boolean isEmpty() {
        return index == 0;
    }
}
