/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pd4;

/**
 *
 * @author TomasUcu
 */
public class BusquedaPorInterpolacion {
    
    public static void main(String[] args) {
        System.out.println(busquedaInterpolacion(new int[]{1,2,3,4,5,6,7,8}, 8));
    }
    
    // La interpolacion funciona siempre que los arrays esten ordenados.
    public static int busquedaInterpolacion(int[] arr, int elementoBuscar) {
        int startIndex = 0;
        int lastIndex = arr.length - 1;
        
        // El indice inicial tiene que ser menor que el final. El elemento a buscar tiene que 
        // ser mayor que el elemento del indice inicial y menor que el elemento del inidice final.
        while ( startIndex < lastIndex && 
                elementoBuscar >= arr[startIndex] && 
                elementoBuscar <= arr[lastIndex] ) {
            
            // Formula de interpolación:
            int pos = startIndex + 
                        ((lastIndex - startIndex) / (arr[lastIndex] - arr[startIndex]))
                        * (elementoBuscar - arr[startIndex]); 
            
            if(arr[pos] == elementoBuscar) {
                return pos;
            } else if (arr[pos] < elementoBuscar ) {
                startIndex = pos + 1;
            } else {
                lastIndex = pos - 1;
            }
        }
        return -1;
    }
}
