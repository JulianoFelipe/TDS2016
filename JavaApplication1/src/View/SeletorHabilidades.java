/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControleArena;
import Model.Criaturas.Escolha;
import Model.Habilidades.HabilidadeBase;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author FREE
 */
public class SeletorHabilidades extends JFrame{
    ControleArena control;
    volatile boolean lock = false;
    public SeletorHabilidades(List< HabilidadeBase > lista_de_skills , ControleArena control)
    {
        this.control = control;
        this.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        
        JPanel preenchedor_borda_superior = new JPanel();
        preenchedor_borda_superior.setBackground(Color.RED);
        preenchedor_borda_superior.setPreferredSize(new Dimension(900,20));
        
        g.gridx = 0;
        g.gridwidth = 90;
        g.gridy = 0;
        g.gridheight = 2;
        add(preenchedor_borda_superior,g);
        
        JPanel preenchedor_vertical = new JPanel();
        preenchedor_vertical.setBackground(Color.RED);
        preenchedor_vertical.setPreferredSize(new Dimension(20,370));
        
        g.gridx = 0;
        g.gridwidth = 2;
        g.gridy = 2;
        g.gridheight = 37;
        add(preenchedor_vertical,g);
        
        for (int i=0;i<4;i++)
        {
            JButton btSelecionar = new JButton("Selecionar");
            btSelecionar.setName( Integer.toString(i) );
            btSelecionar.setPreferredSize(new Dimension(200,70));
            
            g.gridx = 2 + 22*i;
            g.gridwidth = 20;
            g.gridy = 32;
            g.gridheight = 7;
            add(btSelecionar,g);
            
            btSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if (lock == false)
                    {
                        System.out.println("acao feita!");
                        lock = true;
                        control.indice = Integer.parseInt( btSelecionar.getName() );
                        control.frame_a_exibir = FrameExibido.SKILL_USADA;
                        control.escolha = Escolha.INDICE_ESCOLHIDO;
                        try {
                            control.criarProximoFrame();
                        } catch (IOException ex) {
                            Logger.getLogger(SeletorCriaturas.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(SeletorHabilidades.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            
           
            
            CartaHabilidade carta_skill;
            if (i >= lista_de_skills.size())
            {
                carta_skill = new CartaHabilidade(null);
                btSelecionar.setEnabled(false);
            }
            else
            {
                carta_skill = new CartaHabilidade(lista_de_skills.get(i));
            }
            carta_skill.setPreferredSize(new Dimension(200,300));
            
            g.gridx = 2 + 22*i;
            g.gridwidth = 20;
            g.gridy = 2;
            g.gridheight = 30;
            add(carta_skill,g);
            
            JPanel preenchedor_vertical2 = new JPanel();
            preenchedor_vertical2.setBackground(Color.RED);
            preenchedor_vertical2.setPreferredSize(new Dimension(20,370));
            
            g.gridx = 2 + 22*i + 20;
            g.gridwidth = 2;
            g.gridy = 2;
            g.gridheight = 37;
            add(preenchedor_vertical2,g);
        }
        JPanel preenchedor_borda_inferior = new JPanel();
        preenchedor_borda_inferior.setBackground(Color.RED);
        preenchedor_borda_inferior.setPreferredSize(new Dimension(900,20));
        
        g.gridx = 0;
        g.gridwidth = 90;
        g.gridy = 39;
        g.gridheight = 2;
        add(preenchedor_borda_inferior,g);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.pack();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);
        
    }
}