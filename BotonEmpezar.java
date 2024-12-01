//FUNCIONA ZOOM pero no borra la pantalla anterior
package Proyecto;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static Proyecto.PantallaCargaApp.nickname;

public class BotonEmpezar extends JButton {
    private Image ImagenBoton;
    private JFrame parentFrame;

    public BotonEmpezar(String imagePath, JFrame parentFrame) {
        this.parentFrame = parentFrame;
        try {
            ImagenBoton = ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading image from: " + imagePath);
        }
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setOpaque(false);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reproducirZoomSecuencia();
                System.out.println(nickname);


            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (ImagenBoton != null) {
            g.drawImage(ImagenBoton, 0, 0, getWidth(), getHeight(), this);
        }
    }


    public void reproducirZoomSecuencia() {
        Thread zoomThread = new Thread(new Runnable() {
            int imagenActual = 1;
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(BotonEmpezar.this);

            @Override
            public void run() {
                try {
                    while (imagenActual <= 68) {
                        String imagePath = "Imagenes/FondosInicio/FondoInicio" + imagenActual + ".png";
                        cambiarImagenFondo(frame, imagePath);
                        imagenActual++;
                        Thread.sleep(1);
                    }
                    abrirPantallaPrincipal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        zoomThread.start();
    }



    private void cambiarImagenFondo(JFrame frame, String imagePath) {
        java.net.URL imgURL = getClass().getResource(imagePath);
        if (imgURL == null) {
            System.out.println("Error: No se encontró la imagen en la ruta: " + imagePath);
            return;
        }
        ImageFondo imagenFondo = new ImageFondo(imagePath);
        frame.setContentPane(imagenFondo);
        frame.revalidate();
        frame.repaint();
    }



    private void abrirPantallaPrincipal() {
        parentFrame.getContentPane().removeAll();
        parentFrame.revalidate();
        parentFrame.repaint();
        ImageApp app = new ImageApp();
        app.startApp();
    }

}
