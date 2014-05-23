/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author logan
 */
public class PieceCarre extends Piece {
  
    public PieceCarre(){
        super();
        super.largeur = 2;
        super.hauteur = 2;
        
        super.decalageMasqueX = 0;
        super.decalageMasqueY = 0;
        
        super.tab = new boolean[4][2][2];
        for(int i = 0; i < 4 ;  i++){
            for(int j = 0; j < 2; j++){
                for(int k = 0; k < 2; k++){
                    super.tab[i][j][k] = true;
                }
            }
        }
        super.type = 7;
    }
}
