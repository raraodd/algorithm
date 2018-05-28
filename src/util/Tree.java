package util;

public class Tree <Key> {

    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    public static Node insert (Node head, int data) {
        Node p = new Node(data);

        if(head == null) {
            head = p;
        } else {
            Node node = head;
            while (node != null) {
                if(node.data >= data) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            }
            node = p;
        }

        return head;
    }

    public static void main(String args[])
    {
        Node head = null;

        int T[] = new int[]{1,5,2,7,0};

        int i = 0;
        while (i < T.length) {
            head = insert(head, T[i]);
            i++;
        }
    }
}
