package View;
/*
 * Joshua Pineda
 * John Strauser
 */
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Optional;
import java.util.StringTokenizer;

import application.Song;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
	//private int oldIndex = 0;
	private File saveFile;
	
	public void start() {
		obslist = FXCollections.observableArrayList(
				/*
				new Song("Song1","Artist2"),
				new Song("Song3", "Artist2",2019,"Album2"),
				new Song("ABC123","1","Arbum"),
				new Song("song1","Artist1",2018,"Album8"),
				new Song("test","5","alb")*/
				);
		//load from file
		try{
			saveFile = new File("saveFile.txt");
			BufferedReader br = new BufferedReader(new FileReader(saveFile));
			System.out.println("About to enter for loop");
			for(String line; (line = br.readLine()) != null; ){
				//System.out.println("enter for loop");
				StringTokenizer tokens = new StringTokenizer(line, "-");
				//System.out.println("tokenizer made");
				String name = tokens.nextToken();
				//System.out.println("name = "+name);
				String artist = tokens.nextToken();
				//System.out.println("artist = "+artist);
				String yearString = tokens.nextToken();
				int year = -1;
				if(!yearString.equals("none")){
					try{
						year = Integer.parseInt(yearString);
					}catch(Exception e){
						//handle parsing error
					}
				}
				//System.out.println("year = "+year);
				String album = tokens.nextToken();
				//System.out.println("-"+album+"-");
				if(album.equals("none")){
					
					album = "";
				}
				//System.out.println("album = "+album);
				//build the song class and add to obslist
				Song entry;
				if(year == -1 && album.equals("")){
					entry = new Song(name,artist);
				}else if(year == -1 && !album.equals("")){
					entry = new Song(name,artist,album);
				}else if(year != -1 && album.equals("")){
					entry = new Song(name,artist,year);
				}else{
					entry = new Song(name,artist,year,album);
				}
				
				obslist.add(entry);
				
			}
			br.close();
		}catch(FileNotFoundException e){
			System.out.println("File was not made yet, should make list empty");
		}catch(Exception e){
			System.out.println("Exception thrown: "+e);
		}
		
		
		sort();
		
		SongLibrary.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>(){
			@Override
			public void changed(ObservableValue<? extends Song> observable, Song oldValue, Song newValue) {
				//change detail ints for old and new selections
				if(obslist.contains(oldValue)){
					oldValue.setDetail(0);
				}
				if(obslist.contains(newValue)){
					newValue.setDetail(1);
				}
				//refresh list view
				SongLibrary.refresh();
				
			} 
		});
		
		SongLibrary.setItems(obslist);
	}
	public void sort(){
		Comparator<Song> comparator = Comparator.comparing(Song::getNameLower).thenComparing(Song::getArtistLower);
		obslist.sort(comparator);
	}
	
	public boolean CheckDuplicate(String Song_title, String Artist, int edit){
		for(int i=0; i<obslist.size();i++) {
			if(Song_title.equals(obslist.get(i).getName()) && Artist.equals(obslist.get(i).getArtist())) {
				if(edit == 1 && SongLibrary.getSelectionModel().getSelectedIndex() == i) {
					return false;
				}
				return true;
			}
		}
		return false;
	}
	public void addSongHandler() {

		if (Song_title.getText().equals("") || Artist.getText().equals("")) {
			showError(2,Song_title.getText(),Artist.getText());
		}else{
			//if all 4 dialog boxes are filled
			if(!Year.getText().equals("") && !Album.getText().equals("")){
				System.out.println("All are filled");
				//make sure only digits are in Year
				try{
					if (CheckDuplicate(Song_title.getText(),Artist.getText(),0)){
						showError(3,Song_title.getText(),Artist.getText());
					}
					else {
						Song newsong = new Song(Song_title.getText(),Artist.getText(),Integer.parseInt(Year.getText()),Album.getText());
						if(confirmAction(1,Song_title.getText(),Artist.getText()))
							obslist.add(newsong);
					}
			
				}catch(NumberFormatException e){
					showError(1,Song_title.getText(),Artist.getText());
				}catch(Exception e){
					//other unknown exceptions
					//To-do
				}
			//if only Song and artist are filled
			}else if(Year.getText().equals("") && Album.getText().equals("")){
				System.out.println("Song and Artist filled");
				if (CheckDuplicate(Song_title.getText(),Artist.getText(),0)){
					showError(3,Song_title.getText(),Artist.getText());
				}
				else {
					Song newsong = new Song(Song_title.getText(),Artist.getText());
					if(confirmAction(1,Song_title.getText(),Artist.getText()))
						obslist.add(newsong);
				}
			//all but year are filled
			}else if(Year.getText().equals("")){
				System.out.println("All but year are filled");
				if (CheckDuplicate(Song_title.getText(),Artist.getText(),0)){
					showError(3,Song_title.getText(),Artist.getText());
				}
				else {
					Song newsong = new Song(Song_title.getText(),Artist.getText(),Album.getText());
					if(confirmAction(1,Song_title.getText(),Artist.getText()))
						obslist.add(newsong);
				}
			//all but album are filled
			}else{
				System.out.println("All but Album are filled");
				//make sure only digits are in Year
				try{
					if (CheckDuplicate(Song_title.getText(),Artist.getText(),0)){
						showError(3,Song_title.getText(),Artist.getText());
					}
					else {
						Song newsong = new Song(Song_title.getText(),Artist.getText(),Integer.parseInt(Year.getText()),Album.getText());
						if(confirmAction(1,Song_title.getText(),Artist.getText()))
							obslist.add(newsong);
					}
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
	public void editSongHandler() throws IOException{

		int selectedIndex = SongLibrary.getSelectionModel().getSelectedIndex();
		if(selectedIndex == -1) {
			//error dialog "no selection made yet"
		}else{
			//do the edit
			if (Song_title.getText().equals("") || Artist.getText().equals("")) {
				//error, value to change to are not filled
				//need new error code
				showError(2,Song_title.getText(),Artist.getText());
			}else{
				try{
					int year = Integer.parseInt(Year.getText());
					//need new confirmation dialog for confirmation of change here
					if (CheckDuplicate(Song_title.getText(),Artist.getText(),1)) {
						showError(3,Song_title.getText(),Artist.getText());
					}
					else {
						if(confirmAction(1,Song_title.getText(),Artist.getText())) {
							obslist.get(selectedIndex).setName(Song_title.getText());
							obslist.get(selectedIndex).setArtist(Artist.getText());
							obslist.get(selectedIndex).setYear(year);
							obslist.get(selectedIndex).setAlbum(Album.getText());
						}
					}

				}catch(NumberFormatException e){
					if(Year.getText().equals("")) {
						if (CheckDuplicate(Song_title.getText(),Artist.getText(),1)) {
							showError(3,Song_title.getText(),Artist.getText());
						}
						else {
							if(confirmAction(1,Song_title.getText(),Artist.getText())) {
								obslist.get(selectedIndex).setName(Song_title.getText());
								obslist.get(selectedIndex).setArtist(Artist.getText());
								obslist.get(selectedIndex).setYear(-1);
								obslist.get(selectedIndex).setAlbum(Album.getText());
							}
						}
					}else {
						showError(1,Song_title.getText(),Artist.getText());
					}
				}catch(Exception e){
					//other unknown exceptions
					//To-do
				}
			}			
		}
		
		//sort list at the end
		sort();
	}
	public void deleteSongHandler(){
		
		int selectedIndex = SongLibrary.getSelectionModel().getSelectedIndex();
		obslist.remove(selectedIndex, selectedIndex+1);
		
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
	 * 2) Title or artist not entered
	 * */
	
	public void showError(int errorType,String Song_title, String artist) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("There was an error!");
		switch(errorType) {
			
			case 1:
				alert.setContentText("Error, Song \"" + Song_title + "\" by " + artist + " cannot have a year that is not an integer");
				alert.showAndWait();
				break;
			case 2:
				alert.setContentText("Title and artist fields must be filled in");
				alert.showAndWait();
				break;
			case 3:
				alert.setContentText("Error, Song \"" + Song_title + "\" by " + artist + " already exists in the library.");
				alert.showAndWait();
				break;
			
		}
	}
	public ObservableList<Song> getObslist(){
		return obslist;
	}
}
