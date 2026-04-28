package ejercicio5;

import java.io.*;

public class Main {
	public static void main(String[] args) {
		String rutaArchivo1 = "boletin2/ejercicio5/archivo1.txt";
		String rutaArchivo2 = "boletin2/ejercicio5/archivo2.txt";

		try (BufferedReader br1 = new BufferedReader(new FileReader(rutaArchivo1));
				BufferedReader br2 = new BufferedReader(new FileReader(rutaArchivo2))) {

			String linea1;
			String linea2;
			int numLinea = 1;
			boolean sonIguales = true;

			while (true) {
				linea1 = br1.readLine();
				linea2 = br2.readLine();

				if (linea1 == null && linea2 == null) {
					break;
				}

				if (linea1 == null || linea2 == null) {
					sonIguales = false;
					System.out.println("Los archivos son distintos.");
					System.out.println("Un archivo es más largo que el otro.");
					System.out.println("Diferencia a partir de la línea: " + numLinea);
					break;
				}

				if (!linea1.equals(linea2)) {
					sonIguales = false;
					System.out.println("Los archivos son distintos.");

					int longitudMinima = Math.min(linea1.length(), linea2.length());
					int numCaracter = -1;

					for (int i = 0; i < longitudMinima; i++) {
						if (linea1.charAt(i) != linea2.charAt(i)) {
							numCaracter = i + 1;
							break;
						}
					}

					if (numCaracter == -1) {
						numCaracter = longitudMinima + 1;
					}

					System.out.println("Primera diferencia encontrada en:");
					System.out.println("- Línea: " + numLinea);
					System.out.println("- Carácter: " + numCaracter);
					break;
				}

				numLinea++;
			}

			if (sonIguales) {
				System.out.println("Los archivos son exactamente iguales.");
			}

		} catch (IOException e) {
			System.out.println("Error al leer los archivos: " + e.getMessage());
		}
	}
}