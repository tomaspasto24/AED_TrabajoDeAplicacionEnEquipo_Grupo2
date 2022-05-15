/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TA2;

/**
 *
 * @author SebaFripp
 */
public class UtilMath {

    public static int factorial(int num) {
        int result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    public static void isPrime(long n) {
        int i = 2;
        int auxiliar = 0;
        while (i < n) {
            if (n % i == 0) {
                System.out.print("El numero: " + n + " no es primo. ");
                auxiliar = 1;
                break;
            } else {
                i++;
            }
        }
        if (i == n) {
            System.out.print("El numero: " + n + " es primo. ");
        }
        int suma = 0;
        
        while (auxiliar <= n) {
            suma += auxiliar;
            auxiliar += 2;
        }
        System.out.println("La suma: " + suma);
    }
}
