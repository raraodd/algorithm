package util;

/**
 * Created by SRIN on 7/19/2017.
 */
public class RedBlackTree<Key extends Comparable, Value> {

    final int RED = 1;
    final int BLACK = 2;

    class Node {
        Key key;
        Value value;
        Node left, right, parent;
        int color;

        Node (Key key, Value value) {
            this.key = key;
            this.value = value;
            left = right = parent = null;
            color = RED;
        }

        Node() {
            left =  right = parent = null;
            color = BLACK;
        }
    }

    Node root, x, y, now;
    Node NIL;

    RedBlackTree() {
        NIL = new Node();
        root = NIL;
    }

    public void insert(Key key, Value value) {
        now = new Node(key, value);
        now.left = now.right = now.parent = NIL;

        x = root;
        y = NIL;

        while (x != NIL) {
            y = x;
            if(now.key.compareTo(x.key) < 0) {
                x = x.left;
            }
        }
    }
}
