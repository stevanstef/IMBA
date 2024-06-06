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

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class IMBA extends JFrame {

    // Declare variables and constants
    private JButton enter, search, select, dMovie, aMovie, back, save, y, n, submit, help;
    public JPanel lib, add, img, st, desc, login, aDesc;
    public JFrame yn;
    private JTextField nameText, yearText, genreText, ratingText, searchText, imageText, usernameText;
    public JTextArea word, descriptionText;
	public JTable table, tableMaster;
    public JLabel message, ynText, welcome, sortMessage;
    public DefaultTableModel model, modelMaster;
	public JPasswordField p;
    public int start, end, MAX;
    String input, movName, movYear, movGenre, movRating, movDescription, movImagepath;
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

        rows = new String[MAX];

		lib = new JPanel();
	 	GridBagConstraints gbc = new GridBagConstraints();
	 	gbc.gridx = 1;
    	gbc.gridy = 0;
    	gbc.insets = new Insets(150, 200, 200, 200);
	 	add(lib, gbc);
	    lib.setVisible(true);
        
        rows = rd.readFile(movieFile, MAX);
        info = re.getRecords(rows);
        
        String[] columnNames = {"<html><font color=\"red\">Title</font></html>", "<html><font color=\"red\">Year</font></html>", "<html><font color=\"red\">Genre</font></html>", "<html><font color=\"red\">Rating</font></html>"};
        String[] columnNamesMaster = {"Title", "Year", "Genre", "Rating", "Description", "Image path"};
        data = new String[rows.length][6];
        for (int i = 0; i < rows.length; i++) {
            if (rows[i] != null) {
                String[] parts = rows[i].split(" \\| ");
                data[i][0] = parts[0];
                data[i][1] = parts[1];
                data[i][2] = parts[2];
				data[i][3] = parts[3];
                data[i][4] = parts[4];
                data[i][5] = parts[5];
            }
        }

        tableMaster = new JTable();
        modelMaster = new DefaultTableModel(data, columnNamesMaster);
        tableMaster.setModel(modelMaster);
        model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        table.setFont(new Font("Serif", Font.PLAIN, 16));
        lib.add(table);

        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        table.setModel(model);
        tableMaster.setRowSorter(rowSorter);
        table.setRowSorter(rowSorter);
		lib.setForeground(Color.BLACK);
        table.getTableHeader().setReorderingAllowed(false);
    
        rowSorter.addRowSorterListener(new RowSorterListener() {
            public void sorterChanged(RowSorterEvent e) {
                if (e.getType() == RowSorterEvent.Type.SORTED) {
                    int rowCount = tableMaster.getRowCount();
                    int columnCount = tableMaster.getColumnCount();
                    Object[][] tableData = new Object[rowCount][columnCount];
                    for (int row = 0; row < rowCount; row++) {
                        for (int column = 0; column < columnCount; column++) {
                            tableData[row][column] = tableMaster.getValueAt(row, column);
                        }
                    }
                    modelMaster.setDataVector(tableData, columnNamesMaster);
                    tableMaster.setModel(modelMaster);

                    model.setDataVector(tableData, columnNames);
                    table.setModel(model);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		  scrollPane.setPreferredSize(new Dimension(500, 141));
        lib.add(scrollPane);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                check = 1;
                if (e.getClickCount() == 2) {
                    ur.updateFile(movieFile, modelMaster);
                    check = 0;
                    fade();
                    int row = table.getSelectedRow();
                    int column = 0;
                    movName = tableMaster.getModel().getValueAt(row, column).toString();
                    movName.trim();
                    movYear = tableMaster.getModel().getValueAt(row, column+1).toString();
                    movYear.trim();
                    movGenre = tableMaster.getModel().getValueAt(row, column+2).toString();
                    movGenre.trim();
                    movRating = tableMaster.getModel().getValueAt(row, column+3).toString();
                    movRating.trim();
                    movDescription = tableMaster.getModel().getValueAt(row, column+4).toString();
                    movDescription.trim();
                    movImagepath = tableMaster.getModel().getValueAt(row, column+5).toString();
                    movImagepath.trim();
                    buildDisplay();
                    dm.display(movName, movYear, movGenre, movRating, movDescription, movImagepath, img, word);
                }
                try {
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
	 	help.setVisible(false);
        welcome.setVisible(false);
        sortMessage.setVisible(false);
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

    public void libraryRefresh(){
        MAX = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(movieFile))) {
            while (reader.readLine() != null) {
                MAX++;
            }
            } catch (IOException e) {
                e.printStackTrace();
            }

        rows = new String[MAX];
    }

    private void fade(){
	 	sortMessage.setVisible(false);
	 	help.setVisible(false);
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
        gbc.insets = new Insets(50, 640, 0, 200);
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
        word.setPreferredSize( new Dimension( 350, 800));
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

        descriptionText = new JTextArea();

        aDesc = new JPanel();
        aDesc.setLayout(new BoxLayout(aDesc, BoxLayout.Y_AXIS));
        aDesc.setVisible(false);
        aDesc.add(description);
		aDesc.add(descriptionText);
        descriptionText.setLineWrap(true);
        descriptionText.setWrapStyleWord(true);
        descriptionText.setPreferredSize( new Dimension(150, 200));

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
                isNull = false;
					 String usernameT = usernameText.getText();
                if (usernameT == null || usernameT.trim().isEmpty()){
						 	isNull = true;}
						  
					if (!isNull){
					 	help.setVisible(true);
						sortMessage.setVisible(true);
               	        welcome.setVisible(true);
               	        welcome.setText("Welcome to IMBA, " + usernameT + "!");
                	    login.setVisible(false);
                	libraryRun();}
            usernameText.setText("");
				}
        });
        enter.setVisible(true);

        back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                check = 0;
				sortMessage.setVisible(true);
				help.setVisible(true);
                welcome.setVisible(true);
                add.setVisible(false);
                aDesc.setVisible(false);
                back.setVisible(false);
                img.setVisible(false);
                save.setVisible(false);
                message.setVisible(false);
                desc.setVisible(false);
                word.setEditable(false);
                ur.updateFile(movieFile, modelMaster);
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
                    movName = tableMaster.getModel().getValueAt(row, column).toString();
                    movName.trim();
                    movYear = tableMaster.getModel().getValueAt(row, column+1).toString();
                    movYear.trim();
                    movGenre = tableMaster.getModel().getValueAt(row, column+2).toString();
                    movGenre.trim();
                    movRating = tableMaster.getModel().getValueAt(row, column+3).toString();
                    movRating.trim();
                    movDescription = tableMaster.getModel().getValueAt(row, column+4).toString();
                    movDescription.trim();
                    movImagepath = tableMaster.getModel().getValueAt(row, column+5).toString();
                    movImagepath.trim();
                    buildDisplay();
                    dm.display(movName, movYear, movGenre, movRating, movDescription, movImagepath, img, word);
                }
                
            }
        });
        select.setVisible(false);
		  
		help = new JButton("Help");
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("www.google.com"));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });
        help.setVisible(false);

        save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				String nameT = nameText.getText();
				String yearT = yearText.getText();
				String genreT = genreText.getText();
				String ratingT = ratingText.getText();
                String descriptionT = descriptionText.getText();
                String imageT = imageText.getText();
				String inputText[] = new String[6];
                inputText[0] = nameT;
                inputText[1] = yearT;
                inputText[2] = genreT;
				inputText[3] = ratingT;
                inputText[4] = descriptionT;
                inputText[5] = imageT;
                isNull = false;
                ratingText.setText("/10");           
                for (String element : inputText){
                    if (element == null || element.trim().isEmpty()){
                        isNull = true;
                        break;}
                }
				if (!isNull){
                    message.setVisible(true);
                    message.setText("SUCCESS! Movie saved to library!");
                    model.addRow(inputText);
                    modelMaster.addRow(inputText);
                    ur.updateFile(movieFile, modelMaster);
				}
                if (isNull){
                    message.setText("ERROR: Not all fields have been filled out.");
                    message.setVisible(true);
				}
                nameText.setText("");
                yearText.setText("");
                genreText.setText("");
                descriptionText.setText("");
                imageText.setText("");
            }
        });
        save.setVisible(false);

        submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				String searchT = searchText.getText();
                isNull = false;
                if (searchT == null || searchT.trim().isEmpty()){
                    isNull = true;}

				if (!isNull){
                    ur.updateFile(movieFile, modelMaster);
                    rows = rd.readFile(movieFile, MAX);
                    data = new String[rows.length][6];
                    for (int i = 0; i < rows.length; i++) {
                        if (rows[i] != null) {
                            String[] parts = rows[i].split(" \\| ");
                            data[i][0] = parts[0];
                            data[i][1] = parts[1];
                            data[i][2] = parts[2];
                            data[i][3] = parts[3];
                            data[i][4] = parts[4];
                            data[i][5] = parts[5];
                        }
                    }
                    int search = s.searching(movieFile, data, 0, searchT);
                    if (search != -1){                     
                        fade();
                        int column = 0;
                        movName = tableMaster.getModel().getValueAt(search, column).toString();
                        movName.trim();
                        movYear = tableMaster.getModel().getValueAt(search, column+1).toString();
                        movYear.trim();
                        movGenre = tableMaster.getModel().getValueAt(search, column+2).toString();
                        movGenre.trim();
                        movRating = tableMaster.getModel().getValueAt(search, column+3).toString();
                        movRating.trim();
                        movDescription = tableMaster.getModel().getValueAt(search, column+4).toString();
                        movDescription.trim();
                        movImagepath = tableMaster.getModel().getValueAt(search, column+5).toString();
                        movImagepath.trim();
                        buildDisplay();
                        dm.display(searchT, movYear, movGenre, movRating, movDescription, movImagepath, img, word);
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
                gbc.gridx = 1;
    			gbc.gridy = 0;
    			gbc.insets = new Insets(1, 400, -10, 1);
				add(aDesc, gbc);
                add.setLayout(new BoxLayout(add, BoxLayout.Y_AXIS));
	    		add.setVisible(true);
                aDesc.setVisible(true);
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
        gbc.insets = new Insets(1, 700, 430, 1);
        add(help, gbc);

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

        JLabel password = new JLabel("Password (optional):");
        password.setForeground(Color.BLACK);
        login.add(password, Integer.valueOf(1));

        p = new JPasswordField("",6);
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
                TableRowSorter<TableModel> masterRowSorter = (TableRowSorter<TableModel>) tableMaster.getRowSorter();
                tableMaster.setRowSorter(null);
                model.removeRow(row);
                modelMaster.removeRow(row);
                rows = rd.readFile(movieFile, MAX);
                info = re.getRecords(rows);
                libraryRefresh();
                ur.updateFile(movieFile, modelMaster);
                tableMaster.setRowSorter(masterRowSorter);
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
        gbc.insets = new Insets(1, 200, 280, 200);
        add(welcome, gbc);
        welcome.setVisible(false);
        welcome.setForeground(Color.WHITE);
		  
		sortMessage = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(1, 5, 220, 230);
        add(sortMessage, gbc);
        sortMessage.setVisible(false);
        sortMessage.setForeground(Color.WHITE);
		sortMessage.setText("Click a heading to sort the table by that attribute.");
    }
    
    public static void main(String[] args) {
        new IMBA();
    }
}