import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.Utilities; 
import java.awt.event.*;
import javax.swing.table.*;

class DisplayMovie
{
	public void display(String movieName, JPanel d)
	{
      d.setVisible(true);
		if (movieName.contains("Rush Hour")){

		}
		else if (movieName.contains("Kung Fu Panda")){
			System.out.println("Display Kung Fu Panda");
		}
		else if (movieName.contains("Jurassic Park")){
			System.out.println("Display Jurassic Park");
		}
		else if (movieName.contains("The Lord of the Rings: The Return of the King")){
			System.out.println("Display The Lord of the Rings: The Return of the King");
		}
		else if (movieName.contains("Up")){
			System.out.println("Display Up");
		}
		else if (movieName.contains("Inception")){
			System.out.println("Display Inception");
		}
		else if (movieName.contains("Interstellar")){
			System.out.println("Display Interstellar");
		}
		else if (movieName.contains("Oppenheimer")){
			System.out.println("Display Oppenheimer");
		}
		else if (movieName.contains("Pulp Fiction")){
			System.out.println("Display Pulp Fiction");
		}
		else if (movieName.contains("Dune Part Two")){
			System.out.println("Display Dune Part Two");
		}
		else if (movieName.contains("The Matrix")){
			System.out.println("Display The Matrix");
		}
		else if (movieName.contains("Fight Club")){
			System.out.println("Display Fight Club");
		}
		else if (movieName.contains("Shrek")){
			System.out.println("Display Shrek");
		}
		else if (movieName.contains("Rio")){
			System.out.println("Display Rio");
		}
		else if (movieName.contains("Avengers: Endgame")){
			System.out.println("Display Avengers: Endgame");
		}
		else if (movieName.contains("Harry Potter and the Philosopher's Stone")){
			System.out.println("Display Harry Potter and the Philosopher's Stone");
		}
		else if (movieName.contains("Planet Earth")){
			System.out.println("Display Planet Earth");
		}
		else if (movieName.contains("Akira")){
			System.out.println("Display Akira");
		}
		else if (movieName.contains("Spirited Away")){
			System.out.println("Display Spirited Away");
		}
		else if (movieName.contains("Your Name")){
			System.out.println("Display Your Name");
		}
		else{
			System.out.println("Display Generic Movie");
		}
	}
}
