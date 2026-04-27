package ejercicio02;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(new FileReader("boletin1/ejercicio02/Enteros"))) {

            int suma = 0;
            int cantidad = 0;

            while (sc.hasNextInt()) {
                suma += sc.nextInt();
                cantidad++;
            }

            System.out.println("Números leídos: " + cantidad);
            System.out.println("Suma: " + suma);
            System.out.println("Media: " + (double) suma / cantidad);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}