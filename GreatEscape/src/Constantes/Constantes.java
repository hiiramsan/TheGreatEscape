/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Constantes;

/**
 * Constantes que se utilizarán para las animaciones del personaje.
 * Contiene las direcciones y constantes relacionadas con las acciones del jugador.
 * @author Carlos Sanchez
 */
public class Constantes {

    /**
     * Clase interna que define constantes para las direcciones del personaje.
     */
    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    /**
     * Clase interna que define constantes relacionadas con las acciones del jugador.
     */
    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 3;
        public static final int JUMPING = 6;
        public static final int COVER = 4;
        // public static final int DIE = 7;
        public static final int ATTACK = 8;

        /**
         * Obtiene la cantidad de sprites asociados a una acción del jugador.
         *
         * @param playerAction La acción del jugador.
         * @return La cantidad de sprites asociados a la acción del jugador.
         */
        public static int GetSpriteAmount(int playerAction) {
            switch (playerAction) {
                case RUNNING:
                    return 7;
                case IDLE:
                    return 2;
                case JUMPING:
                    return 7;
                case COVER:
                    return 5;
//                case DIE:
//                    return 7;
                case ATTACK:
                    return 7;
                default:
                    return 1;
            }
        }
    }
}
