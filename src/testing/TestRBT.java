package testing;

import util.RedBlackTree;

/**
 * Created by SRIN on 7/21/2017.
 */
public class TestRBT {

    public static void main(String[] args) {

        RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();

        rbt.insert(1,2);
        rbt.insert(2,3);
        rbt.insert(3,6);
        rbt.insert(4,7);
        rbt.insert(5,5);
        rbt.insert(6,2);
        rbt.insert(7,8);

//        rbt.delete_node(rbt.search(3));

//        rbt.print();
//        rbt.print(rbt.search(10));
//        rbt.print(rbt.search(2));
//        rbt.print(rbt.search(3));
//        rbt.print(rbt.search(4));
//        rbt.print(rbt.search(5));
//        rbt.print(rbt.search(6));
//        rbt.print(rbt.search(7));

    }
}
