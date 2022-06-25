/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package grupo2.parcial2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author santi
 */
public class TGrafoNoDirigidoTest {

    public TGrafoNoDirigidoTest() {
    }

    private static TGrafoNoDirigido grafo;

    @BeforeAll
    public static void beforeAll() {
        grafo = getGrafo(6, "1", "2", "3", "4", "5", "6",
                "1,2,6", "1,3,1", "1,4,5", "2,3,5", "3,4,5",
                "2,5,3", "4,6,2", "3,5,6", "3,6,4", "5,6,6");
    }

    private static TGrafoNoDirigido getGrafo(int numVertices, String... datos) {
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

        return new TGrafoNoDirigido(listaVertices, listaAristas);
    }

    @Test
    public void testBpf() {
    }

    @Test
    public void testBea() {
    }

    @Test
    public void testPrimKruskal() {
        TVertice v1 = new TVertice("1");
        TVertice v2 = new TVertice("2");
        TVertice v3 = new TVertice("3");
        TVertice v4 = new TVertice("4");
        TVertice v5 = new TVertice("5");

        ArrayList<TVertice> vertices = new ArrayList();
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);

        TArista a1 = new TArista("1", "4", 3);
        TArista a2 = new TArista("4", "5", 4);
        TArista a3 = new TArista("5", "2", 2);
        TArista a4 = new TArista("2", "1", 3);
        TArista a5 = new TArista("2", "3", 2);

        TArista a6 = new TArista("5", "3", 1);
        TArista a7 = new TArista("3", "1", 1);

        ArrayList<TArista> aristas = new ArrayList();
        aristas.add(a1);
        aristas.add(a2);
        aristas.add(a3);
        aristas.add(a4);
        aristas.add(a5);
        aristas.add(a6);
        aristas.add(a7);

        TGrafoNoDirigido grafo = new TGrafoNoDirigido(vertices, aristas);

        TGrafoNoDirigido grafoKruskal = grafo.kruskal();

        TAristas aristasKruskal = grafoKruskal.getAristas();
        System.out.println("Ejercicio Krsukal: ");
        aristasKruskal.forEach((ar) -> {
            System.out.println("Destino:" + ar.etiquetaDestino + " Origen:" + ar.etiquetaOrigen + " Costo:" + ar.costo);
        });

        System.out.println("Ejercicio Prim: ");
        TGrafoNoDirigido grafoPrim = grafo.prim("1");
        grafoPrim.getAristas().forEach((ar) -> {
            System.out.println("Destino:" + ar.etiquetaDestino + " Origen:" + ar.etiquetaOrigen + " Costo:" + ar.costo);
        });

    }

    @Test
    public void testPrim() {
        TGrafoNoDirigido resultado = grafo.prim("1");

        TAristas aristas = resultado.getAristas();
        System.out.println("Prim - aristas: " + aristas);
        assertEquals(5, aristas.size());
    }

    @Test
    public void testKruskal() {
        TGrafoNoDirigido resultado = grafo.kruskal();

        TAristas aristas = resultado.getAristas();
        System.out.println("Kruskal - aristas: " + aristas);
        assertEquals(5, aristas.size());
    }

    @Test
    public void testDistanciaBEA() {
        TGrafoNoDirigido grafo = getGrafo(8,
                "1", "2", "3", "4", "5", "6", "7", "8",
                "1,2,1", "1,3,1", "1,4,1",
                "7,4,1", "7,2,1",
                "5,2,1", "5,3,1",
                "6,3,1", "6,4,1",
                "8,1,1", "8,3,1");

        assertEquals(0, grafo.distanciaBEA("1", "1"));
        assertEquals(1, grafo.distanciaBEA("1", "2"));
        assertEquals(1, grafo.distanciaBEA("1", "3"));
        assertEquals(1, grafo.distanciaBEA("1", "4"));
        assertEquals(2, grafo.distanciaBEA("1", "5"));
        assertEquals(2, grafo.distanciaBEA("1", "6"));
        assertEquals(2, grafo.distanciaBEA("1", "7"));
        assertEquals(1, grafo.distanciaBEA("1", "8"));
        assertEquals(3, grafo.distanciaBEA("5", "4"));
    }

    @Test
    public void testPuntosArticulacion() {
        TGrafoNoDirigido grafo = getGrafo(12,
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
                "4,1,1", "3,1,1", "1,2,1", "2,6,1", "2,5,1", "5,7,1", "5,8,1",
                "5,11,1", "11,10,1", "8,9,1", "8,10,1", "10,12,1");

        List<Comparable> res = grafo.puntosArticulacion();
        assertTrue(res.contains("1"));
        assertTrue(res.contains("2"));
        assertFalse(res.contains("3"));
        assertFalse(res.contains("4"));
        assertTrue(res.contains("5"));
        assertFalse(res.contains("6"));
        assertFalse(res.contains("7"));
        assertTrue(res.contains("8"));
        assertFalse(res.contains("9"));
        assertTrue(res.contains("10"));
        assertFalse(res.contains("11"));
        assertFalse(res.contains("12"));
    }

}
