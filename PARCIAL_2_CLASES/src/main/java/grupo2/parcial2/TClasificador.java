package grupo2.parcial2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.function.Function;

public class TClasificador {

    public static final int METODO_CLASIFICACION_INSERCION = 1;
    public static final int METODO_CLASIFICACION_SHELL = 2;
    public static final int METODO_CLASIFICACION_BURBUJA = 3;
    public static final int METODO_CLASIFICACION_QUICKSORT = 4;
    public static final int METODO_CLASIFICACION_HEAPSORT = 5;
    public static final int METODO_CLASIFICACION_RADIXSORT = 6;

    private final Random ran;

    public TClasificador() {
        this.ran = new Random();
        this.ran.setSeed(42);
    }

    /**
     * Punto de entrada al clasificador
     *
     * @param metodoClasificacion
     * @param orden
     * @param tamanioVector
     * @return Un vector del tam. solicitado, ordenado por el algoritmo
     * solicitado
     */
    public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion) {
        switch (metodoClasificacion) {
            case METODO_CLASIFICACION_INSERCION:
                return ordenarPorInsercion(datosParaClasificar);
            case METODO_CLASIFICACION_SHELL:
                return ordenarPorShell(datosParaClasificar);
            case METODO_CLASIFICACION_BURBUJA:
                return ordenarPorBurbuja(datosParaClasificar);
            case METODO_CLASIFICACION_QUICKSORT:
                return ordenarPorQuickSort(datosParaClasificar);
            case METODO_CLASIFICACION_HEAPSORT:
                return ordenarPorHeapSort(datosParaClasificar);
            case METODO_CLASIFICACION_RADIXSORT:
                return ordenarPorRadixSort(datosParaClasificar);
            default:
                System.err.println("Este codigo no deberia haberse ejecutado");
                break;
        }
        return datosParaClasificar;
    }

    private void intercambiar(int[] vector, int pos1, int pos2) {
        int temp = vector[pos2];
        vector[pos2] = vector[pos1];
        vector[pos1] = temp;
    }

    /**
     * @param datosParaClasificar
     * @return
     */
    private int[] ordenarPorShell(int[] datosParaClasificar) {
        int j, inc;
        int[] incrementos = new int[]{3223, 358, 51, 10, 3, 1};

        for (int posIncrementoActual = 0; posIncrementoActual < incrementos.length; posIncrementoActual++) {
            inc = incrementos[posIncrementoActual];
            if (inc < (datosParaClasificar.length / 2)) {
                for (int i = inc; i < datosParaClasificar.length; i++) {
                    j = i - inc;
                    while (j >= 0) {
                        if (datosParaClasificar[j] > datosParaClasificar[j + inc]) {
                            intercambiar(datosParaClasificar, j, j + inc);
                        }
                        j -= inc;
                    }
                }
            }
        }
        
        return datosParaClasificar;
    }

    /**
     * @param datosParaClasificar
     * @return
     */
    protected int[] ordenarPorInsercion(int[] datosParaClasificar) {
        if (datosParaClasificar != null) {
            for (int i = 1; i < datosParaClasificar.length; i++) {
                int j = i - 1;
                while ((j >= 0) && (datosParaClasificar[j + 1] < datosParaClasificar[j])) {
                    intercambiar(datosParaClasificar, j + 1, j);
                    j--;
                }
            }
            return datosParaClasificar;
        }
        return null;
    }

    private int[] ordenarPorBurbuja(int[] datosParaClasificar) {
        int n = datosParaClasificar.length - 1;
        for (int i = 0; i <= n; i++) {
            for (int j = n; j >= (i + 1); j--) {
                if (datosParaClasificar[j] < datosParaClasificar[j - 1]) {
                    intercambiar(datosParaClasificar, j - 1, j);
                }
            }
        }
        return datosParaClasificar;
    }

    public boolean estaOrdenado(int[] vector) {
        for (int i = 0; i < (vector.length - 1); i++) {
            if (vector[i] > vector[i + 1]) {
                return false;
            }
        }
        return true;
    }

    protected int[] ordenarPorQuickSort(int[] datosParaClasificar) {
        int altura = quicksort(datosParaClasificar, 0, datosParaClasificar.length - 1);
        System.out.println("Altura: " + altura);
        return datosParaClasificar;
    }

    private int quicksort(int[] entrada, int i, int j) {
        int izquierda = i;
        int derecha = j;

        int posicionPivote = encuentraPivote(izquierda, derecha, entrada);
        if (posicionPivote >= 0) {
            // El pivote es el elemento de la entrada cuyo índice es "posicionPivote".
            int pivote = entrada[posicionPivote];
            while (izquierda <= derecha) {
                while ((entrada[izquierda] < pivote) && (izquierda < j)) {
                    // L debe moverse a la derecha.
                    izquierda++;
                }

                while ((pivote < entrada[derecha]) && (derecha > i)) {
                    // R debe moverse a la izquierda.
                    derecha--;
                }

                if (izquierda <= derecha) {
                    intercambiar(entrada, derecha, izquierda);
                    izquierda++;
                    derecha--;
                }
            }

            int maxAltura = 0;

            // Un tercer error relacionado con las llamadas recursivas,
            // respecto a los límites de los subarreglos.
            if (i < derecha) {
                // derecha es el fin del subvector izquierdo.
                int alturaIzquierda = quicksort(entrada, i, derecha);
                if (maxAltura < alturaIzquierda) {
                    maxAltura = alturaIzquierda;
                }
            }
            if (izquierda < j) {
                // izquierda es el fin del subvector derecho.
                int alturaDerecha = quicksort(entrada, izquierda, j);
                if (maxAltura < alturaDerecha) {
                    maxAltura = alturaDerecha;
                }
            }

            return maxAltura + 1;
        }
        return 0;
    }

    private int encuentraPivoteElementoCentral(int i, int j) {
        return (int) (j + i) / 2;
    }

    private int encuentraPivote(int i, int j, int[] entrada) {
        int primero = entrada[i];
        for (int k = i; k <= j; k++) {
            int num = entrada[k];
            if (num != primero) {
                return num > primero ? k : i;
            }
        }
        return -1; // TODOS LOS ELEMENTOS SON IGUALES.
    }

    private int encuentraPivoteRandom(int i, int j) {
        return this.ran.nextInt(i, j);
    }

    protected int[] ordenarPorHeapSort(int[] datosParaClasificar) {
        for (int i = (datosParaClasificar.length - 2) / 2; i >= 0; i--) { //Armo el heap inicial de n-1 div 2 hasta 0
            armaHeap(datosParaClasificar, i, datosParaClasificar.length - 1);
        }

        // El bucle termina si i > 0
        for (int i = datosParaClasificar.length - 1; i > 0; i--) {
            intercambiar(datosParaClasificar, 0, i);
            armaHeap(datosParaClasificar, 0, i - 1);
        }
        
        for (int i = (datosParaClasificar.length - 1) / 2; i >= 0; i--) {
            intercambiar(datosParaClasificar, i, datosParaClasificar.length - 1 - i);
        }
        
        return datosParaClasificar;
    }

    private void armaHeap(int[] datosParaClasificar, int primero, int ultimo) {
        if (primero < ultimo) {
            int r = primero;
            // Los hijos están en 2r+1 y 2r+2
            while ((r * 2 + 1) <= ultimo) {
                if (ultimo == 2 * r + 1) { //r tiene un hijo solo
                    if (datosParaClasificar[r] > datosParaClasificar[r * 2 + 1]) {
                        intercambiar(datosParaClasificar, r, 2 * r + 1);
                    }
                    r = ultimo;
                } else { //r tiene 2 hijos
                    int posicionIntercambio;
                    if (datosParaClasificar[2 * r + 1] > datosParaClasificar[2 * r + 2]) {
                        posicionIntercambio = 2 * r + 2;
                    } else {
                        posicionIntercambio = 2 * r + 1;
                    }
                    if (datosParaClasificar[r] > datosParaClasificar[posicionIntercambio]) {
                        intercambiar(datosParaClasificar, r, posicionIntercambio);
                        r = posicionIntercambio;
                    } else {
                        r = ultimo;
                    }
                }
            }
        }
    }

    // Funciona con números de hasta cinco dígitos, pues solo toma aquellos
    // como claves.
    private int[] ordenarPorRadixSort(int[] datosParaClasificar) {
        // Determina cuáles son las múltiples claves para cada paso del ordenamiento,
        // del menos significativo al más significativo.
        ArrayList<Function<Integer, Integer>> selectoresDeClaves = new ArrayList<>(5);
        selectoresDeClaves.add(i -> i % 10);
        selectoresDeClaves.add(i -> (i / 10) % 10);
        selectoresDeClaves.add(i -> (i / 100) % 10);
        selectoresDeClaves.add(i -> (i / 1000) % 10);
        selectoresDeClaves.add(i -> (i / 10000) % 10);

        // Function<T, U> es una interfaz que representa métodos en forma de objetos
        for (Function<Integer, Integer> selectorClaves : selectoresDeClaves) {
            ArrayList<LinkedList<Integer>> urnas = new ArrayList<>(10);
            for (int i = 0; i < 10; i++) {
                urnas.add(new LinkedList<>());
            }

            for (int dato : datosParaClasificar) {
                urnas.get(selectorClaves.apply(dato)).add(dato);
            }

            int i = 0;
            for (LinkedList<Integer> urna : urnas) {
                for (Integer el : urna) {
                    datosParaClasificar[i] = el;
                    i++;
                }
            }
        }

        return datosParaClasificar;
    }
}
