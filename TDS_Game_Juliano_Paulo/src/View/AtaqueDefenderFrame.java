/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Criaturas.Monstro;
import Model.Criaturas.CriaturaBase;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
        main_panel.setPreferredSize(new Dimension(940,420));
        add(main_panel);
        
        main_panel.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        
        JLabel imagemTopo = new JLabel();
        imagemTopo.setPreferredSize(new Dimension(940,20));
        imagemTopo.setIcon(new ImageIcon( ImageIO.read(new File(getClass().getResource("/View/Imagens/atacar_defender_superiorimagem.png").getFile() ) ) ));
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 940;
        g.gridheight = 20;
        main_panel.add(imagemTopo,g);
        
        CartaCriatura atacante_carta = new CartaCriatura(atacante,true);
        atacante_carta.setPreferredSize(new Dimension(280,380));
        g.gridx = 0;
        g.gridy = 20;
        g.gridwidth = 280;
        g.gridheight = 380;
        main_panel.add(atacante_carta,g);
        
        JLabel batalha_icon = new JLabel();
        Icon img_icon;
        BufferedImage img = ImageIO.read(new File(getClass().getResource("/View/Imagens/battle_icon.png").getFile()));
        img_icon = new ImageIcon(img);
        batalha_icon.setIcon(img_icon);
        batalha_icon.setPreferredSize(new Dimension(380,380));
        g.gridx = 280;
        g.gridy = 20;
        g.gridwidth = 380;
        g.gridheight = 380;
        main_panel.add(batalha_icon,g);
        
        defensor_carta = new CartaCriatura(defensor,true);
        defensor_carta.setPreferredSize(new Dimension(280,380));
        g.gridx = 660;
        g.gridy = 20;
        g.gridwidth = 280;
        g.gridheight = 380;
        main_panel.add(defensor_carta,g);
        
        label_resultado = new JLabel("Damage : ");
        label_resultado.setPreferredSize(new Dimension(940,20));
        label_resultado.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridx = 0;
        g.gridy = 400;
        g.gridwidth = 940;
        g.gridheight = 20;
        main_panel.add(label_resultado,g);
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        
        this.pack();
        ViewGlobal.centralizarJanela(this);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
}
