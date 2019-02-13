package application;

/*
 * Joshua Pineda
 * John Strauser
 */

import View.SonglibController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class SongLib extends Application{
	private ObservableList<Song> obslist;
	private SonglibController songlibcontroller;
	@Override
	public void start(Stage primaryStage) {
		try {
			//test
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/songlib.fxml"));
			GridPane root = (GridPane)loader.load();
			
			songlibcontroller = loader.getController();
			songlibcontroller.start();
			
			
			Scene scene = new Scene(root,475,475);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void stop() {
		System.out.println("Save file here");
		//save file
		obslist = songlibcontroller.getObslist();
		System.out.println(obslist.get(1)+"");
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
