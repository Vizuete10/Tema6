package ejercicio4;

import java.io.*;

public class Main {
	public static void main(String[] args) {
		String archivoCodec = "boletin2/ejercicio4/codec.txt";
		String archivoEntrada = "boletin2/ejercicio4/mensaje_original.txt";
		String archivoSalida = "boletin2/ejercicio4/mensaje_cifrado.txt";

		String alfabeto = "";
		String cifrado = "";

		try (BufferedReader brCodec = new BufferedReader(new FileReader(archivoCodec))) {
			String lineaAlfabeto = brCodec.readLine();
			String lineaCifrado = brCodec.readLine();

			if (lineaAlfabeto != null && lineaCifrado != null) {
				alfabeto = lineaAlfabeto.replace("Alfabeto: ", "").trim();
				cifrado = lineaCifrado.replace("Cifrado: ", "").trim();
			} else {
				System.out.println("Error: 'codec.txt' no tiene el formato correcto.");
			}
		} catch (IOException e) {
			System.out.println("Error al leer el archivo de códigos: " + e.getMessage());
			return;
		}

		try (BufferedReader brMensaje = new BufferedReader(new FileReader(archivoEntrada));
				BufferedWriter bwMensaje = new BufferedWriter(new FileWriter(archivoSalida))) {

			int caracterLeido;

			while ((caracterLeido = brMensaje.read()) != -1) {
				char charOriginal = (char) caracterLeido;
				char charBuscado = Character.toLowerCase(charOriginal);

				int posicion = alfabeto.indexOf(charBuscado);

				if (posicion != -1) {
					char charCifrado = cifrado.charAt(posicion);

					if (Character.isUpperCase(charOriginal)) {
						bwMensaje.write(Character.toUpperCase(charCifrado));
					} else {
						bwMensaje.write(charCifrado);
					}
				} else {

					bwMensaje.write(charOriginal);
				}
			}

			System.out.println("¡Proceso terminado! Revisa el archivo: " + archivoSalida);

		} catch (IOException e) {
			System.out.println("Error procesando los mensajes: " + e.getMessage());
		}
	}
}