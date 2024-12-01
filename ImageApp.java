package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageApp {
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private JFrame frame;
    private JPanel imagePanel;
    private JLabel imageLabel;
    final int screen_Width = dim.width;
    final int screen_Height = dim.height;

    public void startApp() {
        JFrame frame = new JFrame();

        frame.setSize(screen_Width, screen_Height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        ImageFondo imagenFondo = new ImageFondo("Imagenes/InGame/FondoBueno.jpg");
        imagenFondo.setLayout(null);
        frame.add(imagenFondo);

        ImagePanel imagenPersonaje = new ImagePanel("Imagenes/InGame/Astronauta.png");
        imagenPersonaje.setBounds(50, 20, 360, 720);
        imagenFondo.add(imagenPersonaje);

        BotonConImagen botonFarming = new BotonConImagen("Imagenes/InGame/Nave.png", e -> panelFarming.abrirPantallaFarming());
        botonFarming.setBounds(920, 40, 200, 200);
        imagenFondo.add(botonFarming);


        BotonConImagen botonCrafting = new BotonConImagen("Imagenes/InGame/Yunquee.png", e -> panelCrafting.abrirPantallaCrafting());
        botonCrafting.setBounds(920, 300, 200, 200);
        imagenFondo.add(botonCrafting);


        BotonConImagen botonBossFight = new BotonConImagen("Imagenes/InGame/BOSSFIGHTEDIT.jpg", e -> panelBossFight.abrirPantallaBossFight());
        botonBossFight.setBounds(920, 700, 200, 150);
        imagenFondo.add(botonBossFight);



        frame.setVisible(true);
    }






    private void abrirPantallaBossFight() {
        // Lógica específica para abrir la pantalla de Boss Fight
        JFrame nuevaVentana = new JFrame();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        nuevaVentana.setSize(dim.width, dim.height);
        nuevaVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nuevaVentana.setExtendedState(JFrame.MAXIMIZED_BOTH);
        nuevaVentana.setUndecorated(true);

        ImageFondo nuevoFondo = new ImageFondo("Imagenes/InGame/BOSSFIGHTBackground.jpg");
        nuevoFondo.setLayout(null);
        nuevaVentana.add(nuevoFondo);
        nuevaVentana.setVisible(true);
    }

    public static void main(String[] args) {
        ImageApp app = new ImageApp();
        //panelCrafting2.abrirPantallaCrafting();
        app.startApp();
    }
}
