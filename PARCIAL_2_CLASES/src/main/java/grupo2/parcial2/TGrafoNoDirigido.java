/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo2.parcial2;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 *
 * @author santi
 */
public class TGrafoNoDirigido extends TGrafoDirigido {

    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
    }

    @Override
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        return super.eliminarArista(nomVerticeOrigen, nomVerticeDestino) && super.eliminarArista(nomVerticeDestino, nomVerticeOrigen);
    }

    @Override
    public boolean insertarArista(TArista arista) {
        if (!super.insertarArista(arista)) {
            return false;
        }
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

    public Collection<TVertice> bea() {
        Collection<TVertice> res = new LinkedList<>();
        if (this.getVertices() != null) {
            for (TVertice vertice : this.getVertices().values()) {
                if (!vertice.getVisitado()) {
                    vertice.bea(res);
                }
            }
            this.limpiarVisitados();
        }
        return res;
    }

    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        TVertice origen = this.getVertices().get(etiquetaOrigen);
        if (origen == null) {
            return null;
        }

        return this.bea(origen);
    }

    public Collection<TVertice> bea(TVertice origen) {
        if (origen.getVisitado()) {
            return null;
        }

        Collection<TVertice> res = new LinkedList<>();
        origen.bea(res);

        this.limpiarVisitados();
        return res;
    }
    
    public int distanciaBEA(Comparable origenEti, Comparable destinoEti) {
        if (destinoEti.compareTo(origenEti) == 0) {
            return 0;
        }
        
        if (!this.existeVertice(destinoEti)) {
            return -1;
        }
        
        TVertice origen = this.getVertices().get(origenEti);
        Map<Comparable, Integer> distanciaBEADicc = new HashMap<>(getVertices().size());

        Queue<TVertice> cola = new LinkedList<>();

        origen.setVisitado(true);
        distanciaBEADicc.put(origen.getEtiqueta(), 0);
        cola.offer(origen);

        while (!cola.isEmpty()) {
            TVertice x = cola.poll();
            for (Object adyOb : x.getAdyacentes()) {
                TAdyacencia ady = (TAdyacencia) adyOb;

                TVertice y = ady.getDestino();
                if (!y.getVisitado()) {
                    int distanciaBEA = distanciaBEADicc.get(x.getEtiqueta()) + 1;
                    
                    if (y.getEtiqueta().compareTo(destinoEti) == 0) {
                        this.limpiarVisitados();
                        return distanciaBEA;
                    }
                    
                    distanciaBEADicc.put(y.getEtiqueta(), distanciaBEA);
                    y.setVisitado(true);
                    cola.offer(y);
                }
            }
        }
        this.limpiarVisitados();
        return Integer.MAX_VALUE;
    }
    
    // Precondiciones:
    //     El grafo tiene al menos un vértice
    //     El grafo es conexo
    public List<Comparable> puntosArticulacion() {
        if (this.getVertices().isEmpty()) {
            return new LinkedList<>();
        }
        
        TVertice origen = this.getVertices().values().iterator().next();
        List<Comparable> res = new LinkedList<>();
        origen.puntosArticulacion(new int[1], res, new HashMap<>(), null);
        this.limpiarVisitados();
        return res;
    }

}
