/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Criaturas.Monstro;
import Criaturas.CriaturaBase;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
public class AtaqueDefenderFrame extends JFrame{
    JLabel label_resultado;
    CartaCriatura defensor_carta;
    public AtaqueDefenderFrame(CriaturaBase atacante, CriaturaBase defensor) throws IOException
    {
        JPanel main_panel = new JPanel();
        main_panel.setPreferredSize(new Dimension(800,440));
        add(main_panel);
        
        main_panel.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        
        JLabel atacante_label = new JLabel("Atacante");
        atacante_label.setPreferredSize(new Dimension(100,20));
        g.gridx = 10;
        g.gridy = 0;
        g.gridwidth = 10;
        g.gridheight = 2;
        //main_panel.add(atacante_label,g);
        
        JLabel label_test = new JLabel();
        label_test.setPreferredSize(new Dimension(800,20));
        label_test.setIcon(new ImageIcon( ImageIO.read(new File(getClass().getResource("/View/Imagens/atacar_defender_superiorimagem.png").getFile() ) ) ));
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 80;
        g.gridheight = 2;
        main_panel.add(label_test,g);
        
        
        JLabel defensor_label = new JLabel("Defensor");
        defensor_label.setPreferredSize(new Dimension(100,20));
        g.gridx = 70;
        g.gridy = 0;
        g.gridwidth = 10;
        g.gridheight = 2;
        //main_panel.add(defensor_label,g);
        
        CartaCriatura atacante_carta = new CartaCriatura(atacante);
        atacante_carta.setPreferredSize(new Dimension(200,400));
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth = 20;
        g.gridheight = 40;
        main_panel.add(atacante_carta,g);
        
        JLabel batalha_icon = new JLabel();
        Icon img_icon;
        BufferedImage img = ImageIO.read(new File(getClass().getResource("/View/Imagens/battle_icon.png").getFile()));
        img_icon = new ImageIcon(img);
        batalha_icon.setIcon(img_icon);
        batalha_icon.setPreferredSize(new Dimension(400,400));
        g.gridx = 20;
        g.gridy = 2;
        g.gridwidth = 40;
        g.gridheight = 40;
        main_panel.add(batalha_icon,g);
        
        defensor_carta = new CartaCriatura(defensor);
        defensor_carta.setPreferredSize(new Dimension(200,400));
        g.gridx = 60;
        g.gridy = 2;
        g.gridwidth = 20;
        g.gridheight = 40;
        main_panel.add(defensor_carta,g);
        
        label_resultado = new JLabel("Damage : ");
        label_resultado.setPreferredSize(new Dimension(400,20));
        g.gridx = 40;
        g.gridy = 42;
        g.gridwidth = 80;
        g.gridheight = 2;
        main_panel.add(label_resultado,g);
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        
        this.pack();
        
        this.setResizable(false);
        this.setVisible(true);
        
    }
    
    public void updateDefensor(CriaturaBase defensor) throws IOException
    {
        if (defensor_carta != null)
        {
            defensor_carta.updateMe(defensor);
        }
    }
    
    public void setText(String text)
    {
        label_resultado.setText(text);
    }
    
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable(){
        @Override
        public void run()
        {
            try {
                AtaqueDefenderFrame a = new AtaqueDefenderFrame(new Monstro(),new Monstro());
            } catch (IOException ex) {
                Logger.getLogger(AtaqueDefenderFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });
    }
}
