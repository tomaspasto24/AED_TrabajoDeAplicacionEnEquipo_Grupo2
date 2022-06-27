/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.ut9_ta1;

import static com.mycompany.ut9_ta1.TClasificador.METODO_CLASIFICACION_BURBUJA;
import static com.mycompany.ut9_ta1.TClasificador.METODO_CLASIFICACION_INSERCION;
import static com.mycompany.ut9_ta1.TClasificador.METODO_CLASIFICACION_SHELL;
import static com.mycompany.ut9_ta1.UT9_TA1.vector;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author TomasUcu
 */
public class TClasificadorTest {

    public TClasificadorTest() {
    }

    @Test
    public void testClasificar() {
        int[] vector = {5, 4, 2, 3, 9};

        TClasificador clasif = new TClasificador();

        System.out.println(clasif.estaOrdenado(vector));

        int[] clasificacion = clasif.clasificar(vector, METODO_CLASIFICACION_INSERCION);

        
        assertArrayEquals(clasificacion, new int[] {2, 3, 4, 5, 9});
    }

    @Test
    public void testShellSort() {
        TClasificador clasif = new TClasificador();

        vector = new int[]{5, 4, 2, 3, 9};

        int[] shell = clasif.clasificar(vector, METODO_CLASIFICACION_SHELL);

        assertArrayEquals(shell, new int[] {2, 3, 4, 5, 9});
    }

    @Test
    public void testBurbuja() {
        TClasificador clasif = new TClasificador();

        vector = new int[]{5, 4, 2, 3, 9};

        int[] burbuja = clasif.clasificar(vector, METODO_CLASIFICACION_BURBUJA);
        assertArrayEquals(burbuja, new int[] {2, 3, 4, 5, 9});

    }

}
