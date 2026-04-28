package ejercicio2;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        System.out.print("Introduce un nombre para el libro de firmas: ");
        String nuevoNombre = sc.nextLine();
        boolean existe = false;

        System.out.println("--- Libro de Firmas Actual ---");
        try (BufferedReader in = new BufferedReader(new FileReader("boletin2/ejercicio2/firmas.txt"))) {
            String linea;
            while ((linea = in.readLine()) != null) {
                System.out.println(linea);
                if (linea.equalsIgnoreCase(nuevoNombre)) {
                    existe = true;
                }
            }
        } catch (IOException e) {
            System.out.println("El libro de firmas está vacío o no existe aún.");
        }

        if (!existe) {
            // FileWriter con el parámetro append a true  envuelto en BufferedWriter 
            try (BufferedWriter out = new BufferedWriter(new FileWriter("boletin2/ejercicio2/firmas.txt", true))) {
                out.write(nuevoNombre); 
                out.newLine();  
                System.out.println("Firma añadida con éxito.");
            } catch (IOException e) {
                System.out.println("Error al escribir: " + e.getMessage());
            }
        } else {
            System.out.println("Error: El nombre ya se encuentra en el libro de firmas.");
        }
    }
}