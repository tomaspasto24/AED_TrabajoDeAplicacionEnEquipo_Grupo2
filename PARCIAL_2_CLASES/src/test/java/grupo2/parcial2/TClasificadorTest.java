/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package grupo2.parcial2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static grupo2.parcial2.TClasificador.*;
import java.util.Arrays;

/**
 *
 * @author santi
 */
public class TClasificadorTest {

    private GeneradorDatosGenericos generador = new GeneradorDatosGenericos();

    public TClasificadorTest() {
    }

    @Test
    public void testOrdenarPorQuickSort300() {
        int[] vector = generador.generarDatosAleatorios();
        TClasificador clasificador = new TClasificador();
    }

    @Test
    public void testOrdenarPorRadixSort() {
        int[] vector = generador.generarDatosAleatorios(99999);
        TClasificador clasificador = new TClasificador();
        System.out.println("Antes  : " + Arrays.toString(vector));
        vector = clasificador.clasificar(vector, METODO_CLASIFICACION_RADIXSORT);
        System.out.println("Despues: " + Arrays.toString(vector));
        assertTrue(clasificador.estaOrdenado(vector));
    }

    @Test
    public void testOrdenarPorHeapsort() {
        for (int i = 0; i < 10; i++) {
            int[] vector = generador.generarDatosAleatorios(99999);
            TClasificador clasificador = new TClasificador();
            System.out.println("Antes  : " + Arrays.toString(vector));
            vector = clasificador.clasificar(vector, METODO_CLASIFICACION_HEAPSORT);
            System.out.println("Despues: " + Arrays.toString(vector));
            assertTrue(clasificador.estaOrdenado(vector));
        }
    }

}
