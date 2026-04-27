package ejercicio04;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("boletin1/ejercicio04/cadenas.txt"))) {

            System.out.println("Introduce cadenas de texto ('fin' para terminar):");

            while (true) {
                String linea = sc.nextLine();

                if (linea.equalsIgnoreCase("fin")) {
                    break;
                }

                bw.write(linea);
                bw.newLine();
            }

            System.out.println("Cadenas guardadas en cadenas.txt");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}