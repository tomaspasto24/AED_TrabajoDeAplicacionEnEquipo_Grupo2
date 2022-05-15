/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pd2;

import java.lang.reflect.Array;

/**
 *
 * @author TomasUcu
 */
public class InvertirArray {

    public static void main(String[] args) {
        int[] ar = { 1, 2, 3, 4, 5};
        imprimirElementosArray(invertirArray(ar, 0, 4));
        
//        imprimirElementosArray(invertirArray(ar, 0, 6));
        int[] ar1 = { 1 }; 
        imprimirElementosArray(invertirArray(ar1, 0, 0));
        
        int[] ar2 = { }; 
        imprimirElementosArray(invertirArray(ar2, 0, 0));
    }
    public static int[] invertirArray(int[] arr, int i, int j) {
        if(i >= j) {
            return arr;
        }
        System.out.println(i + "i, j" + j);
        int valuei = arr[i];
        int valuej = arr[j];
        
        Array.setInt(arr, i, valuej);
        Array.setInt(arr, j, valuei);
        
        return invertirArray(arr, ++i, --j);
    }
    
    private static void imprimirElementosArray(int[] arr){
        for(int c: arr){
            System.out.print(c + " ");
        }     
        System.out.println();
    }
    
}
