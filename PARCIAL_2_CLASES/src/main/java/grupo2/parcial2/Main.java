/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo2.parcial2;

import java.util.ArrayList;

/**
 *
 * @author TomasUcu
 */
public class Main {
    public static void main(String[] args) {
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
        aristasKruskal.forEach( (ar) -> { System.out.println("Destino:" + ar.etiquetaDestino + " Origen:" + ar.etiquetaOrigen + " Costo:" + ar.costo); });

        System.out.println("Ejercicio Prim: ");
        TGrafoNoDirigido grafoPrim = grafo.prim("1");
       grafoPrim.getAristas().forEach( (ar) -> { System.out.println("Destino:" + ar.etiquetaDestino + " Origen:" + ar.etiquetaOrigen + " Costo:" + ar.costo); });
    }
}
