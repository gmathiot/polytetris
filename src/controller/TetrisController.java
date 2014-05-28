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
    private boolean mute;
    
    public TetrisController(Grille grille)
    {
        this.grille = grille;
        this.descend = false;
        this.pause = false;
        this.mute = false;
    }

    public void run() {
        while(true)
        {
            if(!this.isPause())
            {
               if(!this.grille.termine)
               {    //si le jeu est en cours
                    try {
                        Thread.sleep(780 - grille.score.level * 80);
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
               {   //si le jeu est fini
                    try {
                        Thread.sleep(780 - grille.score.level * 80);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TetrisController.class.getName()).log(Level.SEVERE, null, ex);
                    }
               }
            }
            else
            {
                try {
                    // si le jeu est en pause
                    this.wait();
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
        if(!this.isPause())
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
            this.setPause(!this.isPause());                
        }
        if(e.getKeyCode() == KeyEvent.VK_R) //touche R
        {
            //vide la grille (cheatcode)
            grille.termine = false;
            grille.reinitialiserTableau();
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE) //touche espace
        {
            try {
                //garde une pièce
                grille.holdPiece();
            } catch (Exception ex) {
                Logger.getLogger(TetrisController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getKeyCode() == KeyEvent.VK_M) //touche M
        {
            this.setMute(!this.isMute());
            //TODO
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * @return the pause
     */
    public boolean isPause() {
        return pause;
    }

    /**
     * @param pause the pause to set
     */
    public void setPause(boolean pause) {
        this.pause = pause;
    }

    /**
     * @return the mute
     */
    public boolean isMute() {
        return mute;
    }

    /**
     * @param mute the mute to set
     */
    public void setMute(boolean mute) {
        this.mute = mute;
    }
}
