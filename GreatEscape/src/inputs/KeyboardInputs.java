/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inputs;

import static Constantes.Constantes.Directions.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel;

/**
 * Clase que maneja los eventos del teclado.
 * Implementa la interfaz KeyListener para responder a las pulsaciones y liberaciones de teclas.
 * 
 * @author Carlos Sanchez
 */
public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;

    /**
     * Constructor de la clase KeyboardInputs.
     * 
     * @param gamePanel Panel del juego al que se asocian los eventos del teclado.
     */
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Método no utilizado en este caso.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Maneja la liberación de teclas.
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.getJuego().getJugador().setUp(false);
                break;
            case KeyEvent.VK_A:
                gamePanel.getJuego().getJugador().setLeft(false);
                break;
            case KeyEvent.VK_S:
                gamePanel.getJuego().getJugador().setDown(false);
                break;
            case KeyEvent.VK_D:
                gamePanel.getJuego().getJugador().setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                gamePanel.getJuego().getJugador().setJump(false);
                break;
            case KeyEvent.VK_K:
                gamePanel.getJuego().getJugador().setAttacking(false);
                break;
            case KeyEvent.VK_L:
                gamePanel.getJuego().getJugador().setCovering(false);
                break;
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        // Maneja la pulsación de teclas.
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.getJuego().getJugador().setUp(true);
                break;
            case KeyEvent.VK_A:
                gamePanel.getJuego().getJugador().setLeft(true);
                break;
            case KeyEvent.VK_S:
                gamePanel.getJuego().getJugador().setDown(true);
                break;
            case KeyEvent.VK_D:
                gamePanel.getJuego().getJugador().setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                gamePanel.getJuego().getJugador().setJump(true);
                break;
            case KeyEvent.VK_K:
                gamePanel.getJuego().getJugador().setAttacking(true);
                break;
            case KeyEvent.VK_L:
                gamePanel.getJuego().getJugador().setCovering(true);
                break;
        }
    }
}
