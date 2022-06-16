package ut8.ut8_pd1;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Arrays;

public class PruebaGrafo {

    public static void main(String[] args) {
        TGrafoDirigido grafo;
        {
            ArrayList<TVertice> listaVertices = new ArrayList<>(8);
            {
                String[] vectorVertices = {
                    "Artigas", "Canelones", "Colonia", "Durazno",
                    "Florida", "Montevideo", "Punta del Este", "Rocha"
                };
                for (String vertice : vectorVertices) {
                    listaVertices.add(new TVertice(vertice));
                }
            }

            ArrayList<TArista> listaAristas = new ArrayList<>(13);
            {
                String[] vectorAristas = {
                    "Artigas,Rocha,400", "Canelones,Artigas,500",
                    "Canelones,Colonia,200", "Canelones,Durazno,170",
                    "Canelones,Punta del Este,90", "Colonia,Montevideo,180",
                    "Florida,Durazno,60", "Montevideo,Artigas,700",
                    "Montevideo,Canelones,30", "Montevideo,Punta del Este,130",
                    "Punta del Este,Rocha,90", "Rocha,Montevideo,270"
                };
                for (String arista : vectorAristas) {
                    String[] datos = arista.split(",", 3);
                    String origen = datos[0];
                    String destino = datos[1];
                    double costo = Double.parseDouble(datos[2]);
                    
                    listaAristas.add(new TArista(origen, destino, costo));
                }
            }
            
            grafo = new TGrafoDirigido(listaVertices, listaAristas);
        }
        
        Double[][] matrizCostos = UtilGrafos.obtenerMatrizCostos(grafo);
        UtilGrafos.imprimirMatrizMejorado(matrizCostos, grafo.getVertices(), "Matriz de adyacencia de grafo de prueba");

        Double[][] matrizFloyd = grafo.floyd();
        UtilGrafos.imprimirMatrizMejorado(matrizFloyd, grafo.getVertices(), "Matriz de Floyd de grafo de prueba");
    }
}
