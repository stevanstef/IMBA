import javax.swing.table.DefaultTableModel;
import java.io.*;

public class UpdateRecords {

    public void updateFile(String filePath, DefaultTableModel tableModel) {
        // Create a File object for the specified file path
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Iterate over the rows and columns of the table model
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                StringBuilder rowContent = new StringBuilder();
                for (int col = 0; col < tableModel.getColumnCount(); col++) {
                    rowContent.append(tableModel.getValueAt(row, col));
                    if (col < tableModel.getColumnCount() - 1) {
                        rowContent.append(" | ");
                    }
                }
                writer.write(rowContent.toString());
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

