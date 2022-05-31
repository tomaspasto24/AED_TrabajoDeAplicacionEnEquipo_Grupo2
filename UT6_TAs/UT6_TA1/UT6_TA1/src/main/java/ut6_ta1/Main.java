/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut6_ta1;

/**
 *
 * @author jechague
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IHash conjHash = new THash(222);
        int promedio = 0;

        // Crear una tabla de tipo THash e insertar las claves del archivo "claves_insertar.txt"
        String[] clavesAInsertar = ManejadorArchivosGenerico.leerArchivo("src/main/java/ut6_ta1/claves_insertar.txt");
        for (String claveStr : clavesAInsertar) {
            int clave = Integer.parseInt(claveStr);
            promedio += conjHash.insertar(clave);
        }
        
        System.out.println("Promedio al insertar: " + ((float)promedio / clavesAInsertar.length));

        promedio = 0;
        // Buscar en la tabla creada anteriormente las claves indicadas en el archivo "claves_buscar.txt"
        String[] clavesABuscar = ManejadorArchivosGenerico.leerArchivo("src/main/java/ut6_ta1/claves_buscar.txt");
        for (String claveStr : clavesABuscar) {
            int clave = Integer.parseInt(claveStr);
            promedio += conjHash.buscar(clave);
        }

        System.out.println("Promedio al buscar: " + ((float)promedio / clavesABuscar.length));

    }

}
