package StepDefinitions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Report{

	private static String filename_time = "";
	public static String filename;
	
	public static void CreateFile(String filename) {
		try {	        
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss");
	        String formattedDateTime = currentDateTime.format(formatter);
	        filename_time = formattedDateTime.toString();
	        Report.filename = filename + filename_time;
	        String currentPath = System.getProperty("user.dir");
	        System.out.println("Current working directory: " + currentPath);
	        
	        File myObj = new File("Reports/"+Report.filename + ".txt");
		    if (myObj.createNewFile()) {
		    	System.out.println("File created: " + myObj.getName());
		    } else {
		        System.out.println("File already exists.");
		    }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		} 
	
	public static void WriteToFile(String filename, String msg, boolean success)
	{
	
		 LocalDateTime currentDateTime = LocalDateTime.now();
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss");
	     String formattedDateTime = currentDateTime.format(formatter);
	     String time = formattedDateTime.toString();
	     
	     try (FileWriter myWriter = new FileWriter("Reports/"+filename + ".txt", true)) {
	    	 if(success) {
		    	 myWriter.write(" | " + time + " | " + "PASSED - " + msg + " | " + "\n");
		     }
		     else {
		    	 myWriter.write(" | " + time + " | " + "FAILED - " + msg + " | " + "\n");
		    	 myWriter.write(" | " + time + " | " + " - END - | " + "\n");
		     }
	     } catch (IOException e) {
	    	 System.out.println("An error occurred.");
	    	 e.printStackTrace();
	     }
	}  
	
}