/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pd2;

/**
 *
 * @author TomasUcu
 */
public class SumaLineal {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(sumaRecursiva(arr, 5));
//        System.out.println(sumaRecursiva(new int[0], 5));
//        System.out.println(sumaRecursiva(arr, -2));
    }
    
    public static int sumaLineal(int[] arr, int n) {
        if( arr.length == 0 || n <= 0 ) {
            throw new IllegalArgumentException("El array está vacío o n es menor o igual a 0.");
        }
        return sumaRecursiva(arr, n);
    }
    
    private static int sumaRecursiva(int[] arr, int n) {
        if( n == 1 ){
            return arr[0];
        }
        return sumaLineal( arr, n-1 ) + arr[ n-1 ];
    }
}
