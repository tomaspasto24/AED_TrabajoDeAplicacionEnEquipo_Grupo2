

import java.util.LinkedList;

public class TNodoTrie implements INodoTrie {

    private static final int CANT_CHR_ABECEDARIO = 26;
    private TNodoTrie[] hijos;
    private boolean esPalabra;

    public TNodoTrie() {
        hijos = new TNodoTrie[CANT_CHR_ABECEDARIO];
        esPalabra = false;
    }

    @Override
    public void insertar(String unaPalabra) {
        TNodoTrie nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            int indice = unaPalabra.charAt(c) - 'a';
            if (nodo.hijos[indice] == null) {
                nodo.hijos[indice] = new TNodoTrie();
            }
            nodo = nodo.hijos[indice];
        }
        nodo.esPalabra = true;
    }

    private void imprimir(String s, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s);
                
            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                if (nodo.hijos[c] != null) {
                    imprimir(s+(char)(c + 'a'), nodo.hijos[c]);
                }
            }
        }
    }

    @Override
    public void imprimir() {
        
        imprimir("", this);
    }
    
      private TNodoTrie buscarNodoTrie(String s) {
        TNodoTrie nodo = this;
     
         // implementar
        
        return nodo;
    }
    
     private void predecir(String s, String prefijo, LinkedList<String> palabras, TNodoTrie nodo) {
     // implementar
       
    }

    @Override
    public void predecir(String prefijo, LinkedList<String> palabras) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int buscar(String s) {
       int cont = 0;
       TNodoTrie nodoActual = this;
       char [] partes = s.toCharArray();
       for(char m: partes)
       {
           TNodoTrie unHijo = this.obtenerHijo(m);
           cont++;
           if(unHijo != null)
           {
               nodoActual = unHijo;
               
           }
           else
           {
               return cont;
           }
       }
       if(nodoActual.esPalabra)   
       {
           return cont;
       }
       else
       {
           return 0;
       }
    }
    
    private TNodoTrie obtenerHijo(char m)
    {
        int index = m-'a';
        if( index >= 0 && index <=25)
        {
            return this.hijos[index];
        }
        return null;
    }
    
    public int buscarRecursivo(String palabra, int indice, int longitud) {
        char c = palabra.charAt(indice);
        TNodoTrie nodoLetra = this.obtenerHijo(c);
        
        if (nodoLetra != null && this.esPalabra) {
            return longitud;
        }
        
        if (nodoLetra == null) {
            return 0;
        }
        
        return nodoLetra.buscarRecursivo(palabra, indice + 1, longitud + 1);
    }
  
}