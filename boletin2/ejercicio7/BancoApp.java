package ejercicio7;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BancoApp {
    static final String ARCHIVO_CLIENTES = "boletin2/ejercicio7/clientes.txt";
    static List<Cliente> clientes = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        cargarClientes(); 
        int opcion;

        do {
            System.out.println("\n--- MENÚ BANCO ---");
            System.out.println("1. Alta cliente");
            System.out.println("2. Baja cliente");
            System.out.println("3. Listar clientes");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1: altaCliente(); break;
                case 2: bajaCliente(); break;
                case 3: listarClientes(); break;
                case 4: guardarClientes(); System.out.println("¡Hasta pronto!"); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    // --- MÉTODOS DEL MENÚ ---

    private static void cargarClientes() {
        File file = new File(ARCHIVO_CLIENTES);
        if (!file.exists()) return; 

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_CLIENTES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 4) {
                    String dni = datos[0];
                    String nombre = datos[1];
                    LocalDate fecha = LocalDate.parse(datos[2]); 
                    double saldo = Double.parseDouble(datos[3]);
                    
                    clientes.add(new Cliente(dni, nombre, fecha, saldo));
                }
            }
            Collections.sort(clientes); 
        } catch (Exception e) {
            System.out.println("Error al cargar clientes: " + e.getMessage());
        }
    }

    private static void altaCliente() {
        System.out.print("DNI: ");
        String dni = sc.nextLine();
        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine();
        
        System.out.print("Fecha nacimiento (AAAA-MM-DD): ");
        LocalDate fecha = null;
        try {
            fecha = LocalDate.parse(sc.nextLine());
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha incorrecto.");
            return;
        }

        System.out.print("Saldo inicial: ");
        double saldo = Double.parseDouble(sc.nextLine().replace(",", "."));

        clientes.add(new Cliente(dni, nombre, fecha, saldo));
        Collections.sort(clientes); 
        System.out.println("Cliente dado de alta con éxito.");
    }

    private static void bajaCliente() {
        System.out.print("Introduce el DNI del cliente a dar de baja: ");
        String dni = sc.nextLine();
        boolean borrado = clientes.removeIf(c -> c.getDni().equalsIgnoreCase(dni));
        
        if (borrado) System.out.println("Cliente eliminado.");
        else System.out.println("No se encontró ningún cliente con ese DNI.");
    }

    private static void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        double maxSaldo = Double.MIN_VALUE;
        double minSaldo = Double.MAX_VALUE;
        double sumaSaldos = 0;

        System.out.println("\n--- LISTA DE CLIENTES ---");
        for (Cliente c : clientes) {
            System.out.println("DNI: " + c.getDni() + " | Nombre: " + c.getNombre() + 
                               " | Edad: " + c.getEdad() + " | Saldo: " + c.getSaldo() + "€");
            
            if (c.getSaldo() > maxSaldo) maxSaldo = c.getSaldo();
            if (c.getSaldo() < minSaldo) minSaldo = c.getSaldo();
            sumaSaldos += c.getSaldo();
        }

        System.out.println("-------------------------");
        System.out.println("Saldo Máximo: " + maxSaldo + "€");
        System.out.println("Saldo Mínimo: " + minSaldo + "€");
        System.out.println("Promedio: " + (sumaSaldos / clientes.size()) + "€");
    }

    private static void guardarClientes() {
        // Al instanciar FileWriter SIN el 'true', sobrescribimos el archivo con los datos actualizados
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_CLIENTES))) {
            for (Cliente c : clientes) {
                bw.write(c.toFicheroFormato());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar en el archivo: " + e.getMessage());
        }
    }
}