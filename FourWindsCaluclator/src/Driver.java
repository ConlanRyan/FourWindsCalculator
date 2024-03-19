import java.io.File;
import java.time.LocalDate;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Do you live in the northern or southern hemisphere? (n/s): ");
		String hemisphere = scan.nextLine().toLowerCase().replace(" ", "");
		boolean startloop = true;
		Playlist fourWinds = new Playlist();
	
		while (startloop) {
			if(hemisphere.equals("n")||hemisphere.equals("northernhemisphere")) {
				startloop = false;
				fourWinds = new Playlist("fourwindsNORTHERN.txt");
			}
			else if (hemisphere.equals("s")||hemisphere.equals("southernhemisphere")) {
				startloop = false;
				fourWinds = new Playlist("fourwindsSOUTHERN.txt");
			}
			else {
				startloop = true;
			}
			
			
		}
				
		boolean loop = true;
		while(loop) {
			System.out.println("\nChoose an option:\n\n");
			System.out.println("	1. Get today's song");
			System.out.println("	2. Get the time until the next song starts");
			System.out.println("	3. Find when a particular song starts and ends during the year");
			System.out.println("	4. Find which song is playing on your birthday");
			System.out.println("	5. Find which song is playing on a particular date");
			System.out.println("	6. List the date ranges of all the songs\n\n");
			String s = scan.nextLine();
			
			int option = Integer.parseInt(s); 
			
			if(option==1) {
				System.out.println("\n\nToday's song is "+fourWinds.whichSongToday());
				System.out.print("\nWould you like to return to the menu? (y/n): ");
				String a1=scan.nextLine();
				if(a1.equals("y")) {
					loop = true;
				}
				else if(a1.equals("n")) {
					loop = false;
				}
			}
			if(option==2) {
				System.out.println("\nThe next song is "+fourWinds.nextSongChange());
				
				System.out.print("\nWould you like to return to the menu? (y/n): ");
				String b1=scan.nextLine();
				if(b1.equals("y")) {
					loop = true;
				}
				else if(b1.equals("n")) {
					loop = false;
				}
			}
			if(option==3) {
				boolean correctInput = false;
				
				while(!correctInput) {
					try {
						boolean keepGoing = true;
						while(keepGoing) {
							System.out.print("\n\nType in a song from Notos, Eurus, Boreas, or Zephyrus: ");
							String c = scan.nextLine();
							System.out.println("\nThe start of '"+c+"' is the "+fourWinds.periodOfTimeForThisSong(c).get(0).getDayOfMonth()+" of "+fourWinds.periodOfTimeForThisSong(c).get(0).getMonth()+", and the end is the "+fourWinds.periodOfTimeForThisSong(c).get(1).getDayOfMonth()+" of "+fourWinds.periodOfTimeForThisSong(c).get(1).getMonth()+". That is a "+(fourWinds.periodOfTimeForThisSong(c).get(1).getDayOfYear()-fourWinds.periodOfTimeForThisSong(c).get(0).getDayOfYear())+" day streak!");
							System.out.print("\nWould you like to input another song? (y/n): ");
							String c1=scan.nextLine();
							if(c1.equals("y")) {
								keepGoing = true;
							}
							else if(c1.equals("n")) {
								keepGoing = false;
							}
						}
						correctInput = true;
					}
					catch (IllegalArgumentException iae) {
						correctInput=false;
						System.out.println("\n"+iae.getMessage()+" Try Again!\n");
					}
				}
				
				System.out.print("\nWould you like to return to the menu? (y/n): ");
				String c2 = scan.nextLine();
				if(c2.equals("y")) {
					loop = true;
				}
				else if(c2.equals("n")) {
					loop = false;
				}
			}
			if(option==4) {
				boolean birthday = true;
				while(birthday) {
					System.out.print("\n\nEnter your birthday (mm-dd): ");
					String d = scan.nextLine();
					System.out.println("\nThe song that's on your birthday this year is "+fourWinds.theSongForMyBirthday(d));
					System.out.print("\nWould you like to input another birthday? (y/n): ");
					String d1=scan.nextLine();
					if(d1.equals("y")) {
						birthday = true;
					}
					else if(d1.equals("n")) {
						birthday = false;
					}
				}
				
				System.out.print("\nWould you like to return to the menu? (y/n): ");
				String d2 =scan.nextLine();
				if(d2.equals("y")) {
					loop = true;
				}
				else if(d2.equals("n")) {
					loop = false;
				}
			}
			if(option==5) {
				
				boolean date = true;
				while(date) {
					System.out.print("\n\nEnter any date you'd like (yyyy-mm-dd): ");
					String e = scan.nextLine();
					System.out.println("\nThe song that plays during that date is "+fourWinds.whichSongIsOnThisDate(e));
					System.out.print("\nWould you like to input another? (y/n): ");
					String e1=scan.nextLine();
					if(e1.equals("y")) {
						date = true;
					}
					else if(e1.equals("n")) {
						date = false;
					}
				}
				System.out.print("\nWould you like to return to the menu? (y/n): ");
				String e2 =scan.nextLine();
				if(e2.equals("y")) {
					loop = true;
				}
				else if(e2.equals("n")) {
					loop = false;
				}
				
			}
			
			if(option==6) {
				System.out.println(fourWinds.listOfRanges());
				System.out.print("\nWould you like to return to the menu? (y/n): ");
				String f1 = scan.nextLine();
				if(f1.equals("y")) {
					loop = true;
				}
				else if(f1.equals("n")) {
					loop = false;
				}
			}
			
			
			
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		
			
		

}
