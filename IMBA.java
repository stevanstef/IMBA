//Filename: IMBA.java
//Date: May 21st 2024

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

public class IMBA extends JFrame {

    // Declare variables and constants
    private JButton enter, search, sort, select, dMovie, aMovie, back, save;
    public JPanel lib, add, desc;
	private JTextField nameText, yearText, genreText;
	public JTable table;
    public DefaultTableModel model;
    public int start, end, MAX;
    String input, value;
    static String movieFile = "movieList.txt"; // movieFile = "movieList.txt"

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
		
        MAX = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(movieFile))) {
            while (reader.readLine() != null) {
                MAX++;
            }
            } catch (IOException e) {
                e.printStackTrace();
            }

		lib = new JPanel();
	 	GridBagConstraints gbc = new GridBagConstraints();
	 	gbc.gridx = 1;
    	gbc.gridy = 0;
    	gbc.insets = new Insets(150, 200, 200, 200);
	 	add(lib, gbc);
	    lib.setVisible(true);
        
        rows = rd.readFile(movieFile, MAX);
        info = re.getRecords(rows);

        String[] columnNames = {"Title", "Year", "Genre"};
        String[][] data = new String[rows.length][3];
        for (int i = 0; i < rows.length; i++) {
            if (rows[i] != null) {
                String[] parts = rows[i].split(" \\| ");
                data[i][0] = parts[0];
                data[i][1] = parts[1];
                data[i][2] = parts[2];
            }
        }

        model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        table.setFont(new Font("Serif", Font.PLAIN, 16));
        lib.add(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(500, 141));
        lib.add(scrollPane);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    check = 1;
                    dMovie.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (check != 0)
                            {
                                check = 0;
                                int row = table.getSelectedRow();
                                model.removeRow(row);
                                rows = rd.readFile(movieFile, 20);
                                info = re.getRecords(rows);
                                ur.updateFile(movieFile, model);

                                String[][] data = new String[rows.length][3];
                                for (int i = 0; i < rows.length; i++) {
                                    if (rows[i] != null) {
                                        String[] parts = rows[i].split(" \\| ");
                                        data[i][0] = parts[0];
                                        data[i][1] = parts[1];
                                        data[i][2] = parts[2];
                                    }
                                }
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
                    int row = table.getSelectedRow();
                    int column = 0;
                    value = table.getModel().getValueAt(row, column).toString();
                    value.trim();
                    if (value.contains("Rush Hour")){
                        System.out.println("Display Rush Hour");
                    }
                    else if (value.contains("Kung Fu Panda")){
                        System.out.println("Display Kung Fu Panda");
                    }
                    else if (value.contains("Jurassic Park")){
                        System.out.println("Display Jurassic Park");
                    }
                    else if (value.contains("The Lord of the Rings: The Return of the King")){
                        System.out.println("Display The Lord of the Rings: The Return of the King");
                    }
                    else if (value.contains("Up")){
                        System.out.println("Display Up");
                    }
                    else if (value.contains("Inception")){
                        System.out.println("Display Inception");
                    }
                    else if (value.contains("Interstellar")){
                        System.out.println("Display Interstellar");
                    }
                    else if (value.contains("Oppenheimer")){
                        System.out.println("Display Oppenheimer");
                    }
                    else if (value.contains("Pulp Fiction")){
                        System.out.println("Display Pulp Fiction");
                    }
                    else if (value.contains("Dune Part Two")){
                        System.out.println("Display Dune Part Two");
                    }
                    else if (value.contains("The Matrix")){
                        System.out.println("Display The Matrix");
                    }
                    else if (value.contains("Fight Club")){
                        System.out.println("Display Fight Club");
                    }
                    else if (value.contains("Shrek")){
                        System.out.println("Display Shrek");
                    }
                    else if (value.contains("Rio")){
                        System.out.println("Display Rio");
                    }
                    else if (value.contains("Avengers: Endgame")){
                        System.out.println("Display Avengers: Endgame");
                    }
                    else if (value.contains("Harry Potter and the Philosopher's Stone")){
                        System.out.println("Display Harry Potter and the Philosopher's Stone");
                    }
                    else if (value.contains("Planet Earth")){
                        System.out.println("Display Planet Earth");
                    }
                    else if (value.contains("Akira")){
                        System.out.println("Display Akira");
                    }
                    else if (value.contains("Spirited Away")){
                        System.out.println("Display Spirited Away");
                    }
                    else if (value.contains("Your Name")){
                        System.out.println("Display Your Name");
                    }
                    else{
                        System.out.println("Display Generic Movie");
                    }
                }
                
            }
        });
        select.setVisible(false);

        save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				String nameT = nameText.getText();
				String yearT = yearText.getText();
				String genreT = genreText.getText();
                String inputText[] = new String[3];
                inputText[0] = nameT;
                inputText[1] = yearT;
                inputText[2] = genreT;
                model.addRow(inputText);
                ur.updateFile(movieFile, model);
					 
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
