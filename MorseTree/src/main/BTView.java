package main;
import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


public class BTView extends Pane {
    private BinaryTree<String> tree = new BinaryTree<>();
    private double radius = 15; // Tree node radius
    private double vGap = 50; //
    private ArrayList<BinaryTree.TreeNode<String>> path;

	BTView(BinaryTree<String> tree) {
        this.tree = tree;
        path = new ArrayList<BinaryTree.TreeNode<String> >();
        setStatus("Tree is empty");
    }

    public void setStatus(String msg) {
        this.getChildren().add(new Text(20, 20, msg));
    }
    
    public void setPath(ArrayList<BinaryTree.TreeNode<String>> path) {
    	this.path = path;
    }

    public ArrayList<BinaryTree.TreeNode<String>> getPath() {
		return path;
	}
    
    public void displayTree() {
        this.getChildren().clear(); 
        if (tree.getRoot() != null) {
            // Display tree recursively
            displayTree(tree.getRoot(), getWidth() / 2, vGap,
                    getWidth() / 4, this.path);
        }
    }


    private void displayTree(BinaryTree.TreeNode<String> root,
                             double x, double y, double hGap, ArrayList<BinaryTree.TreeNode<String>> path) {
        if (root.left != null) {
            // Draw a line to the left node
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            // Draw the left subtree recursively
            displayTree(root.left, x - hGap, y + vGap, hGap / 2, path);
        }

        if (root.right != null) {
            // Draw a line to the right node
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            // Draw the right subtree recursively
            displayTree(root.right, x + hGap, y + vGap, hGap / 2, path);
        }

        // Display a node
        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.WHITE);
        for(BinaryTree.TreeNode<String> i : path)
        	if(root.equals(i))
        		circle.setFill(Color.ORANGE);
        circle.setStroke(Color.BLACK);
        this.getChildren().addAll(circle,
                new Text(x - 4, y + 4, root.data.toString()));
    }
}
