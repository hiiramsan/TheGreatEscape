/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Constantes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import main.Juego;

/**
 * Clase para cargar y guardar los escenarios que se están utilizando.
 * Proporciona métodos para obtener atlas de sprites y datos de niveles.
 * @author Carlos Sanchez
 */
public class LoadSave {
    
    // Rutas a los archivos de atlas de sprites y datos de niveles
    public static final String PLAYER_ATLAS = "multimedia/charactersprites.png";
    public static final String LEVEL_ATLAS = "multimedia/outside_sprites2.png";
    public static final String LEVEL_ONE_DATA = "multimedia/lvl1.png";
    public static final String LEVEL_TWO_DATA = "multimedia/lvl5.png";
    public static final String LEVEL_THREE_DATA = "multimedia/lvl4.png";
    public static final String LEVEL_FOUR_DATA = "multimedia/lvl4def.png";
    public static final String LEVEL_FIVE_DATA = "multimedia/lvl5555.png";
    public static final String R = "multimedia/loginFondo.png";
    
    /**
     * Obtiene un atlas de sprites desde el archivo especificado.
     *
     * @param fileName Ruta al archivo del atlas de sprites.
     * @return Una instancia de BufferedImage que representa el atlas de sprites.
     */
    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
    
    /**
     * Obtiene los datos del nivel 1 desde el atlas de sprites.
     *
     * @return Una matriz que representa los datos del nivel 1.
     */
    public static int[][] GetLevelData() {
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
        return extractLevelData(img);
    }
    
    /**
     * Obtiene los datos del nivel 2 desde el atlas de sprites.
     *
     * @return Una matriz que representa los datos del nivel 2.
     */
    public static int[][] GetLevelData2() {
        BufferedImage img = GetSpriteAtlas(LEVEL_TWO_DATA);
        return extractLevelData(img);
    }
    
    /**
     * Obtiene los datos del nivel 3 desde el atlas de sprites.
     *
     * @return Una matriz que representa los datos del nivel 3.
     */
    public static int[][] GetLevelData3() {
        BufferedImage img = GetSpriteAtlas(LEVEL_THREE_DATA);
        return extractLevelData(img);
    }
    
    /**
     * Obtiene los datos del nivel 4 desde el atlas de sprites.
     *
     * @return Una matriz que representa los datos del nivel 4.
     */
    public static int[][] GetLevelData4() {
        BufferedImage img = GetSpriteAtlas(LEVEL_FOUR_DATA);
        return extractLevelData(img);
    }
    
    /**
     * Obtiene los datos del nivel 5 desde el atlas de sprites.
     *
     * @return Una matriz que representa los datos del nivel 5.
     */
    public static int[][] GetLevelData5() {
        BufferedImage img = GetSpriteAtlas(LEVEL_FIVE_DATA);
        return extractLevelData(img);
    }
    
    // Método privado para extraer datos de nivel desde una imagen
    private static int[][] extractLevelData(BufferedImage img) {
        int[][] lvlData = new int[img.getHeight()][img.getWidth()];
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value >= 48) {
                    value = 0;
                }
                lvlData[j][i] = value;
            }
        }
        return lvlData;
    }
}
