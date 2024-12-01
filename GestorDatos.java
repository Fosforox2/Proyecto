package Proyecto;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GestorDatos {
    private static final String RUTA_BASE_DATOS = "datos/";

    private static void prepararDirectorio() {
        File directorio = new File(RUTA_BASE_DATOS);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio 'datos/' creado exitosamente.");
            } else {
                System.err.println("No se pudo crear el directorio 'datos/'. Verifica permisos.");
            }
        }
    }

    public static Map<String, Integer> cargarDatos(String nombreUsuario) {
        prepararDirectorio();
        Map<String, Integer> datos = new HashMap<>();
        String rutaArchivo = RUTA_BASE_DATOS + nombreUsuario + ".txt";
        File archivo = new File(rutaArchivo);

        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split("=");
                    if (partes.length == 2) {
                        datos.put(partes[0], Integer.parseInt(partes[1]));
                    }
                }
            } catch (IOException e) {
                System.err.println("Error al cargar datos desde " + rutaArchivo);
                e.printStackTrace();
            }
        } else {

            System.out.println("El archivo no existe: " + rutaArchivo);
        }

        return datos;
    }

    public static void guardarDatos(String nombreUsuario, Map<String, Integer> datos) {
        prepararDirectorio();
        String rutaArchivo = RUTA_BASE_DATOS + nombreUsuario + ".txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Map.Entry<String, Integer> entry : datos.entrySet()) {
                bw.write(entry.getKey() + "=" + entry.getValue());
                bw.newLine();
            }
            System.out.println("Datos guardados correctamente en: " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar datos en " + rutaArchivo);
            e.printStackTrace();
        }
    }

    public static Map<String, Integer> obtenerDatosIniciales() {
        Map<String, Integer> datosIniciales = new HashMap<>();
        datosIniciales.put("Sticks", 0);
        datosIniciales.put("Minerals", 0);
        datosIniciales.put("LavaRock", 0);
        datosIniciales.put("armaLevel", 1);
        datosIniciales.put("armaduraLevel", 1);
        datosIniciales.put("NaveLevel", 1);
        return datosIniciales;
    }
}
