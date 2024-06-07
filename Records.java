import java.util.StringTokenizer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

class Records // Separates a line of records into 6 parts
{
	/** 
	 * @param data[]
	 * @return String[][]
	 */
	public String[][] getRecords(String data[])
	{
		int n = data.length;
		String records[][] = new String[n][6]; // Stores records in a 2D array
		for(int p=0; p<n; p++) // Initialize all records
			for(int q=0; q<6; q++)
				records[p][q] = ""; // Initialize the 2D array to empty strings
		for(int line=0; line<n; line++) {
			StringTokenizer st = new StringTokenizer(data[line], "#");
			int i = 0;
			while(st.hasMoreTokens() && i<6) {
				records[line][i] = st.nextToken(); // Store each item of record
				i++;
			} // End while
		} // End for
		return records; // Return 2D array records
	} // End getRecords
} // End Records
