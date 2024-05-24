import java.io.*;
class ReadData //reads data from external file using readFile() method
{
	
	/** 
	 * @param fname
	 * @param max
	 * @return String[]
	 */
	public String[] readFile(String fname, int max) //fname = "records.txt"
	{
		String data[] = new String[max]; //max = maximum number of records
		for(int k=0; k<max;k++) //initialize data array to store max records
		{
			data[k] = "";
		}
		int i = 0; //start counting records
		try
		{
			DataInput in =  new DataInputStream(new FileInputStream(fname));
			String txt = in.readLine();
			while (txt!= null)
			{
				data[i++] = txt; //store record in array, data; and increment i
				txt = in.readLine();
			}//end while
		}catch (Exception e){};
		return data; //return the entire array of records
	}//end readFile
}//end ReadData
