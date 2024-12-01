package Proyecto;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ImageFondo extends JPanel {
    private Image image;

        public ImageFondo(String imagePath) {
            try {
                image = ImageIO.read(getClass().getResource(imagePath));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error loading image from: " + imagePath);
            }
        }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {

            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
