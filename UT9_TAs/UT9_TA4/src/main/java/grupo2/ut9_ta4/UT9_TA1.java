/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package grupo2.ut9_ta4;

import static grupo2.ut9_ta4.TClasificador.METODO_CLASIFICACION_INSERCION;
import static grupo2.ut9_ta4.TClasificador.METODO_CLASIFICACION_SHELL;
import static grupo2.ut9_ta4.TClasificador.METODO_CLASIFICACION_BURBUJA;
import java.util.Arrays;

/**
 *
 * @author TomasUcu
 */
public class UT9_TA1 {
    public static int[] vector = { 5, 4, 2, 3, 9 };

    public static void main(String[] args) {
		TClasificador clasif = new TClasificador();
		GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
		int[] vectorAleatorio = gdg.generarDatosAleatorios();
		int[] vectorAscendente = gdg.generarDatosAscendentes();
		int[] vectorDescendente = gdg.generarDatosDescendentes();

//		int[] resAleatorio = clasif.clasificar(vectorAleatorio,
//				METODO_CLASIFICACION_INSERCION);
//		for (int i = 0; i < resAleatorio.length; i++) {
//			System.out.print(resAleatorio[i] + " ");
//		}
//		int[] resAscendente = clasif.clasificar(vectorAscendente,
//				METODO_CLASIFICACION_INSERCION);
//		for (int i = 0; i < resAscendente.length; i++) {
//			System.out.print(resAscendente[i] + " ");
//		}
//		int[] resDescendente = clasif.clasificar(vectorDescendente,
//				METODO_CLASIFICACION_INSERCION);
//		for (int i = 0; i < resDescendente.length; i++) {
//			System.out.print(resDescendente[i] + " ");
//		}

                System.out.println("Clasificación");

                System.out.println(clasif.estaOrdenado(vector));
                
                int[] clasificacion = clasif.clasificar(vector, METODO_CLASIFICACION_INSERCION);
                
                System.out.println(Arrays.toString(clasificacion));
                System.out.println(clasif.estaOrdenado(clasificacion));
                
                System.out.println("Shellsort");
                vector = new int[] { 5, 4, 2, 3, 9 };
                
                int[] shell = clasif.clasificar(vector, METODO_CLASIFICACION_SHELL);
                System.out.println(Arrays.toString(shell));
                System.out.println(clasif.estaOrdenado(shell));
                
                System.out.println("Burbuja");
                vector = new int[] { 5, 4, 2, 3, 9 };
                
                int[] burbuja = clasif.clasificar(vector, METODO_CLASIFICACION_BURBUJA);
                System.out.println(Arrays.toString(burbuja));
                System.out.println(clasif.estaOrdenado(burbuja));
    }
}
