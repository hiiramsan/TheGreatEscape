/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Personajes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

/**
 * Clase abstracta que representa un personaje en el juego.
 * Proporciona métodos y propiedades básicas comunes a todos los personajes.
 * 
 * @author Carlos Sancez
 */
public abstract class Personaje {
    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;
    
    /**
     * Constructor de la clase Personaje.
     * 
     * @param x      Posición x inicial del personaje.
     * @param y      Posición y inicial del personaje.
     * @param width  Ancho del personaje.
     * @param height Altura del personaje.
     */
    public Personaje(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    /**
     * Dibuja el hitbox del personaje en el contexto gráfico proporcionado.
     * 
     * @param g Contexto gráfico en el que se dibuja el hitbox.
     */
    protected void drawHitbox(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }
    
    /**
     * Inicializa el hitbox del personaje con las coordenadas y dimensiones especificadas.
     * 
     * @param x      Posición x del hitbox.
     * @param y      Posición y del hitbox.
     * @param width  Ancho del hitbox.
     * @param height Altura del hitbox.
     */
    protected void initHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    /**
     * Obtiene el hitbox del personaje.
     * 
     * @return Un objeto Rectangle2D.Float que representa el hitbox del personaje.
     */
    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }
    

}
