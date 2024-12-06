/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package levels;

import Constantes.LoadSave;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Juego;
import static main.Juego.TILES_SIZE;

/**
 * Gestor de niveles que maneja la carga, dibujo y actualización de los niveles del juego.
 * Se encarga de importar los sprites del nivel y gestionar la representación del nivel actual.
 * 
 * @author Carlos Sanchez
 */
public class LevelManager {

    private Juego juego;
    private BufferedImage[] levelSprite;
    private Level level;

    /**
     * Constructor de la clase LevelManager.
     * 
     * @param juego Instancia del juego al que está asociado el gestor de niveles.
     */
    public LevelManager(Juego juego) {
        this.juego = juego;
        importOutsideSprites(); // Importa los sprites del nivel desde el atlas.
        level = new Level(LoadSave.GetLevelData()); // Carga el nivel actual.
    }
    
    /**
     * Importa los sprites del nivel desde el atlas.
     */
    public void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];
        
        // Divide el atlas en sprites individuales y los almacena en el array levelSprite.
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }
    
    /**
     * Dibuja el nivel en el contexto gráfico proporcionado.
     * 
     * @param g Contexto gráfico en el que se dibuja el nivel.
     */
    public void draw(Graphics g) {
        for (int j = 0; j < Juego.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < Juego.TILES_IN_WIDTH; i++) {
                int index = level.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], TILES_SIZE * i, TILES_SIZE * j, TILES_SIZE, TILES_SIZE, null);
            }
        }
    }
    
    /**
     * Actualiza el estado del gestor de niveles.
     * Puede usarse para implementar lógica de juego relacionada con los niveles.
     */
    public void update() {
        // Implementa la lógica de actualización si es necesario.
    }
    
    /**
     * Obtiene el nivel actual.
     * 
     * @return El objeto Level que representa el nivel actual.
     */
    public Level getCurrentLevel() {
        return level;
    }
    
    /**
     * Actualiza los datos del nivel con nuevos datos.
     * 
     * @param nuevoLvlData Nuevos datos del nivel representados como una matriz de enteros.
     */
    public void actualizarLvlData(int[][] nuevoLvlData) {
        this.level = new Level(nuevoLvlData);
    }
}
