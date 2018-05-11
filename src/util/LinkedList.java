package util;

import java.util.Scanner;

public class LinkedList {

    public static class Node {
        int data;
        Node next;

        Node (int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node insert(Node head, int data) {
        Node p = new Node(data);

        if (head == null) {
            head = p;
        } else if (head.next == null) {
            head.next = p;
        } else {
            Node start = head;
            while (start.next != null) {
                start = start.next;
            }
            start.next = p;
        }

        return head;
    }

    public static Node search(Node head, int data){
        Node node = head;

        if(node == null) {
            return null;
        } else {
            while (node != null) {
                if(node.data == data) {
                    return node;
                }
                node = node.next;
            }
        }
        return head;
    }

    public static Node delete(Node head, int data) {
        Node node = head;
        Node prev = head;

        if (node == null) {
            head = null;
        } else if (node.next == null) {
            if(node.data == data) {
                return null;
            }
        } else {
            while (node != null) {
                if(node.data == data) {
                    prev.next = node.next;
                } else {
                    prev = node;
                }
                node = node.next;
            }
        }
        return head;
    }

    public static void printNode(Node head) {
        Node node = head;

        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }

        System.out.println();
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

        head = delete(head,2);

        System.out.println("search " + search(head, 7).data);

        printNode(head);

    }
}
