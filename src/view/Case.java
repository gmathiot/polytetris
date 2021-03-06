/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author logan
 */
public class Case extends JPanel{
    public Case(){
        super();
        setBackground(Color.DARK_GRAY);
    }
    
    public void setColor(Color c){
        setBackground(c);
    }
    
    public void setColor(Integer i, boolean iAmBlue){
        setBackground(getColor(i,iAmBlue));
    }
    
    public static Color getColor(Integer i, boolean iAmBlue)
    {
        Color c = Color.BLACK;
        if(!iAmBlue)
        {
            switch (i) {
                case 1 :
                    c = Color.BLUE;
                        break;
                case 2 : 
                    c = Color.CYAN;
                        break;
                case 3 : 
                    c = Color.RED;
                        break;
                case 4 : 
                    c = Color.MAGENTA;
                        break;
                case 5 : 
                    c = Color.ORANGE;
                        break;
                case 6 : 
                    c = Color.GREEN;
                        break;
                case 7 : 
                    c = Color.YELLOW;
                        break;

                default : c = Color.BLACK;
            }
        }
        else if(i>0)
            c = Color.BLUE;
        return c;
    }
}
