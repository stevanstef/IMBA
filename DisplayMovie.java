import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.Utilities; 
import java.awt.event.*;
import javax.swing.table.*;

class DisplayMovie
{
	public void display(String movieName, String movieYear, String movieGenre, String movieRating, String movieDescription, String imagePath, JPanel i, JTextArea d)
	{
	
	i.removeAll();
	d.removeAll();
    i.setVisible(true);
	d.setText(movieName + "\n");
	d.append(movieYear + "           " + movieGenre + "           " + movieRating + "\n");
	d.append(movieDescription);
	ImageIcon ii = new ImageIcon(imagePath);
	File imageFile = new File(imagePath);
	if (imageFile.exists()) {

	} else {
		ii = new ImageIcon("movie_images/noMov.jpg");
	}

	ii.setImage(ii.getImage().getScaledInstance(300, 350, Image.SCALE_DEFAULT));
	JLabel pic = new JLabel(ii);
	i.add(pic);
	}
}
