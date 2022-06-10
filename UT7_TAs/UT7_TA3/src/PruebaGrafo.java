
public class PruebaGrafo {

    public static void main(String[] args) {
        TGrafoDirigido vueleSeguro = (TGrafoDirigido) UtilGrafos.cargarGrafo(
                "./src/aeropuertos_1.txt",
                "./src/conexiones_1.txt",
                false, TGrafoDirigido.class);

        Object[] etiquetasarray = vueleSeguro.getEtiquetasOrdenado();

        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(vueleSeguro.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, vueleSeguro.getVertices(), "Matriz");

        for (Comparable c : new Comparable[]{
            "Asuncion", "San_Pablo", "Curitiba", "Santos"
        }) {
            System.out.println("\t" + c);
            for (TVertice i : vueleSeguro.bpf(c)) {
                System.out.println(i.getEtiqueta());
            }
        }
        /*
        Double[][] mfloyd = vueleSeguro.floyd();
        UtilGrafos.imprimirMatrizMejorado(mfloyd, vueleSeguro.getVertices(), "Matriz luego de FLOYD");
        for (int i = 0; i < etiquetasarray.length; i++) {
            System.out.println("excentricidad de " + etiquetasarray[i] + " : " + vueleSeguro.obtenerExcentricidad((Comparable) etiquetasarray[i]));
        }
         */

        System.out.println();
//        System.out.println("Centro del grafo: " + vueleSeguro.centroDelGrafo());

    }
}
