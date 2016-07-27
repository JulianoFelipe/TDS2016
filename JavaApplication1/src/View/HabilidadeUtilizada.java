/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControleArena;
import Model.Criaturas.CriaturaBase;
import Model.Habilidades.HabilidadeBase;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author FREE
 */
public class HabilidadeUtilizada extends JFrame {
    final private ControleArena control;
    final private JFrame eu_mesmo;
    public HabilidadeUtilizada(ControleArena control,CriaturaBase atacante,HabilidadeBase habilidade,boolean deve_fechar_sozinho) throws IOException
    {
        this.eu_mesmo = this;
        this.control = control;
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
        
        CartaHabilidade panel_habilidade = new CartaHabilidade(habilidade);
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
        
        btConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                control.frame_a_exibir = FrameExibido.SKILL_USADA;
                eu_mesmo.dispose();
                try {
                    control.criarProximoFrame();
                } catch (IOException ex) {
                    Logger.getLogger(HabilidadeUtilizada.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HabilidadeUtilizada.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        JCheckBox boxDePular = new JCheckBox("Nao faz nada :D");
        boxDePular.setPreferredSize(new Dimension(200,40));
        g.gridx = 20;
        g.gridwidth = 20;
        g.gridy = 36;
        g.gridheight = 4;
        this.add(boxDePular,g);
        
        if (deve_fechar_sozinho)
        {
            panel_habilidade.getBotao().setEnabled(false);
            btConfirmar.setEnabled(false);
        }
        
        this.pack();
        ViewGlobal.centralizarJanela(this);
        this.setVisible(true);
    }
}
