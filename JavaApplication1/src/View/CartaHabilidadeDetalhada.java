/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Habilidades.HabilidadeBase;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author FREE
 */
public class CartaHabilidadeDetalhada extends JFrame{
    CartaHabilidadeDetalhada(HabilidadeBase skill)
    {
        setLayout(new GridBagLayout());
        setResizable(false);
        GridBagConstraints g = new GridBagConstraints();
        
        CartaHabilidade carta_skill = new CartaHabilidade(skill);
        carta_skill.setFather(this);
        carta_skill.painel_informativo_ativado = false;
        carta_skill.setPreferredSize(new Dimension(200,300));
        carta_skill.setButtonText("X");
        
        g.gridx = 0;
        g.gridwidth = 2;
        g.gridy = 0;
        g.gridheight = 3;
        add(carta_skill,g);
        
        JTextArea taDescricao = new JTextArea();
        taDescricao.setPreferredSize(new Dimension(400,300));
        taDescricao.setText(skill.getDescricao());
        taDescricao.setLineWrap(true);
        taDescricao.setWrapStyleWord(true);
        
        g.gridx = 2;
        g.gridwidth = 4;
        g.gridy = 0;
        g.gridheight = 3;
        add(taDescricao,g);
        
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);
    }
}
