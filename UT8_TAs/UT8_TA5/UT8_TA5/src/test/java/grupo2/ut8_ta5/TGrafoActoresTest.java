/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package grupo2.ut8_ta5;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author santi
 */
public class TGrafoActoresTest {

    public TGrafoActoresTest() {
    }

    @Test
    public void testNumBacon() {
        String[] actores = ManejadorArchivosGenerico.leerArchivo("src/main/java/grupo2/ut8_ta5/actores.csv", false);
        String[] enPelicula = ManejadorArchivosGenerico.leerArchivo("src/main/java/grupo2/ut8_ta5/en_pelicula.csv", false);

        TVerticeActor[] vertices = new TVerticeActor[actores.length];
        for (int i = 0; i < actores.length; i++) {
            vertices[i] = new TVerticeActor(actores[i]);
        }

        TArista[] aristas = new TArista[enPelicula.length];
        for (int i = 0; i < enPelicula.length; i++) {
            String[] datosArista = enPelicula[i].split(",", 3);
            aristas[i] = new TArista(datosArista[0], datosArista[1], Double.parseDouble(datosArista[2]));
        }
        
        TGrafoActores grafo = new TGrafoActores(Arrays.asList(vertices), Arrays.asList(aristas));
        
        assertEquals(0, grafo.numBacon("Kevin_Bacon"));
        assertEquals(1, grafo.numBacon("Harrison_Ford"));
    }

}
