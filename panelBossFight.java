package Proyecto;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class panelBossFight {



    public static void abrirPantallaBossFight() {
        //El valor X sera el nivel, en este caso Armadura
        int x;
        // Validar usuario actual
        String usuarioActual = PantallaCargaApp.nickname;
        if (usuarioActual == null || usuarioActual.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: Usuario no definido.");
            return;
        }

        JFrame nuevaVentana = new JFrame("Pantalla de Crafting");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        nuevaVentana.setSize(dim.width, dim.height);
        nuevaVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nuevaVentana.setExtendedState(JFrame.MAXIMIZED_BOTH);
        nuevaVentana.setUndecorated(true);

        // Leer datos del archivo del usuario
        Map<String, Integer> datosInventario = cargarDatosInventario(usuarioActual);

        ImageFondo imagenFondo = new ImageFondo("Imagenes/InGame/BossFondo.png");
        imagenFondo.setLayout(null);
        nuevaVentana.add(imagenFondo);


        x = 3;
    }



    // Método para cargar datos del archivo del usuario
    private static Map<String, Integer> cargarDatosInventario(String usuarioActual) {
        Map<String, Integer> datos = new HashMap<>();
        String rutaArchivo = "datos/" + usuarioActual + ".txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("=");
                if (partes.length == 2) {
                    datos.put(partes[0], Integer.parseInt(partes[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar el inventario.");
        }

        return datos;
    }

    // Método para guardar datos en el archivo del usuario
    private static void guardarDatosInventario(String usuarioActual, Map<String, Integer> datos) {
        StringBuilder contenido = new StringBuilder();
        datos.forEach((clave, valor) -> contenido.append(clave).append("=").append(valor).append("\n"));

        String rutaArchivo = "datos/" + usuarioActual + ".txt";

        try (java.io.FileWriter writer = new java.io.FileWriter(rutaArchivo)) {
            writer.write(contenido.toString());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar el inventario.");
        }
    }
}
