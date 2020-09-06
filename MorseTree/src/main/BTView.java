package main;

import threadss.PathThread;
import java.util.LinkedList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BTView extends Pane {

    private BinaryTree<String> tree = new BinaryTree<>();
    private static final double RADIUS = 17;
    private static final double VGAP = 50;
    private LinkedList<BinaryTree.TreeNode<String>> path;

    BTView(BinaryTree<String> tree) {
        this.tree = tree;
        path = new LinkedList<>();
        setStatus("Tree is empty");
    }

    public enum radius {
        RADIUS;

    }

    public enum vGap {
        VGAP;
    }

    public void setStatus(String msg) {
        this.getChildren().add(new Text(20, 20, msg));
    }

    public void setPath(LinkedList<BinaryTree.TreeNode<String>> path) {
        this.path = path;
    }

    public void clearPath() {
        this.path = new LinkedList<>();
    }

    public LinkedList<BinaryTree.TreeNode<String>> getPath() {
        return path;
    }

    public void displayTree() {
        this.getChildren().clear();
        if (tree.getRoot() != null) {
            displayTree(tree.getRoot(), getWidth() / 2, VGAP, getWidth() / 4);
        }
    }

    private void displayTree(BinaryTree.TreeNode<String> root,
            double x, double y, double hGap) {

        Circle circle = new Circle(x, y, RADIUS);

        circle.setFill(Color.WHITE);

        if (root.left != null) {
            getChildren().add(new Line(x - hGap, y + VGAP, x, y));
            displayTree(root.left, x - hGap, y + VGAP, hGap / 2);

        }

        if (root.right != null) {
            getChildren().add(new Line(x + hGap, y + VGAP, x, y));
            displayTree(root.right, x + hGap, y + VGAP, hGap / 2);
        }
        circle.setStroke(Color.BLACK);

        this.getChildren().addAll(circle,
                new Text(x - 4, y + 4, root.data));
    }

    public void mostrarPath(String code) {
        int size = getChildren().size();
        Thread thread = new Thread(new PathThread(VGAP,code,size,this));
        thread.start();
    }
}
