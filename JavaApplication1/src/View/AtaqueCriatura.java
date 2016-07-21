/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import CriaturasPackage.*;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Paulo.Tenorio
 */
public class AtaqueCriatura extends JFrame{
    AtaqueCriatura() throws IOException
    {
        JPanel main_panel = new JPanel();
        
        JPanel alto_panel = new JPanel();
        
        JPanel atacante_alto_panel = new JPanel();
        atacante_alto_panel.setPreferredSize(new Dimension(200,30));
        
        JPanel panel_preenchedor = new JPanel();
        panel_preenchedor.setPreferredSize(new Dimension(400,30));
        
        JPanel defensor_alto_panel = new JPanel();
        defensor_alto_panel.setPreferredSize(new Dimension(200,30));
        
        JLabel atacante_label = new JLabel("Atacante");
        atacante_alto_panel.add(atacante_label);
        
        JLabel defensor_label = new JLabel("Defensor");
        defensor_alto_panel.add(defensor_label);
        
        alto_panel.add(atacante_alto_panel);
        alto_panel.add(panel_preenchedor);
        alto_panel.add(defensor_alto_panel);
        
        
        JPanel meio_panel = new JPanel();
        
        JPanel batalha_panel = new JPanel();
        batalha_panel.setPreferredSize(new Dimension(400,305));
        batalha_panel.setForeground(Color.PINK);
        
        JLabel batalha_icon = new JLabel();
        Icon img_icon;
        BufferedImage img = ImageIO.read(new File(getClass().getResource("/View/Imagens/battle_icon.png").getFile()));
        System.out.println("img="+img);
        img_icon = new ImageIcon(img);
        batalha_icon.setIcon(img_icon);
        batalha_panel.add(batalha_icon);
        
        meio_panel.setPreferredSize(new Dimension(305*3,400));
        
        alto_panel.setLayout(new BoxLayout(alto_panel,BoxLayout.X_AXIS));
        meio_panel.setLayout(new BoxLayout(meio_panel, BoxLayout.X_AXIS));
        main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));
        CartaCriatura atacante = new CartaCriatura(new Monstro());
        
        CartaCriatura defensor = new CartaCriatura(new Monstro());
        
        meio_panel.add(atacante);
        meio_panel.add(batalha_panel);
        meio_panel.add(defensor);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        main_panel.add(alto_panel);
        main_panel.add(meio_panel);
        
        this.add(main_panel);
        this.pack();
        
        this.setResizable(false);
        this.setVisible(true);
        
    }
    
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable(){
        @Override
        public void run()
        {
            try {
                AtaqueCriatura a = new AtaqueCriatura();
            } catch (IOException ex) {
                Logger.getLogger(AtaqueCriatura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });
    }
}
