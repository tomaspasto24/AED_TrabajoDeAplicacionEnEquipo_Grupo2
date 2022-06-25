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

public class TGrafoNoDirigido extends TGrafoDirigido {

    private TAristas aristas;

    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
        this.aristas = new TAristas();
        int cont = 0;
        for (TArista a : aristas) {
            cont++;
            this.aristas.insertar(a);
        }
//        this.aristas.insertarAmbosSentidos(aristas);
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
            TArista nuevaArista = this.aristas.buscarMin(U, V);
            res.add(nuevaArista);
            Comparable etiDestino = nuevaArista.getEtiquetaDestino();
            if (U.contains(etiDestino)) {
                U.add(nuevaArista.getEtiquetaOrigen());
                V.remove(nuevaArista.getEtiquetaOrigen());
            } else {
                U.add(etiDestino);
                V.remove(etiDestino);
            }
        }

        return res;
    }

    public TGrafoNoDirigido prim(Comparable inicio) {
        if (!this.existeVertice(inicio)) {
            return null;
        }

        return new TGrafoNoDirigido(this.getVertices().values(), this.primAristas(inicio));
    }

    // Los componentes están definidos por su raíz, la cual se representa por un par clave-valor donde la clave y el valor son iguales.
    // Si una etiqueta no apunta a sí misma en el mapa, entonces pertenece al mismo componente que la etiqueta a la cual sí apunta.
    public TGrafoNoDirigido kruskal() {
        TAristas F = new TAristas();
        int numVertices = this.getVertices().size();
        Map<Comparable, Integer> componentes = new HashMap<>();
        int i = 0;
        for (Comparable vertice : this.getVertices().keySet()) {
            componentes.put(vertice, i);
            i++;
        }
        int contador = numVertices - 1;

        Iterator<TArista> iter = aristas.iterator();
        while (contador > 0 && iter.hasNext()) {
            TArista arista = iter.next();
            Integer comp1 = componentes.get(arista.etiquetaOrigen);
            Integer comp2 = componentes.get(arista.etiquetaDestino);
            if (!comp1.equals(comp2)) {
                F.add(arista);
                for (Comparable vertice : this.getVertices().keySet()) {
                    if (componentes.get(vertice).equals(comp1)) {
                        componentes.put(vertice, comp2);
                    }
                }
                contador--;
            }
        }
        if (contador > 0) {
            return null;
        }
        TGrafoNoDirigido kruskal = new TGrafoNoDirigido(this.getVertices().values(), F);
        return kruskal;
    }

    @Override
    public TAristas getAristas() {
        return this.aristas;
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
