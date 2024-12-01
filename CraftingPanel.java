package Proyecto;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CraftingPanel extends JPanel {
    private Objeto objeto;
    private int cantidadDisponible;
    private int cantidadNecesaria;
    private ImageIcon imagenObjeto;
    private JLabel cantidadLabel; // Mover la declaración aquí para que sea accesible
    private JButton construirButton; // Mover la declaración aquí para que sea accesible

    public CraftingPanel(Objeto objeto, int cantidadDisponible, int cantidadNecesaria) {
        this.objeto = objeto;
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadNecesaria = cantidadNecesaria;

        // Cargar la imagen del objeto
        try {
            BufferedImage imagenOriginal = ImageIO.read(getClass().getResource("Imagenes/Materiales/" + objeto.getName() + ".png"));
            Image imagenRedimensionada = imagenOriginal.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            imagenObjeto = new ImageIcon(imagenRedimensionada);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: No se pudo cargar la imagen para " + objeto.getName());
            // Cargar una imagen por defecto en caso de error
            imagenObjeto = new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/default.png"))
                    .getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        }

        // Configurar el layout del panel
        setLayout(new BorderLayout());
        JLabel imagenLabel = new JLabel(imagenObjeto);
        add(imagenLabel, BorderLayout.WEST);
        cantidadLabel = new JLabel("Tienes " + cantidadDisponible + "/" + cantidadNecesaria + " materiales");
        add(cantidadLabel, BorderLayout.CENTER);
        construirButton = new JButton("Construir");
        add(construirButton, BorderLayout.EAST);

        // Habilitar o deshabilitar el botón "Construir" basado en la cantidad de materiales
        actualizarBoton();

        construirButton.addActionListener(e -> construir());
    }

    private void construir() {
        // Verificar si hay suficientes materiales antes de construir
        if (cantidadDisponible >= cantidadNecesaria) {
            // Resta los materiales y actualiza la interfaz
            cantidadDisponible -= cantidadNecesaria; // Resta los materiales
            cantidadLabel.setText("Tienes " + cantidadDisponible + "/" + cantidadNecesaria + " materiales"); // Actualiza la etiqueta
            System.out.println("Construiste: " + objeto.getName());
        } else {
            JOptionPane.showMessageDialog(this, "No tienes suficientes materiales para construir " + objeto.getName());
        }

        // Actualiza el estado del botón
        actualizarBoton();
    }

    private void actualizarBoton() {
        // Deshabilitar el botón si ya no hay suficientes materiales
        if (cantidadDisponible < cantidadNecesaria) {
            construirButton.setEnabled(false);
        } else {
            construirButton.setEnabled(true);
        }
    }
}
