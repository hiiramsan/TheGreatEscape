/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que proporciona métodos para validar cadenas según ciertos criterios.
 * Incluye validaciones para cadenas vacías, tamaño de usuario y contraseña, y formato de usuario y contraseña.
 * 
 * @author Carlos Sanchez
 */
public class Validadores {
    /**
     * Valida que la cadena no sea vacía.
     * 
     * @param s Cadena a validar.
     * @return true si la cadena es vacía, false si no lo es.
     */
    public boolean cadenaVacia(String s) {
        CharSequence cadena = s;

        String reCadena = "^\\s*$";

        Pattern pattern = Pattern.compile(reCadena);

        Matcher matcher = pattern.matcher(cadena);

        return matcher.matches();
    }
    
    /**
     * Valida el tamaño de un nombre de usuario.
     * 
     * @param s Nombre de usuario a validar.
     * @return true si el tamaño del nombre de usuario es válido, false si no lo es.
     */
    public boolean validaUsuarioTam(String s) {
        CharSequence cadena = s;

        String reCadena = "^\\w{5,20}$";

        Pattern pattern = Pattern.compile(reCadena);

        Matcher matcher = pattern.matcher(cadena);

        return matcher.matches();
    }
    
    /**
     * Valida el tamaño de una contraseña.
     * 
     * @param s Contraseña a validar.
     * @return true si el tamaño de la contraseña es válido, false si no lo es.
     */
    public boolean validaContraTam(String s) {
        CharSequence cadena = s;

        String reCadena = "^\\w{6,24}$";

        Pattern pattern = Pattern.compile(reCadena);

        Matcher matcher = pattern.matcher(cadena);

        return matcher.matches();
    }
    
    /**
     * Valida el formato de un nombre de usuario.
     * 
     * @param s Nombre de usuario a validar.
     * @return true si el formato del nombre de usuario es válido, false si no lo es.
     */
    public boolean validaUsuario(String s){
        CharSequence cadena = s;

        String reCadena = "^[^.,{}´¿?()\\[\\]]{5,20}$";

        Pattern pattern = Pattern.compile(reCadena);

        Matcher matcher = pattern.matcher(cadena);

        return matcher.matches();
    }
    
    /**
     * Valida el formato de una contraseña.
     * 
     * @param s Contraseña a validar.
     * @return true si el formato de la contraseña es válido, false si no lo es.
     */
    public boolean validaContrasena(String s){
        CharSequence cadena = s;

        String reCadena = "^(?=.*[A-Z])(?=.*\\d).{6,24}$";

        Pattern pattern = Pattern.compile(reCadena);

        Matcher matcher = pattern.matcher(cadena);

        return matcher.matches();
    }
}
