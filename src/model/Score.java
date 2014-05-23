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
    //défini la valeur des combos ( 1 ligne simple = 100pts, 2 lignes d'un coup 250pts...)
    public int[] tabScore = new int[]{ 0, 100, 250, 500, 1000};
    
    public Score()
    {
        this.score = 0;
    }
    
    public void incrementScore(int nbLigne, int level)
    {
        this.score += tabScore[nbLigne]*(level + 1);
    }
}
