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
    
    public void setColor(Color color){
        setBackground(color);
    }
}
