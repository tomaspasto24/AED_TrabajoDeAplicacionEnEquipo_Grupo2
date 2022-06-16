package com.mycompany.ut7_ta6_ej2;


import static java.lang.System.in;
import java.util.Collection;

public class PruebaGrafo {

    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("./src/aeropuertos_1.txt", "./src/conexiones_1.txt",
                false, TGrafoDirigido.class);

        Object[] etiquetasarray = gd.getEtiquetasOrdenado();

   
        gd.desvisitarVertices();
   
        if (gd.tieneCiclo()) {
            System.out.println("tiene ciclos");
        } else {
            System.out.println("no tiene ciclos");
        }
        
        //// calcular todos los caminos, buscar el camino crítico, etc etc
    }
}
