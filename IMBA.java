//Filename: IMBA.java
//Date: May 21st 2024

import java.awt.*;
import javax.swing.*;
import java.io.*;

public class IMBA extends Frame {

      //Declare variables and constants
		private JButton btn1;
      static String movieFile = "movieList.txt";      //movieFile = "movieList.txt"
      final int MAX = 40;                        //Set MAX number opf records
      String[][] movieArray = new String[40][40]; 
      /*ReadData rd = new ReadData();
      Records raRe = new Records();                
		Records moRe = new Records();
      Sorter s = new Sorter();
      SearchInput si = new SearchInput();
		DeleteRating dr = new DeleteRating();
		KeyInput ki = new KeyInput();
		AddRating nr = new AddRating();*/
		
		public void paint(Graphics g) {
         Toolkit tk = Toolkit.getDefaultToolkit();
         Image img = tk.getImage("Background.png");
         g.drawImage(img, 0, 30, this);     
    }//end paint
      
      public IMBA() { // constructor lo prepare window size and menubar
			btn1 = new JButton("Enter");  
			add(btn1);			
			setVisible(true);
			setLayout(new FlowLayout(FlowLayout.CENTER, 5, 440));
			setTitle("IMBA");
			resize(960, 540);
       	button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                // Insert code here
            }
        });
      }// end constructor
		
   	public static void main(String str[]){
         	new IMBA();// Instantiates LTS class
         	} //end main
}//end class   

