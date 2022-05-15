/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pd6;

/**
 *
 * @author TomasUcu
 */
public class FibonacciBottomUp {
    
    public static void main(String[] args) {
        System.out.println(fibonacci(15));
    }
    
    public static int fibonacci(int n){
        if(n == 0) {
            return 0;
        }
        
        int fibAnterior = 0;
        int fibActual = 1;

        for(int i = 1; i < n; i++) {
            int fibNuevo = fibActual + fibAnterior;
            fibAnterior = fibActual;
            fibActual = fibNuevo;
        }

        return fibActual;
    }
}
