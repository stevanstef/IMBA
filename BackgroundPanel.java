import java.awt.*;
import javax.swing.*;

public class BackgroundPanel extends JPanel {
    private Image img;

    // Constructor to set background image
    public BackgroundPanel(String imagePath) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        img = tk.getImage(imagePath); // Load image
    }

    
    /** 
     * @param g
     */
    // Method to paint background image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            // Draw image to fill the panel
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
