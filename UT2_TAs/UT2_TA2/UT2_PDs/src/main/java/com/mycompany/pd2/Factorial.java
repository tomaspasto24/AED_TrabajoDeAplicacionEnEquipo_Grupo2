/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pd2;

/**
 *
 * @author TomasUcu
 */
public class Factorial {
    
    public static void main(String[] args) {
//        System.out.println(factorial(0));
        System.out.println(factorial(4));
        System.out.println(factorial(5));
//        System.out.println(factorial(-5));
    }

    public static int factorial(int n) {
        if( n <= 0) {
            throw new IllegalArgumentException("La entrada debe ser mayor a 0.");
        }
        return factorialRecursive(n);
    }
    
    private static int factorialRecursive(int n) {
        if(n == 1) {
            return 1;
        }
        return n * factorial(n-1);
    }

}
