import java.io.*;

class ReadData // Class to read data from an external file using the readFile() method
{
	/** 
	 * Reads data from a file and returns it as an array of strings.
	 * 
	 * @param fname the name of the file to read from
	 * @param max the maximum number of records to read
	 * @return String[] an array containing the records read from the file
	 */
	public String[] readFile(String fname, int max)
	{
		String data[] = new String[max]; // Initialize an array to store up to 'max' records
		for(int k = 0; k < max; k++) {data[k] = "";} // Initialize the data array to empty strings
		int i = 0; // Counter to keep track of the number of records read
		try {
			DataInput in = new DataInputStream(new FileInputStream(fname)); // Create a DataInputStream to read from the file
			String txt = in.readLine(); // Read the first line from the file
			while (txt != null) { // Continue reading lines until the end of the file is reached
				data[i++] = txt; // Store the current line in the data array and increment the counter
				txt = in.readLine(); // Read the next line from the file
			}
		} catch (Exception e) {} // Handle any exceptions that occur during file reading
		return data; // Return the entire array of records
	} // End of readFile method
} // End of ReadData class
