/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.prototipos;

import Model.Criaturas.Monstro;
import View.CartaCriatura;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author FREE
 */
public class test {
    public static void main(String args[])
    {
        JFrame frame = new JFrame();
        
        CartaCriatura carta = new CartaCriatura(new Monstro());
        //carta.setBackground(Color.RED);
        frame.add(carta);
        
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
