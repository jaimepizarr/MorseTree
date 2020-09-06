/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Threads.CircleThread;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import main.BTView;

/**
 *
 * @author PC
 */
public class PathThread implements Runnable {

    private double x;
    private double y;
    private double hGap;
    private String code;
    private int size;
    private BTView view;
    private double vgap;

    public PathThread(double vgap, String code, int size, BTView view) {
        this.view = view;
        this.y = vgap;
        this.code = code;
        this.size = size;
        this.x = view.getWidth() / 2;
        this.hGap = view.getWidth() / 4;
        this.vgap = vgap;
    }

    @Override
    public void run() {
        Platform.runLater(new CircleThread(x, y, view));
        try {
            Thread.sleep(600);
            for (int i = 0; i < code.length(); i++) {
                if (code.charAt(i) == '.') {
                    moveRight();
                } else if (code.charAt(i) == '-') {
                    moveLeft();
                } else {
                    returnToRoot(i);
                    i++;
                }
                Platform.runLater(new CircleThread(x, y, view));
                Thread.sleep(600);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(BTView.class.getName()).log(Level.SEVERE, null, ex);
            Thread.currentThread().interrupt();
        }
        cleanView();
    }

    private void moveRight() {
        x += hGap;
        y += vgap;
        hGap /= 2;
        playMusic("/resources/Punto.mpeg");
    }

    private void moveLeft() {
        x -= hGap;
        y += vgap;
        hGap /= 2;
        playMusic("/resources/Raya.mpeg");
    }

    private void returnToRoot(int i) throws InterruptedException {
        if (i + 1 < code.length() && code.charAt(i + 1) != ' ') {
            Platform.runLater(new TextThread(x, y, code.charAt(i + 1), view));
            x = view.getWidth() / 2;
            y = vgap;
            hGap = view.getWidth() / 4;
            Thread.sleep(600);
        }
        cleanView();
    }

    private void cleanView() {
        Platform.runLater(()
                -> view.getChildren().remove(size, view.getChildren().size()));
    }

    private void playMusic(String musicFile) {
        Media sound = new Media(this.getClass().getResource(musicFile).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(1);
    }

}
