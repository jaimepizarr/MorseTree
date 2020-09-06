/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import main.BTView;

/**
 *
 * @author PC
 */
public class CircleThread implements Runnable {

        private double x;
        private double y;
        private BTView view;
        private static final double RADIUS = 17;

        public CircleThread(double x, double y,BTView view) {
            this.x = x;
            this.y = y;
            this.view= view;
        }

        @Override
        public void run() {
            Circle circle2 = new Circle(x, y, RADIUS);
            circle2.setFill(Color.ORANGE);
            circle2.setStroke(Color.BLACK);
            view.getChildren().add(circle2);

        }

    }
