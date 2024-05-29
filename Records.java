import java.util.StringTokenizer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

class Records //seperates a line of records into 6 parts
{
 
 /** 
  * @param data[]
  * @return String[][]
  */
 public String[][] getRecords(String data[])
 {
  int n  = data.length;
  String records[][] = new String[n][6]; //stores records in a 2d array
  for(int p=0;p<n;p++) //initialize all records
   for(int q=0;q<6;q++)
      records[p][q]="";
  for(int line=0;line<n;line++)
  {
   StringTokenizer st = new StringTokenizer(data[line],"#");
   int i = 0;
   while(st.hasMoreTokens()&& i<6)
   {
        records[line][i] = st.nextToken(); //store each item of record
        i++;
   }//end while
  }//end for
  return records; //return 2d array records
 } //end getRecords
} //end Records