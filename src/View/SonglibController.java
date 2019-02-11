package View;

import java.awt.event.ActionEvent;
import java.util.Comparator;

/*
 * Joshua Pineda
 * John Strauser
 */
import application.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SonglibController {
	@FXML TableView<Song> SongLibrary;
	@FXML TableColumn<Song,String> tableTitle;
	@FXML TableColumn<Song,String> tableArtist;
	@FXML TableColumn<Song,Integer> tableYear;
	@FXML TableColumn<Song,String> tableAlbum;
	@FXML Button addButton;
	@FXML TextField Song_title;
	@FXML TextField Artist;
	@FXML TextField Year;
	@FXML TextField Album;
	

	
	private ObservableList<Song> obslist;
	
	public void start() {
		obslist = FXCollections.observableArrayList(
				new Song("Song1","Artist2"),
				new Song("Song3", "Artist2",2019,"Album2"),
				new Song("ABC123","1"),
				new Song("song1","Artist1"),
				new Song("test","5")
				);
		SongLibrary.setItems(obslist);
		
		//set columns for tableview
		tableTitle.setCellValueFactory(new PropertyValueFactory<Song,String>("Name"));
		tableArtist.setCellValueFactory(new PropertyValueFactory<Song,String>("Artist"));
		tableYear.setCellValueFactory(new PropertyValueFactory<Song,Integer>("Year"));
		tableAlbum.setCellValueFactory(new PropertyValueFactory<Song,String>("Album"));
		
		//this sort call might not be necessary
		sort();
	}
	public void sort(){
		Comparator<Song> comparator = Comparator.comparing(Song::getNameLower).thenComparing(Song::getArtistLower);
		obslist.sort(comparator);
	}
	public void addSongHandler() {

		if (Song_title.getText() == null || Artist.getText() == null) {
			//Send error dialog box
		}
		Song newsong = new Song(Song_title.getText(),Artist.getText());
		
		obslist.add(newsong);
		
		//sort list at the end
		sort();
	}
	public void editSongHandler(){
		
		
		
		//sort list at the end
		sort();
	}
	public void deleteSongHandler(){
		
		
		
		//sort list at the end
		//this sort might not be necessary
		sort();
	}
}
