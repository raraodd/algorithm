package util;

import static java.lang.Integer.max;

/**
 * Created by SRIN on 7/19/2017.
 */
public class RedBlackTree<Value> {

    final int RED = 1;
    final int BLACK = 2;

    public class Node {
        Integer key;
        Value value;
        Node left, right, parent;
        int color;

        Node (Integer key, Value value) {
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

    public RedBlackTree() {
        NIL = new Node();
        root = NIL;
    }

    public void insert(Integer key, Value value) {
        now = new Node(key, value);
        now.left = now.right = now.parent = NIL;

        x = root;
        y = NIL;

        while (x != NIL) {
            y = x;
            if(now.key.compareTo(x.key) < 0) {
                x = x.left;
            } else if(now.key.compareTo(x.key) > 0) {
                x = x.right;
            } else {
                x.value = value;
                return;
            }
        }

        if(y == NIL) {
            root = now;
        } else if (now.key.compareTo(y.key) < 0) {
            y.left = now;
        } else {
            y.right = now;
        }

        now.parent = y;

        insert_fix(now);
    }

    public void print() {
        print(root);
    }

    public void  clear() {
        clear(root);
        root = NIL;
    }

    public Node search (Integer key) {
        x = root;

        while (x != NIL) {
            if ((key - x.key) < 0) {
                x = x.left;
            } else if ((key - x.key) > 0) {
                x = x.right;
            }
            else break;
        }

        return x;
    }

    public Value get (Integer key) {
        x = root;

        while (x != NIL) {
            if ((key - x.key) < 0) {
                x = x.left;
            } else if ((key - x.key) > 0) {
                x = x.right;
            }
            else break;
        }

        return x.value;
    }

    public int depth() {
        return depth(root);
    }

    public void delete_node(Node z) {
        Node y = z;
        int y_original_color = y.color;
        if(z.left == NIL) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == NIL) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = tree_minimum(z.right);
            y_original_color = y.color;
            x = y.right;
            if(y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if(y_original_color == BLACK) delete_fixup(x);
    }

    private Node tree_minimum(Node node) {
        while (node.left != NIL) {
            node = node.left;
        }
        return node;
    }

    private void transplant(Node u, Node v) {
        if(u.parent == NIL) root = v;
        else if (u == u.parent.left) u.parent.left = v;
        else u.parent.right = v;
        v.parent = u.parent;
    }

    private void delete_fixup(Node x) {
        while(x != root && x.color == BLACK) {
            if(x == x.parent.left) {
                Node w = x.parent.right;
                if(w.color == RED) {
                    w.color = BLACK;
                    x.parent.color = RED;
                    left_rotate(x.parent);
                    w = x.parent.right;
                }
                if(w.left.color == BLACK && w.right.color == BLACK) {
                    w.color = RED;
                    x = x.parent;
                } else if (w.right.color == BLACK) {
                    w.left.color = BLACK;
                    w.color = RED;
                    right_rotate(w);
                    w = x.parent.right;
                } else {
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.right.color = BLACK;
                    left_rotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = BLACK;
    }

    private int depth(Node n) {
        if(n == NIL) return 0;
        return 1 + max(depth(n.left), depth(n.right));
    }

    public void print (Node n) {
        if (n == NIL) return;
        print(n.left);
        System.out.println("Key: " + n.key + " value: " + n.value + " Color: " + n.color);
        print(n.right);
    }

    private void clear(Node n) {
        if(n == NIL) return;
        clear(n.left);
        clear(n.right);
        n = null;
    }

    private void left_rotate(Node x) {
        y = x.right;

        x.right = y.left;
        if(x.right != NIL) {
            x.right.parent = x;
        }

        y.parent = x.parent;
        if(x.parent == NIL) {
            root = y;
        }
        else if (x.parent.left == x) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    private void right_rotate(Node y) {
        x = y.left;

        y.left = x.right;
        if(y.left != NIL) {
            y.left.parent = y;
        }

        x.parent = y.parent;
        if(y.parent == NIL) {
            root = x;
        } else if (y.parent.left == y) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }

        x.right = y;
        y.parent = x;
    }

    private void insert_fix(Node x) {
        while (x.parent.color == RED) {
            if(x.parent.parent.left == x.parent) {
                y = x.parent.parent.right;
                if(y.color == RED) {
                    y.color = BLACK;
                    x.parent.color = BLACK;
                    x.parent.parent.color = RED;
                    x = x.parent.parent;
                } else {
                    if(x.parent.right == x) {
                        x = x.parent;
                        left_rotate(x);
                    }
                    x.parent.color = BLACK;
                    x.parent.parent.color = RED;
                    right_rotate(x.parent.parent);
                }
            } else {
                y = x.parent.parent.left;
                if(y.color == RED) {
                    y.color = BLACK;
                    x.parent.color = BLACK;
                    x.parent.parent.color = RED;
                    x = x.parent.parent;
                } else {
                    if(x.parent.left == x) {
                        x = x.parent;
                        right_rotate(x);
                    }
                    x.parent.color = BLACK;
                    x.parent.parent.color = RED;
                    left_rotate(x.parent.parent);
                }
            }
        }
        root.color = BLACK;
    }

}
