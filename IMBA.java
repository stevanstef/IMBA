//Filename: IMBA.java
//Date: May 21st 2024

import java.awt.*;
import javax.swing.*;
import java.io.*;

public class IMBA extends Frame {

      //Declare variables and constants
		private JButton btn1;
      static String movieFile = "movieList.txt";      //movieFile = "movieList.txt"
		static String ratingFile = "ratingList.txt";      //ratingFile = "ratingList.txt"
      final int MAX = 40;                        //Set MAX number opf records
      String[][] movieArray = new String[40][40]; 
		String[][] ratingArray = new String[40][40];
      /*ReadData rd = new ReadData();
      Records raRe = new Records();                
		Records moRe = new Records();
      Sorter s = new Sorter();
      SearchInput si = new SearchInput();
		DeleteRating dr = new DeleteRating();
		KeyInput ki = new KeyInput();
		AddRating nr = new AddRating();*/
      
      public IMBA() { // constructor lo prepare window size and menubar
		   btn1 = new JButton("Enter");
			setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
			add(btn1);
			setTitle("IMBA");
			setSize(960, 540);
			setVisible(true);
      }// end constructor
  /* public boolean action(Event evt, Object obj) {// Activate menuitem
         if(evt.target instanceof MenuItem) {
            String label = (String) obj;
         if (label.equals("Quit"))
            System.exit(0); // quit window
            
         else if (label.equals("Load") ){
            System.out.println("Load coming up !");
            rows = rd.readFile(fileName, 10);
            info = re.getRecords(rows);}
         else if (label.equals("List") ){
            System.out.println("List coming up !");}
         else if (label.equals("New"))
            System.out.println("New coming up !" );
         else if (label.equals("Delete")){
            System.out.println("Delete coming up !" );
				si2.keyInput(fileName, info, item);}
         else if (label.equals("Print") )
            System.out.println("Print coming up !" );
         else if (label.equals("Names")){
            System.out.println("Names coming up !");
            info = s.sort(info, 0);}
         else if (label.equals("IDs")){
            System.out.println("IDs coming up !");
				info = s.sort(info, 1);}
         else if (label.equals("Homeforms")){
            System.out.println("Homeforms coming up !");
				info = s.sort(info, 2);}
         else if (label.equals("Periods")){
            System.out.println("Periods coming up !");
				info = s.sort(info, 5);}
            
            
         else if (label.equals("By Names")){
            System.out.println("By Names coming up !");
				si.keyInput(fileName, info, 0);}
         else if (label.equals("By IDs"))
            System.out.println("By IDs coming up !");      
         else if (label.equals("By Homeforms"))
            System.out.println("By Homeforms coming up !");
         else if (label.equals("By Periods"))
            System.out.println("By Periods coming up !");
            
         else if (label.equals("Help")){
            System.out.println("Help coming up !");
				System.out.println("\n(1) Select Load from Data menu first.");
				System.out.println("\n(2) Select any menu item.");
				System.out.println("\n(3) Select List from Data to view results.");}
         }
         return true;
   }//end action*/

   
   public void paint(Graphics g) {
         Toolkit tk = Toolkit.getDefaultToolkit();
         Image img = tk.getImage("Background.png");
         g.drawImage(img, 0, 30, this);     
    }//end paint


   
   
   public static void main(String str[]){
         new IMBA();// Instantiates LTS class
         } //end main
   }//end class   

