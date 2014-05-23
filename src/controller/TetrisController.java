/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Grille;

/**
 *
 * @author logan
 */
public class TetrisController implements Runnable, KeyListener{
    
    private Thread t;
    
    private boolean descend;
    
    private Grille grille;
    
    private boolean pause;
    
    public TetrisController(Grille grille)
    {
        this.grille = grille;
        this.descend = false;
        this.pause = false;
    }

    public void run() {
        while(true)
        {
            if(this.pause == false)
            {
               if(!this.grille.termine)
               {
                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TetrisController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   try {
                       if(!this.descend)
                       {
                           grille.descendrePiece(); 
                       }
                   } catch (Exception ex) {
                       Logger.getLogger(TetrisController.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
               else
               {
                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TetrisController.class.getName()).log(Level.SEVERE, null, ex);
                    }
               }
            }
            else
            {
                try {
                    Thread.sleep(700);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TetrisController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void start()
    {
        t = new Thread(this, "thread");
        t.start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(this.pause == false)
        {
            switch(e.getKeyCode())
            {
                case KeyEvent.VK_LEFT : //touche fleche de gauche
                    this.grille.decaleGauchePiece();
                    break;

                case KeyEvent.VK_RIGHT : //touche fleche de droite
                    this.grille.decaleDroitePiece();
                    break;

                case KeyEvent.VK_UP : //touche fleche du haut
                    this.grille.rotationPiece();
                    break;

                case KeyEvent.VK_DOWN : //touche fleche du bas
                    try {
                        this.descend = true;
                        this.grille.descendrePiece();
                        this.descend = false;
                    } catch (Exception ex) {

                    }
                    break;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_P) //touche P
        {
            //si le jeu est en pause, il ne l'est plus, sinon il passe en pause
            this.pause = !this.pause;
        }
        if(e.getKeyCode() == KeyEvent.VK_R) //touche R
        {
            grille.termine = false;
            grille.reinitialiserTableau();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
