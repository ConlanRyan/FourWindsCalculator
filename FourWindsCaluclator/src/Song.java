
public class Song {
	private String title;
	private int lengthInSeconds;
	
	
	public Song() {
		title="";
		lengthInSeconds=0;
	}
	public Song(String title,int length) {
		this.title=title;
		this.lengthInSeconds=length;
	}
	public Song(Song s) {
		title=s.getTitle();
		lengthInSeconds=s.getLength();
	}
	public Song(String s) {
		title = s.substring(0,s.indexOf(";"));
		
		String lengthString = s.substring(s.indexOf(";")+1);
		int minutes = Integer.parseInt(lengthString.substring(0,lengthString.indexOf(":")));
		int seconds = Integer.parseInt(lengthString.substring(lengthString.indexOf(":")+1));
		
		lengthInSeconds=(minutes*60)+seconds;
	}
	
	public String getTitle() {
		return title;
	}
	public int getLength() {
		return lengthInSeconds;
	}
	
	public void setTitle(String title) {
		this.title=title;
	}
	public void setLength(int length) {
		this.lengthInSeconds=length;
	}
	
	
	
	
	public String toString() {
		return getTitle();
	}
}
