package Model;

import java.util.Random;

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
    private AVLNode<E> root;

    public E getRoot(){
        return root.elem;
    }
    private int height(AVLNode<E> n) {
        return n == null ? -1 : n.height;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }
    private int getBalance(AVLNode<E> n) {
        return n == null ? 0 : height(n.right) - height(n.left);
    }
    private void updateHeight(AVLNode<E> node){
        node.height = 1 + max(height(node.left), height(node.right));
    }
    private AVLNode<E> rotateRight(AVLNode<E> y) {
        AVLNode<E> x = y.left;
        AVLNode<E> z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }
    private AVLNode<E> rotateLeft(AVLNode<E> y) {
        AVLNode<E> x = y.right;
        AVLNode<E> z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }
    private AVLNode<E> rebalance(AVLNode<E> z) {
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
    private AVLNode<E> insertHelper(AVLNode<E> node, E elem) {
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
    private AVLNode<E> mostLeftChild(AVLNode<E> node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
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

    private AVLNode<E> deleteHelper(AVLNode<E> node, E elem) {
        if (node == null) {
            return node;
        } else if (node.elem.compareTo(elem) > 0) {
            node.left = deleteHelper(node.left, elem);
        } else if (node.elem.compareTo(elem) < 0) {
            node.right = deleteHelper(node.right, elem);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                AVLNode<E> mostLeftChild = mostLeftChild(node.right);
                node.elem = mostLeftChild.elem;
                node.right = deleteHelper(node.right, node.elem);
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        return node;
    }


    // insert element
    public void insert(E elem) {
        if (elem == null) throw new IllegalArgumentException();
        root = insertHelper(root, elem);
    }


    // search element
    public E find(E elem) {
        if (elem == null) throw new IllegalArgumentException();

        return findHelper(root, elem).elem;
    }

    public void delete(E elem){
        root = deleteHelper(root, elem);
    }

    public AVLNode<E> randomNodeHelper(AVLNode<E> node){
        Random rn = new Random();
        int randInt = rn.nextInt(100);
        if(node == null)
            return randomNodeHelper(root);
        if(randInt %  7 == 0 )
            return node;
        if(randInt % 2 == 0)
            return randomNodeHelper(node.left);
        else return randomNodeHelper(node.right);
    }


    public E randomNode(){
        return randomNodeHelper(root).elem;
    }

    private class AVLNode<E> {
        public int height;
        public E elem;
        public AVLNode<E> left, right;

        public AVLNode(E elem) {
            this.elem = elem;
        }
    }

}
