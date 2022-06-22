/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut8.ut8_pd1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author santi
 */
public class TGrafoNoDirigido extends TGrafoDirigido {

    private TAristas aristas = null;

    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
    }

    @Override
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if (!super.eliminarArista(nomVerticeOrigen, nomVerticeDestino)) {
            return false;
        }

        this.aristas.removeIf(a
                -> (a.etiquetaOrigen.compareTo(nomVerticeOrigen) == 0
                && a.etiquetaDestino.compareTo(nomVerticeDestino) == 0)
                || (a.etiquetaOrigen.compareTo(nomVerticeDestino) == 0
                && a.etiquetaDestino.compareTo(nomVerticeOrigen) == 0)
        );

        return super.eliminarArista(nomVerticeDestino, nomVerticeOrigen);
    }

    @Override
    public boolean insertarArista(TArista arista) {
        if (this.aristas == null) {
            this.aristas = new TAristas();
        }
        
        if (!super.insertarArista(arista)) {
            return false;
        }
        this.aristas.add(arista);
        TArista opuesto = new TArista(arista.etiquetaDestino, arista.etiquetaOrigen, arista.costo);
        return super.insertarArista(opuesto);
    }

    private Collection<TArista> primAristas(Comparable inicio) {
        if (!this.existeVertice(inicio)) {
            return null;
        }

        Collection<Comparable> U = new LinkedList<>();
        U.add(inicio);

        Collection<TArista> res = new LinkedList<>();
        Collection<Comparable> V = new LinkedList<>(this.getVertices().keySet());
        V.remove(inicio);

        while (!V.isEmpty()) {
            TArista nuevaArista = this.obtenerMinimo(U, V);
            res.add(nuevaArista);
            U.add(nuevaArista.getEtiquetaDestino());
            V.remove(nuevaArista.getEtiquetaDestino());
        }

        return res;
    }

    public TGrafoNoDirigido prim(Comparable inicio) {
        if (!this.existeVertice(inicio)) {
            return null;
        }

        return new TGrafoNoDirigido(this.getVertices().values(), this.primAristas(inicio));
    }

    private static Comparable raizDe(Comparable inicio, Map<Comparable, Comparable> componentes) {
        if (inicio == null) {
            return null;
        }

        Comparable aux = componentes.get(inicio);
        while (aux != null && !inicio.equals(aux)) {
            inicio = aux;
            aux = componentes.get(inicio);
        }

        return inicio;
    }

    private static void optimizarRaiz(Comparable inicio, Map<Comparable, Comparable> componentes) {
        if (inicio == null) {
            return;
        }

        LinkedList<Comparable> vertices = new LinkedList<>();

        Comparable aux = componentes.get(inicio);
        while (aux != null && !inicio.equals(aux)) {
            vertices.add(inicio);
            inicio = aux;
            aux = componentes.get(inicio);
        }

        for (Comparable vertice : vertices) {
            componentes.put(vertice, inicio);
        }
    }

    // Los componentes están definidos por su raíz, la cual se representa por un par clave-valor donde la clave y el valor son iguales.
    // Si una etiqueta no apunta a sí misma en el mapa, entonces pertenece al mismo componente que la etiqueta a la cual sí apunta.
    public TGrafoNoDirigido kruskal() {
        int numVertices = this.getVertices().size();
        Map<Comparable, Comparable> componentes = new HashMap<>(numVertices * 4 / 3);
        for (Comparable vertice : this.getVertices().keySet()) {
            componentes.put(vertice, vertice);
        }

        TAristas aristas = this.getAristas();
        aristas.sort((a1, a2) -> Double.compare(a1.costo, a2.costo));

        TAristas res = new TAristas();

        int contador = numVertices - 1;

        Iterator<TArista> iter = aristas.iterator();
        while (contador > 0 && iter.hasNext()) {
            TArista arista = iter.next();
            Comparable comp1 = raizDe(arista.etiquetaOrigen, componentes);
            Comparable comp2 = raizDe(arista.etiquetaDestino, componentes);
            if (!comp1.equals(comp2)) {
                res.add(arista);
                componentes.put(comp1, comp2);
                contador--;
            }
        }

        if (contador > 0) {
            return null;
        }

        return new TGrafoNoDirigido(this.getVertices().values(), res);
    }

    public TArista obtenerMinimo(Collection<Comparable> origenes, Collection<Comparable> destinos) {
        double min = Double.MAX_VALUE;
        TArista res = null;

        for (Comparable origenEti : origenes) {
            TVertice origen = this.buscarVertice(origenEti);
            for (Comparable destinoEti : destinos) {
                TAdyacencia ady = origen.buscarAdyacencia(destinoEti);
                if (ady != null && min > ady.getCosto()) {
                    min = ady.getCosto();
                    res = new TArista(origenEti, destinoEti, ady.getCosto());
                }
            }
        }

        return res;
    }

    @Override
    public TAristas getAristas() {
        TAristas res = new TAristas();

        List<Comparable> verticesVisitados = new LinkedList<>();
        for (Map.Entry<Comparable, TVertice> entrada : this.getVertices().entrySet()) {
            Comparable origenEti = entrada.getKey();
            TVertice origen = entrada.getValue();

            for (Object ob : origen.getAdyacentes()) {
                TAdyacencia ady = (TAdyacencia) ob;
                if (!verticesVisitados.contains(ady.getEtiqueta())) {
                    res.add(new TArista(origenEti, ady.getEtiqueta(), ady.getCosto()));
                }
            }

            verticesVisitados.add(origenEti);
        }

        return res;
    }

}
