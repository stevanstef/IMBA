import java.awt.*;
import java.io.File;
import javax.swing.*;

class DisplayMovie {
    
	/** 
	 * @param movieName
	 * @param movieYear
	 * @param movieGenre
	 * @param movieRating
	 * @param movieDescription
	 * @param imagePath
	 * @param i
	 * @param d
	 */
	public void display(String movieName, String movieYear, String movieGenre, String movieRating, String movieDescription, String imagePath, JPanel i, JTextArea d) {
        // Clear panels
		i.removeAll(); 
        d.removeAll();

		// Set panel visibility
        i.setVisible(true); 

        // Set movie details in text area
        d.setText(movieName + "\n");
        d.append(movieYear + "           " + movieGenre + "           " + movieRating + "\n");
        d.append(movieDescription); // Append movie description

        // Load and set movie image
        ImageIcon ii = new ImageIcon(imagePath);
        File imageFile = new File(imagePath);
        if (!imageFile.exists()) {
            ii = new ImageIcon("movie_images/noMov.jpg"); // Set default image if image doesn't exist
        }
        ii.setImage(ii.getImage().getScaledInstance(300, 350, Image.SCALE_DEFAULT)); // Resize image
        JLabel pic = new JLabel(ii);
        i.add(pic); // Add image to panel
    }
}
