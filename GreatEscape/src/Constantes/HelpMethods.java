/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Constantes;

import java.awt.geom.Rectangle2D;
import main.Juego;

/**
 * Métodos de ayuda para la detección de colisiones entre el personaje y el escenario.
 * Contiene funciones para verificar la posibilidad de moverse, detectar si un punto es sólido,
 * obtener la posición del personaje con respecto a las paredes y techos/suelos, y verificar
 * si hay una entidad en el suelo.
 * 
 * @author Carlos Sanchez
 */
public class HelpMethods {

    /**
     * Verifica si el personaje puede moverse a la posición especificada sin colisionar con objetos sólidos.
     *
     * @param x      Posición x del personaje.
     * @param y      Posición y del personaje.
     * @param width  Ancho del personaje.
     * @param height Altura del personaje.
     * @param lvlData Datos del nivel que representan objetos sólidos.
     * @return true si el personaje puede moverse, false si hay colisión.
     */
    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        if (!IsSolid(x, y, lvlData) && !IsSolid(x + width, y + height, lvlData)
                && !IsSolid(x + width, y, lvlData) && !IsSolid(x, y + height, lvlData)) {
            return true;
        }
        return false;
    }

    /**
     * Verifica si un punto específico es sólido en el nivel.
     *
     * @param x      Posición x del punto.
     * @param y      Posición y del punto.
     * @param lvlData Datos del nivel que representan objetos sólidos.
     * @return true si el punto es sólido, false si no lo es.
     */
    private static boolean IsSolid(float x, float y, int[][] lvlData) {
        int maxWidth = lvlData[0].length * Juego.TILES_SIZE;

        if (x < 0 || x >= maxWidth || y < 0 || y >= Juego.GAME_HEIGHT) {
            return true;
        }

        float xIndex = x / Juego.TILES_SIZE;
        float yIndex = y / Juego.TILES_SIZE;
        int value = lvlData[(int) yIndex][(int) xIndex];
        if (value >= 48 || value < 0 || value != 11) {
            return true;
        }
        return false;
    }

    /**
     * Obtiene la posición x del personaje con respecto a las paredes.
     *
     * @param hitbox  Hitbox (rectángulo de colisión) del personaje.
     * @param xSpeed  Velocidad en el eje x del personaje.
     * @return La posición x ajustada del personaje con respecto a las paredes.
     */
    public static float GetPersonajeXPosWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int) (hitbox.x / Juego.TILES_SIZE);
        if (xSpeed > 0) {
            int tileXPos = currentTile * Juego.TILES_SIZE;
            int xOffset = (int) (Juego.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset - 1;
        } else {
            return currentTile * Juego.TILES_SIZE;
        }
    }

    /**
     * Obtiene la posición y del personaje con respecto a los techos/suelos.
     *
     * @param hitbox  Hitbox (rectángulo de colisión) del personaje.
     * @param airSpeed Velocidad en el eje y del personaje.
     * @return La posición y ajustada del personaje con respecto a los techos/suelos.
     */
    public static float GetPersonajeYPosUnderRoofOrAvobeFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int) (hitbox.y / Juego.TILES_SIZE);
        if (airSpeed > 0) {
            // Caída
            int tileYPos = currentTile * Juego.TILES_SIZE;
            int yOffset = (int) (Juego.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset - 1;
        } else {
            // Salto
            return currentTile * Juego.TILES_SIZE;
        }
    }

    /**
     * Verifica si hay una entidad en el suelo en la posición del hitbox.
     *
     * @param hitbox  Hitbox (rectángulo de colisión) de la entidad.
     * @param lvlData Datos del nivel que representan objetos sólidos.
     * @return true si hay una entidad en el suelo, false si no la hay.
     */
    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
        if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData)
                && !IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData)) {
            return false;
        }
        return true;
    }
}
