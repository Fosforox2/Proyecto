package Proyecto;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class PantallaCargaApp {
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private JFrame frame;
    public static String nickname = ""; // Variable global para almacenar el nombre del usuario

    public void StartGame() {
        frame = new JFrame();
        frame.setSize(dim.width, dim.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        ImagenPanelInicio imagenFondo = new ImagenPanelInicio("Imagenes/FondosInicio/FondoInicio3.png");
        imagenFondo.setLayout(null);
        frame.add(imagenFondo);


        JTextField nicknameField = new JTextField();

        nicknameField.setBounds(715, 650, 100, 40);
        nicknameField.setHorizontalAlignment(JTextField.CENTER);
        imagenFondo.add(nicknameField);


        BotonConImagen botonEmpezar = new BotonConImagen("Imagenes/FondosInicio/LetrasEmpezarJuego.png", e -> {
            nickname = nicknameField.getText().trim();
            if (!nickname.isEmpty()) {
                iniciarJuego(nickname); // Pasa el nombre de usuario
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, ingresa un nombre de usuario.");
            }
        });

        botonEmpezar.setBounds(615, 680, 300, 150);
        imagenFondo.add(botonEmpezar);

        frame.setVisible(true);
    }

    private void iniciarJuego(String nickname) {
        System.out.println("Iniciando el juego para el usuario: " + nickname);
        reproducirZoomSecuencia(nickname);
    }

    public void reproducirZoomSecuencia(String nickname) {
        Thread zoomThread = new Thread(() -> {
            int imagenActual = 3;
            try {
                while (imagenActual <= 68) {
                    String imagePath = "Imagenes/FondosInicio/FondoInicio" + imagenActual + ".png";
                    cambiarImagenFondo(frame, imagePath);
                    imagenActual++;
                    Thread.sleep(0,1);
                }
                abrirPantallaPrincipal(nickname);
            } catch (InterruptedException e) {
                e.printStackTrace();
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

    private void abrirPantallaPrincipal(String nickname) {
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();


        Map<String, Integer> datosUsuario = GestorDatos.cargarDatos(nickname);
        System.out.println("Datos cargados para " + nickname + ": " + datosUsuario);



        datosUsuario.put("Sticks", datosUsuario.getOrDefault("Sticks", 0));
        datosUsuario.put("Minerals", datosUsuario.getOrDefault("Minerals", 0));
        datosUsuario.put("LavaRock", datosUsuario.getOrDefault("LavaRock", 0));
        datosUsuario.put("WeaponLevel", datosUsuario.getOrDefault("WeaponLevel", 1));
        datosUsuario.put("ArmaduraLevel", datosUsuario.getOrDefault("ArmaduraLevel", 1));
        datosUsuario.put("NaveLevel", datosUsuario.getOrDefault("NaveLevel", 1));

        GestorDatos.guardarDatos(nickname, datosUsuario);


        ImageApp app = new ImageApp();
        app.startApp();
    }
}
