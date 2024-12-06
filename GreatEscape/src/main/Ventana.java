/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;

/**
 * La clase Ventana se encarga de gestionar la ventana principal del juego.
 * Configura las propiedades de la ventana, agrega el panel de juego y maneja eventos de enfoque de la ventana.
 * 
 * @author Carlos Sanchez
 */
public class Ventana {

    private JFrame jframe;

    /**
     * Constructor de la clase Ventana.
     * 
     * @param gamePanel El panel de juego que se agrega a la ventana.
     */
    public Ventana(GamePanel gamePanel) {
        jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setResizable(false);
        jframe.pack();
        jframe.setVisible(true);
        jframe.addWindowFocusListener(new WindowFocusListener() {

            @Override
            public void windowLostFocus(WindowEvent e) {
                // Se notifica al juego cuando la ventana pierde el enfoque.
                gamePanel.getJuego().windowFocusLost();
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {
                // No se implementa en este momento, se podría manejar algún evento al recuperar el enfoque.
            }
        });
    }
}
