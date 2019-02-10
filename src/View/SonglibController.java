package View;

import java.awt.event.ActionEvent;

/*
 * Joshua Pineda
 * John Strauser
 */
import application.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SonglibController {
	@FXML TableView<Song> SongLibrary;
	@FXML Button addButton;
	@FXML TextField Song_title;
	@FXML TextField Artist;
	@FXML TextField Year;
	@FXML TextField Album;
	

	
	private ObservableList<Song> obslist;
	
	public void start() {
		obslist = FXCollections.observableArrayList(
				new Song("Song1","Artist1")
				);
		SongLibrary.setItems(obslist);

		
	}
	public void addSongHandler() {

			if (Song_title.getText() == null || Artist.getText() == null) {
				//Send error dialog box
			}
			Song newsong = new Song(Song_title.getText(),Artist.getText());

			
			obslist.add(newsong);
		

	}
}
