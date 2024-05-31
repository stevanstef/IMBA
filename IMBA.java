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
    private JButton enter, search, sort, select, dMovie, aMovie, back, save, y, n, genreS, yearS, alphaS, ratingS;
    public JPanel lib, add, desc;
    public JFrame yn, sorter;
	private JTextField nameText, yearText, genreText, ratingText;
	public JTable table;
    public JLabel message, ynText;
    public DefaultTableModel model;
    public int start, end, MAX;
    String input, value;
    boolean isNull;
    static String movieFile = "movieList.txt"; // movieFile = "movieList.txt"

    int check = 0;
    String[] rows = new String[MAX];
	String info [][] = new String[MAX][MAX];
	ReadData rd = new ReadData();
    Records re = new Records();
    UpdateRecords ur = new UpdateRecords();
    DisplayMovie dm = new DisplayMovie();
	 Sort s = new Sort();

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

        String[] columnNames = {"Title", "Year", "Genre", "Rating"};
        String[][] data = new String[rows.length][4];
        for (int i = 0; i < rows.length; i++) {
            if (rows[i] != null) {
                String[] parts = rows[i].split(" \\| ");
                data[i][0] = parts[0];
                data[i][1] = parts[1];
                data[i][2] = parts[2];
				data[i][3] = parts[3];
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
										  sorter.setVisible(false);
                                yn.setVisible(true);
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

        JLabel name = new JLabel("Title:");
        name.setBounds(componentX, componentY, 100, componentHeight);
        name.setForeground(Color.BLACK);
        add.add(name, Integer.valueOf(1));

        nameText = new JTextField(10);
        nameText.setBounds(componentX + 100, componentY, componentWidth, componentHeight);
        add.add(nameText, Integer.valueOf(1));

        JLabel year = new JLabel("Year:");
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

        JLabel rating = new JLabel("Rating:");
        rating.setBounds(componentX + componentWidth + 30, componentY + 2 * componentSpacing, 100, componentHeight);
        rating.setForeground(Color.BLACK);
        add.add(rating, Integer.valueOf(1));

        ratingText = new JTextField(10);
        ratingText.setBounds(componentX + componentWidth + 130, componentY + 2 * componentSpacing, componentWidth, componentHeight);
        add.add(ratingText, Integer.valueOf(1));

        enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to be performed when the button is clicked
                libraryRun();
            }
        });
        enter.setVisible(true);

        back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                add.setVisible(false);
                back.setVisible(false);
                desc.setVisible(false);
                save.setVisible(false);
                message.setVisible(false);
                libraryRun();
                  
            }
        });
        back.setVisible(false);

        select = new JButton("Select");
        select.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (check != 0)
                {
                    check = 0;
						  sorter.setVisible(false);
                    selectRun();
                    int row = table.getSelectedRow();
                    int column = 0;
                    value = table.getModel().getValueAt(row, column).toString();
                    value.trim();
                    desc = new JPanel();
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 1;
                    gbc.gridy = 0;
                    add(desc, gbc);
                    dm.display(value, desc);
                    back.setVisible(true);
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
				String ratingT = ratingText.getText();
				String inputText[] = new String[4];
                inputText[0] = nameT;
                inputText[1] = yearT;
                inputText[2] = genreT;
				inputText[3] = ratingT;
                isNull = false;           
                for (String element : inputText){
                    if (element == null || element.trim().isEmpty()){
                        isNull = true;
                        break;}
                }
				if (!isNull){
                    message.setVisible(true);
                    message.setText("SUCCESS! Movie saved to library!");
                    model.addRow(inputText);
                    ur.updateFile(movieFile, model);
				}
                if (isNull){
                    message.setText("ERROR: Not all fields have been filled out.");
                    message.setVisible(true);
				}

				nameText.setText("");
                yearText.setText("");
                genreText.setText("");
				ratingText.setText("");

            }
        });
        save.setVisible(false);

        search = new JButton("Search");
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to be performed when the button is clicked
					 sorter.setVisible(false);
                System.out.println("Search still under development");
            }
        });
        search.setVisible(false);
		  
		  genreS = new JButton("Genre");
        genreS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to be performed when the button is clicked
                System.out.println("Genre");
            }
        });
        genreS.setVisible(false);
		  
		  yearS = new JButton("Year");
        yearS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to be performed when the button is clicked
                System.out.println("Year");
            }
        });
        yearS.setVisible(false);
		  
		  alphaS = new JButton("A - Z");
        alphaS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
					System.out.println("A - Z");
            }
        });
        alphaS.setVisible(false);
		  
		  ratingS = new JButton("Rating");
        ratingS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to be performed when the button is clicked
                System.out.println("Rating");
            }
        });
        genreS.setVisible(false);
		  
		  sorter = new JFrame();
		  sorter.setSize(300, 80);
		  sorter.setTitle("Sorter");
        sorter.setBackground(Color.WHITE);
        sorter.setLocation(300, 350);
        sorter.setLayout(new FlowLayout());
		  sorter.add(genreS);
		  sorter.add(yearS);
		  sorter.add(alphaS);
		  sorter.add(ratingS);

        sort = new JButton("Sort");
        sort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to be performed when the button is clicked
					 genreS.setVisible(true);
					 yearS.setVisible(true);
					 alphaS.setVisible(true);
					 ratingS.setVisible(true);
	    			 sorter.setVisible(true);	
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
    			gbc.insets = new Insets(75, 100, 100, 100);
				add(add, gbc);
	    		add.setVisible(true);
                save.setVisible(true);
					 sorter.setVisible(false);					 
		  }});
        
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
        gbc.insets = new Insets(120, 1, 1, 1);
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
        
        yn = new JFrame();
        yn.setSize(275, 100);
        yn.setBackground(Color.WHITE);
        yn.setLocation(300, 200);
        yn.setLayout(new FlowLayout());

        ynText = new JLabel("Are you sure you want to delete this movie?");
        ynText.setForeground(Color.BLACK);

        ynText.setHorizontalAlignment(SwingConstants.CENTER);
        yn.add(ynText, BorderLayout.NORTH);
        yn.setVisible(false);

        y = new JButton("Yes");
        n = new JButton("No");
        y.setVisible(true);
        n.setVisible(true);
        y.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                yn.setVisible(false);
                int row = table.getSelectedRow();
                model.removeRow(row);
                rows = rd.readFile(movieFile, MAX);
                info = re.getRecords(rows);
                ur.updateFile(movieFile, model);

                String[][] data = new String[rows.length][4];
                for (int i = 0; i < rows.length; i++) {
                    if (rows[i] != null) {
                        String[] parts = rows[i].split(" \\| ");
                        data[i][0] = parts[0];
                        data[i][1] = parts[1];
                        data[i][2] = parts[2];
                        data[i][3] = parts[3];
                    }
                }
            }
        });

        n.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 yn.setVisible(false);
            }
        });

        yn.add(y);
        yn.add(n);

        message = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(50, 1, 1, 1);
        add(message, gbc);
        message.setVisible(false);
        message.setForeground(Color.WHITE);  
    } // end makeMenus

    public static void main(String[] args) {
        new IMBA(); // Instantiates IMBA class
    } // end main
} // end class