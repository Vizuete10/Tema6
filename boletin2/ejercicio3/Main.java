package ejercicio3;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String rutaArchivo = "boletin2/ejercicio3/texto_largo.txt"; 

        try (BufferedReader in = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            int contador = 0;
            
            while ((linea = in.readLine()) != null) {
                System.out.println(linea);
                contador++;
                
                // Pausar cada 24 líne
                if (contador == 24) {
                    System.out.println("--- Pulsa ENTER para continuar ---");
                    sc.nextLine();
                    contador = 0;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}