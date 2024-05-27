import java.util.StringTokenizer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

class AddRecord //seperates a line of records into 6 parts
{
 
 /** 
  * @param data[]
  * @return String[][]
  */
 public String[][] aRecord(String data[],String n, String y, String g, JTextArea d)
 {
  int j  = data.length;
  String records[][] = new String[j][6]; //stores records in a 2d array
  for(int p=0;p<j;p++) //initialize all records
   for(int q=0;q<6;q++)
      records[p][q]="";
  for(int line=0;line<j;line++)
  {
   if(data[line].equals(" "))
	{
		d.append(n + y + g);
	}
  }
  return records; //return 2d array records
 } //end getRecords
} //end Records