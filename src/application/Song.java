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
	
	//int used to determine if detail is to be added to the toString
	private int detail = 0;
	//int used to determine if Year and Artist are not default
	private int aPresent;
	private int yPresent;
	//string for toString
	private String outString;
	
	public Song() {
		super();
	}
	public Song(String name, String artist) {
		setName(name);
		setArtist(artist);
		setAPresent(0);
		setYPresent(0);
		updateString();
	}
	public Song(String name, String artist, int year, String album) {
		setName(name);
		setArtist(artist);
		setYear(year);
		setAlbum(album);
		setAPresent(1);
		setYPresent(1);
		updateString();
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
	public int getDetail(){
		return detail;
	}
	public void setDetail(int detail){
		this.detail=detail;
		System.out.println("detail was changed to "+detail);
		updateString();
	}
	public int getYPresent() {
		return yPresent;
	}
	public void setYPresent(int yPresent) {
		this.yPresent = yPresent;
	}
	public int getAPresent() {
		return aPresent;
	}
	public void setAPresent(int aPresent) {
		this.aPresent = aPresent;
	}
	public void updateString(){
		System.out.println("Update string called");
		outString = Name+" - "+Artist;
		if(getDetail()==1){
			System.out.println("adding details");
			if(getYPresent()==1){
				System.out.println("added year");
				outString += " - "+Year;
			}
			if(getAPresent()==1){
				System.out.println("added album");
				outString += " - "+Album;
			}
		}
		System.out.println("output string = "+outString);
	}
	@Override
	public String toString() {
		//return "Song [Name=" + Name + ", Artist=" + Artist + ", Year=" + Year + ", Album=" + Album + "]";
		return outString;
	}

}
