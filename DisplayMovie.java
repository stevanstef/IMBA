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
	public void display(String movieName, JPanel i, JTextArea d)
	{
	  String imagePath = "";
	  i.removeAll();
      i.setVisible(true);
		if (movieName.contains("Rush Hour")){
			imagePath = "movie_images/rush_hour.jpg";
			d.setText("1998            Comedy            7.0/10 \n \n A loyal and dedicated Hong Kong Inspector teams up with a reckless and loudmouthed L.A.P.D. detective to rescue the Chinese Consul's kidnapped daughter, while trying to arrest a dangerous crime lord along the way.");
		}
		else if (movieName.contains("Kung Fu Panda")){
			imagePath = "movie_images/panda.jpg";
			d.setText("2008            Family            7.6/10 \n \n To everyone's surprise, including his own, Po, an overweight, clumsy panda, is chosen as protector of the Valley of Peace. His suitability will soon be tested as the valley's arch-enemy is on his way.");
		}
		else if (movieName.contains("Jurassic Park")){
			imagePath = "movie_images/jurassic_park.jpg";
			d.setText("1993         Adventure            8.2/10 \n \n A pragmatic paleontologist touring an almost complete theme park on an island in Central America is tasked with protecting a couple of kids after a power failure causes the park's cloned dinosaurs to run loose.");
		}
		else if (movieName.contains("The Lord of the Rings: The Return of the King")){
			imagePath = "movie_images/lotr.jpg";
			d.setText("2003         Adventure            9.0/10 \n \n Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.");
		}
		else if (movieName.contains("Up")){
			imagePath = "movie_images/up.jpg";
			d.setText("2009            Family            8.3/10 \n \n 78-year-old Carl Fredricksen travels to Paradise Falls in his house equipped with balloons, inadvertently taking a young stowaway.");
		}
		else if (movieName.contains("Inception")){
			imagePath = "movie_images/inception.jpg";
			d.setText("2010            Action            8.8/10 \n \n A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.");
		}
		else if (movieName.contains("Interstellar")){
			imagePath = "movie_images/interstellar.jpg";
			d.setText("2014            Sci-Fi            8.7/10 \n \n When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.");
		}
		else if (movieName.contains("Oppenheimer")){
			imagePath = "movie_images/oppenheimer.jpg";
			d.setText("2023            Drama             8.3/10 \n \n The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb.");
		}
		else if (movieName.contains("Pulp Fiction")){
			imagePath = "movie_images/pulp_fiction.jpg";
			d.setText("1994            Action            8.9/10 \n \n The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.");
		}
		else if (movieName.contains("Dune Part Two")){
			imagePath = "movie_images/dune.jpg";
			d.setText("2024            Sci-Fi            8.6/10 \n \n Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.");
		}
		else if (movieName.contains("The Matrix")){
			imagePath = "movie_images/matrix.jpg";
			d.setText("1999            Sci-Fi            8.7/10 \n \n When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.");
		}
		else if (movieName.contains("Fight Club")){
			imagePath = "movie_images/fight_club.jpg";
			d.setText("1999            Drama             8.8/10 \n \n An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more.");
		}
		else if (movieName.contains("Shrek")){
			imagePath = "movie_images/shrek.jpg";
			d.setText("2001            Family            7.9/10 \n \n A mean lord exiles fairytale creatures to the swamp of a grumpy ogre, who must go on a quest and rescue a princess for the lord in order to get his land back.");
		}
		else if (movieName.contains("Rio")){
			imagePath = "movie_images/rio.jpg";
			d.setText("2011            Family            6.9/10 \n \n When Blu, a domesticated macaw from small-town Minnesota, meets the fiercely independent Jewel, he takes off on an adventure to Rio de Janeiro with the bird of his dreams.");
		}
		else if (movieName.contains("Avengers: Endgame")){
			imagePath = "movie_images/endgame.jpg";
			d.setText("2019            Action            8.4/10 \n \n After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.");
		}
		else if (movieName.contains("Harry Potter and the Philosopher's Stone")){
			imagePath = "movie_images/harry_potter.jpg";
			d.setText("2001            Fantasy            7.6/10 \n \n An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world.");
		}
		else if (movieName.contains("Planet Earth")){
			imagePath = "movie_images/planet_earth.jpg";
			d.setText("2006         Documentary          9.4/10 \n \n A documentary series on the wildlife found on Earth. Each episode covers a different habitat: deserts, mountains, deep oceans, shallow seas, forests, caves, polar regions, fresh water, plains and jungles.");
		}
		else if (movieName.contains("Akira")){
			imagePath = "movie_images/akira.jpg";
			d.setText("1988            Action            8.0/10 \n \n A secret military project endangers Neo-Tokyo when it turns a biker gang member into a rampaging psychic psychopath who can only be stopped by a teenager, his gang of biker friends and a group of psychics.");
		}
		else if (movieName.contains("Spirited Away")){
			imagePath = "movie_images/spirited_away.jpg";
			d.setText("2001            Fantasy           8.6/10 \n \n During her family's move to the suburbs, a sullen 10-year-old girl wanders into a world ruled by gods, witches and spirits, and where humans are changed into beasts.");
		}
		else if (movieName.contains("Your Name")){
			imagePath = "movie_images/your_name.jpg";
			d.setText("2016            Romance           8.4/10 \n \n Two teenagers share a profound, magical connection upon discovering they are swapping bodies. Things manage to become even more complicated when the boy and girl decide to meet in person.");
		}
		else{
			imagePath = "movie_images/noMov.jpg";
			d.setText("");
			d.setEditable(true);
		}
		ImageIcon ii = new ImageIcon(imagePath);
		ii.setImage(ii.getImage().getScaledInstance(300, 350, Image.SCALE_DEFAULT));
		JLabel pic = new JLabel(ii);
		i.add(pic);
	}
}
