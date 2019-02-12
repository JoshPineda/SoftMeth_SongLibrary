package View;
/*
 * Joshua Pineda
 * John Strauser
 */
import java.awt.event.ActionEvent;
import java.util.Comparator;
import java.util.Optional;

import application.Song;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class SonglibController {
	@FXML ListView<Song> SongLibrary;
	@FXML Button addButton;
	@FXML Button editButton;
	@FXML Button deleteButton;
	@FXML TextField Song_title;
	@FXML TextField Artist;
	@FXML TextField Year;
	@FXML TextField Album;

	private ObservableList<Song> obslist;
	private int oldIndex = 0;
	
	public void start() {
		obslist = FXCollections.observableArrayList(
				new Song("Song1","Artist2"),
				new Song("Song3", "Artist2",2019,"Album2"),
				new Song("ABC123","1","Artist"),
				new Song("song1","Artist1",2018,"Album8"),
				new Song("test","5","2012")
				);
		
		
		//this sort call might not be necessary
		sort();
		
		SongLibrary.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>(){
			@Override
			public void changed(ObservableValue<? extends Song> observable, Song oldValue, Song newValue) {
				if(obslist.contains(oldValue)){
					oldValue.setDetail(0);
					obslist.set(oldIndex, oldValue);
				}
				if(obslist.contains(newValue)){
					newValue.setDetail(1);
					obslist.set(SongLibrary.getSelectionModel().getSelectedIndex(), newValue);
				}
				oldIndex = SongLibrary.getSelectionModel().getSelectedIndex();
				
			} 
		});
		
		SongLibrary.setItems(obslist);
		
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

		if (Song_title.getText().equals("") || Artist.getText().equals("")) {
			//Send error dialog box
			System.out.println("Title or Artist not entered error");
		}else{
			//if all 4 dialog boxes are filled
			if(!Year.getText().equals("") && !Album.getText().equals("")){
				System.out.println("All are filled");
				//make sure only digits are in Year
				try{
					Song newsong = new Song(Song_title.getText(),Artist.getText(),Integer.parseInt(Year.getText()),Album.getText());
					if(confirmAction(1,Song_title.getText(),Artist.getText()))
						obslist.add(newsong);
				}catch(NumberFormatException e){
					showError(1,Song_title.getText(),Artist.getText());
				}catch(Exception e){
					//other unknown exceptions
					//To-do
				}
			//if only Song and artist are filled
			}else if(Year.getText().equals("") && Album.getText().equals("")){
				System.out.println("Song and Artist filled");
				Song newsong = new Song(Song_title.getText(),Artist.getText());
				if(confirmAction(1,Song_title.getText(),Artist.getText()))
					obslist.add(newsong);
			//all but year are filled
			}else if(Year.getText().equals("")){
				System.out.println("All but year are filled");
				Song newsong = new Song(Song_title.getText(),Artist.getText(),Album.getText());
				if(confirmAction(1,Song_title.getText(),Artist.getText()))
					obslist.add(newsong);
			//all but album are filled
			}else{
				System.out.println("All but Album are filled");
				//make sure only digits are in Year
				try{
					Song newsong = new Song(Song_title.getText(),Artist.getText(),Integer.parseInt(Year.getText()));
					if(confirmAction(1,Song_title.getText(),Artist.getText()))
						obslist.add(newsong);
				}catch(NumberFormatException e){
					showError(1,Song_title.getText(),Artist.getText());
				}catch(Exception e){
					//other unknown exceptions
					//To-do
				}
			}
		}
		
		//sort list at the end
		sort();
	}
	public void editSongHandler(){
		
		
		
		//sort list at the end
		sort();
	}
	public void deleteSongHandler(){
		
		int selectedIndex = SongLibrary.getSelectionModel().getSelectedIndex();
		//unsupportedOperationException occurs here
		obslist.remove(selectedIndex);
		
		//sort list at the end
		//this sort might not be necessary
		sort();
	}
	
	/*Raises a dialog box
	 * Action Types: (Hardcoded into confirmAction)
	 * 1) Add Song
	 * 2) Edit Song
	 * 3) Delete Song
	 * */
	public boolean confirmAction(int actionType, String Song_title, String artist) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		boolean conf = false;
		switch(actionType) {
			//Add
			case 1:
				alert.setHeaderText("You are about to add " + Song_title + " by " + artist);
				alert.setContentText("Are you sure?");
				Optional<ButtonType> resultA = alert.showAndWait();
				if (resultA.get() == ButtonType.OK){
					conf = true;
				}
				break;
			//Edit
			case 2:
				alert.setHeaderText("You are about to edit " + Song_title + " by " + artist);
				alert.setContentText("Are you sure?");
				Optional<ButtonType> resultE = alert.showAndWait();
				if (resultE.get() == ButtonType.OK){
					conf = true;
				} 
				break;
			//Delete
			case 3:
				alert.setHeaderText("You are about to delete " + Song_title + " by " + artist);
				alert.setContentText("Are you sure?");
				Optional<ButtonType> resultD = alert.showAndWait();
				if (resultD.get() == ButtonType.OK){
					conf = true;
				}
				break;
		}
		return conf;

	}
	
	/* Raises an error dialog box
	 * Error Types:
	 * 1) Numberformat Exception:- Year is not an integer
	 * */
	
	public void showError(int errorType,String Song_title, String artist) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		switch(errorType) {
			case 1:
				alert.setHeaderText("Error, Song \"" + Song_title + "\" by " + artist + " cannot have a year that is not an integer");
				break;
			
		}
	}
}
