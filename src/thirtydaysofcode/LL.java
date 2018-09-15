package thirtydaysofcode;

import util.HashTable;

import java.util.Scanner;

/**
 * Created by wendy on 9/15/18.
 */
public class LL {

    static class Node {
        public HashTable.Data data;
        public Node next;

        public Node (HashTable.Data data) {
            this.data = data;
            this.next = null;
        }
    }

    public Node head;
    public Node tail;

    public LL() {
        this.head = null;
        this.tail = null;
    }

    public void insertNode(HashTable.Data data) {
        // 1. create a new node
        Node node = new Node(data);

        // 2. if head null insert to head
        // 3. else insert to tail
        if(this.head == null) {
            this.head = node;
        } else {
            this.tail.next = node;
        }

        this.tail = node;
    }

    public Node insertNodeAtPosition(Node head, HashTable.Data data, int position) {
        Node node = new Node(data);

        if (position == 0) {
            node.next = head;
            return node;
        } else {
            Node curr = head;
            int currPos = 0;
            // tergantung position mulai dr berapa
            while (currPos != position - 1) {
                curr = curr.next;
                currPos += 1;
            }
            node.next = curr.next;
            curr.next = node;
            return head;
        }
    }

    public Node deleteNode(Node head, int position) {

        if (position == 0) {
            return head.next;
        } else {
            Node prev = head;
            Node curr = head.next;
            int currPos = 1;
            while (currPos != position) {
                prev = curr;
                curr = curr.next;
                currPos += 1;
            }
            prev.next = curr.next;
            return head;
        }


    }

    static void printLinkedList(Node head) {
        Node node = head;
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        LL llist = new LL();
//
//        int llistCount = scanner.nextInt();
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//        for (int i = 0; i < llistCount; i++) {
//            int llistItem = scanner.nextInt();
//            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//            llist.insertNode(llistItem);
//        }
//
//        int position = scanner.nextInt();
//        int insertedItem = scanner.nextInt();
//        int deletedPosition = scanner.nextInt();
//
//        llist.head = llist.insertNodeAtPosition(llist.head, insertedItem, position);
//        llist.head = llist.deleteNode(llist.head, deletedPosition);
//
//        printLinkedList(llist.head);
//
//        scanner.close();
    }


}
