/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.ut7_ta6_ej2;

<<<<<<< HEAD
=======
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
>>>>>>> santiagodeolivera
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author santi
 */
public class TGrafoDirigidoTest {
    
    public TGrafoDirigidoTest() {
    }
<<<<<<< HEAD
    
    @Test
    public void testCaminoCritico() {
=======

    private static TGrafoDirigido getGrafo(int numVertices, String... datos) {
        List<TVertice> listaVertices = new LinkedList<>();
        {
            for (int i = 0; i < numVertices; i++) {
                String[] datosVertice = datos[i].split(",", 2);
                String etiqueta = datosVertice[0];
                Integer verticeDatos = Integer.valueOf(datosVertice[1]);
                TVertice vertice = new TVertice(etiqueta);
                vertice.setDatos(verticeDatos);
                listaVertices.add(vertice);
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
    
    @Test
    public void testCaminoCritico() {
        TGrafoDirigido grafo = getGrafo(8, "1", "2", "");
>>>>>>> santiagodeolivera
    }
    
}
