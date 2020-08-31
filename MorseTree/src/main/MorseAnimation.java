package main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MorseAnimation extends Application {

    @Override
    public void start(Stage primaryStage) {
        BinaryTree<String> tree = new BinaryTree<>();

        BorderPane pane = new BorderPane();
        BTView view = new BTView(tree);
        pane.setCenter(view);
        TextField tfKey = new TextField();
        Text texto = new Text();
        view.getChildren().add(texto);
        tfKey.setPrefColumnCount(10);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btTraductor = new Button("Traducir");
        Button btClear = new Button("Clear");
        HBox hBox = new HBox(5);
   
        hBox.getChildren().addAll(new Label("Ingrese Palabra: "), tfKey, btTraductor, btClear);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);
        
        btTraductor.setOnAction(e -> {
            
            
                String key = tfKey.getText();
                if (!key.equals("")) {
                    view.mostrarPath(tree.encode(key,tree.codesMorse()));
                } else {
                    System.out.println("Is Empty");
                }
            
        });

//        btClear.setOnAction(e -> {
//            
//            tfKey.setText("");
//            view.clearPath();
//            view.displayTree();
//
//        });

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
