import java.awt.*;
import javax.swing.*;

public class BackgroundPanel extends JPanel {
    private Image img;

    public BackgroundPanel(String imagePath) {
        // Load the image
        Toolkit tk = Toolkit.getDefaultToolkit();
        img = tk.getImage(imagePath);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            // Draw the image to fill the panel
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    }

}
