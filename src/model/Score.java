/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author p1309133
 */
public class Score {
    
    public int score;
    public int[] tabScore = new int[]{ 0, 40, 100, 300 , 1200};
    
    public Score()
    {
        this.score = 0;
    }
    
    public void incrementScore(int nbLigne, int level)
    {
        this.score += tabScore[nbLigne]*(level + 1);
    }
    
}
