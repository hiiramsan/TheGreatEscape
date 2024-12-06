/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import Constantes.LoadSave;
import Personajes.Enemigo;
import Personajes.Jugador;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import levels.LevelManager;

/**
 * La clase Juego implementa la lógica principal del juego, incluyendo el bucle
 * de juego, la actualización de los elementos del juego y la representación
 * gráfica.
 *
 * @author Carlos Sanchez
 */
public class Juego implements Runnable {

    private Ventana ventana;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Jugador jugador;
    private int vidas = 3;
    private int tiempo = 0;
    private int puntajeActual = tiempo;
    private Puntaje gestorPuntaje;
    private int nivel = 1;
    private long lastScoreUpdateTime = System.currentTimeMillis();
    private final long scoreUpdateInterval = 1000;
    private int scoreIncrement = 1;
    AudioClip musica, golpe;
    URL direccionMusica;
    private LevelManager levelManager;
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 2.0f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    private Image fondo;
    private int fondoActual = 0;
    private int totalFondos = 5;
    public boolean finished = false;
    private Enemigo enemigo, enemigo2, enemigo3, enemigo4;
    BufferedImage imagenEnemigo = cargarImagenEnemigo("/multimedia/bullet4.png");
    private int tiempoInmunidad = 0;
    private final int duracionInmunidad = 2;

    /**
     * Constructor de la clase Juego. Inicializa todos los componentes del
     * juego.
     */
    public Juego() {
        direccionMusica = getClass().getResource("/multimedia/musiquita.wav");
        musica = Applet.newAudioClip(direccionMusica);
        direccionMusica = getClass().getResource("/multimedia/off.wav");
        golpe = Applet.newAudioClip(direccionMusica);
        musica.loop();
        levelManager = new LevelManager(this);
        jugador = new Jugador(200, 500, (int) (64 * SCALE), (int) (40 * SCALE));
        jugador.loadLvlData(levelManager.getCurrentLevel().getLevelData());
        cargarFondo();
        gamePanel = new GamePanel(this);
        ventana = new Ventana(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
        enemigo = new Enemigo(randomXEnemy(), randomYEnemy(), 50, 20, 2.0f, this);
        enemigo2 = new Enemigo(randomXEnemy(), randomYEnemy(), 50, 20, 2.0f, this);
        enemigo.setJugador(jugador);
        enemigo2.setJugador(jugador);
        gestorPuntaje = new Puntaje();
    }

    /**
     * Genera un número aleatorio para la posición X de los enemigos.
     *
     * @return La posición X aleatoria.
     */
    public int randomXEnemy() {
        Random rand = new Random();
        int numeroAleatorio = rand.nextInt(1001) + 1500;
        return numeroAleatorio;
    }

    /**
     * Genera un número aleatorio para la posición Y de los enemigos.
     *
     * @return La posición X aleatoria.
     */
    public int randomYEnemy() {
        Random rand = new Random();
        int numeroAleatorio = rand.nextInt(501) + 100;
        return numeroAleatorio;
    }

    /**
     * Carga una imagen del enemigo desde la ruta proporcionada.
     *
     * @param ruta La ruta de la imagen del enemigo.
     * @return La imagen cargada del enemigo.
     */
    private BufferedImage cargarImagenEnemigo(String ruta) {
        try {
            URL url = getClass().getResource(ruta);
            if (url != null) {
                return ImageIO.read(url);
            } else {
                System.err.println("No se pudo cargar la imagen del enemigo.");
                return null;
            }
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen del enemigo: " + e.getMessage());
            return null;
        }
    }

    /**
     * Carga el fondo del juego desde un archivo de imagen.
     */
    private void cargarFondo() {
        ImageIcon ii = new ImageIcon(getClass().getResource("/multimedia/pesopluma2.png"));
        fondo = ii.getImage();
    }

    /**
     * Inicia el bucle principal del juego.
     */
    public void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Actualiza la lógica del juego, incluyendo la actualización del jugador,
     * enemigos, gestión de puntajes, cambios de nivel y eventos del juego.
     */
    public void update() {

        if (tiempoInmunidad > 0) {
            tiempoInmunidad--;
        }

        if (!finished) {
            jugador.update();
            levelManager.update();
            if (vidas > 0) {
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastScoreUpdateTime >= scoreUpdateInterval) {
                    tiempo += scoreIncrement;
                    lastScoreUpdateTime = currentTime;
                }
            }

            if (vidas == 0) {
                finished = true;
                musica.stop();
                mostrarDialogoDeConfirmacion();

            }

            if (jugador.getHitbox().x + jugador.getHitbox().width >= 1550) {
                jugador.getHitbox().x = 200;
                cambiarFondo();
                nivel++;
                int newXEnemy = randomXEnemy();
                int newYEnemy = randomYEnemy();
                enemigo.setX(newXEnemy);
                enemigo.getHitbox().x = newXEnemy;
                enemigo.setY(newYEnemy);
                enemigo.getHitbox().y = newYEnemy;
                enemigo.aumentarVelocidad(nivel);
                enemigo.aumentarVelocidad(nivel);

                if (nivel > 5) {
                    enemigo2.setX(newXEnemy);
                    enemigo2.getHitbox().x = newXEnemy;
                    enemigo2.setY(newYEnemy);
                    enemigo2.getHitbox().y = newYEnemy;
                    enemigo2.aumentarVelocidad(nivel);
                    enemigo2.aumentarVelocidad(nivel);
                }
            }

            if (enemigo.getHitbox().x < 0 && jugador.getHitbox().x < 1430) {
                int newXEnemy = randomXEnemy();
                int newYEnemy = randomYEnemy();
                enemigo.setX(newXEnemy);
                enemigo.getHitbox().x = newXEnemy;
                enemigo.setY(newYEnemy);
                enemigo.getHitbox().y = newYEnemy;
            }

            if (enemigo2.getHitbox().x < 0 && jugador.getHitbox().x < 1430) {
                int newXEnemy = randomXEnemy();
                int newYEnemy = randomYEnemy();
                if (nivel > 5) {
                    enemigo2.setX(newXEnemy);
                    enemigo2.getHitbox().x = newXEnemy;
                    enemigo2.setY(newYEnemy);
                    enemigo2.getHitbox().y = newYEnemy;
                }
            }

            if (jugador.getHitbox().y > 800) {
                System.out.println("Pierde vida por caida");
                pierdeVida();
                jugador.getHitbox().x = 200;
                jugador.getHitbox().y = 200;
            }

            if (tiempoInmunidad == 0 && enemigo.colision()) {
                System.out.println("Pierde vida por colision con enemigo 1");
                jugador.getHitbox().x = 200;
                jugador.getHitbox().y = 200;
                pierdeVida();
                tiempoInmunidad = duracionInmunidad * UPS_SET;
            } else {
                enemigo.update();
            }

            if (nivel > 7) {
                if (tiempoInmunidad == 0 && enemigo2.colision()) {
                    System.out.println("Pierde vida por colision con enemigo 2");
                    jugador.getHitbox().x = 200;
                    jugador.getHitbox().y = 200;
                    pierdeVida();
                    tiempoInmunidad = duracionInmunidad * UPS_SET;
                } else {
                    enemigo2.update();
                }
            }

        } else {

            reiniciarJuego();
            finished = false;
        }

    }

    /**
     * Actualiza las imagenes del juego, incluyendo la actualización del
     * jugador, enemigos, gestión de puntajes, cambios de nivel y eventos del
     * juego.
     */
    public void render(Graphics g) {
        g.drawImage(fondo, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
        levelManager.draw(g);
        Font perdiste = new Font("Arial", Font.BOLD, 100);
        Font punt = new Font("Arial", Font.BOLD, 50);
        g.setFont(perdiste);
        g.setColor(Color.RED);
        if (vidas < 1) {
            int puntajeActual = tiempo;
            gestorPuntaje.actualizarPuntajeMaximo(puntajeActual);
            g.drawString("Game Over!", 500, 150);
            g.setFont(punt);
            g.setColor(Color.BLACK);
            g.drawString("Tiempo: " + tiempo + "s", 570, 230);
            g.drawString("Tiempo Record: " + gestorPuntaje.getPuntajeMaximo() + "s", 570, 280);
        } else {
            Font score = new Font("Arial", Font.BOLD, 25);
            g.setFont(score);
            g.setColor(Color.white);
            g.drawString("Tiempo: " + tiempo + "s", 1350, 50);
            g.drawString("Vidas: " + vidas, 650, 50);
            g.drawString("Nivel: " + nivel, 40, 50);

            jugador.render(g);
            enemigo.render(g, imagenEnemigo);
            enemigo.render(g);

            if (nivel > 7) {
                enemigo2.render(g, imagenEnemigo);
                enemigo2.render(g);
            }

        }

    }

    /**
     * Este metodo actualiza los frames y repinta el panel de juego. Implementa logica para que el juego corra a 120 FPS
     */
    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                //System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;

            }
        }
    }

    /**
     * Cambia el fondo actual del juego a través de un ciclo predefinido. Si se
     * alcanza el último fondo, vuelve al primero.
     */
    private void cambiarFondo() {
        fondoActual++;
        if (fondoActual >= totalFondos) {
            fondoActual = 0;
        }
        cargarNuevoFondo();
    }

    /**
     * Carga un nuevo fondo y ajusta la posición inicial del jugador según el
     * fondo actual.
     */
    private void cargarNuevoFondo() {
        int[][] nuevoLvlData = null;
        switch (fondoActual) {
            case 0:
                nuevoLvlData = LoadSave.GetLevelData();
                jugador.getHitbox().x = 200;
                jugador.getHitbox().y = 300;
                break;
            case 1:
                nuevoLvlData = LoadSave.GetLevelData2();
                jugador.getHitbox().x = 200;
                jugador.getHitbox().y = 100;
                break;
            case 2:
                nuevoLvlData = LoadSave.GetLevelData3();
                jugador.getHitbox().x = 200;
                jugador.getHitbox().y = 200;
                break;
            case 3:
                nuevoLvlData = LoadSave.GetLevelData4();
                jugador.getHitbox().x = 200;
                jugador.getHitbox().y = 200;
                break;
            case 4:
                nuevoLvlData = LoadSave.GetLevelData5();
                jugador.getHitbox().x = 200;
                jugador.getHitbox().y = 300;
                break;
        }

        levelManager.actualizarLvlData(nuevoLvlData);
        jugador.loadLvlData(nuevoLvlData);
    }

    /**
     * Maneja el evento cuando la ventana pierde el enfoque. Restaura las
     * direcciones del jugador a su estado inicial.
     */
    public void windowFocusLost() {
        jugador.resetDirBooleans();
    }

    /**
     * Obtiene la instancia del jugador.
     *
     * @return La instancia del jugador.
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * Obtiene el número actual de vidas del jugador.
     *
     * @return El número de vidas del jugador.
     */
    public int getVidas() {
        return vidas;
    }

    /**
     * Establece el número de vidas del jugador.
     *
     * @param vidas El nuevo número de vidas del jugador.
     */
    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    /**
     * Obtiene el tiempo actual de juego.
     *
     * @return El tiempo actual de juego en segundos.
     */
    public int getTiempo() {
        return tiempo;
    }

    /**
     * Establece el tiempo actual de juego.
     *
     * @param tiempo El nuevo tiempo de juego en segundos.
     */
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    /**
     * Obtiene el nivel actual del juego.
     *
     * @return El nivel actual del juego.
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * Establece el nivel actual del juego.
     *
     * @param nivel El nuevo nivel del juego.
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    /**
     * Reduce la cantidad de vidas del jugador en uno y reproduce un sonido de
     * golpe. Si las vidas llegan a cero, activa la lógica de game over.
     */
    private void pierdeVida() {
        if (vidas != 0) {
            vidas--;
            golpe.play();
        }
    }

    /**
     * Reinicia el juego a su estado inicial, incluyendo vidas, tiempo, nivel,
     * fondo y posición del jugador. Adicionalmente, carga un nuevo fondo y
     * restablece la posición de los enemigos. Vuelve a reproducir la música de
     * fondo.
     */
    public void reiniciarJuego() {
        vidas = 3;
        tiempo = 0;
        nivel = 1;
        fondoActual = 0;
        jugador.getHitbox().x = 200;
        jugador.getHitbox().y = 500;
        cargarNuevoFondo();
        enemigo.setX(randomXEnemy());
        enemigo.setY(randomYEnemy());
        enemigo.resetVelocidad();
        enemigo2.setX(randomXEnemy());
        enemigo2.setY(randomYEnemy());
        enemigo2.resetVelocidad();
        musica.loop();
    }

    /**
     * Muestra un cuadro de diálogo de confirmación al jugador cuando el juego
     * termina. Si elige jugar de nuevo, reinicia el juego; de lo contrario,
     * sale del programa.
     */
    private void mostrarDialogoDeConfirmacion() {
        int opcion = JOptionPane.showConfirmDialog(
                null,
                "¿Deseas jugar de nuevo?",
                "Game Over",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            reiniciarJuego();
        } else {
            System.exit(0);
        }
    }
}
