import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class main {
	/**
	 * This method validates the price and throws the corresponding exception if it's wrong.
	 * @param x is the price
	 * @param pw is the PrintWriter obj that reads the price
	 * @param line is the line containing the price
	 */
	
	public static void validPrice(String x,PrintWriter pw,String line){
        double y = Double.parseDouble(x);
        if (y < 0) {
        	 try {
             	throw new BadPriceException();
             }
             catch(BadPriceException e) {
             	pw.println("Error: Invalid Price");
             	pw.println("Record: "+line);
             	pw.println();
             }
        }
        
    }
	/**
	 * This method validates the year and throws the corresponding exception if it's wrong.
	 * @param x is the year
	 * @param pw is the PrintWriter obj that reads the year
	 * @param line is the line containing the year
	 */

    public static void validYear(String x,PrintWriter pw,String line){
        int y = Integer.parseInt(x);
        if (!((y >= 1995) && (y <= 2010))) {
            try {
            	throw new BadYearException();
            }
            catch(BadYearException e) {
            	pw.println("Error: Invalid Year");
            	pw.println("Record: "+line);
            	pw.println();
            }
        }
        
    }
    /**
	 * This method validates the ISBN and throws the corresponding exception if it's wrong.
	 * @param x is the ISBN
	 * @param pw is the PrintWriter obj that reads the ISBN
	 * @param line is the line containing the ISBN
	 */

 public static void validISBN(String x,PrintWriter pw,String line){
    	
    	int sum = 0;
        if (x.length() == 10){
        	try {
            for (int i=0; i<x.length(); i++){
            	
                int z = Integer.parseInt(String.valueOf(x.charAt(i)));
                sum += z*(x.length()-i);
            }
            if (!(sum%11 == 0)){
            	
            	try {
            		
                	throw new  BadIsbn10Exception();
                }
                catch(BadIsbn10Exception e) {
                	
                
                	pw.println("Error: Invalid ISBN10");
                	pw.println("Record: "+line);
                	pw.println();
                }
            } 
        	}
        	catch(Exception e2) {
        		
        		pw.println("Error: Invalid ISBN10");
            	pw.println("Record: "+line);
            	pw.println();
        	}
        }else if (x.length() == 13){
        	
            try {
            	for (int i=0; i<x.length(); i++){
                    int z = Integer.parseInt(String.valueOf(x.charAt(i)));
                    if (!(i%2 == 0)){
                        z *= 3;
                    }
                sum +=z;
                }
                if (!(sum%10 == 0)){
                	try {
                    	throw new BadIsbn13Exception();
                    }
                    catch(BadIsbn13Exception e) {
                    	
                    	pw.println("Error: Invalid ISBN13");
                    	pw.println("Record: "+line);
                    	pw.println();
                    }
                } 
            }
            catch(Exception e2) {
            	
            	pw.println("Error: Invalid ISBN10");
            	pw.println("Record: "+line);
            	pw.println();
            }
        }
        
    }
 /**
	 * lineCounter will counter (return) the number of lines in each file separately as parameter of s
	 * @param s is the name of the file
	 * @return number of lines in the file
	 * @throws IOException
	 */

    public static boolean validGenre(String x){
        if ((x == "CCB") || (x == "HCB") || (x == "MTV") || (x == "MRB") || (x == "NEB") || (x == "OTR") || (x == "SSM") || (x == "TPA")){
            return true;
        }else return false;
    }
	
	/**
	 * lineCounter will counter (return) the number of lines in each file separately as parameter of s
	 * @param s is the name of the file
	 * @return number of lines in the file
	 * @throws IOException
	 */
	public static int lineCounterFile(String s) throws IOException {
		BufferedReader br = null;
		int counter = 0;
		String line;
		br =  new BufferedReader(new FileReader(s));
		while ((line = br.readLine()) != null) {
                counter++;
            }
		br.close();
		return counter;
		
		
	}
	/**
	 * Creates an array of lines
	 * @param fileName
	 * @return the array of lines in the file
	 * @throws IOException
	 */
	 public static String[] createArrayOfLines(String fileName) throws IOException {
			
		 
		 	String line;
		    String [] linesArray = new String [lineCounterFile(fileName)];
		    BufferedReader br =  new BufferedReader(new FileReader(fileName));
 	
				for(int i = 0; i < linesArray.length;i++) {
					line = br.readLine();
					linesArray[i] = line;
				}
 		br.close();
 		return linesArray;
 		
}
	 /**
		 * This method checks if an element of the array is an empty String
		 * @param array of Strings representing one line
		 * @return a boolean value, false if an element is an empty String and true otherwise
		 */
	 public static boolean emptyIndex(String[] array) {
		 for(int i = 0; i < array.length;i++) {
			 if(array[i] == "") {
				 return false;
			 }
		 }
		 return true;
	 }
	 /**
		 *  This method checks for all the syntax errors and writes the "syntax_error_file.txt" and all the genre files.
		 * @param br bufferedReader obj to read from the files
		 * @param files
		 * @throws IOException
		 * @throws TooFewFieldsException
		 * @throws TooManyFieldsException
		 */
	 public static void checking(BufferedReader br,String[] files) throws IOException,TooFewFieldsException, TooManyFieldsException{
		 
		PrintWriter pw =  new PrintWriter(new FileOutputStream("syntax_error_file.txt",true));
		PrintWriter pw1 =  new PrintWriter(new FileOutputStream("Cartoons_Comics.csv.txt",true));
		PrintWriter pw2 =  new PrintWriter(new FileOutputStream("Hobbies_Collectibles.csv.txt",true));
		PrintWriter pw3 =  new PrintWriter(new FileOutputStream("Movies_TV.csv.txt",true));
		PrintWriter pw4 =  new PrintWriter(new FileOutputStream("Music_Radio_Books.csv.txt",true));
		PrintWriter pw5 =  new PrintWriter(new FileOutputStream("Nostalgia_Eclectic_Books.csv.txt",true));
		PrintWriter pw6 =  new PrintWriter(new FileOutputStream("Old_Time_Radio_Books.csv.txt",true));
		PrintWriter pw7 =  new PrintWriter(new FileOutputStream("Sports_Sports_Memorabilia.csv.txt",true));
		PrintWriter pw8 =  new PrintWriter(new FileOutputStream("Trains_Planes_Automobiles.csv.txt",true));
		
		int CCBcounter = 0;
		int HCBcounter = 0;
		int MTVcounter = 0;
		int MRBcounter = 0;
		int NEBcounter = 0;
		int OTRcounter = 0;
		int SSMcounter = 0;
		int TPAcounter = 0;
		
		
		
		 for(int i  = 0; i < files.length;i++) {
			String [] arrayOfLines =  createArrayOfLines(files[i]);
			
			pw.println("syntax error in file: " + files[i]);
			pw.println("====================");
			
			
			
			for(int j = 0; j < arrayOfLines.length;j++) {
				String line = arrayOfLines[j];
				String [] arrayOfData;
				
				//if statement for the title
			
				
				if(line.charAt(0) == '"') { //checking the title with the quotes
					int counter = 3;
					for(int k = 1; k < line.length();k++) {
						if(line.charAt(k) == '"') {
							break;
    						}
    					counter++;
				}
				
				String lineNoTitle = line.substring(counter);
				arrayOfData = lineNoTitle.split(",");
				
				for(int k = 0; k <arrayOfData.length;k++) {
		        	//System.out.println(arrayOfData[k]);
		       }	
			}
				else {//checking the title without the quotes
					int counter = 2;
					for(int k = 1; k < line.length();k++) {
						if(line.charAt(k) == ',') {
							break;
						}
						counter++;
					}
					String lineNoTitle = line.substring(counter);
					 arrayOfData = lineNoTitle.split(",");
    				for(int k = 0; k <arrayOfData.length;k++) {
    		        	//System.out.println(arrayOfData[k]);
    		       }
				}
				//beginning
				
				
				//if statement for the title

				if(line.charAt(0) == ',') {
					try{
						throw new MissingFieldException();
					}
					catch(MissingFieldException e) {
						pw.println("Error: missing title");
						pw.println("Record: " + line);
						pw.println();
					}
					
				 
				 
				}
				if(arrayOfData.length > 5) {
					try{
						throw new TooManyFieldsException();
					}
					catch(TooManyFieldsException e) {
						String s = e.getMessage();
						
					}
				}
				else if(arrayOfData.length < 5) {
					try{
						throw new TooFewFieldsException();
					}
					catch(TooFewFieldsException e) {
						String s = e.getMessage();
						
					}
				}
				if(arrayOfData.length == 5) {
					

					if(arrayOfData[0] == "") {
						try{
							throw new MissingFieldException();
						}
						catch(MissingFieldException e) {
							
							pw.println("Error: missing authors");
							pw.println("Record: " + line);
						}
						
					}
					if(arrayOfData[1] == "") {
						try{
							throw new MissingFieldException();
						}
						catch(MissingFieldException e) {
							pw.println("Error: missing price");
							pw.println("Record: " + line);
						}
						
					}
					if(arrayOfData[2] == "") {
						try{
							throw new MissingFieldException();
						}
						catch(MissingFieldException e) {
							pw.println("Error: missing ISBN");
							pw.println("Record: " + line);
						}
						
					}
					if(arrayOfData[3] == "") {
						try{
							throw new MissingFieldException();
						}
						catch(MissingFieldException e) {
							pw.println("Error: missing genre");
							pw.println("Record: " + line);
						}
						
					}
					if(arrayOfData[4] == "") {
						try{
							throw new MissingFieldException();
						}
						catch(MissingFieldException e) {
							pw.println("Error: missing year");
							pw.println("Record: " + line);
						}
						
					}
					if(!(validGenre(arrayOfData[3]))){
						try{
							throw new UnknownGenreException();
						}
						catch(UnknownGenreException e) {
							pw.println("Error: invalid genre");
							pw.println("Record: " + line);
						}
						
					}
					
					if(true) {
						
					
						if(arrayOfData[3].equals("CCB") && emptyIndex(arrayOfData)) {
							
							pw1.println(line);
							CCBcounter++;
						}
						else if(arrayOfData[3].equals("HCB") && emptyIndex(arrayOfData)) {
							pw2.println(line);
							HCBcounter++;
						}
						else if(arrayOfData[3].equals("MTV") && emptyIndex(arrayOfData)) {
							pw3.println(line);
							MTVcounter++;
						}
						else if(arrayOfData[3].equals("MRB") && emptyIndex(arrayOfData)) {
							pw4.println(line);
							MRBcounter++;
						}
						else if(arrayOfData[3].equals("NEB") && emptyIndex(arrayOfData)) {
							pw5.println(line);
							NEBcounter++;
						}
						else if(arrayOfData[3].equals("OTR") && emptyIndex(arrayOfData)) {
							pw6.println(line);
							OTRcounter++;
						}
						else if(arrayOfData[3].equals("SSM") && emptyIndex(arrayOfData)) {
							pw7.println(line);
							SSMcounter++;
						}
						else if(arrayOfData[3].equals("TPA") && emptyIndex(arrayOfData)) {
							pw8.println(line);
							TPAcounter++;
						}
						
						
						
						
						
					}
					
					
					
					
				}
				
				
		}	 	
			
	 }
		    pw1.println(CCBcounter);
			pw2.println(HCBcounter);
			pw3.println(MTVcounter);
			pw4.println(MRBcounter);
			pw5.println(NEBcounter);
			pw6.println(OTRcounter);
			pw7.println(SSMcounter);
			pw8.println(TPAcounter);

		 
	    		br.close();
	    		pw.close();
	    		pw1.close();
	    		pw2.close();
	    		pw3.close();
	    		pw3.close();
	    		pw4.close();
	    		pw5.close();
	    		pw6.close();
	    		pw7.close();
	    		pw8.close();
	    		
	    		}
	    		
	 /**
		 * This method executes all the necessary methods to do part 1.
		 * It reads "part1_input_file_names.txt" file and uses its information to write all the files needed for part 2.
		 */
	public static void do_part1() {
		BufferedReader br = null;
        PrintWriter pw = null;
        String [] files = new String[16];
        try {
        	pw = new PrintWriter(new FileOutputStream("text_part1"));
            br = new BufferedReader(new FileReader("part1_input_file_names.txt"));
            
            String line;
            br.readLine(); //skip the first line
                for(int i = 0; i < files.length;i++) {
                	line  = br.readLine();
                	files[i] = line;
                }
            
            br.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace(); //like e.getMessage()
;	        }
        catch (Exception e){
            e.printStackTrace();
        }
      
        
        try {
			checking(br,files);
		} catch (IOException | TooFewFieldsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TooManyFieldsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}
	/**
	 * This method checks for semantic errors and prints the file: "semantic_error_file.txt"
	 * @throws IOException
	 */
	
	public static void reading() throws IOException {
		ObjectOutputStream oos = null;
		
		
		BufferedReader br = null;
		PrintWriter pw =  new PrintWriter(new FileOutputStream("semantic_error_file.txt",true));
		String [] textFilesPart1 = {"Cartoons_Comics.csv.txt","Hobbies_Collectibles.csv.txt","Movies_TV.csv.txt","Music_Radio_Books.csv.txt","Nostalgia_Eclectic_Books.csv.txt",
				"Old_Time_Radio_Books.csv.txt","Sports_Sports_Memorabilia.csv.txt","Trains_Planes_Automobiles.csv.txt","syntax_error_file.csv.txt"};
		String [] textFilesPart2 = {"Cartoons_Comics.csv.ser.txt","Hobbies_Collectibles.csv.ser.txt","Movies_TV.csv.ser.txt","Music_Radio_Books.csv.ser.txt","Nostalgia_Eclectic_Books.csv.ser.txt",
				"Old_Time_Radio_Books.csv.ser.txt","Sports_Sports_Memorabilia.csv.ser.txt","Trains_Planes_Automobiles.csv.ser.txt","syntax_error_file.csv.ser.txt"};
		
		try {
			 br = new BufferedReader(new FileReader("part1_input_file_names.txt"));
			
		}
		catch (FileNotFoundException e){
	            e.printStackTrace(); //like e.getMessage()
		        }
	    catch (Exception e){
	            e.printStackTrace();
	        }
		for(int i = 0; i < textFilesPart1.length-1;i++) {//lenght -1
			String [] arrayOfLines =  createArrayOfLines(textFilesPart1[i]);
			
			Book[] bookArray = new Book[lineCounterFile(textFilesPart1[i])-1];
			pw.println("syntax error in file: " + textFilesPart1[i]);
			pw.println("====================");
			int z = 0;
			for(int j = 0; j < arrayOfLines.length;j++) {
				String line = arrayOfLines[j];
				String []arrayOfData = new String[6];
				String [] arrayOfDataNoTitle;
				if (line.length() < 6) {//skips the last line since it is just a number of lines
					break;
				}
				if(line.charAt(0) == '"') {
					int counter = 3;
					for(int k = 1; k < line.length();k++) {
						if(line.charAt(k) == '"') {
							break;
							}
						counter++;
				}
				
			    String title = line.substring(0,counter-1);
			    arrayOfData[0] = title; 
			   // System.out.println(title);
				String lineNoTitle = line.substring(counter);
				arrayOfDataNoTitle = lineNoTitle.split(",");
				
				for(int k = 1; k <arrayOfData.length;k++) { //creating an array of 6 pieces
		        	arrayOfData[k] = arrayOfDataNoTitle[k-1];
		       }
				for(int k = 0; k <arrayOfData.length;k++) {
		        	//System.out.println(arrayOfData[k]);
		       }
				
			}   
				
				else {
					int counter = 1;
					for(int k = 1; k < line.length();k++) {
						if(line.charAt(k) == ',') {
							break;
						}
						counter++;
					}
					String title = line.substring(0,counter);
					
				    arrayOfData[0] = title; 
				    //System.out.println(title);
					String lineNoTitle = line.substring(counter+1);
					
					
					arrayOfDataNoTitle = lineNoTitle.split(",");
					
					 
					for(int k = 1; k <arrayOfData.length;k++) { //creating an array of 6 pieces
			        	arrayOfData[k] = arrayOfDataNoTitle[k-1];
			       }
					for(int k = 0; k <arrayOfData.length;k++) {
			        	//System.out.println(arrayOfData[k]);
			       }
				}
				
				
				validPrice(arrayOfData[2],pw,line);
				validYear(arrayOfData[5],pw,line);
				validISBN(arrayOfData[3],pw,line);
				
				//creating a book object
				bookArray[z] = new Book(arrayOfData[0],arrayOfData[1],Double.parseDouble(arrayOfData[2]),arrayOfData[3],arrayOfData[4],Integer.parseInt(arrayOfData[5]));
				z++;
				
			}
			
			try {
				oos = new ObjectOutputStream(new FileOutputStream(textFilesPart2[i],true));
				for(int l = 0; l < bookArray.length;l++)
				oos.writeObject(bookArray[l]);
				
			}
			catch(IOException e) {
				
				
			}
			}
		
		 oos.close();
		 br.close();
		 pw.close();
		
	}
	/**
	 * This method executes the needed method for part 2
	 */
	public static void do_part2() {
		try {
			reading();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method is used to read the object and put it in an array of Book objects
	 * @param selectedFile
	 * @param record
	 * @return the array of Book Objects
	 * @throws IOException
	 */
	
	public static Book[] binaryFilesReading(String selectedFile,int record) throws IOException {
		ObjectInputStream ois = null;
		Book [] BookObjects = new Book[record];
		//"Cartoons_Comics.csv.ser.txt";
		
		int j = 1;
			try
			{
				// Create an ObjectOutputStream to write into the binary file 
				ois = new ObjectInputStream(new FileInputStream(selectedFile));
				Book bk;
				try
				{
					while(true)
					{
						for(int i = 0; i < BookObjects.length;i++) {
							bk = (Book)ois.readObject();	
							BookObjects[i] = bk;
						
							
						}
						
				}
					}
				
				catch(ClassNotFoundException e)
				{
					
				}
				catch(EOFException e)
				{
					
				}
						}
			catch(FileNotFoundException e)
			{
				
				//System.exit(0);		
			}
			catch(IOException e)
			{
				
				//System.exit(0);			
			}
			
		ois.close();		// Close the file*/
		//System.out.println();
		return BookObjects;
		
		
		
	}
	
	/**
	 * This method counts the record number
	 * @param fileName
	 * @return record number
	 * @throws IOException
	 */
	
	public static int recordNumber(String fileName) throws IOException {
		
		int recordNo = 0;
		recordNo = lineCounterFile(fileName)-1;
			 
		return recordNo;
		
	}
	
	/**
	 * This method is part 3, it displays the information about the books entered by the user
	 * @throws IOException
	 */
	
	public static void do_part3() throws IOException {
		String [] textFilesPart1 = {"Cartoons_Comics.csv.txt","Hobbies_Collectibles.csv.txt","Movies_TV.csv.txt","Music_Radio_Books.csv.txt","Nostalgia_Eclectic_Books.csv.txt",
				"Old_Time_Radio_Books.csv.txt","Sports_Sports_Memorabilia.csv.txt","Trains_Planes_Automobiles.csv.txt","syntax_error_file.csv.txt"};
		String [] textFilesPart2 = {"Cartoons_Comics.csv.ser.txt","Hobbies_Collectibles.csv.ser.txt","Movies_TV.csv.ser.txt","Music_Radio_Books.csv.ser.txt","Nostalgia_Eclectic_Books.csv.ser.txt",
				"Old_Time_Radio_Books.csv.ser.txt","Sports_Sports_Memorabilia.csv.ser.txt","Trains_Planes_Automobiles.csv.ser.txt","syntax_error_file.csv.ser.txt"};
		
		String selectedFile = textFilesPart2[0]; //declaring the default variables
		int record = recordNumber(textFilesPart1[0]);
		int temp_beginning;
		
		
		Scanner input = new Scanner(System.in);
		boolean sentinel = true;
		while(sentinel) {
			System.out.println("---------------------------");
			System.out.println("         Main Menu         ");
			System.out.println("---------------------------");
			System.out.println(" v View the selected file: " + selectedFile);
			System.out.println(" s Select a file to view");
			System.out.println(" x Exit");
			System.out.println("---------------------------");
			System.out.println();
			System.out.print("Enter Your Choice: ");
			
			String userInput = input.next();
			
			if(userInput.equals("x")) {
				sentinel = false; //stop the loop
			}
			else if(userInput.equals("s")) {
				boolean sentinel2 = true;
				while(sentinel2) {
				
				System.out.println("---------------------------");
				System.out.println("       File Sub-Menu       ");
				System.out.println("---------------------------");
				for(int i = 0; i < textFilesPart2.length-1;i++) {
					System.out.print(i+1 + "  "); System.out.printf("%-40s",textFilesPart2[i]); System.out.println("(" + recordNumber(textFilesPart1[i]) + " records)");
				}
				System.out.println("9  Exit");
				System.out.println("---------------------------\n");
				System.out.print("Enter Your Choice ");
				String userInput2 = input.next();
				
				if(userInput2.equals("1")) {
					selectedFile = textFilesPart2[0];
					record = recordNumber(textFilesPart1[0]);
					sentinel2 = false;
					
				}
				else if(userInput2.equals("2")) {
					selectedFile = textFilesPart2[1];
					record = recordNumber(textFilesPart1[1]);
					sentinel2 = false;
				}
				else if(userInput2.equals("3")) {
					selectedFile = textFilesPart2[2];
					record = recordNumber(textFilesPart1[2]);
					sentinel2 = false;
				}
				else if(userInput2.equals("4")) {
					selectedFile = textFilesPart2[3];
					record = recordNumber(textFilesPart1[3]);
					sentinel2 = false;
				}
				else if(userInput2.equals("5")) {
					selectedFile = textFilesPart2[4];
					record = recordNumber(textFilesPart1[4]);
					sentinel2 = false;
				}
				else if(userInput2.equals("6")) {
					selectedFile = textFilesPart2[5];
					record = recordNumber(textFilesPart1[5]);
					sentinel2 = false;
				}
				else if(userInput2.equals("7")) {
					selectedFile = textFilesPart2[6];
					record = recordNumber(textFilesPart1[6]);
					sentinel2 = false;
				}
				else if(userInput2.equals("8")) {
					selectedFile = textFilesPart2[7];
					record = recordNumber(textFilesPart1[7]);
					sentinel2 = false;
				}
				else if(userInput2.equals("9")) {
					sentinel2 = false; //exit the loop
				}
				
				}	
			}
			
			else if(userInput.equals("v")) {
				int pointer = 0;
				System.out.println("viewing: " + selectedFile + "   (" + record + " records)\n" );
				
				boolean sentinel3 = true;
				Book[] BookArray = binaryFilesReading(selectedFile,record);
				while(sentinel3) {
					
					System.out.print("\nEnter an integer 'n': ");
					int userInput3 = input.nextInt();
				
					
					System.out.println("\nDisplaying the objects stored in file: \"" + selectedFile + "\".");
					System.out.println("================================================================ ");
					
					int positive_input = Math.abs(userInput3);
					
					if(userInput3 == 0) {
						sentinel3 = false;
					}
					else if (userInput3 == 1) {
						
						
						for(int k = pointer; k < pointer+1;k++) {
							System.out.println("\nHere is the information of "+ selectedFile +"book # " + (k+1) + ":");
							System.out.println("====================================== ");
							System.out.println(BookArray[k]);
							
						}
						
						
					}
					else if((userInput3> 1) && (userInput3 + pointer < record) ) {
						
						for(int k = pointer; k < pointer+userInput3;k++) {
							System.out.println(k);
							System.out.println("\nHere is the information of "+ selectedFile +"book # " + (k+1) + ":");
							System.out.println("====================================== ");
							System.out.println(BookArray[k]);
							
						}
						
						pointer += userInput3-1;
						
						
					}
					
					else if((userInput3 < 0) && (pointer + userInput3 >= 0)) {
						
						temp_beginning = pointer+userInput3+1;
						
						
						for(int k = temp_beginning ; k < pointer+1;k++) {
							System.out.println("\nHere is the information of "+ selectedFile +"book # " + (k+1) + ":");
							System.out.println("====================================== ");
							System.out.println(BookArray[k]);
							
						}
						
						pointer = pointer - positive_input +1;
					}
					else if(pointer + userInput3 > record-1) {
						
						for(int k = pointer; k < record;k++) {
							System.out.println("\nHere is the information of "+ selectedFile +"book # " + (k+1) + ":");
							System.out.println("====================================== ");
							System.out.println(BookArray[k]);
						}
						pointer = record -1;
						
						
					}
					else if (pointer + userInput3 < 0) {
						
						for(int k = 0; k < pointer+1;k++) {
							System.out.println("\nHere is the information of "+ selectedFile +"book # " + (k+1) + ":");
							System.out.println("====================================== ");
							System.out.println(BookArray[k]);
						}
						pointer = 0;
						
					}
					
					//System.out.println("The pointer is: " + pointer);
					//System.out.println("The pointer  + input is: " + (pointer +userInput3));
				}
			}
		}
		input.close();
		
	
	}
	 
	public static void main(String[] args) throws IOException {
		do_part1();
		do_part2();
		do_part3();

	}

        
}
