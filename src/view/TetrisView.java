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
public class TetrisView extends javax.swing.JFrame implements ActionListener {

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
    public JPanel bigPanelNextPiece;
    public JComponent panelScore;
    public JLabel score;
    public JMenuBar barMenu;
    public JMenu jeuMenu;
    public JMenu optionMenu;
    public JMenu aboutMenu;
    public JMenuItem newGameItem;
    public JMenuItem pauseItem;
    public JMenuItem highScoresItem;
    public JMenuItem aboutItem;
    public JMenuItem muteItem;
    public JMenu colorItem;
    public JRadioButtonMenuItem darkColorItem;
    public JRadioButtonMenuItem normalColorItem;
    public ButtonGroup colorGroup;
    public Son backgroundSon;
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
        //newGameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
        //newGameItem.addActionListener(new ActionListener(){});
        pauseItem = new JMenuItem("Pause", KeyEvent.VK_P);
        highScoresItem = new JMenuItem("High Scores", KeyEvent.VK_H);
        muteItem = new JMenuItem("Mute", KeyEvent.VK_M);
        colorItem = new JMenu("Color");
        normalColorItem = new JRadioButtonMenuItem("Normal", true);
        darkColorItem = new JRadioButtonMenuItem("Dark");
        colorGroup = new ButtonGroup();
        aboutItem = new JMenuItem("About");
        //on met les JRadioButtonMenuItem dans un même groupe (pour le OU exclusif entre-eux)
        colorGroup.add(normalColorItem);
        colorGroup.add(darkColorItem);
        //on ajoute les sous-menus aux menus
        colorItem.add(normalColorItem);
        colorItem.add(darkColorItem);
        optionMenu.add(muteItem);
        optionMenu.add(colorItem);
        aboutMenu.add(aboutItem);
        jeuMenu.add(newGameItem);
        jeuMenu.add(pauseItem);
        jeuMenu.add(highScoresItem);
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
        // Bandeau Haut
        containerTop = new JPanel();
        containerTop.setLayout(new BorderLayout());
        containerTop.setPreferredSize(new Dimension(50, 15));
        container.add(containerTop, BorderLayout.NORTH);
        containerTop.add(new JLabel("PolyTetris"));

        ///////////////////////////////////////////////////////////////////////
        // Bandeau Gauche
        containerLeft = new JPanel();
        containerLeft.setLayout(new FlowLayout());
        containerLeft.setPreferredSize(new Dimension(50, 50));
        containerLeft.setBackground(Color.DARK_GRAY);

        // Texte
        JLabel lblHold = new JLabel("Hold");
        lblHold.setForeground(Color.WHITE);
        containerLeft.add(lblHold);

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
        
        container.add(containerLeft, BorderLayout.WEST);

        ///////////////////////////////////////////////////////////////////////
        // Bandeau Droit
        containerRight = new JPanel();
        containerRight.setLayout(new BorderLayout());
        containerRight.setPreferredSize(new Dimension(50, 50));
        containerRight.setBackground(Color.DARK_GRAY);

        panelScore = new JPanel();
        panelScore.setLayout(new BorderLayout());
        panelScore.setSize(1000, 1000);
        containerRight.add(panelScore, BorderLayout.NORTH);

        score = new JLabel("<html>Score : <br>0</html>");
        score.setPreferredSize(new Dimension(50, 20));
        score.setForeground(Color.WHITE);
        panelScore.setPreferredSize(new Dimension(50, 30));
        panelScore.setBackground(Color.DARK_GRAY);
        panelScore.add(score, BorderLayout.CENTER);

        bigPanelNextPiece = new JPanel();
        bigPanelNextPiece.setLayout(new BorderLayout());
        containerRight.add(bigPanelNextPiece, BorderLayout.CENTER);

        pnlPieceSuiv1 = new JPanel(new GridLayout(4, 4));
        for (int i = 0; i < 16; i++) {
            JComponent pTest = new Case();
            pTest.setBorder(blackLine);
            pnlPieceSuiv1.add(pTest);
        }
        pnlPieceSuiv1.setBorder(blackLine);
        pnlPieceSuiv1.setBackground(backgroundColor);
        bigPanelNextPiece.add(pnlPieceSuiv1, BorderLayout.NORTH);

        pnlPieceSuiv2 = new JPanel(new GridLayout(4, 4));
        for (int i = 0; i < 16; i++) {
            JComponent pTest = new Case();
            pTest.setBorder(blackLine);
            pnlPieceSuiv2.add(pTest);
        }
        pnlPieceSuiv2.setBorder(blackLine);
        pnlPieceSuiv2.setBackground(backgroundColor);
        JPanel panTest = new JPanel();
        panTest.setLayout(new BorderLayout());
        panTest.add(pnlPieceSuiv2, BorderLayout.NORTH);

        pnlPieceSuiv3 = new JPanel(new GridLayout(4, 4));
        for (int i = 0; i < 16; i++) {
            JComponent pTest = new Case();
            pTest.setBorder(blackLine);
            pnlPieceSuiv3.add(pTest);
        }
        pnlPieceSuiv3.setBorder(blackLine);
        pnlPieceSuiv3.setBackground(backgroundColor);
        
        container.add(containerRight, BorderLayout.EAST);
        
        JPanel panTest2 = new JPanel();
        panTest2.setLayout(new BorderLayout());
        panTest2.add(pnlPieceSuiv3, BorderLayout.NORTH);
        panTest2.setBackground(Color.DARK_GRAY);
        panTest.add(panTest2, BorderLayout.CENTER);

        bigPanelNextPiece.add(panTest, BorderLayout.CENTER);

        this.setContentPane(container);

        //lecture du son de fond
        try {
            this.backgroundSon = new Son("src\\sounds\\3.wav");
            this.backgroundSon.start();
        } catch (IllegalArgumentException e) {
            System.out.println("Pas de sortie audio");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void itemStateChanged(ItemEvent e) {
        //...Get information from the item event...
        //...Display it in the text area...
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        javax.swing.JOptionPane.showMessageDialog(this, "Game Over !\nLevel : " + grille.score.level + "\nScore : " + grille.score.score);
    }

    public void setKeyListener(TetrisController control) {
        this.control = control;
        this.addKeyListener(this.control);
    }

    public void display(Grille grille) {
        
        int n = 0;
        for (int i = 0; i < grille.y; i++) {
            for (int j = 0; j < grille.x; j++) {
                ((Case) pan.getComponent(n)).setColor(grille.tab[j][i]);
                n++;
            }
        }

        // Affichage de la liste des pièces (HOLD + Suivantes)
        displayListePiece(pnlPieceHold, grille.pieceHold);
        displayListePiece(pnlPieceSuiv1, grille.tabPieceSuivante[0]);
        displayListePiece(pnlPieceSuiv2, grille.tabPieceSuivante[1]);
        displayListePiece(pnlPieceSuiv3, grille.tabPieceSuivante[2]);

        this.repaint();

        try {
            if (Grille.lectureSon > 0) {
                Son son = new Son("src\\sounds\\" + Grille.lectureSon + ".wav");
                son.play();
                Grille.lectureSon = 0;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Pas de sortie audio");
        }
    }

    public void displayScore(Score score) {
        this.score.setText("<html>Score : <br>" + score.score + "</html>"); //" " + score.level +
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
                    ((Case) pnlDst.getComponent(n)).setColor(Case.getColor(pieceSrc.type));

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
