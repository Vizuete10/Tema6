package ejercicio06;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Integer> numeros = new ArrayList<>();

        // 1. LECTURA: Leer números de un archivo y almacenarlos en la lista
        try (BufferedReader br = new BufferedReader(new FileReader("boletin1/ejercicio06/numeros.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim(); // Limpiamos espacios
                
                // Comprobamos que la línea no esté vacía antes de convertir
                if (!linea.isEmpty()) { 
                    numeros.add(Integer.parseInt(linea));
                }
            }
            System.out.println("Lectura completada. Se han cargado " + numeros.size() + " números.");

        } catch (FileNotFoundException e) {
            System.out.println("Error: No se ha encontrado el archivo original. Comprueba la ruta.");
            return; // Terminamos la ejecución si no hay archivo que leer
        } catch (IOException e) {
            System.out.println("Error de entrada/salida al leer: " + e.getMessage());
            return;
        }

        // 2. ORDENACIÓN: Ordenar la lista de forma ascendente
        Collections.sort(numeros);

        // 3. ESCRITURA: Guardar los números ordenados en un fichero distinto
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("boletin1/ejercicio06/numerosOrdenados.txt"))) {
            for (int n : numeros) {
                bw.write(String.valueOf(n));
                bw.newLine();
            }
            System.out.println("Proceso finalizado. Fichero ordenado creado correctamente.");

        } catch (IOException e) {
            System.out.println("Error al escribir el nuevo archivo: " + e.getMessage());
        }
    }
}