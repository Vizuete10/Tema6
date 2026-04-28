package ejercicio1;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        int caracteres = 0;
        int lineas = 0;
        int palabras = 0;

        // Apertura del fichero con try-with-resources para auto-cierre 
        try (BufferedReader in = new BufferedReader(new FileReader("boletin2/ejercicio1/Carta.txt"))) {
            String linea;
            // readLine() lee hasta el final de la línea y devuelve null al terminar el fichero 
            while ((linea = in.readLine()) != null) {
                lineas++;
                caracteres += linea.length();
                
                // Si la línea no está vacía, contamos las palabras separadas por un espacio 
                if (!linea.trim().isEmpty()) {
                    String[] arrayPalabras = linea.trim().split(" ");
                    palabras += arrayPalabras.length;
                }
            }
            System.out.println("Líneas: " + lineas);
            System.out.println("Palabras: " + palabras);
            System.out.println("Caracteres: " + caracteres);
            
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}