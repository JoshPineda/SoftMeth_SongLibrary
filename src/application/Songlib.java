package application;

/*
 * Joshua Pineda
 * John Strauser
 */

import View.SonglibController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class SongLib extends Application{

	@Override
	public void start(Stage primaryStage) {
		try {
			//test
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/songlib.fxml"));
			GridPane root = (GridPane)loader.load();
			
			SonglibController songlibcontroller = loader.getController();
			songlibcontroller.start();
			
			
			Scene scene = new Scene(root,475,475);
			
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
