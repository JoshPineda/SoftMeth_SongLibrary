package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Songlib extends Application{
	private static GridPane makeGridPane() {
		Button addB = new Button("Add");
		Button delB = new Button("Delete");
		Button editB = new Button("Edit");
		//test
		
		GridPane gridpane = new GridPane();
		gridpane.add(addB, 0, 0);
		gridpane.add(delB, 0, 1);
		gridpane.add(editB, 0, 2);
		return gridpane;
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			//test
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/songlib.fxml"));
			GridPane root = (GridPane)loader.load();
			Scene scene = new Scene(root,400,400);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
