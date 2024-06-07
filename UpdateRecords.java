import javax.swing.table.DefaultTableModel;
import java.io.*;

public class UpdateRecords {

    
    /** 
     * @param filePath
     * @param tableModel
     */
    public void updateFile(String filePath, DefaultTableModel tableModel) {
        File file = new File(filePath); // Create a File object for the specified file path

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int row = 0; row < tableModel.getRowCount(); row++) { // Iterate over the rows of the table model
                StringBuilder rowContent = new StringBuilder();
                for (int col = 0; col < tableModel.getColumnCount(); col++) { // Iterate over the columns of the table model
                    rowContent.append(tableModel.getValueAt(row, col)); // Append cell value to rowContent
                    if (col < tableModel.getColumnCount() - 1) {
                        rowContent.append(" | "); // Append delimiter if not the last column
                    }
                }
                writer.write(rowContent.toString()); // Write the row content to the file
                writer.newLine(); // Move to the next line
            }
            writer.flush(); // Ensure all data is written to the file
        } catch (IOException e) {
            e.printStackTrace(); // Handle any IO exceptions
        }
    }
}
