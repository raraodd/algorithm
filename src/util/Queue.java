package util;

/**
 * Created by SRIN on 7/24/2017.
 */
public class Queue<Item> {
    private Item[] items;
    private int head, tail = 0;
    private int queue_size = 10;

    public Queue() {
        items = (Item[]) new Object[queue_size];
        head = tail = 0;
    }

    public boolean isEmpty() {
        if(head == tail) return true;
        return false;
    }

    public void enqueue(Item item) {
        items[tail] = item;
        if(tail == queue_size-1)
            tail = 0;
        else
            tail++;
    }

    public Item dequeue() {
        Item x = items[head];
        items[head] = null;
        if(head == queue_size-1)
            head = 0;
        else
            head++;
        return x;
    }

    public void print() {
        for(int i=0; i<queue_size; i++) {
            System.out.println("position: " + i +" value: " + items[i]);
        }
        System.out.println("head: " + head + " tail: " + tail);
    }
}
