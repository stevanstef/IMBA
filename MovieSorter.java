import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class MovieSorter {
    private JFrame frame;
    private JList<String> movieList;
    private DefaultListModel<String> listModel;

    public MovieSorter() {
        frame = new JFrame("Movie Sorter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        listModel = new DefaultListModel<>();
        movieList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(movieList);

        JButton sortButton = new JButton("Sort Movies");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortMovies();
            }
        });

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(sortButton, BorderLayout.SOUTH);
    }

    public void show() {
        frame.setVisible(true);
    }

    public void addMovie(String movie) {
        listModel.addElement(movie);
    }

    private void sortMovies() {
        ArrayList<String> movies = Collections.list(listModel.elements());
        Collections.sort(movies);
        listModel.clear();
        for (String movie : movies) {
            listModel.addElement(movie);
        }
    }

    public static void main(String[] args) {
        MovieSorter sorter = new MovieSorter();
        sorter.addMovie("The Shawshank Redemption");
        sorter.addMovie("The Godfather");
        sorter.addMovie("Pulp Fiction");
        sorter.show();
    }
}
