//Filename: IMBA.java
//Date: May 21st 2024

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.Utilities;

public class IMBA extends JFrame {

    // Declare variables and constants
    private JButton enter, search, sort, select, dMovie, aMovie, back, save;
    public JPanel lib, add, desc;
	private JTextField nameText, yearText, genreText;
    public int start, end;
    String input;
    static String movieFile = "movieList.txt"; // movieFile = "movieList.txt"
    final int MAX = 40; // Set MAX number of records
    int check = 0;
    String[] rows = new String[MAX];
	String info [][] = new String[MAX][6];
	ReadData rd = new ReadData();
    Records re = new Records();
    UpdateRecords ur = new UpdateRecords();

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
		  
		lib = new JPanel();
	 	GridBagConstraints gbc = new GridBagConstraints();
	 	gbc.gridx = 1;
    	gbc.gridy = 0;
    	gbc.insets = new Insets(150, 200, 200, 200);
	 	add(lib, gbc);
	    lib.setVisible(true);
        JTextArea textArea = new JTextArea();
        textArea.setSelectionColor(Color.RED);
        textArea.setFont(new Font("Serif", Font.PLAIN, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setPreferredSize( new Dimension( 3600, 800));
        lib.add(textArea);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(500, 141));
        lib.add(scrollPane);

        rows = rd.readFile(movieFile, 30);
        info = re.getRecords(rows, textArea);

        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    check = 1;
                    int clickOffset = textArea.viewToModel2D(e.getPoint());
                    start = Utilities.getRowStart(textArea, clickOffset);
                    end = Utilities.getRowEnd(textArea, clickOffset);
                    textArea.setSelectionStart(start);
                    textArea.setSelectionEnd(end);
                    dMovie.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (check != 0)
                            {
                                check = 0;
                                textArea.replaceRange("", start, end);
                                ur.updateFile(movieFile, textArea);
                            }
                            
                        }
                });
                } catch (Exception ex) {
                    ex.printStackTrace();
                    
                }
            }
        });
   }
    private void addRun(){
        enter.setVisible(false);
        select.setVisible(false);
        sort.setVisible(false);
        search.setVisible(false);
        aMovie.setVisible(false);
        dMovie.setVisible(false);
        lib.setVisible(false);
        back.setVisible(true);
    }

    private void selectRun(){
        enter.setVisible(false);
        select.setVisible(false);
        sort.setVisible(false);
        search.setVisible(false);
        aMovie.setVisible(false);
        dMovie.setVisible(false);
        lib.setVisible(false);
        back.setVisible(true);
	 	GridBagConstraints gbc = new GridBagConstraints();
	 	gbc.gridx = 1;
    	gbc.gridy = 0;
    	gbc.insets = new Insets(150, 200, 200, 200);
	 	add(desc, gbc);
        desc.setVisible(true);
        JTextArea description = new JTextArea();
        description.setSelectionColor(Color.RED);
        description.setFont(new Font("Serif", Font.PLAIN, 16));
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEditable(false);
        description.setPreferredSize( new Dimension( 800, 100000));
        desc.add(description);
    }


    private void buttons() {
        setLayout(new GridBagLayout());
        desc = new JPanel();
        add = new JPanel();
        int componentWidth = 400;
        int componentHeight = 25;
        int componentX = 50;
        int componentY = 20;
        int componentSpacing = 30;
        JLabel name = new JLabel("Name:");
        name.setBounds(componentX, componentY, 100, componentHeight);
        name.setForeground(Color.BLACK);
        add.add(name, Integer.valueOf(1));

        nameText = new JTextField(10);
        nameText.setBounds(componentX + 100, componentY, 35, componentHeight);
        add.add(nameText, Integer.valueOf(1));

        JLabel year = new JLabel("Year released:");
        year.setBounds(componentX, componentY + componentSpacing, 100, componentHeight);
        year.setForeground(Color.BLACK);
        add.add(year, Integer.valueOf(1));

        yearText = new JTextField(10);
        yearText.setBounds(componentX + 100, componentY + componentSpacing, componentWidth, componentHeight);
        add.add(yearText, Integer.valueOf(1));

        JLabel genre = new JLabel("Genre:");
        genre.setBounds(componentX, componentY + 2 * componentSpacing, 100, componentHeight);
        genre.setForeground(Color.BLACK);
        add.add(genre, Integer.valueOf(1));

        genreText = new JTextField(10);
        genreText.setBounds(componentX + 100, componentY + 2 * componentSpacing, componentWidth, componentHeight);
        add.add(genreText, Integer.valueOf(1));

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
                if (check != 0)
                {
                    check = 0;
                    selectRun();
                }
                
            }
        });
        select.setVisible(false);

        save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameText.setText("");
                yearText.setText("");
                genreText.setText("");
            }
        });
        save.setVisible(false);

        search = new JButton("Search");
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to be performed when the button is clicked
                System.out.println("Search still under development");
            }
        });
        search.setVisible(false);

        sort = new JButton("Sort");
        sort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to be performed when the button is clicked
                System.out.println("Sort still under development");
            }
        });
        sort.setVisible(false);

	
        aMovie = new JButton("Add Movie");
        aMovie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to be performed when the button is clicked
                addRun();
				GridBagConstraints gbc = new GridBagConstraints();
	 			gbc.gridx = 1;
    			gbc.gridy = 0;
    			gbc.insets = new Insets(150, 200, 200, 200);
				add(add, gbc);
	    		add.setVisible(true);
                nameText.setText("");
                yearText.setText("");
                genreText.setText("");
                save.setVisible(true);
		  }});
        
        back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                add.setVisible(false);
                back.setVisible(false);
                desc.setVisible(false);
                save.setVisible(false);
                libraryRun();
                  
            }
        });
        back.setVisible(false);
        
        aMovie.setVisible(false);
        dMovie = new JButton("Delete Movie");
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
        gbc.insets = new Insets(30, 1, 1, 1);
        add(save, gbc);

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

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(400, 1, 1, 800);
        add(back, gbc);
    } // end makeMenus

    public static void main(String[] args) {
        new IMBA(); // Instantiates IMBA class
    } // end main
} // end class
