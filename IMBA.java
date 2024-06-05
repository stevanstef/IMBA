//Filename: IMBA.java
//Date: May 21st 2024

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.RowSorter.SortKey;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.Utilities; 
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.RowSorter.SortKey;

public class IMBA extends JFrame {

    // Declare variables and constants
    private JButton enter, search, select, dMovie, aMovie, back, save, y, n, submit;
    public JPanel lib, add, img, st, desc, login;
    public JFrame yn;
    private JTextField nameText, yearText, genreText, ratingText, searchText, descriptionText, imageText, passwordText, usernameText;
    public JTextArea word;
	public JTable table;
    public JLabel message, ynText, welcome;
    public DefaultTableModel model;
    public int start, end, MAX;
    String input, movName, movYear, movGenre, movRating;
    String [][] data;
    boolean isNull;
    public TableRowSorter<TableModel> rowSorter;
    static String movieFile = "movieList.txt"; // movieFile = "movieList.txt"

    int check = 0;
    String[] rows = new String[MAX];
	String info [][] = new String[MAX][MAX];
	ReadData rd = new ReadData();
    Records re = new Records();
    UpdateRecords ur = new UpdateRecords();
    DisplayMovie dm = new DisplayMovie();
    Search s = new Search();

    public IMBA() {
      BackgroundPanel bgPanel = new BackgroundPanel("background.png");
      setContentPane(bgPanel);
      setTitle("IMBA");
      setSize(960, 540);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      buttons();
      setVisible(true);
    }
	 
    private void libraryRun(){
        enter.setVisible(false);
        select.setVisible(true);
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
        table.setAutoCreateRowSorter(true);

        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        table.setModel(model);
        table.setRowSorter(rowSorter);
    
        rowSorter.addRowSorterListener(new RowSorterListener() {
            public void sorterChanged(RowSorterEvent e) {
                if (e.getType() == RowSorterEvent.Type.SORTED) {
                    String[] columnNames = {"Title", "Year", "Genre", "Rating"};
                    int rowCount = table.getRowCount();
                    int columnCount = table.getColumnCount();
                    Object[][] tableData = new Object[rowCount][columnCount];
                    for (int row = 0; row < rowCount; row++){
                         for (int column = 0; column < columnCount; column++){
                            tableData[row][column] = (String) table.getValueAt(row, column);
                        }
                     }
                     
                    model = new DefaultTableModel(tableData, columnNames);
                    table.setModel(model);
                    TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
                    table.setRowSorter(rowSorter);
                    rowSorter.addRowSorterListener(this);
                }
            }
            }
        );

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(500, 141));
        lib.add(scrollPane);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    fade();
                    int row = table.getSelectedRow();
                    int column = 0;
                    movName = table.getModel().getValueAt(row, column).toString();
                    movName.trim();
                    movYear = table.getModel().getValueAt(row, column+1).toString();
                    movYear.trim();
                    movGenre = table.getModel().getValueAt(row, column+2).toString();
                    movGenre.trim();
                    movRating = table.getModel().getValueAt(row, column+3).toString();
                    movRating.trim();
                    buildDisplay();
                    dm.display(movName, movYear, movGenre, movRating, img, word);
                }
                try {
                    check = 1;
                    dMovie.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (check != 0)
                            {
                                check = 0;
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
        welcome.setVisible(false);
        enter.setVisible(false);
        select.setVisible(false);
        search.setVisible(false);
        aMovie.setVisible(false);
        dMovie.setVisible(false);
        lib.setVisible(false);
        back.setVisible(true);
        submit.setVisible(false);
        search.setVisible(false);
        searchText.setVisible(false);
        st.setVisible(false);
    }

    private void fade(){
        welcome.setVisible(false);
        enter.setVisible(false);
        select.setVisible(false);
        search.setVisible(false);
        aMovie.setVisible(false);
        dMovie.setVisible(false);
        lib.setVisible(false);
		back.setVisible(true);
        submit.setVisible(false);
        search.setVisible(false);
        searchText.setVisible(false);
        st.setVisible(false);
    }

    private void buildDisplay(){
        desc.setVisible(true);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(110, 1, 1, 300);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(img, gbc);
        gbc.insets = new Insets(50, 500, 0, 200);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(desc, gbc);
        desc.add(word);
    }

    private void buttons() {
        setLayout(new GridBagLayout());
        add = new JPanel();
        img = new JPanel();
        desc = new JPanel();
        word = new JTextArea();
        word.setFont(new Font("Serif", Font.PLAIN, 16));
        word.setLineWrap(true);
        word.setWrapStyleWord(true);
        word.setPreferredSize( new Dimension( 250, 800));
        word.setEditable(false);

        JLabel name = new JLabel("Title:");
        name.setForeground(Color.BLACK);
        add.add(name, Integer.valueOf(1));

        nameText = new JTextField(20);
        add.add(nameText, Integer.valueOf(1));

        JLabel year = new JLabel("Year:");
        year.setForeground(Color.BLACK);
        add.add(year, Integer.valueOf(1));

        yearText = new JTextField(20);
        add.add(yearText, Integer.valueOf(1));

        JLabel genre = new JLabel("Genre:");
        genre.setForeground(Color.BLACK);
        add.add(genre, Integer.valueOf(1));

        genreText = new JTextField(20);
        add.add(genreText, Integer.valueOf(1));

        JLabel rating = new JLabel("Rating:");
        rating.setForeground(Color.BLACK);
        add.add(rating, Integer.valueOf(1));

        ratingText = new JTextField(10);
        add.add(ratingText, Integer.valueOf(1));
        ratingText.setText("/10");

        JLabel description = new JLabel("Description:");
        description.setForeground(Color.BLACK);
        add.add(description, Integer.valueOf(1));

        descriptionText = new JTextField(10);
        add.add(descriptionText, Integer.valueOf(1));

        JLabel image = new JLabel("Path to image:");
        image.setForeground(Color.BLACK);
        add.add(image, Integer.valueOf(1));

        imageText = new JTextField(10);
        add.add(imageText, Integer.valueOf(1));
		  
		searchText = new JTextField(11);
		st = new JPanel();
		st.setVisible(false);
		st.add(searchText);

        enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                welcome.setVisible(true);
                String usernameT = usernameText.getText();
                welcome.setText("Welcome to IMBA, " + usernameT + "!");
                login.setVisible(false);
                libraryRun();
            }
        });
        enter.setVisible(true);

        back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                welcome.setVisible(true);
                add.setVisible(false);
                back.setVisible(false);
                img.setVisible(false);
                save.setVisible(false);
                message.setVisible(false);
                desc.setVisible(false);
                word.setEditable(false);
                ur.updateFile(movieFile, model);
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
                    fade();
                    int row = table.getSelectedRow();
                    int column = 0;
                    movName = table.getModel().getValueAt(row, column).toString();
                    movName.trim();
                    movYear = table.getModel().getValueAt(row, column+1).toString();
                    movYear.trim();
                    movGenre = table.getModel().getValueAt(row, column+2).toString();
                    movGenre.trim();
                    movRating = table.getModel().getValueAt(row, column+3).toString();
                    movRating.trim();
                    buildDisplay();
                    dm.display(movName, movYear, movGenre, movRating, img, word);
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
                String imageT = imageText.getText();
                String descriptionT = descriptionText.getText();
				String inputText[] = new String[6];
                inputText[0] = nameT;
                inputText[1] = yearT;
                inputText[2] = genreT;
				inputText[3] = ratingT;
                inputText[4] = descriptionT;
                inputText[5] = imageT;
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
                ratingText.setText("/10");
                descriptionText.setText("");
                imageText.setText("");
            }
        });
        save.setVisible(false);

        submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				String searchT = searchText.getText();
                data = new String[rows.length][MAX];
                for (int i = 0; i < rows.length; i++) {
                    if (rows[i] != null) {
                        String[] parts = rows[i].split(" \\| ");
                        data[i][0] = parts[0];
                        data[i][1] = parts[1];
                        data[i][2] = parts[2];
                        data[i][3] = parts[3];
                    }
                }
                isNull = false;       
                if (searchT == null || searchT.trim().isEmpty()){
                    isNull = true;}

				if (!isNull){
                    int search = s.searching(movieFile, data, 0, searchT);
                    if (search != -1){
                        fade();
                        buildDisplay();
                        int column = 0;
                        movName = table.getModel().getValueAt(search, column).toString();
                        movName.trim();
                        movYear = table.getModel().getValueAt(search, column+1).toString();
                        movYear.trim();
                        movGenre = table.getModel().getValueAt(search, column+2).toString();
                        movGenre.trim();
                        movRating = table.getModel().getValueAt(search, column+3).toString();
                        movRating.trim();
                        dm.display(searchT, movYear, movGenre, movRating, img, word);
                    }
				}
                searchText.setText("");
            }
        });
        submit.setVisible(false);
		  
        search = new JButton("Search");
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    submit.setVisible(true);
					search.setVisible(false);
                    searchText.setVisible(true);
					st.setVisible(true);
					searchText.setText("");
            }
        });
        search.setVisible(false);	 

        aMovie = new JButton("Add Movie");
        aMovie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addRun();
				GridBagConstraints gbc = new GridBagConstraints();
	 			gbc.gridx = 1;
    			gbc.gridy = 0;
    			gbc.insets = new Insets(100, 150, 100, 150);
				add(add, gbc);
                add.setLayout(new BoxLayout(add, BoxLayout.Y_AXIS));
	    		add.setVisible(true);
                save.setVisible(true);		 
		  }});
        
        aMovie.setVisible(false);
        dMovie = new JButton("Delete Movie");
        dMovie.setVisible(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(200, 1, 1, 1);
        add(enter, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(400, 1, 1, 800);
        add(select, gbc);
		  
		gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 1, 430, 760);
        add(st, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(250, 1, 1, 1);
        add(save, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(1, 1, 430, 760);
        add(search, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(1, 1, 360, 760);
        add(submit, gbc);
	
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
        
        login = new JPanel();
        login.setLayout(new BoxLayout(login, BoxLayout.PAGE_AXIS));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(70, 1, 1, 1);
        add(login, gbc);

        login.setVisible(true);
        JLabel username = new JLabel("Username:");
        username.setForeground(Color.BLACK);
        login.add(username, Integer.valueOf(1));

        usernameText = new JTextField(10);
        login.add(usernameText, Integer.valueOf(1));

        JLabel password = new JLabel("Password:");
        password.setForeground(Color.BLACK);
        login.add(password, Integer.valueOf(1));

        JPasswordField p = new JPasswordField("",6);
        p.setEchoChar('*');
        login.add(p);

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
                ur.updateFile(movieFile, model);
                rows = rd.readFile(movieFile, MAX);
                info = re.getRecords(rows);
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
        gbc.insets = new Insets(300, 1, 1, 1);
        add(message, gbc);
        message.setVisible(false);
        message.setForeground(Color.WHITE);
		searchText.setVisible(false);

        welcome = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(1, 200, 250, 200);
        add(welcome, gbc);
        welcome.setVisible(false);
        welcome.setForeground(Color.WHITE);
    }
    
    public static void main(String[] args) {
        new IMBA();
    }
}