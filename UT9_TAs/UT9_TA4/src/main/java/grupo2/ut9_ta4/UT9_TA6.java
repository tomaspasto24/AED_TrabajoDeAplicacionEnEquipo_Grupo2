/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo2.ut9_ta4;

import java.util.Arrays;

/**
 *
 * @author santi
 */
public class UT9_TA6 {
    public static void main(String[] args) {
        TClasificador clasif = new TClasificador();
        GeneradorDatosGenericos generador = new GeneradorDatosGenericos();
        
        int[] vector = generador.generarDatosAscendentes(10);
        clasif.clasificar(vector, TClasificador.METODO_CLASIFICACION_HEAPSORT);
        System.out.println(clasif.estaOrdenado(vector));
        System.out.println(Arrays.toString(vector));
    }
}
