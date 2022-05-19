/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package equipo2.ut1_ta3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author santi
 */
public class ContadorPalabras {
    
    public static int contarPalabras(String frase) {
        int resultado = 0;
        boolean hayLetra = false;
        int consonantCont = 0;
        int vowelCont = 0;
        String vowel = "aeiou";
        
        frase = frase.trim().toLowerCase();
        for(int i = 0; i < frase.length(); i++) {
            char c = frase.charAt(i);
            if(Character.isWhitespace(c)) {
                if(hayLetra) {
                    resultado++;
                    hayLetra = false;
                }
            } else if (Character.isDigit(c)) {
                hayLetra = true;
            } else if (Character.isLetter(c)) {
                hayLetra = true;
                if( vowel.indexOf(c) != -1 ) {
                    vowelCont++;
                } else {
                    consonantCont++;
                }
            }
        }
        
        if(hayLetra) {
            resultado++;
        }
        
        System.out.println("Constantes " + consonantCont);
        System.out.println("Vocales " + vowelCont);
        return resultado;
    }

    public static int contarPalabrasMayoresA(String frase, int limite) {
        int resultado = 0;
        int letras = 0;

        frase = frase.trim();
        for (int i = 0; i < frase.length(); i++) {
            char c = frase.charAt(i);
            if (Character.isWhitespace(c)) {
                if (letras > limite) {
                    resultado++;
                }
                letras = 0;
            } else if (Character.isLetter(c) || Character.isDigit(c)) {
                letras++;
            }
        }

        if (letras > limite) {
            resultado++;
        }

        return resultado;
    }

    public static String[] obtenerLineas(String archivo) throws IOException {
        FileReader reader = new FileReader(archivo);
        BufferedReader buffered = new BufferedReader(reader);
        
        String[] resultado = new String[1];
        int resultadoLen = 0;
        
        
        String texto = buffered.readLine();
        while(texto != null) {
            if (resultadoLen >= resultado.length) {
                String[] nuevoVector = new String[resultado.length * 2];
                System.arraycopy(resultado, 0, nuevoVector, 0, resultado.length);
                resultado = nuevoVector;
            }
            resultado[resultadoLen++] = texto;
            texto = buffered.readLine();
        }
        System.out.println("Lineas: " + resultadoLen);
        
        return resultado;
    }
    
    public int cantPalabras(String[] lineas) {
        int palabras = 0;
        for(String linea : lineas) {
            if(linea == null) {
                continue;
            }
            palabras += contarPalabrasMayoresA(linea, 0);
        }
        return palabras;
    }
}
