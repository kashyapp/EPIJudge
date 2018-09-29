package epi.kashyap;

import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Deque;

public class LevelOrder {
    static class Node {
        private final int value;
        private final Node left;
        private final Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    static Node n(int value) {
        return n(value, null, null);
    }

    static Node n(int value, Node left, Node right) {
        return new Node(value, left, right);
    }

    static class Entry {
        private final Node n;
        private final boolean isLast;

        public Entry(Node root, boolean b) {
            n = root;
            isLast = b;
        }
    }


    static void levelOrder(Node root, PrintStream out) {
        if (root == null) return;

        Deque<Entry> q = new ArrayDeque<Entry>();
        q.add(new Entry(root, false));
        q.add(new Entry(null, true));

        while (!q.isEmpty()) {
            Entry e = q.remove();

            if (e.isLast) {
                out.println("");
                if (!q.isEmpty()) {
                    q.add(new Entry(null, true));
                }
            } else {
                Node node = e.n;
                out.print(node.value);
                out.print(" ");

// ->       synchronized(node) {
                if (node.left != null) {
                    q.add(new Entry(node.left, false));
                }
// <-       }

                if (node.right != null) {
                    q.add(new Entry(node.right, false));
                }
            }
        }
    }

    public static void main(String[] args) {
        Node tree = n(1,
                n(2,
                        n(5),
                        n(9,
                                null,
                                n(6))),
                n(4,
                        null,
                        n(3))
        );
        levelOrder(tree, System.out);
    }
}
