/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.awt.Color;

/**
 *
 * @author logan
 */
public class PieceZ extends Piece {
    
    public PieceZ()
    {
        super();
        super.largeur = 3;
        super.hauteur = 3;
        
        super.decalageMasqueX = -1;
        super.decalageMasqueY = 0;
        
        super.tab = new boolean[][][]{ 
                                     {{ true, false, false },
                                     { true, true, false },
                                     { false, true, false }},
            
                                     {{ false, true, true },
                                     { true, true, false },
                                     { false, false, false }},
        
                                     {{ true, false, false },
                                     { true, true, false },
                                     { false, true, false }},
            
                                     {{ false, true, true },
                                     { true, true, false },
                                     { false, false, false }}};
        
        super.color = Color.CYAN;
    }
}
