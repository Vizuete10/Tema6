package ejercicio6;

import java.io.*;

public class Main {
	public static void main(String[] args) {
		String archivo = "boletin2/ejercicio6/deportistas.txt";

		int maxEdad = -1;
		String nombreMaxEdad = "";

		double maxPeso = -1;
		String nombreMaxPeso = "";

		double maxEstatura = -1;
		String nombreMaxEstatura = "";

		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea = br.readLine();

			while ((linea = br.readLine()) != null) {

				String[] datos = linea.split(";");

				if (datos.length == 4) {
					String nombre = datos[0];
					int edad = Integer.parseInt(datos[1].trim());

					double peso = Double.parseDouble(datos[2].replace(",", ".").trim());
					double estatura = Double.parseDouble(datos[3].replace(",", ".").trim());

					if (edad > maxEdad) {
						maxEdad = edad;
						nombreMaxEdad = nombre;
					}
					if (peso > maxPeso) {
						maxPeso = peso;
						nombreMaxPeso = nombre;
					}
					if (estatura > maxEstatura) {
						maxEstatura = estatura;
						nombreMaxEstatura = nombre;
					}
				}
			}

			System.out.println("--- RESULTADOS ---");
			System.out.println("Deportista de mayor edad: " + nombreMaxEdad + " (" + maxEdad + " años)");
			System.out.println("Deportista de mayor peso: " + nombreMaxPeso + " (" + maxPeso + " kg)");
			System.out.println("Deportista de mayor estatura: " + nombreMaxEstatura + " (" + maxEstatura + " m)");

		} catch (IOException e) {
			System.out.println("Error al leer el fichero: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Error en el formato de los números del fichero.");
		}
	}
}