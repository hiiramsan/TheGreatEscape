/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Personajes;
import static Constantes.Constantes.PlayerConstants.ATTACK;
import static Constantes.Constantes.PlayerConstants.COVER;
import static Constantes.Constantes.PlayerConstants.GetSpriteAmount;
import static Constantes.Constantes.PlayerConstants.IDLE;
import static Constantes.Constantes.PlayerConstants.RUNNING;
import Constantes.LoadSave;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.io.InputStream;
//import javax.imageio.ImageIO;
import static Constantes.HelpMethods.*;
import main.Juego;
import static main.Juego.SCALE;

/**
 * Esta clase representa el personaje
 * @author Carlos Sanchez
 */
public class Jugador extends Personaje {

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniVelocidad = 30;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false, covering = false;
    private boolean left, up, right, down, jump;
    private float playerSpeed = 2.5f;
    private int[][] lvlData;

    //private float xDrawOffset = 16 * SCALE;
    private float xDrawOffset = 21 * SCALE;
    private float yDrawOffset = 4 * SCALE;

    //salto y gravedad
    private float airSpeed = 0f;
    private float gravity = 0.04f * Juego.SCALE;
    private float jumpSpeed = -2.25f * Juego.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Juego.SCALE;
    private boolean inAir = false;

    
    /**
     * Crea una instancia del jugador con la posición y dimensiones dadas.
     * Inicializa la animación y la caja de colisión.
     * @param x La posición en el eje X.
     * @param y La posición en el eje Y.
     * @param width El ancho del jugador.
     * @param height La altura del jugador.
     */
    public Jugador(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimation();
        //initHitbox(x, y, 30 * Juego.SCALE, 35 * Juego.SCALE);
        initHitbox(x, y, 20 * Juego.SCALE, 27 * Juego.SCALE);
        
    }

    
    /**
     * Actualiza la posición, la animación y las acciones del jugador.
     */
    public void update() {
        updatePos();
        actAnimacion();
        setAnimation(); 
    }

    
    /**
     * Renderiza la imagen del jugador en el contexto gráfico dado.
     * @param g El contexto gráfico en el que se renderiza el jugador.
     */
    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), 128, 80, null);
        //drawHitbox(g);
    }
    
    /**
     * Actualiza la animacion del Jugador
     */
    private void actAnimacion() {
        aniTick++;
        if (aniTick >= aniVelocidad) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
                covering = false;
            }
        }
    }

    /**
     * Establece la animacion del Jugador
     */
    private void setAnimation() {
        int startAni = playerAction;

        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }

        if (attacking) {
            playerAction = ATTACK;
        }
        
        if(covering) {
            playerAction = COVER;
        }

        if (startAni != playerAction) {
            resetAniTick();
        }
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {
        moving = false;
        
        if(jump) {
            jump();
        }
        
        if (!left && !right && !inAir) {
            return;
        }

        float xSpeed = 0;

        if (left) {
            xSpeed -= playerSpeed;

        }
        if (right) {
            xSpeed += playerSpeed;
        }
        
        if(!inAir) {
            if (!IsEntityOnFloor(hitbox, lvlData)) {
                inAir = true;
            }
				
        }

        if (inAir) {
            if(CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
                hitbox.y += airSpeed;
                airSpeed += gravity;
                //System.out.println("x:" + hitbox.x + ", y:" + hitbox.y);
                updateXPos(xSpeed);
            } else {
                hitbox.y = GetPersonajeYPosUnderRoofOrAvobeFloor(hitbox, airSpeed);
                if(airSpeed > 0) {
                    resetInAir();
                } else {
                    airSpeed = fallSpeedAfterCollision;
                }
                updateXPos(xSpeed);
            }
        } else {
            updateXPos(xSpeed);
        }
        moving = true;
    }
    
    private void jump(){
        if(inAir) {
            return;
        }
        inAir = true;
        airSpeed = jumpSpeed;
        
    }
    
    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
            hitbox.x += xSpeed;
        } else {
            hitbox.x = GetPersonajeXPosWall(hitbox, xSpeed);
        }
    }

    private void loadAnimation() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

        animations = new BufferedImage[9][8];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
        }

    }
    // idk if this works
    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
        
        if(!IsEntityOnFloor(hitbox, lvlData)) {
            inAir = true;
        }
    }

    
    
    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }
    
    public void setCovering(boolean covering) {
        this.covering = covering;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    
    public void setJump(boolean jump) {
        this.jump = jump;
    }

}
