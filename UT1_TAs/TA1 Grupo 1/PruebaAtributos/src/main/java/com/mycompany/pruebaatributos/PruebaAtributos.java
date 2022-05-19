/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.pruebaatributos;

/**
 *
 * @author Seba Fripp
 */
public class PruebaAtributos {
    //int val´or; //"val´or" no es un nombre de variable correcto, por lo tanto, da error.
    private static int valor;
    
    private static String palabra;
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println(valor); // Se imprime 0, ya que no fue inicializada.
        System.out.println(palabra); // Se imprime null, ya que no fue inicializada.
        
        valor = 10;
        System.out.println(valor); // Se imprime 10, ya que se le asigno 10 a la variable "valor".
    }
}
