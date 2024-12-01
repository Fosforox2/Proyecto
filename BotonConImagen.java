package Proyecto;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BotonConImagen extends JButton {
    private Image imagenBoton;

    public BotonConImagen(String imagePath, ActionListener action) {
        try {
            imagenBoton = ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading image from: " + imagePath);
        }

        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setOpaque(false);

        // Asigna la acción específica al botón
        this.addActionListener(action);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenBoton != null) {
            g.drawImage(imagenBoton, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
