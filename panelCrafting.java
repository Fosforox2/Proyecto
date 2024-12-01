package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class panelCrafting {



    public static void abrirPantallaCrafting() {
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

        ImageFondo imagenFondo = new ImageFondo("Imagenes/InGame/Herreria.png");
        imagenFondo.setLayout(null);
        nuevaVentana.add(imagenFondo);
        // Panel principal
        //JPanel panelPrincipal = new JPanel(null);
        //nuevaVentana.add(panelPrincipal);
        x  = 3;
        // Agregar imágenes y botones de crafting
        String[] items = {"Espada", "Armadura", "Dron"};
        int[][] requerimientos = {
                {200, 200, 10},  // Espada: Sticks, Materiales, LavaRock
                {10, 300, 20},  // Armadura
                {50, 800, 100}   // Dron
        };
        String[] imagenes = {
                "Imagenes/Materiales/Espadas/EspadaTier"+ x +".png",
                "Imagenes/Materiales/Armadura/ArmaduraTier"+ x +".png",
                "Imagenes/Materiales/Nave/NaveTier"+ x +".png",
        };

        for (int i = 0; i < items.length; i++) {
            String itemName = items[i];
            int[] itemRequerimientos = requerimientos[i];
            String itemImagen = imagenes[i];

            // Muestra La Espada Armadura y Nave
            ImagePanel imagenItem = new ImagePanel(itemImagen);
            if (i == 0){
                imagenItem.setBounds(50, i * 250 + 20, 280, 280);

            } else if (i == 1) {
                if (x == 1) {
                //ARMADURATIER1
                imagenItem.setBounds(50, i * 250 + 45, 280, 280);
                } else if (x == 2) {
                //ARMADURATIER2
                imagenItem.setBounds(30, i * 250 + 33, 320, 320);
                }else {
                //ARMADURATIER3
                imagenItem.setBounds(50, i * 250 + 55, 280, 280);
            }
                //imagenItem.setBounds(50, i * 250 + 20, 280, 280);

            }else if (i == 2) {
                imagenItem.setBounds(50, i * 250 + 100, 280, 280);

            }

            imagenFondo.add(imagenItem);

            // Texto requerimiento
            String textoRequerimientos = String.format(
                    "Sticks: %d/%d, Materiales: %d/%d, LavaRock: %d/%d",
                    datosInventario.getOrDefault("Sticks", 0), itemRequerimientos[0],
                    datosInventario.getOrDefault("Minerals", 0), itemRequerimientos[1],
                    datosInventario.getOrDefault("LavaRock", 0), itemRequerimientos[2]
            );
            TextWithBorderLabel labelRequerimientos = new TextWithBorderLabel(
                    textoRequerimientos,
                    Color.WHITE, // Color del borde exterior
                    Color.BLACK  // Color del texto interior
            );
            labelRequerimientos.setFont(new Font("Ariel", Font.BOLD, 24));
            //Mover Texto
            labelRequerimientos.setBounds(450, i * 250 + 160, 700, 30);
            imagenFondo.add(labelRequerimientos);

            // Botón de construir
            JButton buildButton = new JButton("Craft");
            //Mover Boton
            buildButton.setBounds(1100, i * 250 + 100, 250, 150);
            buildButton.setEnabled(
                    datosInventario.getOrDefault("Sticks", 0) >= itemRequerimientos[0] &&
                            datosInventario.getOrDefault("Minerals", 0) >= itemRequerimientos[1] &&
                            datosInventario.getOrDefault("LavaRock", 0) >= itemRequerimientos[2]
            );

            final int[] finalRequerimientos = itemRequerimientos;
            buildButton.addActionListener(e -> {
                // Actualizar inventario
                datosInventario.put("Sticks", datosInventario.get("Sticks") - finalRequerimientos[0]);
                datosInventario.put("Minerals", datosInventario.get("Minerals") - finalRequerimientos[1]);
                datosInventario.put("LavaRock", datosInventario.get("LavaRock") - finalRequerimientos[2]);

                guardarDatosInventario(usuarioActual, datosInventario);

                JOptionPane.showMessageDialog(nuevaVentana, itemName + " construido!");
                nuevaVentana.dispose();
                abrirPantallaCrafting(); // Recargar la pantalla
            });

            imagenFondo.add(buildButton);
        }

        nuevaVentana.setVisible(true);
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
