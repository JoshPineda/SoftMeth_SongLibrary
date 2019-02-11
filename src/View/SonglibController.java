package View;
/*
 * Joshua Pineda
 * John Strauser
 */
import java.awt.event.ActionEvent;
import java.util.Comparator;
import application.Song;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class SonglibController {
	@FXML ListView<Song> SongLibrary;
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
		
		//this sort call might not be necessary
		sort();
		
		SongLibrary.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>(){
			@Override
			public void changed(ObservableValue<? extends Song> observable, Song oldValue, Song newValue) {
				System.out.println();
				System.out.println("oldValue= "+oldValue);
				System.out.println("newValue= "+newValue);
				if(obslist.contains(oldValue)){
					oldValue.setDetail(0);
					System.out.println("oldDetail= "+oldValue.getDetail());
				}
				if(obslist.contains(newValue)){
					newValue.setDetail(1);
					System.out.println("newDetail= "+newValue.getDetail());
				}
			} 
		});
		
		/*SongLibrary.getSelectionModel()
			.selectedIndexProperty()
			.addListener(
				(obslist)
				);*/
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
