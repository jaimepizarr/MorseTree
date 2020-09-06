/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadss;

import javafx.scene.text.Text;
import main.BTView;

/**
 *
 * @author PC
 */
public class TextThread implements Runnable {

        private double x;
        private double y;
        private char c;
        private BTView view;

        public TextThread(double x, double y, char c, BTView view) {
            this.x = x - 4;
            this.y = y + 4;
            this.c = c;
            this.view = view;
        }

        @Override
        public void run() {
            view.getChildren().add(new Text(x, y, String.valueOf(c)));

        }

    }