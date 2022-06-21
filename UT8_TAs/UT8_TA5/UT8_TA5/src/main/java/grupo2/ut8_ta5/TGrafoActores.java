/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo2.ut8_ta5;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class TGrafoActores extends TGrafoNoDirigido implements IGrafoKevinBacon {

    public TGrafoActores(Collection<TVerticeActor> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
        }
    }

    @Override
    public int numBacon(Comparable actor) {
        if (actor.compareTo("Kevin_Bacon") == 0) {
            return 0;
        }
        
        if (!this.existeVertice(actor)) {
            return -1;
        }
        
        TVerticeActor KevinBacon = (TVerticeActor) this.getVertices().get("Kevin_Bacon");

        Queue<TVerticeActor> cola = new LinkedList<>();

        KevinBacon.setVisitado(true);
        KevinBacon.setBacon(0);
        cola.offer(KevinBacon);

        while (!cola.isEmpty()) {
            TVerticeActor x = cola.poll();
            for (Object adyOb : x.getAdyacentes()) {
                TAdyacencia ady = (TAdyacencia) adyOb;

                TVerticeActor y = (TVerticeActor) ady.getDestino();
                if (!y.getVisitado()) {
                    y.setBacon(x.getBacon() + 1);
                    
                    if (y.getEtiqueta().compareTo(actor) == 0) {
                        this.desvisitarVertices();
                        return y.getBacon();
                    }
                    
                    y.setVisitado(true);
                    cola.offer(y);
                }
            }
        }
        this.desvisitarVertices();
        return Integer.MAX_VALUE;
    }

}
