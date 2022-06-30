package grupo2.ut9_ta4;

import java.util.Random;

public class GeneradorDatosGenericos {
	private static int TAMANIO_MAX = 32000;
        
	public int[] generarDatosAleatorios() {
            return generarDatosAleatorios(TAMANIO_MAX);
        }
	public int[] generarDatosAleatorios(int tamanioMax) {
		Random rnd = new Random();
		int[] datosGenerados = new int[tamanioMax];
		boolean[] datosUtilizados = new boolean[tamanioMax];
		for (int i = 0; i < datosGenerados.length; i++) {
			int j = rnd.nextInt(tamanioMax);
			while(datosUtilizados[j]){
				j = (j + 1) % tamanioMax;
			}
			datosGenerados[j] = i;
			datosUtilizados[j] = true;
		}
		return datosGenerados;
	}
	
	public int[] generarDatosAscendentes() {
            return generarDatosAscendentes(TAMANIO_MAX);
        }
	public int[] generarDatosAscendentes(int tamanioMax) {
		int [] copiaAscendente = new int[tamanioMax];
		for (int i = 0; i < tamanioMax; i++) {
			copiaAscendente[i] = i;
		}
		return copiaAscendente;
	}
	
	public int[] generarDatosDescendentes() {
            return generarDatosDescendentes(TAMANIO_MAX);
        }
	public int[] generarDatosDescendentes(int tamanioMax) {
		int [] copiaDescendente = new int[tamanioMax];
		for (int i = 0; i < tamanioMax; i++) {
			copiaDescendente[i] = tamanioMax - i;
		}
		return copiaDescendente;
	}
	
}
