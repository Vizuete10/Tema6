package ejercicio4;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Nombres de los archivos
        String archivoCodec = "boletin2/ejercicio4/codec.txt";
        String archivoEntrada = "boletin2/ejercicio4/mensaje_original.txt";
        String archivoSalida = "boletin2/ejercicio4/mensaje_cifrado.txt";

        String alfabeto = "";
        String cifrado = "";

        // Extraer el diccionario de codificación
        try (BufferedReader brCodec = new BufferedReader(new FileReader(archivoCodec))) {
            String lineaAlfabeto = brCodec.readLine();
            String lineaCifrado = brCodec.readLine();

            if (lineaAlfabeto != null && lineaCifrado != null) {
                // Limpiamos las cadenas para quedarnos solo con las letras
                alfabeto = lineaAlfabeto.replace("Alfabeto: ", "").trim();
                cifrado = lineaCifrado.replace("Cifrado: ", "").trim();
            } else {
                System.out.println("Error: 'codec.txt' no tiene el formato correcto.");
                return; // Cortamos la ejecución si no hay código
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de códigos: " + e.getMessage());
            return;
        }

        // Leer el mensaje y escribir el cifrado
        // Podemos abrir flujos de lectura y escritura a la vez separándolos por punto y coma
        try (BufferedReader brMensaje = new BufferedReader(new FileReader(archivoEntrada));
             BufferedWriter bwMensaje = new BufferedWriter(new FileWriter(archivoSalida))) {

            int caracterLeido;
            
            // read() devuelve un int, cuando llega al final del archivo devuelve -1
            while ((caracterLeido = brMensaje.read()) != -1) {
                char charOriginal = (char) caracterLeido; // Cast de int a char
                char charBuscado = Character.toLowerCase(charOriginal); 
                
                // Buscamos en qué posición del alfabeto está esa letra
                int posicion = alfabeto.indexOf(charBuscado);

                if (posicion != -1) {
                    // Si la letra existe en nuestro alfabeto, sacamos su equivalente cifrado
                    char charCifrado = cifrado.charAt(posicion);
                    
                    // Respetamos si la original era mayúscula
                    if (Character.isUpperCase(charOriginal)) {
                        bwMensaje.write(Character.toUpperCase(charCifrado)); 
                    } else {
                        bwMensaje.write(charCifrado); 
                    }
                } else {
                    
                    bwMensaje.write(charOriginal);
                }
            }
            
            System.out.println("¡Proceso terminado! Revisa el archivo: " + archivoSalida);

        } catch (IOException e) {
            System.out.println("Error procesando los mensajes: " + e.getMessage());
        }
    }
}