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
        TetrisView view = new TetrisView();
        view.setVisible(true);
        
        Score score = new Score();
        
        Grille grille = new Grille(10,20,view, score);
        grille.addPiece();
        
        TetrisController control = new TetrisController(grille);
        control.start();
        view.setKeyListener(control);
    }
}

/*TODO

_ ergonomie (couleurs, gestion de l'espace, titre) -Geof //Optionnel
_ ajouter un espace pour la pièce retenue -Geof
_ JMenuBar (New Game, Pause, About) -Geof
_ implémenter le code pour ajouter une pièce à retenir -Log //DONE
_ définir un système de niveaux -Log  //DONE
_ gestion des sons -Log //DONE
_ messages fin de partie (victoire / défaite / lvl up) -Log //DONE
_ gestion des high scores -Log //Optionnel
_ ajouter au JMenuBar l'affichage des high scores -Geof //Optionnel

*/