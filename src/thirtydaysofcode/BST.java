package thirtydaysofcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by SRIN on 1/26/2017.
 */
public class BST {

    static int height;


    static class Node {
        Node left, right;
        int data;

        public Node(int data){
            this.data = data;
            this.left = this.right = null;
        }
    }

    public static void levelOrder(Node root){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            Node curr = queue.remove();
            System.out.print(curr.data+" ");
            if(curr.left != null) queue.add(curr.left);
            if(curr.right != null) queue.add(curr.right);
        }
    }


    public static int getHeight(Node root){
        int heightLeft = 0;
        int heightRight = 0;

//        System.out.print(root.data + " ");

        if (root.left != null) {
            heightLeft = getHeight(root.left) + 1;
        }
        if (root.right != null) {
            heightRight = getHeight(root.right) + 1;
        }

        return (heightLeft > heightRight ? heightLeft : heightRight);
    }


    public static Node insert(Node root, int data){
        if(root == null){
            return new Node(data);
        }
        else{
            Node curr;
            if(data <= root.data){
                curr = insert(root.left, data);
                root.left = curr;
            } else {
                curr = insert(root.right, data);
                root.right = curr;
            }
            return root;
        }
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int T = sc. nextInt();
        Node root = null;

        while (T-->0){
            int data = sc.nextInt();
            root = insert(root, data);
        }

        levelOrder(root);

        height = getHeight(root);
        System.out.println("\nHEIGHT: "+ height);
    }
}
