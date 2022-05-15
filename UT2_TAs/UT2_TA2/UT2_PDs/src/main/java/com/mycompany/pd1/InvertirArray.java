/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pd1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;

/**
 *
 * @author TomasUcu
 */
public class InvertirArray {
    
    public static void main(String[] args) throws IOException {
//        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] ar = funcInvertirArray("numeros.txt");
        for(int i: ar){
            System.out.print(i + " ");
        }
    }
    
    public static int[] funcInvertirArray(String direccionArchivo) throws FileNotFoundException, IOException{
        int valori;
        int valorj;
        int counter = 0;

        File archivo = new File(direccionArchivo);
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];    

        for(int c = 0; c < n; c++){
            arr[c] = Integer.parseInt(br.readLine());
        }
        
        for(int i = 0, j = n - 1; i <= n; i++, j--) {
                if(i < j) {
                    counter++;
                    valori = arr[i];
                    valorj = arr[j];

                    Array.setInt(arr, i, valorj);
                    Array.setInt(arr, j, valori);
                }            
        }     
        
        System.out.println("Número de veces que se ejecuta el condicional if: " + counter);
        System.out.println("Largo del array: " + n);
        System.out.println("Primera posición del array: " + arr[0]);
        System.out.println("Última posición del array: " + arr[n-1]);

        return arr;
    }    
}