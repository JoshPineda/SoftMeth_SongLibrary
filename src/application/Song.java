package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/*
 * Joshua Pineda
 * John Strauser
 */
public class Song {

	private String Name;
	private String Artist;
	private int Year;
	private String Album;
	
	public Song() {
		super();
	}
	public Song(String name, String artist) {
		setName(name);
		setArtist(artist);

	}
	public Song(String name, String artist, int year, String album) {
		setName(name);
		setArtist(artist);
		setYear(year);
		setAlbum(album);
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public String getNameLower(){
		String temp = Name;
		temp = temp.toLowerCase();
		return temp;
	}
	public String getArtist() {
		return Artist;
	}
	public void setArtist(String artist) {
		this.Artist = artist;
	}
	public String getArtistLower(){
		String temp = Artist;
		temp = temp.toLowerCase();
		return temp;
	}
	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		this.Year = year;
	}
	public String getAlbum() {
		return Album;
	}
	public void setAlbum(String album) {
		this.Album = album;
	}

	
	@Override
	public String toString() {
		return "Song [Name=" + Name + ", Artist=" + Artist + ", Year=" + Year + ", Album=" + Album + "]";
	}

}
