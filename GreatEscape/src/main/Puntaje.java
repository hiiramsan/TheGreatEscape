package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * La clase Puntaje maneja el puntaje máximo del juego, permitiendo cargar y guardar
 * el puntaje máximo desde y hacia un archivo de texto.
 */
public class Puntaje {
    private int puntajeMaximo;

    /**
     * Constructor de la clase Puntaje. Inicializa el puntaje máximo cargándolo desde el archivo.
     */
    public Puntaje() {
        cargarPuntajeMaximo();
    }

    /**
     * Obtiene el puntaje máximo actual.
     *
     * @return El puntaje máximo actual.
     */
    public int getPuntajeMaximo() {
        return puntajeMaximo;
    }

    /**
     * Actualiza el puntaje máximo si el nuevo puntaje supera al actual, y guarda el nuevo puntaje máximo en el archivo.
     *
     * @param nuevoPuntaje El nuevo puntaje a comparar y posiblemente establecer como máximo.
     */
    public void actualizarPuntajeMaximo(int nuevoPuntaje) {
        if (nuevoPuntaje > puntajeMaximo) {
            puntajeMaximo = nuevoPuntaje;
            guardarPuntajeMaximo();
            //System.out.println("¡Nuevo puntaje máximo alcanzado!");
        } else {
            //System.out.println("¡Tu puntaje no supera el máximo actual!");
        }
    }

    /**
     * Carga el puntaje máximo desde un archivo de texto.
     * En caso de error al leer el archivo o al convertir el contenido a entero, se maneja la excepción.
     */
    private void cargarPuntajeMaximo() {
        try (BufferedReader br = new BufferedReader(new FileReader("puntaje.txt"))) {
            String linea = br.readLine();
            if (linea != null && !linea.isEmpty()) {
                puntajeMaximo = Integer.parseInt(linea);
            }
        } catch (IOException | NumberFormatException e) {
            //System.err.println("Error al cargar el puntaje máximo: " + e.getMessage());
        }
    }

    /**
     * Guarda el puntaje máximo actual en un archivo de texto.
     * En caso de error al escribir en el archivo, se maneja la excepción.
     */
    private void guardarPuntajeMaximo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("puntaje.txt"))) {
            bw.write(Integer.toString(puntajeMaximo));
        } catch (IOException e) {
            //System.err.println("Error al guardar el puntaje máximo: " + e.getMessage());
        }
    }
}
