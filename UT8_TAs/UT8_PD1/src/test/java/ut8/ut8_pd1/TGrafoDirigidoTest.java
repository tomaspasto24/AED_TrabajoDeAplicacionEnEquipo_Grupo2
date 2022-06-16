/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ut8.ut8_pd1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author santi
 */
public class TGrafoDirigidoTest {

    private static TGrafoDirigido grafo, grafo2;

    private static TGrafoDirigido getGrafo(int numVertices, String... datos) {
        List<TVertice> listaVertices = new LinkedList<>();
        {
            for (int i = 0; i < numVertices; i++) {
                listaVertices.add(new TVertice(datos[i]));
            }
        }

        List<TArista> listaAristas = new LinkedList<>();
        {
            for (int i = numVertices; i < datos.length; i++) {
                String[] datosArista = datos[i].split(",", 3);
                String origen = datosArista[0];
                String destino = datosArista[1];
                double costo = Double.parseDouble(datosArista[2]);

                listaAristas.add(new TArista(origen, destino, costo));
            }
        }

        return new TGrafoDirigido(listaVertices, listaAristas);
    }

    private static TGrafoDirigido getGrafo() {
        return getGrafo(7,
                "1", "2", "3", "4", "5", "6", "7",
                "1,3,1", "1,5,1", "1,7,1", "2,1,1", "2,3,1", "4,1,1",
                "4,3,1", "5,4,1", "5,6,1", "6,1,1", "7,2,1", "7,6,1");
    }

    @BeforeAll
    public static void beforeAll() {
        grafo = getGrafo();
        grafo2 = getGrafo(5, "A", "B", "C", "D", "E",
                "A,C,1", "A,D,4", "B,A,6", "B,E,3",
                "C,B,2", "C,E,1", "D,C,5", "E,A,3");
    }

    public TGrafoDirigidoTest() {
    }

    @Test
    public void testEliminarArista() {
        TGrafoDirigido gr = getGrafo();
        assertTrue(gr.eliminarArista("1", "3"));
        assertFalse(gr.eliminarArista("1", "3"));
        assertTrue(gr.eliminarArista("2", "1"));
        assertTrue(gr.eliminarArista("7", "6"));
        assertFalse(gr.eliminarArista("7", "1"));
    }

    @Test
    public void testExisteArista() {
        assertTrue(grafo.existeArista("1", "3"));
        assertTrue(grafo.existeArista("1", "3"));
        assertTrue(grafo.existeArista("1", "5"));
        assertFalse(grafo.existeArista("2", "7"));
        assertFalse(grafo.existeArista("4", "5"));
        assertTrue(grafo.existeArista("1", "3"));
    }

    @Test
    public void testExisteVertice() {
        assertTrue(grafo.existeVertice("1"));
        assertTrue(grafo.existeVertice("4"));
        assertTrue(grafo.existeVertice("6"));
        assertTrue(grafo.existeVertice("7"));
        assertFalse(grafo.existeVertice("."));
        assertTrue(grafo.existeVertice("1"));
    }

    @Test
    public void testInsertarArista() {
        TGrafoDirigido gr = getGrafo();
        assertTrue(gr.insertarArista(new TArista("1", "4", 1)));
        assertFalse(gr.insertarArista(new TArista("1", "4", 1)));
        assertFalse(gr.insertarArista(new TArista("2", "3", 5)));
    }

    @Test
    public void testInsertarVertice_Comparable() {
        TGrafoDirigido gr = getGrafo();
        assertTrue(gr.insertarVertice("8"));
        assertFalse(gr.insertarVertice("8"));
        assertTrue(gr.insertarVertice("."));
        assertFalse(gr.insertarVertice("4"));
    }

    @Test
    public void testInsertarVertice_TVertice() {
        TGrafoDirigido gr = getGrafo();
        assertTrue(gr.insertarVertice(new TVertice("8")));
        assertFalse(gr.insertarVertice(new TVertice("8")));
        assertTrue(gr.insertarVertice(new TVertice(".")));
        assertFalse(gr.insertarVertice(new TVertice("4")));
    }

    @Test
    public void testGetEtiquetasOrdenado() {
        Comparable[] esperado = {
            "1", "2", "3", "4", "5", "6", "7"
        };

        Object[] resultado = grafo.getEtiquetasOrdenado();

        for (byte i = 0; i < 7; i++) {
            assertEquals(esperado[i], resultado[i]);
        }
    }

    @Test
    public void testGetVertices() {
        Comparable[] esperado = {
            "1", "2", "3", "4", "5", "6", "7"
        };

        Map<Comparable, TVertice> resultado = grafo.getVertices();
        assertEquals(7, resultado.size());

        Set<Comparable> etiquetas = resultado.keySet();
        for (Comparable eti : esperado) {
            assertTrue(etiquetas.contains(eti));
        }
    }

    @Test
    public void testCentroDelGrafo() {
        Comparable esperado = "E";

        Comparable centro = grafo2.centroDelGrafo();

        assertEquals(esperado, centro);
    }

    @Test
    public void testFloyd() {
        Map<Tupla<Comparable, Comparable>, Double> esperado = new HashMap<>();
        for (String entrada : new String[]{
            "A,A,0", "A,B,3", "A,C,1", "A,D,4", "A,E,2",
            "B,A,6", "B,B,0", "B,C,7", "B,D,10", "B,E,3",
            "C,A,4", "C,B,2", "C,C,0", "C,D,8", "C,E,1",
            "D,A,9", "D,B,7", "D,C,5", "D,D,0", "D,E,6",
            "E,A,3", "E,B,6", "E,C,4", "E,D,7", "E,E,0"
        }) {
            String[] datos = entrada.split(",", 3);
            esperado.put(new Tupla<>(datos[0], datos[1]), Double.valueOf(datos[2]));
        }

        Double[][] floyd = grafo2.floyd();

        Comparable[] vertices = new Comparable[5];
        int i = 0;
        for (Comparable vertice : grafo2.getVertices().keySet()) {
            vertices[i++] = vertice;
        }

        for (i = 0; i < 5; i++) {
            Comparable origen = vertices[i];
            for (int j = 0; j < 5; j++) {
                Comparable destino = vertices[j];
                Double costo = floyd[i][j];
                assertEquals(esperado.get(new Tupla<>(origen, destino)), costo, () -> "origen: " + origen + ", destino: " + destino);
            }
        }

    }

    @Test
    public void testObtenerExcentricidad() {
        assertEquals(9.0,  grafo2.obtenerExcentricidad("A"));
        assertEquals(7.0,  grafo2.obtenerExcentricidad("B"));
        assertEquals(7.0,  grafo2.obtenerExcentricidad("C"));
        assertEquals(10.0, grafo2.obtenerExcentricidad("D"));
        assertEquals(6.0,  grafo2.obtenerExcentricidad("E"));
    }

    @Test
    public void testWarshall() {
        // (p -> q) == (~p | q)
        Predicate<Tupla<Comparable, Comparable>> esperado = tupla -> (!tupla.primero.equals("3") || tupla.segundo.equals("3"));

        boolean[][] warshall = grafo2.warshall();

        Comparable[] vertices = new Comparable[5];
        int i = 0;
        for (Comparable vertice : grafo2.getVertices().keySet()) {
            vertices[i++] = vertice;
        }

        for (i = 0; i < 5; i++) {
            Comparable origen = vertices[i];
            for (int j = 0; j < 5; j++) {
                Comparable destino = vertices[j];
                boolean hayCamino = warshall[i][j];
                assertEquals(esperado.test(new Tupla<>(origen, destino)), hayCamino);
            }
        }
    }

    @Test
    public void testEliminarVertice() {
        TGrafoDirigido grafo = getGrafo();
        assertTrue(grafo.eliminarVertice("1"));
        assertTrue(grafo.eliminarVertice("2"));
        assertTrue(grafo.eliminarVertice("3"));
        assertFalse(grafo.eliminarVertice("."));
        assertFalse(grafo.eliminarVertice("1"));
    }

    @Test
    public void testBpf_0args() {
        assertEquals(7, grafo.bpf().size());
    }

    @Test
    public void testBpf_Comparable() {
        assertEquals(7, grafo.bpf("1").size());
        assertEquals(1, grafo.bpf("3").size());
    }

    @Test
    public void testClasificacionTopologica() {
        TGrafoDirigido grafo = getGrafo(10,
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "B,A,1", "C,A,1", "D,B,1", "E,C,1", "F,D,1", "G,D,1",
                "D,E,1", "H,E,1", "J,F,1", "I,G,1", "I,H,1", "J,I,1");
        
        List<Comparable> resultado = grafo.clasificacionTopologica("J");
        System.out.print("Clasificación topológica: ");
        System.out.println(resultado);
    }

    @Test
    public void testTodosLosCaminos() {
        TCaminos resultado = grafo.todosLosCaminos("1", "3");
        
        System.out.println("\nCaminos: ");
        resultado.imprimirCaminosConsola();
        System.out.println();
    }
    
    @Test
    public void testTieneCiclo() {
        
    }

}
