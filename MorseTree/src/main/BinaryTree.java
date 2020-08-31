package main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;



public class BinaryTree<E> {
    protected TreeNode<E> root;
    protected int size = 0;
    
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
        protected String file;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            data = e;
        }
        public TreeNode(){
            
        }
    }

 
    public int getSize() {
        return size;
    }

    public TreeNode<E> getRoot() {
        return root;
    }
    
     public void crearArbol(E root) {
        if (this.root == null) {
            this.root = createNewNode(root);
        }
        HashMap<String, String> codigos = codesMorse();
        codigos.entrySet().forEach((e) -> {
            TreeNode nd = this.root;
            int tam = e.getValue().length() - 1;
            for (int n = 0; n <= tam; ++n) {
                char c = e.getValue().charAt(n);
                if (c == '.') {
                    if (nd.right == null) {
                        nd.right = new TreeNode<>(" ");
                    }
                    if (tam == n) {
                            nd.right.data = e.getKey();
                    }
                    nd = nd.right;
                }
                if (c == '-') {
                    if (nd.left == null) {
                        nd.left = new TreeNode<>(" ");
                    }
                    if (tam == n) {
                            nd.left.data = e.getKey();
                        }
                    nd = nd.left;
                }

            }
        });

    }

 
    public LinkedList<TreeNode<E>> path(String words) throws IOException {
        String codigos=encode(words,codesMorse());
        String[] decode = codigos.split(" ");
        LinkedList<TreeNode<E>> list =new LinkedList<>();
        TreeNode<E> current = root;
        
        list.add(current);
        for(char c:codigos.toCharArray()){
            if(c==('.')){
                current=current.right;
                list.add(current);
            }else if (c==('-')){
                current=current.left;
                list.add(current);      
            }else{
                current=root;
                
            }
        }
        
        return list; 
    }
      
    public String encode(String word,HashMap<String,String> mapCodeMorse){
        StringBuilder codeMorse= new StringBuilder();
        for (int i = 0; i < word.length(); i++) { 
            codeMorse.append(mapCodeMorse.get(String.valueOf(word.charAt(i)).toUpperCase()));
            codeMorse.append(String.valueOf(word.charAt(i)).toUpperCase());
        }
        System.out.println(codeMorse);
        return codeMorse.toString();
    }
    
    public HashMap<String,String> codesMorse() {
        HashMap<String,String> mapCodeMorse= new HashMap<>();
        String cadena;
        FileReader f;
        try {
            f = new FileReader("alfabeto.txt");
            BufferedReader b = new BufferedReader(f);
            while((cadena = b.readLine())!=null) {
                StringBuilder key= new StringBuilder();
                String[] parts = cadena.split("\\|");
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