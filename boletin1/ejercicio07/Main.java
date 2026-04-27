package ejercicio07;

import java.io.*;
import java.util.*;

public class Main {

	static final int MAX_CONTACTOS = 20;
	static final String RUTA_ARCHIVO = "agenda.txt";

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		// Usamos TreeMap para que se ordene alfabéticamente por la clave (Nombre)
		// String.CASE_INSENSITIVE_ORDER hace que "Ana" y "ana" se traten igual en el
		// orden
		TreeMap<String, String> agenda = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

		// --- FASE DE CARGA (Al iniciar) ---
		cargarAgenda(agenda);

		int opcion = 0;

		do {
			System.out.println("\n--- AGENDA TELEFÓNICA ---");
			System.out.println("1. Nuevo contacto");
			System.out.println("2. Buscar por nombre");
			System.out.println("3. Mostrar todos");
			System.out.println("4. Salir");
			System.out.print("Elige una opción: ");

			try {
				opcion = Integer.parseInt(sc.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.println("Por favor, introduce un número válido.");
				continue;
			}

			switch (opcion) {
			case 1:
				// NUEVO CONTACTO
				if (agenda.size() >= MAX_CONTACTOS) {
					System.out.println("Error: La agenda está llena (Máx. " + MAX_CONTACTOS + " contactos).");
				} else {
					System.out.print("Introduce el nombre: ");
					String nombre = sc.nextLine().trim();

					if (agenda.containsKey(nombre)) {
						System.out.println("Error: El contacto '" + nombre + "' ya existe.");
					} else {
						System.out.print("Introduce el teléfono: ");
						String telefono = sc.nextLine().trim();
						agenda.put(nombre, telefono);
						System.out.println("Contacto guardado correctamente.");
					}
				}
				break;

			case 2:
				// BUSCAR POR NOMBRE
				System.out.print("Introduce el nombre a buscar: ");
				String nombreBusqueda = sc.nextLine().trim();

				if (agenda.containsKey(nombreBusqueda)) {
					System.out.println("El teléfono de " + nombreBusqueda + " es: " + agenda.get(nombreBusqueda));
				} else {
					System.out.println("No se encontró ningún contacto con ese nombre.");
				}
				break;

			case 3:
				// MOSTRAR TODOS
				if (agenda.isEmpty()) {
					System.out.println("La agenda está vacía.");
				} else {
					System.out.println("\n--- LISTA DE CONTACTOS (" + agenda.size() + "/" + MAX_CONTACTOS + ") ---");
					// Al ser un TreeMap, la iteración sale ordenada alfabéticamente
					for (Map.Entry<String, String> entrada : agenda.entrySet()) {
						System.out.println("- " + entrada.getKey() + ": " + entrada.getValue());
					}
				}
				break;

			case 4:
				// SALIR Y GUARDAR
				guardarAgenda(agenda);
				System.out.println("Saliendo de la agenda... ¡Hasta pronto!");
				break;

			default:
				System.out.println("Opción incorrecta. Elige un número del 1 al 4.");
			}

		} while (opcion != 4);

		sc.close();
	}

	// --- MÉTODOS AUXILIARES PARA FICHEROS ---

	private static void cargarAgenda(TreeMap<String, String> agenda) {
		File archivo = new File(RUTA_ARCHIVO);
		if (!archivo.exists()) {
			System.out.println("No se encontró archivo de agenda previo. Se creará uno nuevo al salir.");
			return;
		}

		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				// Separamos la línea por la coma que pusimos al guardar
				String[] partes = linea.split(",");
				if (partes.length == 2 && agenda.size() < MAX_CONTACTOS) {
					agenda.put(partes[0].trim(), partes[1].trim());
				}
			}
			System.out.println("Se han cargado " + agenda.size() + " contactos de la memoria.");
		} catch (IOException e) {
			System.out.println("Error al cargar la agenda: " + e.getMessage());
		}
	}

	private static void guardarAgenda(TreeMap<String, String> agenda) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
			for (Map.Entry<String, String> entrada : agenda.entrySet()) {
				// Guardamos en formato: Nombre,Teléfono
				bw.write(entrada.getKey() + "," + entrada.getValue());
				bw.newLine();
			}
			System.out.println("Datos guardados correctamente en " + RUTA_ARCHIVO);
		} catch (IOException e) {
			System.out.println("Error al guardar la agenda: " + e.getMessage());
		}
	}
}