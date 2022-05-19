/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package equipo2.ut1_ta3;
import java.io.IOException;

/**
 *
 * @author santi
 */
public class Program {

    public static void main(String[] args) throws IOException {
        var contadorPalabras = new ContadorPalabras();
        String[] lineas = ContadorPalabras.obtenerLineas("Ejemplo.txt");
        System.out.println(contadorPalabras.cantPalabras(lineas));
    }
}
