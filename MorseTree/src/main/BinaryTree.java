package main;
import java.util.ArrayList;



public class BinaryTree<E> {
    protected TreeNode<E> root;
    protected int size = 0;

 
    public BinaryTree() {
    }


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
    
    


        
    protected TreeNode<E> createNewNode(E e) {  
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

    public TreeNode<E> getRoot() {
        return root;
    }

 
     public java.util.ArrayList<TreeNode<E>> path(String codigos) {
        
	
    ArrayList<String> decode= new ArrayList<String>();
        for (char c : codigos.toCharArray()) {
            decode.add(String.valueOf(c));
            }
         System.out.println(decode);
        java.util.ArrayList<TreeNode<E>> list =new java.util.ArrayList<>();
        TreeNode<E> current = root;
        list.add(current);
         for(String c:decode){
                 try
        {
            Thread.sleep(100);
        }catch(InterruptedException e){}
                 if(c.equals(".")){
                     current=current.right;
                     list.add(current);
                 }else if (c.equals("-")){
                     current=current.left;
                     list.add(current);
                     
                 }
                 else{
                     
                     System.out.println(current.data);
                     current=root;
                 
                 }

         
         }
        return list; 
    }
    

 
}