/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Criaturas.CriaturaBase;
import Model.Habilidades.HabilidadeBase;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author FREE
 */
public class HabilidadeUtilizada extends JFrame {
    public HabilidadeUtilizada(CriaturaBase atacante,HabilidadeBase habilidade,boolean deve_fechar_sozinho) throws IOException
    {
        System.out.println("inicio!");
        this.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        
        JPanel panel_criatura = new CartaCriatura(atacante);
        panel_criatura.setPreferredSize(new Dimension(200,400));
        g.gridx = 0;
        g.gridwidth = 20;
        g.gridy = 0;
        g.gridheight = 40;
        this.add(panel_criatura,g);
        
        JPanel panel_habilidade = new CartaHabilidade(habilidade);
        panel_habilidade.setPreferredSize(new Dimension(200,300));
        g.gridx = 20;
        g.gridwidth = 20;
        g.gridy = 0;
        g.gridheight = 30;
        this.add(panel_habilidade,g);
        
        JButton btConfirmar = new JButton("Confimar");
        btConfirmar.setPreferredSize(new Dimension(200,60));
        g.gridx = 20;
        g.gridwidth = 20;
        g.gridy = 30;
        g.gridheight = 6;
        this.add(btConfirmar,g);
        
        JCheckBox boxDePular = new JCheckBox("Nao faz nada :D");
        boxDePular.setPreferredSize(new Dimension(200,40));
        g.gridx = 20;
        g.gridwidth = 20;
        g.gridy = 36;
        g.gridheight = 4;
        this.add(boxDePular,g);
        
        this.pack();
        ViewGlobal.centralizarJanela(this);
        this.setVisible(true);
    }
}
