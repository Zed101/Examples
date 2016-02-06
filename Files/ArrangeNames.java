package ba.files.practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ArrangeNames {

	public static void main(String[] args) {

		File toRead = new File("names.txt"); // declare and initialize File in the project called names.txt
		File names = new File("onlyNames.txt"); // declare and initialize File in the project called onlyNames.txt
		File addresses = new File("addresses.txt"); // declare and initialize File in the project called addresses.txt

		Scanner reader = null; // declare Scanner
		FileWriter writeNames = null; // declare FileWriter
		FileWriter writeAddresses = null; // declare FileWriter

		try { // try to open reader to names.txt file and writers to two other files
			reader = new Scanner(toRead); // initialize Scanner with file to read from
			writeNames = new FileWriter(names); // initialize FileWriter with file to write to
			writeAddresses = new FileWriter(addresses);	// initialize FileWriter with file to write to

			String line = ""; // declare and initialize String that will hold single line read from file

			while (reader.hasNextLine()) { // read while there is something to read
				line = reader.nextLine(); // read next line

				writeNames.write(line.split(",")[0] + "\n"); // write name to the file, split line string in comma regex and take first [0 index] string from array, and add new line 
				writeAddresses.write(line.split(",")[1] + "\n"); // write name to the file, split line string in comma regex and take second [1 index] string from array, and add new line
				writeNames.flush(); // flush the writer
				writeAddresses.flush(); // flush the writer
			}

			System.out.println("Completed writing"); // print message to console so you know everything went well

		} catch (FileNotFoundException e) { // if File could not be found and opened for reading catch Exception and log messages
			System.out.println("File name.txt could not be found");
			System.err.println(e.getMessage());
			System.err.println(e);
		} catch (IOException e) { // if FileWriter could not open file for writing catch Exception and log messages
			System.out.println("Writer failed to write");
			System.err.println(e);
		} finally { // finally block will always be executed, so all streams are closed in it
			try { // try to close streams
				if (writeNames != null) { // avoid NullPointerException by checking for null
					writeNames.close();	// then close connection
				}
				if (writeAddresses != null) { // avoid NPE
					writeAddresses.close();
				}
				if (reader != null) { // avoid NPE
					reader.close();
				}
			} catch (IOException e) { // catch Exceptions if streams could not be closed
				System.out.println("Failed to close writers");
				System.err.println(e);
			}
		}
	}

}
