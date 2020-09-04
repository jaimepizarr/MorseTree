package main;



import java.util.LinkedList;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;


import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BTView extends Pane {

    private BinaryTree<String> tree = new BinaryTree<>();
    private static final double RADIUS = 17;
    private  static final double VGAP = 50;
    private LinkedList<BinaryTree.TreeNode<String>> path;

    BTView(BinaryTree<String> tree) {
        this.tree = tree;
        path = new LinkedList<>();
        setStatus("Tree is empty");
    }
    
    public enum radius{
    RADIUS; 
   
    }
    public enum vGap{
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
            displayTree(tree.getRoot(), getWidth() / 2, VGAP,getWidth()/4);
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
        Thread thread;
        thread = new Thread(() -> {
            double x = getWidth() / 2;
            double y = VGAP;
            double hGap = getWidth() / 4;
            Platform.runLater(new CircleThread(x, y));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(BTView.class.getName()).log(Level.SEVERE, null, ex);
                Thread.currentThread().interrupt();
            }
            for (char c : code.toCharArray()) {
                String musicFile = null;
                try {
                    switch (c) {
                        case '.':
                            x += hGap;
                            y += VGAP;
                            hGap /= 2;
                            musicFile = "/resources/Punto.mpeg";
                            break;
                        case '-':
                            x -= hGap;
                            y += VGAP;
                            hGap /= 2;
                            musicFile = "/resources/Raya.mpeg";
                            break;
                        default:
                            Platform.runLater(new TextThread(x, y, c));
                            x = getWidth() / 2;
                            y = VGAP;
                            hGap = getWidth() / 4;
                            Thread.sleep(1000);
                            Platform.runLater(()->getChildren().remove(size, getChildren().size()));
                            break;
                    }
                    
                    Platform.runLater(new CircleThread(x, y));
                    if(musicFile != null) {
                        playMusic(musicFile);
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BTView.class.getName()).log(Level.SEVERE, null, ex);
                    Thread.currentThread().interrupt();
                }

            }
            Platform.runLater(()->getChildren().remove(size, getChildren().size()));
        });

        thread.start();

    }
    private void playMusic(String musicFile){
        if(musicFile!=null){
            Media sound = new Media(this.getClass().getResource(musicFile).toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setVolume(1);
            
        }
    }

    private class CircleThread implements Runnable {

        private double x;
        private double y;

        public CircleThread(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void run() {
            Circle circle2 = new Circle(x, y, RADIUS);
            circle2.setFill(Color.ORANGE);
            circle2.setStroke(Color.BLACK);
            getChildren().add(circle2);
            

        }
        
        

    }

    private class TextThread implements Runnable {

        private double x;
        private double y;
        private char c;

        public TextThread(double x, double y, char c) {
            this.x = x - 4;
            this.y = y + 4;
            this.c = c;
        }

        @Override
        public void run() {
            getChildren().add(new Text(x, y, String.valueOf(c)));

        }

    }

}
