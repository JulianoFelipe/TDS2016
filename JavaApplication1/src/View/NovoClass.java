/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import CriaturasPackage.Monstro;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author FREE
 */
public class NovoClass {
    public static void main(String args[])
    {
        try{
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel new_panel = new CartaCriatura(new Monstro());
        
        frame.add(new_panel);
        
        frame.pack();
        frame.setVisible(true);
        }
        catch(Exception e)
        {
            System.out.println("erro = " + e);
        }
    }
}
