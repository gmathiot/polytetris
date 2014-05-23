/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author logan
 */
public abstract class Piece {
    public boolean tab[][][];
    public int largeur;
    public int hauteur;
    public int x;
    public int y;
    public Integer type;
    public int decalageMasqueX;
    public int decalageMasqueY;
    
    
    public Piece(){
        x = 0;
        y = 0;
    }
}
