package grupo2.parcial2;

import java.util.Collection;

import java.util.LinkedList;

public class TAristas extends LinkedList<TArista> {

    private final static String SEPARADOR_ELEMENTOS_IMPRESOS = "-";
    //private Collection<TArista> aristas  = new LinkedList<TArista>();

    /**
     * Busca dentro de la lista de aristas una arista que conecte a etOrigen con
     * etDestino
     *
     * @param etOrigen
     * @param etDestino
     * @return
     */
    public TArista buscar(Comparable etOrigen, Comparable etDestino) {
        for (TArista a : this) {
            if (a.etiquetaDestino.compareTo(etDestino) == 0 && a.etiquetaOrigen.compareTo(etOrigen) == 0) {
                return a;
            }
        }
        return null;
    }

    /**
     * Busca la arista de menor costo que conecte a cualquier nodo de VerticesU
     * con cualquier otro de VerticesV y cuyo costo sea el minimo
     *
     * @param VerticesU - Lista de vertices U
     * @param VerticesV - Lista de vertices V
     * @return
     */
    public TArista buscarMin(Collection<Comparable> VerticesU, Collection<Comparable> VerticesV) {
        TArista minimo = null;
        for (TArista a : this) {
            if (VerticesV.contains(a.etiquetaDestino)
                    && VerticesU.contains(a.etiquetaOrigen)
                    && (minimo == null || minimo.costo > a.costo)) {
                minimo = a;
            }
        }
        return minimo;
    }

    //ORIGEN - DESTINO - COSTO
    public String imprimirEtiquetas() {
        if (this.isEmpty()) {
            return null;
        }
        StringBuilder salida = new StringBuilder();
        for (TArista a : this) {
            salida.append(a.etiquetaOrigen);
            salida.append(SEPARADOR_ELEMENTOS_IMPRESOS);
            salida.append(a.etiquetaDestino);
            salida.append(SEPARADOR_ELEMENTOS_IMPRESOS);
            salida.append(a.costo);
            salida.append("\n");
        }
        return salida.toString();
    }

    void insertarAmbosSentidos(Collection<TArista> aristas) {
        if (aristas == null) {
            return;
        }
        TArista tempArista;
        for (TArista ta : aristas) {
            this.add(ta);
            this.add(ta.aristaInversa());
        }
    }

    public void insertarOrdenadoPorCosto(TArista arista) {
        int indice = 0;
        for (TArista a : this) {
            if (a.costo > arista.costo) {
                this.add(indice, arista);
                break;
            }
            indice++;
        }
    }
}
