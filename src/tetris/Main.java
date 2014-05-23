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
