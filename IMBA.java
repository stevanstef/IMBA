//Filename: IMBA.java
//Date: May 21st 2024

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

public class IMBA extends JFrame {

    // Declare variables and constants
    private JButton enter, search, sort, select, dMovie, aMovie;
    static String movieFile = "movieList.txt"; // movieFile = "movieList.txt"
    final int MAX = 40; // Set MAX number of records
    String[][] movieArray = new String[MAX][40];

    public IMBA() { // constructor to prepare window size and menubar
      BackgroundPanel bgPanel = new BackgroundPanel("background.png");
      setContentPane(bgPanel);
      setTitle("IMBA");
      setSize(960, 540); // Window size
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Properly close the application
      buttons();
      setVisible(true); // Make the JFrame visible
    }

    private void libraryRun(){
        enter.setVisible(false);
        select.setVisible(true);
        sort.setVisible(true);
        search.setVisible(true);
        aMovie.setVisible(true);
        dMovie.setVisible(true);
    }
    private void addRun(){
        enter.setVisible(false);
        select.setVisible(false);
        sort.setVisible(false);
        search.setVisible(false);
        aMovie.setVisible(false);
        dMovie.setVisible(false);


    }


    private void buttons() {
        setLayout(new GridBagLayout());
        enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to be performed when the button is clicked
                libraryRun();
            }
        });
        enter.setVisible(true);

        select = new JButton("Select");
        select.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to be performed when the button is clicked
                libraryRun();
            }
        });
        select.setVisible(false);

        search = new JButton("Search");
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to be performed when the button is clicked
                libraryRun();
            }
        });
        search.setVisible(false);

        sort = new JButton("Sort");
        sort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to be performed when the button is clicked
                libraryRun();
            }
        });
        sort.setVisible(false);
            
        aMovie = new JButton("Add Movie");
        aMovie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to be performed when the button is clicked
                addRun();
            }
        });
        aMovie.setVisible(false);
        
        dMovie = new JButton("Delete Movie");
        dMovie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to be performed when the button is clicked
                libraryRun();
            }
        });
        dMovie.setVisible(false);

        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(400, 1, 1, 1);
        add(enter, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(400, 1, 1, 800);
        add(select, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(1, 1, 430, 800);
        add(search, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(1, 1, 430, 650);
        add(sort, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(400, 1, 1, 600);
        add(aMovie, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(400, 1, 1, 390);
        add(dMovie, gbc);

    } // end makeMenus

    public static void main(String[] args) {
        new IMBA(); // Instantiates IMBA class
    } // end main
} // end class