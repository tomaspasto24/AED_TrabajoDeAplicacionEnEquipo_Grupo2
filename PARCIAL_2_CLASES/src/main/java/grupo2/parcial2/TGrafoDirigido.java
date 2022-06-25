package com.mycompany.ut8_pd2;


import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class TGrafoDirigido implements IGrafoDirigido {
    /*
        tieneCiclo
    */
    

    private Map<Comparable, TVertice> vertices; // vertices del grafo.

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
        }
    }

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino. En
     * caso de no existir la adyacencia, retorna falso. En caso de que las
     * etiquetas sean invalidas, retorna falso.
     *
     */
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de una arista. Las etiquetas
     * pasadas por par�metro deben ser v�lidas.
     *
     * @return True si existe la adyacencia, false en caso contrario
     */
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de un vertice dentro del
     * grafo.-
     *
     * La etiqueta especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     * contrario
     */
    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    /**
     * Metodo encargado de verificar buscar un vertice dentro del grafo.-
     *
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    protected TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    /**
     * Metodo encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea valida,
     * se deben cumplir los siguientes casos: 1) Las etiquetas pasadas por
     * parametros son v�lidas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    public boolean insertarArista(TArista arista) {
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            if ((vertOrigen != null) && (vertDestino != null)) {
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de insertar un vertice en el grafo.
     *
     * No pueden ingresarse vertices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a ingresar.
     * @return True si se pudo insertar el vertice, false en caso contrario
     */
    public boolean insertarVertice(Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            TVertice vert = new TVertice(unaEtiqueta);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override

    public boolean insertarVertice(TVertice vertice) {
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    /**
     * @return the vertices
     */
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    @Override
    public Comparable centroDelGrafo() {
        Map<Comparable, Double> excentricidades = this.obtenerExcentricidades();
        
        double min = Double.MAX_VALUE;
        Comparable res = null;
        for (Map.Entry<Comparable, Double> entrada : excentricidades.entrySet()) {
            Comparable eti = entrada.getKey();
            double excentricidad = entrada.getValue();
            
            if (min > excentricidad) {
                min = excentricidad;
                res = eti;
            }
        }
        
        return res;
    }

    public void dijkstra(Double[]D, Comparable[]P, Comparable origen)
    {
        Double [][] C = UtilGrafos.obtenerMatrizCostos(this.vertices);
        LinkedList<Integer> VS = new LinkedList<>();                //Almaceno los indices de cada vertice
        int contador = 0;
        for(Comparable v : this.vertices.keySet())
        {
            if(v.compareTo(origen) == 0)
            {
                break;
            }
            else
            {
                contador++;
            }
        }
        for(int i  = 0; i < C.length; i++)
        {
            if (i != contador)
            {
                VS.add(i);
            }
        }
        for(int i = 0; i <C.length; i++){
            D[i] = C[contador][i];
        }
        
        while(!VS.isEmpty())
        {
            int w = -1;
            for(Integer v : VS)
            {
                if(w == -1 || D[v] < D[w])
                {
                    w = v;
                }
            }
            VS.remove((Integer)w);
            for(Integer v : VS)
            {
                if(D[w]+C[w][v] < D[v])
                {
                    D[v] = D[w]+C[w][v];
                    P[v] = w;
                }
            }
        }
       
    }
    
    
    
    @Override
    public Double[][] floyd() {
        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(vertices);
        int tamanio = vertices.size();

        for (int k = 0; k < tamanio; k++) {
            for (int i = 0; i < tamanio; i++) {
                if (matriz[i][k] == Double.MAX_VALUE) {
                    continue;
                }
                for (int j = 0; j < tamanio; j++) {
                    if (matriz[i][k] + matriz[k][j] < matriz[i][j]) {
                        matriz[i][j] = matriz[i][k] + matriz[k][j];
                    }
                }
            }
        }
        
        return matriz;
    }

    private Map<Comparable, Double> obtenerExcentricidades() {
        Double[][] matrizFloyd = this.floyd();
        int tamanio = this.getVertices().size();
        
        Comparable[] etiquetas = new Comparable[tamanio];
        int i = 0;
        for (Comparable eti : this.getVertices().keySet()) {
            etiquetas[i++] = eti;
        }
        
        Map<Comparable, Double> res = new HashMap<>(tamanio * 4 / 3);
        
        for (int y = 0; y < tamanio; y++) {
            double max = 0;
            for (int x = 0; x < tamanio; x++) {
                if (max < matrizFloyd[x][y]) {
                    max = matrizFloyd[x][y];
                }
            }
            res.put(etiquetas[y], max);
        }
        
        return res;
    }
    
    @Override
    public Double obtenerExcentricidad(Comparable etiquetaVertice) {
        return this.obtenerExcentricidades().get(etiquetaVertice);
    }

    @Override
    public boolean[][] warshall() {
        Double[][] C = UtilGrafos.obtenerMatrizCostos(this.getVertices());
        boolean[][] A = new boolean[C.length][C.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (C[i][j] != Double.MAX_VALUE) {
                    A[i][j] = true;
                }
            }
        }

        for (int k = 0; k < A.length; k++) {
            for (int i = 0; i < A.length; i++) {
                if (!A[i][k]) {
                    continue;
                }
                for (int j = 0; j < A.length; j++) {
                    A[i][j] |= A[i][k] && A[k][j];
                }
            }
        }

        return A;
    }

    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        if (this.vertices.remove(nombreVertice) == null) {
            return false;
        }
        
        for (TVertice vertice : this.vertices.values()) {
            vertice.eliminarAdyacencia(nombreVertice);
        }
        return true;
    }
    
    public Collection<TVertice> bpf() {
        Collection<TVertice> resultado = new LinkedList<>();
        for (TVertice vertice : this.getVertices().values()) {
            if (!vertice.getVisitado()) {
                resultado.addAll(this.bpf(vertice, false));
            }
        }

        this.limpiarVisitados();
        return resultado;
    }

    public void limpiarVisitados() {
        for (TVertice vertice : this.getVertices().values()) {
            vertice.setVisitado(false);
        }
    }

    public Collection<TVertice> bpf(Comparable etiquetaOrigen) {
        return this.bpf(etiquetaOrigen, true);
    }
    
    private Collection<TVertice> bpf(Comparable etiquetaOrigen, boolean limpiar) {
        if (etiquetaOrigen == null) {
            return null;
        }
        TVertice vertice = this.buscarVertice(etiquetaOrigen);

        return this.bpf(vertice, limpiar);
    }

    public Collection<TVertice> bpf(TVertice verticeOrigen) {
        return this.bpf(verticeOrigen, true);
    }
    
    private Collection<TVertice> bpf(TVertice verticeOrigen, boolean limpiar) {
        if (verticeOrigen == null) {
            return null;
        }
        Collection<TVertice> resultado = new LinkedList<>();
        verticeOrigen.bpf(resultado);
        
        if (limpiar) {
            this.limpiarVisitados();
        }
        return resultado;
    }
    
    public Collection<TVertice> bea(Comparable origenEti) {
        return this.bea(origenEti, true);
    }
    
    private Collection<TVertice> bea(Comparable origenEti, boolean limpiar) {
        if (origenEti == null || !this.existeVertice(origenEti)) {
            return null;
        }
        
        TVertice origen = this.buscarVertice(origenEti);
        
        Queue<TVertice> cola = new LinkedList<>();
        cola.offer(origen);
        
        Collection<TVertice> res = new LinkedList<>();
        
        while (!cola.isEmpty()) {
            TVertice vertice = cola.poll();
            if (vertice.getVisitado()) {
                continue;
            }
            
            vertice.setVisitado(true);
            res.add(vertice);
            for (Object ob : vertice.getAdyacentes()) {
                TAdyacencia ady = (TAdyacencia) ob;
                cola.offer(ady.getDestino());
            }
        }
        
        if (limpiar) {
            this.limpiarVisitados();
        }
        
        return res;
    }
    
    public List<Comparable> clasificacionTopologica(Comparable destino) {
        TVertice vertice = this.buscarVertice(destino);
        if (vertice == null) {
            return null;
        }
        
        List<Comparable> res = new LinkedList<>();
        vertice.clasificacionTopologica(res);
        return res;
    }
    
    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice origen = this.buscarVertice(etiquetaOrigen);
        if (origen == null || this.buscarVertice(etiquetaDestino) == null) {
            return null;
        }
        
        TCaminos caminos = origen.todosLosCaminos(etiquetaDestino, new TCamino(origen), new TCaminos());
        
        return caminos;
    }
    
    public TAristas getAristas() {
        TAristas res = new TAristas();
        
        for (Map.Entry<Comparable, TVertice> entrada : this.getVertices().entrySet()) {
            Comparable origenEti = entrada.getKey();
            TVertice origen = entrada.getValue();
            
            for (Object ob : origen.getAdyacentes()) {
                TAdyacencia ady = (TAdyacencia) ob;
                res.add(new TArista(origenEti, ady.getEtiqueta(), ady.getCosto()));
            }
        }
        
        return res;
    }
}
