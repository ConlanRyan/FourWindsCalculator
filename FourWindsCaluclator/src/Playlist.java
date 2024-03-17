import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Playlist {
	private ArrayList<Song> list;
	private double secondsPerYear = LocalDate.now().lengthOfYear()*24*60*60;
	private double totalLength;
	private double howManyDaysPerSongSecond=0.0;
	private double howManyRealLifeSecondsPerSongSecond=0.0;

	
	public Playlist() {
		list = new ArrayList<Song>();
	}
	
	public Playlist(String textFileAddress) {
		list = new ArrayList<Song>();
		try {
			FileInputStream fs = new FileInputStream(textFileAddress);
			Scanner scan = new Scanner(fs);
			while(scan.hasNext()) {
				String song = scan.nextLine();
				Song newSong = new Song(song);
				list.add(newSong);
				totalLength+=newSong.getLength();
			}
			scan.close();
			howManyDaysPerSongSecond+=secondsPerYear/totalLength/24/60/60;
			howManyRealLifeSecondsPerSongSecond = secondsPerYear/totalLength;
		}
		catch (FileNotFoundException fnfe)  {
			System.out.println("File Not Found. Try again.");
		}
	}
	
	public double getTotalLength() {
		return totalLength;
	}
	
	public String theSongForMyBirthday(String s) {
		return whichSongIsOnThisDate("2024-"+s).getTitle();
	}
	
	//returns a song that corresponds to the LocalDate given
	public Song whichSongIsOnThisDate(String s) {
		LocalDate dt = LocalDate.parse(s);
		
		
		//gets the given day of the year (January 1st would return 1, June 20th would return 171)
		int todaysDayOfYear = dt.getDayOfYear();
		
		//stepper variable to count how many days in we are
		double currentDayOfYear=0.0;
		
		//index we will use for it's song
		int indexOfTodaysSong = 0;
		
		for(int i=0;i<list.size();i++) {
			//checks which song the given date is during
			if(todaysDayOfYear>currentDayOfYear && todaysDayOfYear<(currentDayOfYear+(howManyDaysPerSongSecond*list.get(i).getLength()))) {
				//stores the index of that song
				indexOfTodaysSong=i;
			}
			//adds the days that would pass if we played each song, advancing us to the start of the next song
			currentDayOfYear+=(howManyDaysPerSongSecond*list.get(i).getLength());
		}
		
		return list.get(indexOfTodaysSong);
	}
	
	public ArrayList<LocalDate> periodOfTimeForThisSong(String song) throws IllegalArgumentException {
		double currentDayOfYear=0.0;
		double startDate=0.0;
		double endDate=0.0;
		boolean weFoundIt = false;
		int index = 0;
		for(int i=0;i<list.size();i++) {
			//check if the string they gave us is the title of a song
			if(list.get(i).getTitle().toLowerCase().replace(" ", "").equals(song.toLowerCase().replace(" ", ""))) {
				//if so, then set our in and out variables to be the start and end date we are on in our loop
				startDate=currentDayOfYear;
				endDate=startDate+(howManyDaysPerSongSecond*list.get(i).getLength());
				weFoundIt=true;
				index=i;
			}
			//adds the days that would pass if we played each song, advancing us to the start of the next song
			currentDayOfYear+=(howManyDaysPerSongSecond*list.get(i).getLength());
		}
		
		if(weFoundIt) {
			ArrayList<LocalDate> d = new ArrayList<LocalDate>();
			//this is to avoid an error \/
			if(startDate==0.0) {startDate=1.0;}
			
			
			d.add(LocalDate.ofYearDay(LocalDate.now().getYear(), (int)startDate));
			d.add(LocalDate.ofYearDay(LocalDate.now().getYear(), (int)endDate));
			return d;
		}
		else {
			throw new IllegalArgumentException(song+" is not a song in Eurus, Notos, Boreas, or Zephyrus.");
		}
	}
	public Song whichSongToday() {
		return whichSongIsOnThisDate(LocalDate.now().toString());
		
	}
	
	public String nextSongChange() {
		return songChangeFrom(LocalDate.now().toString(), LocalTime.now().toString());
	}
	
	
	
	//prints out the time (days,hours,minutes,seconds) until the next song change
	public String songChangeFrom(String date, String time) {
		//get todays date and time and convert it to amount of seconds since january 1st
		LocalTime givenTime = LocalTime.parse(time);
		LocalDate givenDate = LocalDate.parse(date);
		int secondsFromStartOfYearToInstantGiven = givenTime.toSecondOfDay()+(givenDate.getDayOfYear()*24*60*60);
		
		//get the next song change's date and time and convert it to amount of seconds since january 1st
		double loopSecondOfYear=0.0;
		double secondsUntilNextSongChange = 0.0;
		int indexOfNextSong =0;
		//go through the list
		for(int i=0;i<list.size();i++) {
			//if the instant given to us is between when a specific song starts and when it ends, then that is the song that the date given is in
			if(secondsFromStartOfYearToInstantGiven>loopSecondOfYear && secondsFromStartOfYearToInstantGiven<loopSecondOfYear+(list.get(i).getLength()*(howManyRealLifeSecondsPerSongSecond))){
				//the amount of seconds until the next song change is the second of the year that the song will change minus the second of the year given to us
				secondsUntilNextSongChange = (loopSecondOfYear+(list.get(i).getLength()*(howManyRealLifeSecondsPerSongSecond))) - secondsFromStartOfYearToInstantGiven;
				indexOfNextSong=i+1;
				if(i==list.size()) {
					indexOfNextSong=0;
				}
			}
			loopSecondOfYear+=(list.get(i).getLength()*(howManyRealLifeSecondsPerSongSecond));
		}
		
		int days = (int)(secondsUntilNextSongChange/60/60/24);
		int hours = (int)(((secondsUntilNextSongChange/60/60/24) - days)*24);
		int minutes = (int)(((((secondsUntilNextSongChange/60/60/24) - days)*24) - hours)*60);
		int seconds = (int)(((((((secondsUntilNextSongChange/60/60/24) - days)*24) - hours)*60)-minutes)*60);
		
		return(list.get(indexOfNextSong).getTitle()+" in "+days+" days, "+hours+" hours, "+minutes+" minutes, and "+seconds+" seconds.");
		
	}

	
	public String toString() {
		String s = "";
		for(int i=0;i<list.size();i++) {
			s+=(list.get(i).toString()+", ");
		}
		return s;
	}

	public String listOfRanges() {
		String returned = "";
		for(Song s : list) {
			String startMonth = periodOfTimeForThisSong(s.toString()).get(0).getMonth().toString();
			String endMonth = periodOfTimeForThisSong(s.toString()).get(1).getMonth().toString();

			int startDay = periodOfTimeForThisSong(s.toString()).get(0).getDayOfMonth();
			int endDay = periodOfTimeForThisSong(s.toString()).get(1).getDayOfMonth();

			int startDayInYear = periodOfTimeForThisSong(s.toString()).get(0).getDayOfYear();
			int endDayOfYear = periodOfTimeForThisSong(s.toString()).get(1).getDayOfYear();

			int timeInBetween = endDayOfYear-startDayInYear;
			
			returned = returned + "\n"+s.getTitle()+" starts on the "+startDay+" of "+startMonth+", and ends on the "+endDay+" of "+endMonth+". A total run of "+timeInBetween+" days.";
			
		}
		return returned;
	}

	
}
