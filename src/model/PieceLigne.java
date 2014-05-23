/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author logan
 */
public class PieceLigne extends Piece {
    
    public PieceLigne()
    {
        super();
        super.largeur = 4;
        super.hauteur = 4;
        
        super.decalageMasqueX = -1;
        super.decalageMasqueY = -1;
        
        super.tab = new boolean[][][]{ 
                                     {{ false, false, false, false },
                                     { true, true, true, true},
                                     { false, false, false, false },
                                     { false, false, false, false}},
            
                                     {{ false, true, false, false },
                                     { false, true, false, false},
                                     { false, true, false, false },
                                     { false, true, false, false}},
        
                                     {{ false, false, false, false },
                                     { true, true, true, true},
                                     { false, false, false, false },
                                     { false, false, false, false}},
        
                                     {{ false, true, false, false },
                                     { false, true, false, false},
                                     { false, true, false, false },
                                     { false, true, false, false}}};
        
        super.type = 3;
    }
}
