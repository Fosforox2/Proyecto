package Proyecto;

import javax.swing.*;
import java.awt.*;

public class panelFarming {
    public static void abrirPantallaFarming() {
        JFrame nuevaVentana = new JFrame("Farming Panel");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        nuevaVentana.setSize(dim.width, dim.height);
        nuevaVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nuevaVentana.setExtendedState(JFrame.MAXIMIZED_BOTH);
        nuevaVentana.setUndecorated(true);

        ImageFondo imagenFondo = new ImageFondo("Imagenes/InGame/FondoBueno.jpg");
        imagenFondo.setLayout(null);

        ImagePanel imagenNave = new ImagePanel("Imagenes/Materiales/Nave/NaveTier3.png");
        imagenNave.setBounds(500, 200, 400, 400);
        imagenFondo.add(imagenNave);

        // Adaptando la funcionalidad de BarraDeCargaConBoton
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setBounds(400, 600, 600, 20);
        imagenFondo.add(progressBar);

        JButton startButton = new JButton("Iniciar Carga");
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setEnabled(false);
        startButton.setBounds(600, 640, 200, 50);
        imagenFondo.add(startButton);

        JButton reclamoButton = new JButton("Reclamar Recursos");
        reclamoButton.setFont(new Font("Arial", Font.BOLD, 16));
        reclamoButton.setEnabled(false);
        reclamoButton.setVisible(false);
        reclamoButton.setBounds(600, 700, 200, 50);
        imagenFondo.add(reclamoButton);

        JComboBox<String> comboBox = new JComboBox<>(new String[]{"Vacía", "Sticks", "Minerals", "LavaRock"});
        comboBox.setFont(new Font("Arial", Font.BOLD, 16));
        comboBox.setBounds(1200, 550, 200, 30);
        imagenFondo.add(comboBox);

        ImagePanel fuegoNave = new ImagePanel("Imagenes/Materiales/Fuego.png");
        fuegoNave.setBounds(552, -50, 301, 300);
        fuegoNave.setVisible(false);
        imagenFondo.add(fuegoNave);

        comboBox.addActionListener(e -> {
            String opcionSeleccionada = (String) comboBox.getSelectedItem();
            startButton.setEnabled(!"Vacía".equals(opcionSeleccionada));
        });

        startButton.addActionListener(e -> {
            startButton.setVisible(false);
            fuegoNave.setVisible(true);
            new BarraCargaManager(progressBar, fuegoNave, reclamoButton, comboBox, startButton).iniciarCarga();
        });

        reclamoButton.addActionListener(e -> {
            String opcionSeleccionada = (String) comboBox.getSelectedItem();
            if (!"Vacía".equals(opcionSeleccionada)) {
                // Acceso al inventario
                String nombreUsuario = PantallaCargaApp.nickname;
                Inventario inventario = new Inventario(nombreUsuario);

                // Incrementar recurso seleccionado
                switch (opcionSeleccionada) {
                    case "Sticks":
                        inventario.incrementar("Sticks", 100);
                        break;
                    case "Minerals":
                        inventario.incrementar("Minerals", 100);
                        break;
                    case "LavaRock":
                        inventario.incrementar("LavaRock", 100);
                        break;
                    default:
                        System.out.println("Opción desconocida: " + opcionSeleccionada);
                        break;
                }

                // Guardar los cambios en el archivo
                inventario.guardar(nombreUsuario);

                // Log y feedback al usuario
                System.out.println("Recurso recolectado: " + opcionSeleccionada + " para el usuario " + nombreUsuario);
                JOptionPane.showMessageDialog(nuevaVentana, opcionSeleccionada + " actualizado en el inventario.");

                // Restablecer botones y combo box
                reclamoButton.setVisible(false);
                reclamoButton.setEnabled(false);
                comboBox.setVisible(true);
                startButton.setVisible(true);
                startButton.setEnabled(true);
            }
        });


        nuevaVentana.add(imagenFondo);
        nuevaVentana.setVisible(true);
    }

    private static class BarraCargaManager {
        private final JProgressBar progressBar;
        private final ImagePanel fuegoNave;
        private final JButton reclamoButton;
        private final JComboBox<String> comboBox;
        private final JButton startButton;
        private Timer timer;

        public BarraCargaManager(JProgressBar progressBar, ImagePanel fuegoNave, JButton reclamoButton, JComboBox<String> comboBox, JButton startButton) {
            this.progressBar = progressBar;
            this.fuegoNave = fuegoNave;
            this.reclamoButton = reclamoButton;
            this.comboBox = comboBox;
            this.startButton = startButton;
        }

        public void iniciarCarga() {
            progressBar.setVisible(true);
            startButton.setEnabled(false);
            comboBox.setVisible(false);
            fuegoNave.setVisible(true);

            int duracionTotal = 5000;
            int intervalo = 100;
            int incremento = 100 / (duracionTotal / intervalo);

            timer = new Timer(intervalo, e -> {
                int valorActual = progressBar.getValue();
                if (valorActual < 100) {
                    progressBar.setValue(valorActual + incremento);
                } else {
                    timer.stop();
                    progressBar.setValue(0);
                    progressBar.setVisible(false);
                    fuegoNave.setVisible(false);
                    reclamoButton.setVisible(true);
                    reclamoButton.setEnabled(true);
                }
            });

            timer.start();
        }
    }
}
