/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.TetrisController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import model.Grille;
import model.Piece;
import model.Score;
import model.Son;

/**
 *
 * @author logan
 */
public class TetrisView extends javax.swing.JFrame {

    /**
     * Creates new form TetrisView
     */
    public JPanel pan;
    public JPanel container;
    public JPanel containerRight;
    public JPanel containerLeft;
    public JPanel containerTop;
    public JPanel pnlPieceHold;
    public JPanel pnlPieceSuiv1;
    public JPanel pnlPieceSuiv2;
    public JPanel pnlPieceSuiv3;
    public JLabel score;
    public JLabel level;
    public JLabel pause;
    public JMenuBar barMenu;
    public JMenu jeuMenu;
    public JMenu optionMenu;
    public JMenu aboutMenu;
    public JMenuItem newGameItem;
    public JMenuItem pauseItem;
    public JMenuItem showKeysItem;
    public JMenuItem aboutItem;
    public JMenuItem muteItem;
    public JMenu colorItem;
    public JRadioButtonMenuItem blueColorItem;
    public JRadioButtonMenuItem normalColorItem;
    public ButtonGroup colorGroup;
    public Son backgroundSon;
    public boolean iAmBlue;
    public static final Color backgroundColor = Color.LIGHT_GRAY;
    private TetrisController control;

    public TetrisView() {
        initComponents();

        container = new JPanel();
        container.setLayout(new BorderLayout());
        //la barre de la fenêtre
        barMenu = new JMenuBar();
        //les différents menus
        jeuMenu = new JMenu("Jeu");
        optionMenu = new JMenu("Options");
        aboutMenu = new JMenu("?");
        //les sous-menus
        newGameItem = new JMenuItem("New Game", KeyEvent.VK_N);
        pauseItem = new JMenuItem("Pause", KeyEvent.VK_P);
        showKeysItem = new JMenuItem("Keys");
        muteItem = new JMenuItem("Mute", KeyEvent.VK_M);
        colorItem = new JMenu("Color");
        normalColorItem = new JRadioButtonMenuItem("Normal", true);
        blueColorItem = new JRadioButtonMenuItem("I am Blue !");
        colorGroup = new ButtonGroup();
        aboutItem = new JMenuItem("About");
        //on met les JRadioButtonMenuItem dans un même groupe (pour le OU exclusif entre-eux)
        colorGroup.add(normalColorItem);
        colorGroup.add(blueColorItem);
        //on ajoute les sous-menus aux menus
        colorItem.add(normalColorItem);
        colorItem.add(blueColorItem);
        optionMenu.add(muteItem);
        optionMenu.add(colorItem);
        aboutMenu.add(aboutItem);
        jeuMenu.add(newGameItem);
        jeuMenu.add(pauseItem);
        jeuMenu.add(showKeysItem);
        //on ajoute les menus à la barre
        barMenu.add(jeuMenu);
        barMenu.add(optionMenu);
        barMenu.add(aboutMenu);
        //on ajoute la barre à la frame
        this.setJMenuBar(barMenu);
        this.setTitle("PolyTetris");

        pan = new JPanel(new GridLayout(20, 10));
        Border blackLine = BorderFactory.createLineBorder(Color.black, 1);
        for (int i = 0; i < 200; i++) {
            JComponent pTest = new Case();
            pTest.setBorder(blackLine);
            pan.add(pTest);
        }
        pan.setBorder(blackLine);
        pan.setBackground(Color.DARK_GRAY);

        container.add(pan, BorderLayout.CENTER);
        
        ///////////////////////////////////////////////////////////////////////
        // EventListener Toolbar
        pauseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.setPause(!control.isPause());
            }
        });
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(aboutItem, "PolyTetris\nLogan PAUL & Geoffrey MATHIOT\nPolytech Lyon - May 2014", "About...", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        normalColorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(normalColorItem.isEnabled())
                {
                    iAmBlue = false;
                    backgroundSon.stop();
                    backgroundSon = new Son("src\\sounds\\3.wav");
                    backgroundSon.start();
                }
            }
        });
        blueColorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(blueColorItem.isEnabled())
                {
                    iAmBlue = true;
                    backgroundSon.stop();
                    backgroundSon = new Son("src\\sounds\\7.wav");
                    backgroundSon.start();
                }
            }
        });
        muteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(control.isMute())
                {
                    control.setMute(false);
                    backgroundSon = new Son("src\\sounds\\3.wav");
                    backgroundSon.start();
                    muteItem.setText("Mute");
                }
                else
                {
                    control.setMute(true);
                    backgroundSon.stop();
                    muteItem.setText("Unmute");
                }
            }
        });
        showKeysItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(showKeysItem, "UP Arrow to rotate\nSPACE to hold\nN for new game\nP to pause\nM to mute", "Keys", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        newGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.setNewGame(true);
            }
        });
        
        ///////////////////////////////////////////////////////////////////////
        // Bandeau Haut
        containerTop = new JPanel();
        containerTop.setLayout(new BorderLayout());
        containerTop.setPreferredSize(new Dimension(50, 15));
        containerTop.setBackground(Color.DARK_GRAY);
        
        // Texte Pause/EC
        pause = new JLabel();
        pause.setForeground(Color.BLACK);
        containerTop.add(pause);
        
        container.add(containerTop, BorderLayout.NORTH);

        ///////////////////////////////////////////////////////////////////////
        // Bandeau Gauche
        containerLeft = new JPanel();
        containerLeft.setLayout(new FlowLayout());
        containerLeft.setPreferredSize(new Dimension(50, 50));
        containerLeft.setBackground(Color.DARK_GRAY);

        // Texte Level
        level = new JLabel("<html>Level : <br>1</html>");
        level.setForeground(Color.WHITE);
        containerLeft.add(level);

        // Grille HOLD
        pnlPieceHold = new JPanel(new GridLayout(4, 4));
        for (int i = 0; i < 16; i++) {
            JComponent pTest = new Case();
            pTest.setBorder(blackLine);
            pnlPieceHold.add(pTest);
        }
        pnlPieceHold.setBorder(blackLine);
        pnlPieceHold.setBackground(backgroundColor);
        pnlPieceHold.setPreferredSize(new Dimension(50, 50));
        containerLeft.add(pnlPieceHold);
        
        // Texte HOLD
        JLabel lblHold = new JLabel("Hold");
        lblHold.setForeground(Color.WHITE);
        containerLeft.add(lblHold);
        
        container.add(containerLeft, BorderLayout.WEST);

        ///////////////////////////////////////////////////////////////////////
        // Bandeau Droit
        containerRight = new JPanel();
        containerRight.setLayout(new FlowLayout());
        containerRight.setPreferredSize(new Dimension(50, 50));
        containerRight.setBackground(Color.DARK_GRAY);

        // Texte
        score = new JLabel("<html>Score : <br>0</html>");
        score.setForeground(Color.WHITE);
        containerRight.add(score);

        // Grille SUIV 1
        pnlPieceSuiv1 = new JPanel(new GridLayout(4, 4));
        for (int i = 0; i < 16; i++) {
            JComponent pTest = new Case();
            pTest.setBorder(blackLine);
            pnlPieceSuiv1.add(pTest);
        }
        pnlPieceSuiv1.setBorder(blackLine);
        pnlPieceSuiv1.setBackground(backgroundColor);
        pnlPieceSuiv1.setPreferredSize(new Dimension(50, 50));
        containerRight.add(pnlPieceSuiv1);

        // GRILLE SUIV 2
        pnlPieceSuiv2 = new JPanel(new GridLayout(4, 4));
        for (int i = 0; i < 16; i++) {
            JComponent pTest = new Case();
            pTest.setBorder(blackLine);
            pnlPieceSuiv2.add(pTest);
        }
        pnlPieceSuiv2.setBorder(blackLine);
        pnlPieceSuiv2.setBackground(backgroundColor);
        pnlPieceSuiv2.setPreferredSize(new Dimension(50, 50));
        containerRight.add(pnlPieceSuiv2);
        
        // GRILLE SUIV 3
        pnlPieceSuiv3 = new JPanel(new GridLayout(4, 4));
        for (int i = 0; i < 16; i++) {
            JComponent pTest = new Case();
            pTest.setBorder(blackLine);
            pnlPieceSuiv3.add(pTest);
        }
        pnlPieceSuiv3.setBorder(blackLine);
        pnlPieceSuiv3.setBackground(backgroundColor);
        pnlPieceSuiv3.setPreferredSize(new Dimension(50, 50));
        containerRight.add(pnlPieceSuiv3);
        
        container.add(containerRight, BorderLayout.EAST);
        this.setContentPane(container);
        
        iAmBlue = false;
        ///////////////////////////////////////////////////////////////////////
        //lecture du son de fond
        try {
            this.backgroundSon = new Son("src\\sounds\\3.wav");
            this.backgroundSon.start();
        } catch (IllegalArgumentException e) {
            System.out.println("Pas de sortie audio");
        }
    }

    public void gameOver(Grille grille) {
        //lecture du son de défaite
        try {
            Son son = new Son("src\\sounds\\1.wav");
            son.play();
            this.backgroundSon.stop();
        } catch (IllegalArgumentException e) {
            System.out.println("Pas de sortie audio");
        }
        display(grille);
        javax.swing.JOptionPane.showMessageDialog(this, "Game Over !\nLevel : " + grille.score.level + "\nScore : " + grille.score.score, "Game Over !", JOptionPane.ERROR_MESSAGE);
    }

    public void setKeyListener(TetrisController control) {
        this.control = control;
        this.addKeyListener(this.control);
    }

    public void display(Grille grille) {
       if(control!=null && control.isNewGame())
       {
            try {
                backgroundSon.stop();
                backgroundSon = new Son("src\\sounds\\3.wav");
                backgroundSon.start();
            } catch (IllegalArgumentException e) {
                System.out.println("Pas de sortie audio");
            }
            control.setNewGame(false);
       }
        int n = 0;
        for (int i = 0; i < grille.y; i++) {
            for (int j = 0; j < grille.x; j++) {
                ((Case) pan.getComponent(n)).setColor(grille.tab[j][i],iAmBlue);
                n++;
            }
        }

        // Affichage de la liste des pièces (HOLD + Suivantes)
        displayListePiece(pnlPieceHold, grille.pieceHold);
        displayListePiece(pnlPieceSuiv1, grille.tabPieceSuivante[0]);
        displayListePiece(pnlPieceSuiv2, grille.tabPieceSuivante[1]);
        displayListePiece(pnlPieceSuiv3, grille.tabPieceSuivante[2]);

        if(control != null && control.isPause()) {
            pause.setText("En pause");
            pause.setForeground(Color.ORANGE);
        } else if(control!= null && control.grille.termine) {
            pause.setText("Game over");
            pause.setForeground(Color.RED);
        } else {
            pause.setText("En cours");
            pause.setForeground(Color.GREEN);
        }
        if(iAmBlue) {
            pause.setForeground(Color.BLUE);
        }
        
        this.repaint();

        try {
            if (Grille.lectureSon > 0 && !control.isMute()) {
                Son son = new Son("src\\sounds\\" + Grille.lectureSon + ".wav");
                son.play();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Pas de sortie audio");
        }
        Grille.lectureSon = 0;
    }

    public void displayScoreLevel(Score score) {
        this.score.setText("<html>Score : <br>" + score.score + "</html>");
        this.level.setText("<html>Level : <br>" + score.level + "</html>");
    }
    
    /**
     * Permet de dessiner les listes de pièces sur les côtés de l'interface
     * @param pnlDst Panel de destination
     * @param pieceSrc Pièce a dessiner
     */
    public void displayListePiece(JPanel pnlDst, Piece pieceSrc) {
        
        // On parcours notre forme pour afficher les bonnes couleurs
        int n = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                
                if (pieceSrc == null || j >= pieceSrc.hauteur || i >= pieceSrc.largeur || !pieceSrc.tab[0][j][i])
                    ((Case) pnlDst.getComponent(n)).setColor(backgroundColor);
                else
                    ((Case) pnlDst.getComponent(n)).setColor(Case.getColor(pieceSrc.type,iAmBlue));

                n++;
            }
        }        
    }
    

    /**
     * This method is called from within the controlstructor to initialize the
     * form. WARNING: Do NOT modify this code. The controltent of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(300, 400));
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TetrisView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TetrisView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TetrisView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TetrisView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TetrisView().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
