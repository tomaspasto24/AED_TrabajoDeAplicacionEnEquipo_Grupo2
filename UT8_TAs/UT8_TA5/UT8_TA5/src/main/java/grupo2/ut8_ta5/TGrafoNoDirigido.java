package grupo2.ut8_ta5;


import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido {

    protected TAristas lasAristas = new TAristas();
    
    protected TGrafoNoDirigido() {
        super();
    }

    /**
     *
     * @param vertices
     * @param aristas
     */
    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
        lasAristas.insertarAmbosSentidos(aristas);

    }

    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        TArista arInv = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        tempbool = (super.insertarArista(arista) && super.insertarArista(arInv));
        return tempbool;
    }

    public TAristas getLasAristas() {
        return lasAristas;
    }

    @Override
    public TGrafoNoDirigido Prim() {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
        // Tools | Templates.
    }

    @Override
    public TGrafoNoDirigido Kruskal() {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
        // Tools | Templates.
    }

    @Override
    public Collection<TVertice> bea() {
        Collection<TVertice> res = new LinkedList<>();
        if (this.getVertices() != null) {
            for (TVertice vertice : this.getVertices().values()) {
                if (!vertice.getVisitado()) {
                    vertice.bea(res);
                }
            }
            this.desvisitarVertices();
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

        this.desvisitarVertices();
        return res;
    }
}
