/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package levels;

/**
 * Clase que representa un nivel del juego con sus datos.
 * Contiene métodos para obtener información sobre el nivel, como el índice de sprite en una posición específica.
 * 
 * @author Carlos Sanchez
 */
public class Level {
    
    private int[][] lvlData;
    
    /**
     * Constructor de la clase Level.
     * 
     * @param lvlData Datos del nivel representados como una matriz de enteros.
     */
    public Level(int[][] lvlData) {
        this.lvlData = lvlData;
    }
    
    /**
     * Obtiene el índice de sprite en una posición específica del nivel.
     * 
     * @param x Coordenada x en el nivel.
     * @param y Coordenada y en el nivel.
     * @return El índice de sprite en la posición (x, y) del nivel.
     */
    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }
    
    /**
     * Obtiene los datos completos del nivel representados como una matriz de enteros.
     * 
     * @return Los datos del nivel como una matriz de enteros.
     */
    public int[][] getLevelData() {
        return lvlData;
    }
}
