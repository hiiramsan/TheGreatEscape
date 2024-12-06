package Personajes;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import main.Juego;

/**
 * Clase que representa a un enemigo en el juego.
 * Extiende la clase abstracta Personaje.
 * 
 * @author Carlos Sanchez
 */
public class Enemigo extends Personaje {
    Juego juego;
    private float velocidadX;
    private Jugador jugador;

    /**
     * Constructor de la clase Enemigo.
     * 
     * @param x         Posición x inicial del enemigo.
     * @param y         Posición y inicial del enemigo.
     * @param width     Ancho del enemigo.
     * @param height    Altura del enemigo.
     * @param velocidadX Velocidad horizontal del enemigo.
     * @param juego     Instancia del juego.
     */
    public Enemigo(float x, float y, int width, int height, float velocidadX, Juego juego) {
        super(x, y, width, height);
        this.velocidadX = velocidadX;
        initHitbox(x, y, 20 * Juego.SCALE, 10 * Juego.SCALE); // Inicializar el hitbox
        this.juego = juego;
    }

    // Métodos getters y setters

    public float getVelocidadX() {
        return velocidadX;
    }

    public void setVelocidadX(float velocidadX) {
        this.velocidadX = velocidadX;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle2D.Float hitbox) {
        this.hitbox = hitbox;
    }

    /**
     * Actualiza la posición del enemigo.
     */
    public void update() {
        x -= velocidadX;
        updatePos();
    }

    /**
     * Renderiza la imagen del enemigo en el contexto gráfico proporcionado.
     * 
     * @param g      Contexto gráfico en el que se renderiza la imagen.
     * @param image  Imagen del enemigo.
     */
    public void render(Graphics g, BufferedImage image) {
        g.drawImage(image, (int) x, (int) y, width, height, null);
    }

    /**
     * Renderiza la imagen del enemigo en el contexto gráfico proporcionado.
     * (Método sobreescrito de la clase Personaje)
     * 
     * @param g Contexto gráfico en el que se renderiza la imagen.
     */
    
    public void render(Graphics g) {
        // Método opcional para renderizar el hitbox
        //drawHitbox(g);
    }

    /**
     * Establece el jugador al que el enemigo persigue.
     * 
     * @param jugador Jugador al que el enemigo persigue.
     */
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    /**
     * Actualiza la posición del enemigo.
     */
    private void updatePos() {
        float xSpeed = 0;
        xSpeed -= velocidadX;
        hitbox.x += xSpeed;
    }

    /**
     * Verifica si hay colisión entre el enemigo y el jugador.
     * 
     * @return true si hay colisión, false si no la hay.
     */
    public boolean colision() {
        Area areaA = new Area(juego.getJugador().getHitbox());
        Area areaB = new Area(getHitbox());
        areaA.intersect(areaB);

        return !areaA.isEmpty();
    }

    /**
     * Aumenta la velocidad del enemigo según el nivel proporcionado.
     * 
     * @param nivel Nivel que determina el aumento de velocidad.
     */
    public void aumentarVelocidad(int nivel) {
        if (nivel < 10) {
            this.velocidadX += 0.1 * nivel;
        }
    }

    /**
     * Restablece la velocidad del enemigo a un valor predeterminado.
     */
    public void resetVelocidad() {
        this.velocidadX = 2.0f;
    }
}
