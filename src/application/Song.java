package application;

public class Song {

	private String Name;
	private String Artist;
	private int Year;
	private String Album;
	
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
	public String getArtist() {
		return Artist;
	}
	public void setArtist(String artist) {
		this.Artist = artist;
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