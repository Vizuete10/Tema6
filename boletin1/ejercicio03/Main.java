package ejercicio03;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("boletin1/ejercicio03/Alumnos"))) {

            int totalEdad = 0;
            double totalEstatura = 0;
            int cantidad = 0;
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.trim().split("\\s+");

                String nombre = partes[0];
                int edad = Integer.parseInt(partes[1]);
                double estatura = Double.parseDouble(partes[2]);

                System.out.println("Nombre: " + nombre);

                totalEdad += edad;
                totalEstatura += estatura;
                cantidad++;
            }

            System.out.println("Media edad:    " + (double) totalEdad / cantidad);
            System.out.println("Media estatura: " + totalEstatura / cantidad);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}