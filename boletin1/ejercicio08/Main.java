package ejercicio08;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Main {

    static final String FICHERO = "src/ejercicio8/temperaturas.txt";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- ESTACIÓN METEOROLÓGICA ---");
            System.out.println("1. Registrar nueva temperatura");
            System.out.println("2. Mostrar historial de registros");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> registrarTemperatura(sc);
                case 2 -> mostrarHistorial();
                case 3 -> System.out.println("¡Hasta pronto!");
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 3);
    }

    static void registrarTemperatura(Scanner sc) {

        System.out.print("Fecha (AAAA-MM-DD) o pulsa Enter para usar la fecha de hoy: ");
        String entrada = sc.nextLine().trim();

        String fecha;
        if (entrada.isEmpty()) {
            fecha = LocalDate.now().toString();
        } else {
            fecha = entrada;
        }

        System.out.print("Temperatura máxima: ");
        int tMax = Integer.parseInt(sc.nextLine().trim());

        System.out.print("Temperatura mínima: ");
        int tMin = Integer.parseInt(sc.nextLine().trim());

        if (tMin > tMax) {
            System.out.println("Error: la mínima no puede ser mayor que la máxima.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHERO, true))) {
            bw.write(fecha + "," + tMax + "," + tMin);
            bw.newLine();
            System.out.println("Registro guardado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    static void mostrarHistorial() {

        File f = new File(FICHERO);
        if (!f.exists()) {
            System.out.println("No hay registros todavía.");
            return;
        }

        int maxTemp = Integer.MIN_VALUE;
        int minTemp = Integer.MAX_VALUE;

        System.out.println("\nFecha          T.Máx   T.Mín");
        System.out.println("------------------------------");

        try (BufferedReader br = new BufferedReader(new FileReader(FICHERO))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");

                String fecha = partes[0];
                int tMax = Integer.parseInt(partes[1]);
                int tMin = Integer.parseInt(partes[2]);

                System.out.printf("%-15s %4d°C  %4d°C%n", fecha, tMax, tMin);

                if (tMax > maxTemp) maxTemp = tMax;
                if (tMin < minTemp) minTemp = tMin;
            }

            System.out.println("------------------------------");
            System.out.println("T.Máxima más alta:  " + maxTemp + "°C");
            System.out.println("T.Mínima más baja:  " + minTemp + "°C");

        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
    }
}