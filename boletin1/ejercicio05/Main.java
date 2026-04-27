package ejercicio05;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce tu nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Introduce tu edad: ");
        int edad = Integer.parseInt(sc.nextLine());

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("boletin1/ejercicio05/datos.txt", true))) {

            bw.write(nombre + " " + edad);
            bw.newLine();

            System.out.println("Datos guardados correctamente.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}