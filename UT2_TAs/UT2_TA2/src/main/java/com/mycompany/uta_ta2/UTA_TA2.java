/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.uta_ta2;

/**
 *
 * @author nmais
 */
public class UTA_TA2 {

    public static void main(String[] args) {
        System.out.println("SubEquipo B");
        int pruebaFact= 5;
        int pruebaFibonacci = 3;
        int [] array = new int [1]; 
        System.out.println("El resultado de hacer el facotrial de " + pruebaFact + " es: " + factorialIterativo(pruebaFact));
        //System.out.println(FibonacciBinario(3, array));
        System.out.println("El resultado de hacer FibonacciBinario de " + pruebaFibonacci + " es: " + FibonacciBinario(pruebaFibonacci, array) + " entra a la recursion: " + array[0] + " veces");
        System.out.println("SubEquipo A");
        int[] r = fibonacciLineal(1000);
        System.out.println(r[0]);
        System.out.println(r[2]);
    }
    
    //SubEquipo B
    //O(1)+O(n)==> O(n)
    public static long factorialIterativo(int n)
    {
        long fact= n;    //O(1)
        while(n>1)      // Condicion=O(1). Bucle = O(n)
        {
            n--;        //O(1)
            fact*=n;    //O(1)
        }
        return fact;
    }
    
    //SubEquipo B
    //Max(O(1), O(2^k) ==> O(2^k)
    public static int FibonacciBinario(int k, int []pContador)
    {
        if(k < 0)
        {
            System.out.println("Error, no se puede hacer fibonacci de un numero negativo");
            return 0;
        }
        if(k==0 || k==1)            //O(1)
        {
            pContador[0]=pContador[0]+1;
            return k;               //O(1)
        }
        else
        {
            pContador[0]=pContador[0]+1;
            return FibonacciBinario(k-1, pContador) + FibonacciBinario(k-2,pContador);   //T(k)=T(k-1)+T(k-2) = O(2^k)
        }
    }
    public static long factorial(long n) {
        if (n == 0) {
            return 1;
        }
        return factorial(n - 1) * n;
    }
    
    public static int[] fibonacciLineal(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("No puede ser negativo");
        }
        if (n == 1) {
            return new int[]{1, 0, 0};
        }
        int[] midResult = fibonacciLineal(n - 1);
        return new int[]{midResult[0] + midResult[1], midResult[0], midResult[2] + 1};
    }
}
