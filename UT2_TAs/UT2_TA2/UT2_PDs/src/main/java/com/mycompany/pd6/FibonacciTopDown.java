/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pd6;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 *
 * @author TomasUcu
 */
public class FibonacciTopDown {
    
    private static int invocaciones = 0;
    private static int cantMemo = 0;
    
    public static void main(String[] args) {
        System.out.println(fibonacci(25, new int[1]));  
        System.out.println("Cantidad de veces que se invoca al método recursivo completo: " + invocaciones );
        System.out.println("cantidad de veces que se utilizan valores previamente almacenados: " + cantMemo );
    }

    public static int fibonacci(int n, int[] memo) {
        invocaciones++;
        
        // Controlar la capacidad máxima de memo.        
        if( memo.length <= n ) {
            memo = Arrays.copyOf(memo, n + 1);
        }
        
        // Saber si se encuentra n calculado en memo, en tal caso retorna el valor que se calculó anteriormente.
        if( IntStream.of(memo).anyMatch(x -> x == n) ) {
            cantMemo++;
            return memo[n];       
        }
        
        // Caso base.
        if( n <= 1 ) {
            memo[n] = n;
            return n;
        } else {
            // Llamada recursiva.
            memo[n] = fibonacci(n-1, memo) + fibonacci(n-2, memo);
            return memo[n];
        }
    }
}
