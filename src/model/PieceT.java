/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;

/**
 *
 * @author logan
 */
public class PieceT extends Piece {
    
    public PieceT(){
        super();
        super.largeur = 3;
        super.hauteur = 3;
        
        super.decalageMasqueX = -1;
        super.decalageMasqueY = -1;
        
        super.tab = new boolean[][][]{ 
                                     {{ false, false, false },
                                     { true, true, true},
                                     { false, true, false }},
            
                                     {{ false, true, false },
                                     { false, true, true},
                                     { false, true, false }},
                                     
                                     {{ false, true, false },
                                     { true, true, true},
                                     { false, false, false }},
            
                                     {{ false, true, false },
                                     { true, true, false},
                                     { false, true, false }}
                                     };
        
        super.color = Color.CYAN;
    }
    
}
