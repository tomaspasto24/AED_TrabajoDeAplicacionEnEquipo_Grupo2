/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut8.ut8_pd1;

import java.util.Objects;

public class Tupla<T, U> {
    public T primero;
    public U segundo;
    
    public Tupla(T primero, U segundo) {
        this.primero = primero;
        this.segundo = segundo;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof Tupla) {
            Tupla tupla = (Tupla) other;
            return this.primero.equals(tupla.primero) && this.segundo.equals(tupla.segundo);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.primero);
        hash = 67 * hash + Objects.hashCode(this.segundo);
        return hash;
    }
    
}
