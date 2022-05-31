/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ut6_ta1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author santi
 */
public class THashTest {
    
    public THashTest() {
    }

    @Test
    public void testFuncionHashing() {
        IHash conjHash = new THash(12);
        
        int[] claves = {2, 3, 4, 5};
        for (int i : claves) {
            conjHash.insertar(i);
        }
        
        assertEquals(1, conjHash.buscar(2));
    }
    
}
