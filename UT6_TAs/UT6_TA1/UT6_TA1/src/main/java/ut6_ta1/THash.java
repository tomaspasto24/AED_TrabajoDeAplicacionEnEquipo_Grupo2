/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut6_ta1;

/**
 *
 * @author santi
 */
public class THash implements IHash {

    private final Integer[] arr;

    public THash(int capacidad) {
        this.arr = new Integer[capacidad];
    }

    @Override
    public int buscar(int unaClave) {
        int hash = this.funcionHashing(unaClave);
        for (int i = 0; i < this.arr.length; i++) {
            if (this.arr[hash] == null) {
                return i + 1;
            } else if (this.arr[hash].equals(unaClave)) {
                return i + 1;
            }
            hash = (hash + 1) % this.arr.length;
        }
        return this.arr.length;
    }

    @Override
    public int insertar(int unaClave) {
        int hash = this.funcionHashing(unaClave);
        for (int i = 0; i < this.arr.length; i++) {
            if (this.arr[hash] == null) {
                this.arr[hash] = unaClave;
                return i + 1;
            } else if (this.arr[hash].equals(unaClave)) {
                return i + 1;
            }
            hash = (hash + 1) % this.arr.length;
        }
        return this.arr.length;
    }

    @Override
    public int funcionHashing(int unaClave) {
        return (int)(this.arr.length * (((float)unaClave / 7) % 1));
    }

}
