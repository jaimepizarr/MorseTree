package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Node;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class BTView extends Pane {

    private BinaryTree<String> tree = new BinaryTree<>();
    private final double radius = 17;
    private final double vGap = 50;
    private LinkedList<BinaryTree.TreeNode<String>> path;

    BTView(BinaryTree<String> tree) {
        this.tree = tree;
        path = new LinkedList<>();
        setStatus("Tree is empty");
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

            displayTree(tree.getRoot(), getWidth() / 2, vGap,
                    getWidth() / 4, this.path);
        }
    }

    private void displayTree(BinaryTree.TreeNode<String> root,
            double x, double y, double hGap, LinkedList<BinaryTree.TreeNode<String>> path) {

        Circle circle = new Circle(x, y, radius);

        circle.setFill(Color.WHITE);
//        for(BinaryTree.TreeNode<String> i : path) {
//            if(root.equals(i)){
//                System.out.println(circle.getFill());
//                circle.setFill(Color.ORANGE);
//                System.out.println(i.data);
//            }
//        }
//        Thread thread = new Thread(new Runnable(){
//            
//            @Override
//            public void run() {
//                for(BinaryTree.TreeNode<String> i : path){
//                    
//                    try {
//                        if(root.equals(i)){
//                            circle.setFill(Color.ORANGE);
//                            System.out.println(i.data);
//                            Thread.sleep(500);
//                        }else{
//                            Thread.sleep(1000);
//                        }
//                    }catch (InterruptedException ex) {
//                        Logger.getLogger(BTView.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }
//            
//            
//        });
//        thread.start();

        if (root.left != null) {
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            displayTree(root.left, x - hGap, y + vGap, hGap / 2, path);

        }

        if (root.right != null) {
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            displayTree(root.right, x + hGap, y + vGap, hGap / 2, path);
        }
        circle.setStroke(Color.BLACK);

        this.getChildren().addAll(circle,
                new Text(x - 4, y + 4, root.data.toString()));
    }

    public void mostrarPath(String code) {
        Thread thread = new Thread(() -> {
            
            double x = getWidth() / 2;
            double y = vGap;
            double hGap = getWidth() / 4;
            Platform.runLater(new T1(x, y));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(BTView.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (char c : code.toCharArray()) {
                
                try {
                    switch (c) {
                        case '.':
                            x += hGap;
                            y += vGap;
                            Platform.runLater(new T1(x, y));
                            hGap /= 2;
                            break;
                        case '-':
                            x -= hGap;
                            y += vGap;
                            Platform.runLater(new T1(x, y));
                            hGap /= 2;
                            break;
                        default:
                            x = getWidth() / 2;
                            y = vGap;
                            hGap = getWidth() / 4;
                            Thread.sleep(1000); 
                            break;
                    }

                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BTView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        thread.start();

    }

    private class T1 implements Runnable {

        private double x;
        private double y;

        public T1(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void run() {
            Circle circle2 = new Circle(x, y, radius);
            circle2.setFill(Color.ORANGE);
            circle2.setStroke(Color.BLACK);
            getChildren().add(circle2);

        }

    }

}
