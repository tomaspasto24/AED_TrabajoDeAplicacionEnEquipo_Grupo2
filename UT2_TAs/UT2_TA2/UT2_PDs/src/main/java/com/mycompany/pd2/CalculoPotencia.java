/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pd2;

/**
 *
 * @author TomasUcu
 */
public class CalculoPotencia {
    
    public static void main(String[] args) {
        System.out.println(calculo(5, 5));        
        System.out.println(calculo(5.5, 5));
        System.out.println(calculo(-5, 5));
    }
    
    public static double calculo(double num, double exp) {
        if( exp <= 0 ){
            return 1;
        }
        return calculo(num, exp - 1) * num;
    }
}
