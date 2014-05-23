/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.TetrisController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import model.Grille;
import model.Score;
import tetris.Main;

/**
 *
 * @author logan
 */
public class TetrisView extends javax.swing.JFrame {

    /**
     * Creates new form TetrisView
     */
    public JComponent pan;
    public JComponent piece1;
    public JComponent piece2;
    public JComponent piece3;
    public JLabel score;
    public JComponent container;
    public JComponent containerEast;
    public JComponent panelScore;
    public JComponent grandPanelPieceSuivante;
    public static final Color backgroundColor = Color.LIGHT_GRAY;
    
    private TetrisController con;
    
    public TetrisView() {
        //setSize(200,400);
        initComponents();
        
        container = new JPanel();
        container.setLayout(new BorderLayout());
        
        pan = new JPanel(new GridLayout(20,10));
        Border blackLine = BorderFactory.createLineBorder(Color.black, 1);
        for(int i = 0; i < 200; i++){
            JComponent pTest = new Case();
            pTest.setBorder(blackLine);
            pan.add(pTest);
        }
        pan.setBorder(blackLine);
        pan.setBackground(Color.DARK_GRAY);
        
        container.add(pan, BorderLayout.CENTER);
        
        containerEast = new JPanel();
        containerEast.setLayout(new BorderLayout());
        container.add(containerEast, BorderLayout.EAST);
        containerEast.setPreferredSize(new Dimension(50, 50));
        
        panelScore = new JPanel();
        panelScore.setLayout(new BorderLayout());
        panelScore.setSize(1000,1000);
        containerEast.add(panelScore, BorderLayout.NORTH);
        containerEast.setBackground(backgroundColor);
        
        score = new JLabel("<html>Score : <br>0</html>");
        score.setPreferredSize(new Dimension(50,20));
        score.setForeground(Color.WHITE);
        panelScore.setPreferredSize(new Dimension(50,30));
        panelScore.setBackground(Color.DARK_GRAY);
        panelScore.add(score, BorderLayout.CENTER);
        
        grandPanelPieceSuivante = new JPanel();
        grandPanelPieceSuivante.setLayout(new BorderLayout());
        containerEast.add(grandPanelPieceSuivante, BorderLayout.CENTER);
        
        piece1 = new JPanel(new GridLayout(4,4));
        for(int i = 0; i < 16; i++){
            JComponent pTest = new Case();
            pTest.setBorder(blackLine);
            piece1.add(pTest);
        }
        piece1.setBorder(blackLine);
        piece1.setBackground(backgroundColor);
        grandPanelPieceSuivante.add(piece1, BorderLayout.NORTH);
        
        piece2 = new JPanel(new GridLayout(4,4));
        for(int i = 0; i < 16; i++){
            JComponent pTest = new Case();
            pTest.setBorder(blackLine);
            piece2.add(pTest);
        }
        piece2.setBorder(blackLine);
        piece2.setBackground(backgroundColor);
        JPanel panTest = new JPanel();
        panTest.setLayout(new BorderLayout());
        panTest.add(piece2, BorderLayout.NORTH);
        
        piece3 = new JPanel(new GridLayout(4,4));
        for(int i = 0; i < 16; i++){
            JComponent pTest = new Case();
            pTest.setBorder(blackLine);
            piece3.add(pTest);
        }
        piece3.setBorder(blackLine);
        piece3.setBackground(backgroundColor);
        JPanel panTest2 = new JPanel();
        panTest2.setLayout(new BorderLayout());
        panTest2.add(piece3, BorderLayout.NORTH);
        panTest2.setBackground(Color.DARK_GRAY);
        panTest.add(panTest2, BorderLayout.CENTER);
        //grandPanelPieceSuivante.add(piece3, BorderLayout.SOUTH);
        
        grandPanelPieceSuivante.add(panTest, BorderLayout.CENTER);
        
        this.setContentPane(container);
        
    }
    
    public void gameOver()
    {
        javax.swing.JOptionPane.showMessageDialog(this,"Game Over !");
    }
    
    public void setKeyListener(TetrisController con)
    {
        this.con = con;
        this.addKeyListener(this.con);
    }
    
    public void display(Grille grille){
        int n = 0;
        for(int i = 0; i < grille.y; i++){
            for(int j = 0; j < grille.x; j++){
                ((Case)pan.getComponent(n)).setColor(grille.tab[j][i]);
                n++;
            }
        }
        
        Color piece[][] = new Color[4][4];     
        
        n = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                ((Case)piece1.getComponent(n)).setColor(backgroundColor);
                n++;
            }
        }
        
        n = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                ((Case)piece2.getComponent(n)).setColor(backgroundColor);
                n++;
            }
        }
        
        n = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                ((Case)piece3.getComponent(n)).setColor(backgroundColor);
                n++;
            }
        }
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                piece[i][j] = backgroundColor;
            }
        }
        
        int masqueX = 0, masqueY = 0;
        for(int i = grille.tabPieceSuivante[0].x; i < grille.tabPieceSuivante[0].x + grille.tabPieceSuivante[0].largeur; i++){
            masqueY = 0;
            for(int j = grille.tabPieceSuivante[0].y; j < grille.tabPieceSuivante[0].y + grille.tabPieceSuivante[0].hauteur ; j++){
                
                if(grille.tabPieceSuivante[0].tab[0][masqueX][masqueY] == true)
                {
                    piece[i][j] = Case.getColor(grille.tabPieceSuivante[0].type);
                }
                masqueY++; 
            }
            masqueX++;
        }
        
        n = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                ((Case)piece1.getComponent(n)).setColor(piece[j][i]);
                n++;
            }
        }
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                piece[i][j] = backgroundColor;
            }
        }
        
        masqueX = 0;
        masqueY = 0;
        for(int i = grille.tabPieceSuivante[1].x; i < grille.tabPieceSuivante[1].x + grille.tabPieceSuivante[1].largeur; i++){
            masqueY = 0;
            for(int j = grille.tabPieceSuivante[1].y; j < grille.tabPieceSuivante[1].y + grille.tabPieceSuivante[1].hauteur ; j++){
                
                if(grille.tabPieceSuivante[1].tab[0][masqueX][masqueY] == true)
                {
                    piece[i][j] = Case.getColor(grille.tabPieceSuivante[1].type);
                }
                masqueY++; 
            }
            masqueX++;
        }
        
        n = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                ((Case)piece2.getComponent(n)).setColor(piece[j][i]);
                n++;
            }
        }
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                piece[i][j] = backgroundColor;
            }
        }
        
        masqueX = 0;
        masqueY = 0;
        for(int i = grille.tabPieceSuivante[2].x; i < grille.tabPieceSuivante[2].x + grille.tabPieceSuivante[2].largeur; i++){
            masqueY = 0;
            for(int j = grille.tabPieceSuivante[2].y; j < grille.tabPieceSuivante[2].y + grille.tabPieceSuivante[2].hauteur ; j++){
                
                if(grille.tabPieceSuivante[2].tab[0][masqueX][masqueY] == true)
                {
                    piece[i][j] = Case.getColor(grille.tabPieceSuivante[2].type);
                }
                masqueY++; 
            }
            masqueX++;
        }
        
        n = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                ((Case)piece3.getComponent(n)).setColor(piece[j][i]);
                n++;
            }
        }
        
        this.repaint();
    }
    
    public void displayScore(Score score)
    {
        this.score.setText("<html>Score : <br>" + score.score + "</html>");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(250, 400));
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
