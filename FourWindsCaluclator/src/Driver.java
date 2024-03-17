import java.time.LocalDate;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		Playlist fourWinds = new Playlist("C:\\Users\\James Hunt\\Desktop\\fourwinds.txt");
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Press enter to see today's song: ");
		String c = scan.nextLine();
		System.out.println("Today's song is "+fourWinds.whichSongToday());
		
		System.out.print("\n\nPress enter to see when the next song will be: ");
		String f = scan.nextLine();
		System.out.println("The next song will start "+fourWinds.nextSongChange());
		
		boolean badUserInput = true;
		while(badUserInput) {
			try {
				boolean keepGoing = true;
				while(keepGoing) {
					System.out.print("\n\nType in a song from Notos, Eurus, Boreas, or Zephyrus: ");
					String s = scan.nextLine();
					System.out.println("The start of '"+s+"' is the "+fourWinds.periodOfTimeForThisSong(s).get(0).getDayOfMonth()+" of "+fourWinds.periodOfTimeForThisSong(s).get(0).getMonth()+", and the end is the "+fourWinds.periodOfTimeForThisSong(s).get(1).getDayOfMonth()+" of "+fourWinds.periodOfTimeForThisSong(s).get(1).getMonth()+". That is a "+(fourWinds.periodOfTimeForThisSong(s).get(1).getDayOfYear()-fourWinds.periodOfTimeForThisSong(s).get(0).getDayOfYear())+" day streak!");
					System.out.print("\n\nWould you like to input another? (y/n): ");
					String yesOrNo=scan.nextLine();
					if(yesOrNo.equals("y")) {
						keepGoing = true;
					}
					else if(yesOrNo.equals("n")) {
						keepGoing = false;
					}
					badUserInput=false;
				}
				
			}
			catch (IllegalArgumentException iae) {
				badUserInput=true;
				System.out.println(iae.getMessage()+" Try Again!\n");
			}
		}
		
		System.out.print("\n\nEnter your birthday (mm-dd): ");
		String b = scan.nextLine();
		System.out.println("The song that's on your birthday this year is "+fourWinds.theSongForMyBirthday(b));
		
		System.out.print("\n\nEnter any date you'd like (yyyy-mm-dd): ");
		String e = scan.nextLine();
		System.out.println("The song that plays during that date is "+fourWinds.whichSongIsOnThisDate(e));

	}

	
}
