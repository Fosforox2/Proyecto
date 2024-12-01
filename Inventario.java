package Proyecto;

import java.util.HashMap;
import java.util.Map;

public class Inventario {
    private static Map<String, Integer> datos;

    public Inventario(String nombreUsuario) {
        datos = GestorDatos.cargarDatos(nombreUsuario);
        if (datos.isEmpty()) {
            // Inicializar con valores predeterminados
            datos.put("Sticks", 0);
            datos.put("Minerals", 0);
            datos.put("LavaRock", 0);
            datos.put("armaLevel", 1);
            datos.put("armaduraLevel", 1);
            datos.put("NaveLevel", 1);
        }
    }

    public int get(String clave) {
        return datos.getOrDefault(clave, 0);
    }

    public void set(String clave, int valor) {
        datos.put(clave, valor);
    }

    public static void incrementar(String clave, int cantidad) {
        datos.put(clave, datos.getOrDefault(clave, 0) + cantidad);
    }

    public void decrementar(String clave, int cantidad) {
        int valorActual = datos.getOrDefault(clave, 0);
        if (valorActual - cantidad < 0) {
            throw new IllegalArgumentException("No se puede decrementar por debajo de 0: " + clave);
        }
        datos.put(clave, valorActual - cantidad);
    }

    public Map<String, Integer> getDatos() {
        return datos; // Retorna el mapa interno de datos
    }

    public void guardar(String nombreUsuario) {
        GestorDatos.guardarDatos(nombreUsuario, datos);
    }
}
