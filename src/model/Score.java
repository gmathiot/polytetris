/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author logan
 */
public class Score {
    
    public int score;
    public int level;
    public int nbLigneReussies;
    //défini la valeur des combos ( 1 ligne simple = 100pts, 2 lignes d'un coup 250pts...)
    public int[] tabScore = new int[]{ 0, 100, 250, 500, 1000};
    
    public Score()
    {
        this.score = 0;
        this.level = 1;
        this.nbLigneReussies = 0;
    }
    
    public boolean actualiseLevel()
    {
        //toute les 10 lignes réussies, le niveau augmente
        if(this.nbLigneReussies>=this.level*10)
        {
           this.level++;
           return true;
        }
        return false;
    }
    
    public void incrementScore(int nbLigne)
    {
        //les lignes rapportent plus de point en faisant des combos, mais aussi en fonction du niveau
        this.score += tabScore[nbLigne]*(this.level);
    }
}
