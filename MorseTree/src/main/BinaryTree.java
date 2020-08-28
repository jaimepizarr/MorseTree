package main;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class BinaryTree<E> {
    protected TreeNode<E> root;
    protected int size = 0;

    /** Create a default binary tree */
    public BinaryTree() {
    }

    /** Create a binary tree from an array of objects */
    public boolean add(E child, E parent, String position){
        TreeNode<E> nc = searchNode(child);
        if(nc!= null)
            return false;
        nc= new TreeNode<>(child);
        if(parent == null && root == null&&position.equals("I")){
            root = nc;
            return true;
        }
        TreeNode<E> np = searchNode(parent);
        if(np!=null){
            if(np.left==null&& position.equals("L") ){
                np.left=nc;
                return true;
            }
            else if(np.right==null&& position.equals("R")){
                np.right=nc;
                return true;
            }
        }
        return false;
    }
    private TreeNode<E> searchNode(E data){
        return searchNode(data,root);
    }
    
       private TreeNode<E> searchNode(E data, TreeNode<E> n){
        if(n==null) return n;
        else if (n.data.equals(data)) return n;
        else{
            TreeNode<E> nl = searchNode(data,n.left);
            if(nl!=null) return nl;
            return searchNode(data,n.right);
            
        }
    }
    
    

    /*
    @Override /** Insert element o into the binary tree
    

    public boolean insert(E e) {
        if (root == null)
            root = createNewNode(e); // Create a new root
            //root = new TreeNode<>(e);	// Question:  Why not do it this way?  It would work.
        else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null)
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                }
                else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                }
                else
                    return false; // Duplicate node not inserted

            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0)
                parent.left = createNewNode(e);
                //parent.left = new TreeNode<>(e);
            else
                parent.right = createNewNode(e);
        }

        size++;
        return true; // Element inserted successfully
    }
*/
        
    protected TreeNode<E> createNewNode(E e) {  // Factory method design pattern
        return new TreeNode<>(e);
    }

 
    
    
    public int height() {
    	return height(root);
    }
    
    protected int height(TreeNode<E> root) {
    	if(root == null)
    		return 0;
    	return 1 + Math.max(height(root.left), height(root.right));
    }


    /** This inner class is static, because it does not access
     any instance members defined in its outer class */
    public static class TreeNode<E> {
        protected E data;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            data = e;
        }
    }

 
    public int getSize() {
        return size;
    }

    /** Returns the root of the tree */
    public TreeNode<E> getRoot() {
        return root;
    }

    /** Returns a path from the root leading to the specified element */
     public java.util.ArrayList<TreeNode<E>> path(E e) {
        java.util.ArrayList<TreeNode<E>> list =
                new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root
        list.add(current);
/*
        while (current != null) {
            list.add(current); // Add the node to the list
            if (e.compareTo(current.data) < 0) {
                current = current.left;
            }
            else if (e.compareTo(current.data) > 0) {
                current = current.right;
            }
            else
                break;
        }
*/
        return list; // Return an array list of nodes
    }
    
 
 
}