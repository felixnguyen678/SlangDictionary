package Model;

/*
 An AVL tree is a sorted binary tree in which the heights of two subtrees at any
 given node differ by at most 1
 Space:
  Best case: O(N)
  Worst case: O(N)
 Time complexity:
  Building binary tree:
   Best case: O(logN)
   Worst case: O(logN)
   Best case:
    Searching element: O(logN)
    Adding element: O(logN)   -> to add, find new element position where belongs to
    Delete element: O(logN)   -> to add, find new element position where belongs to
   Worst case:
    Searching element: O(logN) -> every element sorted order
    Adding element: O(logN)
    Delete element: O(logN)
 */
@SuppressWarnings("Duplicates")
public class AVLTree<E extends Comparable<E>> {
    public AVLNode<E> root;

    private int height(AVLNode<E> n) {
        return n == null ? -1 : n.height;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    private int getBalance(AVLNode<E> n) {
        return n == null ? 0 : height(n.right) - height(n.left);
    }
    AVLNode<E> rotateRight(AVLNode<E> y) {
        AVLNode<E> x = y.left;
        AVLNode<E> z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }
    AVLNode<E> rotateLeft(AVLNode<E> y) {
        AVLNode<E> x = y.right;
        AVLNode<E> z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }
    AVLNode<E> rebalance(AVLNode<E> z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.right.right) > height(z.right.left)) {
                z = rotateLeft(z);
            } else {
                z.right = rotateRight(z.right);
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.left.left) > height(z.left.right))
                z = rotateRight(z);
            else {
                z.left = rotateLeft(z.left);
                z = rotateRight(z);
            }
        }
        return z;
    }
    AVLNode<E> insertHelper(AVLNode<E> node, E elem) {
        if (node == null) {
            return new AVLNode<E>(elem);
        } else if (node.elem.compareTo(elem) > 0) {
            node.left = insertHelper(node.left, elem);
        } else if (node.elem.compareTo(elem) < 0) {
            node.right = insertHelper(node.right, elem);
        } else {
            System.out.println("duplicated key");
            return node;
        }
        return rebalance(node);
    }

    AVLNode<E> delete(AVLNode<E> node, E elem) {
        if (node == null) {
            return node;
        } else if (node.elem.compareTo(elem) > 0) {
            node.left = delete(node.left, elem);
        } else if (node.elem.compareTo(elem) < 0) {
            node.right = delete(node.right, elem);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                AVLNode<E> mostLeftChild = mostLeftChild(node.right);
                node.elem = mostLeftChild.elem;
                node.right = delete(node.right, node.elem);
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        return node;
    }



    void updateHeight(AVLNode<E> node){
        node.height = 1 + max(height(node.left), height(node.right));
    }

    // insert element
    public void insert(E elem) {
        if (elem == null) throw new IllegalArgumentException();
        root = insertHelper(root, elem);
    }



    private AVLNode<E> mostLeftChild(AVLNode<E> node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    // search element
    public E find(E elem) {
        if (elem == null) throw new IllegalArgumentException();

        return findHelper(root, elem).elem;
    }

    private AVLNode<E> findHelper(AVLNode<E> n, E elem) {
        // usual search
        while (n != null) {
            // pass right subtree as new tree
            if (n.elem.compareTo(elem) > 0)
                n = n.left;

                // pass left subtree as new tree
            else if (n.elem.compareTo(elem) < 0)
                n = n.right;
            else
                return n; // if the key is found return 1
        }
        return new AVLNode<>(null);
    }



    private class AVLNode<E> {
        private int height;
        private E elem;
        private AVLNode<E> left, right;

        public AVLNode(E elem) {
            this.elem = elem;
        }
    }
}
