import javax.swing.*;
import java.io.*;

public class UpdateRecords {
    public void updateFile(String filePath, JTextArea textArea) {
        // Get the content from the JTextArea
        String content = textArea.getText();
        
        // Create a File object for the specified file path
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Write the content to the file
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
