/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import inputs.KeyboardInputs;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import static Constantes.Constantes.PlayerConstants.*;
import static Constantes.Constantes.Directions.*;
import java.awt.Color;
import static main.Juego.GAME_HEIGHT;
import static main.Juego.GAME_WIDTH;

/**
 * Panel principal del juego donde se renderiza el juego y se manejan eventos de teclado.
 * Extiende JPanel y utiliza un objeto KeyboardInputs para manejar eventos de teclado.
 * 
 * @author Carlos Sanchez
 */
public class GamePanel extends JPanel {

    private Juego juego;
    
    /**
     * Constructor de la clase GamePanel.
     * 
     * @param juego Instancia del juego asociada al panel.
     */
    public GamePanel(Juego juego) {
        this.juego = juego;
        setPanelSize(); // Establece el tamaño del panel.
        addKeyListener(new KeyboardInputs(this)); // Agrega un listener de teclado.
        setFocusable(true); // Permite que el panel tenga el foco para recibir eventos de teclado.
    }

    /**
     * Establece el tamaño preferido del panel.
     */
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
    }

    /**
     * Método que se llama automáticamente cuando se quiere renderizar el contenido del panel.
     * 
     * @param g Objeto Graphics para dibujar en el panel.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        juego.render(g); // Renderiza el juego en el contexto gráfico proporcionado.
    }

    /**
     * Obtiene la instancia del juego asociada al panel.
     * 
     * @return La instancia del juego asociada al panel.
     */
    public Juego getJuego() {
        return juego;
    }
}
