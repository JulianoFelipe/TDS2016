/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.prototipos;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author FREE
 */
public class test_3 {
    final static JFrame frame = new JFrame();
    final static JPanel panel = new JPanel();
    final static JLabel label = new JLabel();
    
    
    public static void main(String args[])
    {
        panel.setPreferredSize(new Dimension(30,30));
        panel.setBackground(Color.RED);
        frame.add(panel);
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
