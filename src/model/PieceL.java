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
public class PieceL extends Piece {
    
    public PieceL()
    {
        super();
        super.largeur = 3;
        super.hauteur = 3;
        
        super.decalageMasqueX = -1;
        super.decalageMasqueY = -1;
        
        super.tab = new boolean[][][]{ 
                                     {{ false, false, true },
                                     { true, true, true },
                                     { false, false, false }},
            
                                     {{ true, true, false },
                                     { false, true, false },
                                     { false, true, false }},
        
                                     {{ false, false, false },
                                     { true, true, true },
                                     { true, false, false }},
                                     
                                     {{ false, true, false },
                                     { false, true, false },
                                     { false, true, true }}};
        
        super.color = Color.LIGHT_GRAY;
    }
}
