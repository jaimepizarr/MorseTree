package main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MorseAnimation extends Application {

    @Override
    public void start(Stage primaryStage) {
        BinaryTree<String> tree = new BinaryTree<>();

        BorderPane pane = new BorderPane();
        BTView view = new BTView(tree);
        pane.setCenter(view);
        TextField tfKey = new TextField();

        tfKey.setPrefColumnCount(10);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btTraductor = new Button("Traducir");
        Button btClear = new Button("Clear");
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("Ingrese Palabra: "), tfKey, btTraductor, btClear);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);

        btTraductor.setOnAction(e -> {
            try {
                String key = tfKey.getText();
                if (!key.equals("")) {
                    view.setPath(tree.path(key));
                    view.displayTree();
                } else {
                    System.out.println("Is Empty");
                }
            } catch (IOException ex) {
                Logger.getLogger(MorseAnimation.class.getName()).log(Level.SEVERE, null, ex);
            }
            view.displayTree();
        });

        btClear.setOnAction(e -> {
            tfKey.setText("");
            view.clearPath();
            view.displayTree();

        });

        Scene scene = new Scene(pane, 1250, 450);
        primaryStage.setTitle("Code Morse");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        tree.crearArbol("&");
        view.displayTree();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
