package ejercicio01;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(new FileReader("boletin1/ejercicio01/NumeroReales"))) {
        	sc.useLocale(java.util.Locale.US);

            double suma = 0;
            int cantidad = 0;

            while (sc.hasNextDouble()) {
                suma += sc.nextDouble();
                cantidad++;
            }

            System.out.println("Números leídos: " + cantidad);
            System.out.println("Suma: " + suma);
            System.out.println("Media: " +  suma / cantidad);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}