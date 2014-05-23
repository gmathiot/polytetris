/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import controller.TetrisController;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import model.Grille;
import model.Score;
import model.Son;
import view.TetrisView;

/**
 *
 * @author logan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        TetrisView view = new TetrisView();
        view.setVisible(true);
        
        //Son son = new Son("..\\tetris2.wav");
        //son.start();
        
        Score score = new Score();
        
        Grille grille = new Grille(10,20,view, score);
        grille.addPiece();
        
        TetrisController con = new TetrisController(grille);
        con.start();
        view.setKeyListener(con);
    }
}

/*TODO

_ ergonomie (couleurs, gestion de l'espace, titre) -Geof
_ ajouter un espace pour la pièce retenue -Geof
_ JMenuBar (New Game, Pause, About) -Geof
_ implémenter le code pour ajouter une pièce à retenir -Log
_ définir un système de niveaux -Log
_ gestion des sons -Log
_ messages fin de partie (victoire / défaite / lvl up) -Geof
_ gestion des high scores (optionnel) -Log
_ ajouter au JMenuBar l'affichage des high scores (optionnel) -Geof

*/