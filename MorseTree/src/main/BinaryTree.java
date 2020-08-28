package main;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;



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

 
     public java.util.ArrayList<TreeNode<E>> path(String words) throws IOException {
        
    String codigos=encode(words,codesMorse());
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
    
    private String encode(String word,HashMap<String,String> mapCodeMorse){
        StringBuilder codeMorse= new StringBuilder();
        
       
        for (int i = 0; i < word.length(); i++) { 
            codeMorse.append(mapCodeMorse.get(String.valueOf(word.charAt(i))));
            codeMorse.append(" ");
        }
        codeMorse.deleteCharAt(codeMorse.length()-1);
        return codeMorse.toString();
    }
    private HashMap<String,String> codesMorse() {
        HashMap<String,String> mapCodeMorse= new HashMap<>();
        String cadena;
        FileReader f;
        try {
            f = new FileReader("alfabeto.txt");
            BufferedReader b = new BufferedReader(f);
            while((cadena = b.readLine())!=null) {
                StringBuilder key= new StringBuilder();
                String[] parts = cadena.split("\\|");
                System.out.println(parts.toString());
                for(int i=1;i<parts.length;i++) key.append(parts[i]);
                mapCodeMorse.put( parts[0],key.toString());
                key.delete(0,key.length());
            }
        } catch (IOException ex) {
            Logger.getLogger(BinaryTree.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapCodeMorse;
    
}

}