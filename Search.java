import java.io.*;

class Search {
    public int searching(String fileName, String data[][], int item, String input) {
        for (int i = 0; i < data.length; i++) {
            if (data[i][item].equals(input)) {
                return i;
            }
        }
        return -1;
}}
