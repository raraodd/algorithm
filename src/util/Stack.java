package util;

/**
 * Created by SRIN on 7/24/2017.
 */
public class Stack<Item> {

    private Item[] items;
    private int top = 0;
    private int stack_size = 10;

    public Stack() {
        items = (Item[]) new Object[stack_size];
        top = 0;
    }

    public boolean isEmpty() {
        if (top == 0) return true;
        return false;
    }

    public void push(Item item) {
        items[top] = item;
        top++;
    }

    public Item pop() throws Exception {
        if(isEmpty()) throw new Exception("underflow");
        top = top - 1;
        return items[top+1];
    }

    public void print() {
        for (int i = 0; i < top; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }
}
