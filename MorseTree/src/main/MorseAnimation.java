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
		hBox.getChildren().addAll(new Label("Ingrese Palabra: "), tfKey, btTraductor,btClear);
		hBox.setAlignment(Pos.CENTER);
		pane.setBottom(hBox);
                
                btTraductor.setOnAction(e -> {
                    try {
                        String key = tfKey.getText();
                        if(!key.equals("")){
                        view.setPath(tree.path(key));
                        view.displayTree();}
                        else{
                            System.out.println("Is Empty");}
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
               
                tree.add("()",null,"I");
                tree.add("T","()","L");
                tree.add("E","()","R");
                tree.add("M","T","L");
                tree.add("N","T","R");
                tree.add("A","E","L");
                tree.add("I","E","R");
                tree.add("O","M","L");
                tree.add("G","M","R");
                tree.add("K","N","L");
                tree.add("D","N","R");
                tree.add("W","A","L");
                tree.add("R","A","R");
                tree.add("U","I","L");
                tree.add("S","I","R");
                //NIVEL 2
                tree.add("-","O","L");
                tree.add(".","O","R");
                tree.add("Q","G","L");
                tree.add("Z","G","R");
                tree.add("Y","K","L");
                tree.add("C","K","R");
                tree.add("X","D","L");
                tree.add("B","D","R");
                tree.add("J","W","L");
                tree.add("P","W","R");
                tree.add("L","R","R");
                tree.add("_","U","L");
                tree.add("F","U","R");
                tree.add("V","S","L");
                tree.add("H","S","R");
                //NIVEL 3
                
                tree.add("0","-","L");
                tree.add("9","-","R");
                tree.add("8","Q","L");
                tree.add("7","Y","L");
                tree.add("6","B","R");
                tree.add("1","J","L");
                tree.add("2","_","L");
                tree.add("3","V","L");
                tree.add("4","V","R");
                tree.add("5","H","R");
		view.displayTree();
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
