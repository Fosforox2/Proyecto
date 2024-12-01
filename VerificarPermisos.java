package Proyecto;

import java.io.File;

public class VerificarPermisos {
    public static void main(String[] args) {
        String rutaCarpeta = "datos/";
        File carpeta = new File(rutaCarpeta);

        // Verificar existencia y permisos
        if (carpeta.exists()) {
            System.out.println("La carpeta existe.");
            System.out.println("¿Escribir permitido? " + carpeta.canWrite());
        } else {
            System.out.println("La carpeta no existe. Intentando crearla...");
            if (carpeta.mkdirs()) {
                System.out.println("Carpeta creada exitosamente.");
            } else {
                System.out.println("No se pudo crear la carpeta. Verifica los permisos.");
            }
        }

        // Prueba con un archivo dentro de la carpeta
        File archivoPrueba = new File(rutaCarpeta + "prueba.txt");
        try {
            if (archivoPrueba.createNewFile()) {
                System.out.println("Archivo de prueba creado exitosamente.");
                System.out.println("¿Escribir permitido en el archivo? " + archivoPrueba.canWrite());
            } else {
                System.out.println("No se pudo crear el archivo de prueba.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ocurrió un error al crear el archivo de prueba.");
        }
    }
}
