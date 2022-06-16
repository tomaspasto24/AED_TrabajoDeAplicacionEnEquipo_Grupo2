/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ut8.ut8_pd1;

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
    public void testBpf(){
    }
    
    @Test
    public void testBea(){
    }
    
    @Test
    public void testPrim(){
        TGrafoNoDirigido resultado = grafo.prim("1");
        
        TAristas aristas = resultado.getAristas();
        System.out.println("Prim - aristas: " + aristas);
        assertEquals(5, aristas.size());
    }
    
    @Test
    public void testKruskal(){
        TGrafoNoDirigido resultado = grafo.kruskal();
        
        TAristas aristas = resultado.getAristas();
        System.out.println("Kruskal - aristas: " + aristas);
        assertEquals(5, aristas.size());
    }
    
}
