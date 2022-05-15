/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.ut1;

import TA2.UtilMath;

/**
 *
 * @author iruby
 */
public class UT1 {

    public static void main(String[] args) {
        // Ejemplo de factorial
        System.out.println(UtilMath.factorial(4));

        // Ejemplo siendo los numeros primos
        UtilMath.isPrime(7);
        UtilMath.isPrime(43);
        
        // Ejemplo no siendo primos
        UtilMath.isPrime(10);
        UtilMath.isPrime(88);
    }
}
