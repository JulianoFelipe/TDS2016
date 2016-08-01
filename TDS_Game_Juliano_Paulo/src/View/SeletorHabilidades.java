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
    ControleArena controle;
    volatile boolean lock = false;
    
    boolean estaEmBatalha = false;
    
    public SeletorHabilidades(List< HabilidadeBase > listaDeHabilidades , ControleArena control, boolean estaEmBatalha)
    {
        this.controle = control;
        this.estaEmBatalha = estaEmBatalha;
        this.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        
        JPanel preenchedor_borda_superior = new JPanel();
        preenchedor_borda_superior.setBackground(Color.GRAY);
        preenchedor_borda_superior.setPreferredSize(new Dimension(612,20));
        
        g.gridx = 0;
        g.gridwidth = 612;
        g.gridy = 0;
        g.gridheight = 20;
        add(preenchedor_borda_superior,g);
        
        JPanel preenchedor_vertical = new JPanel();
        preenchedor_vertical.setBackground(Color.GRAY);
        preenchedor_vertical.setPreferredSize(new Dimension(20,234));
        
        g.gridx = 0;
        g.gridwidth = 20;
        g.gridy = 20;
        g.gridheight = 234;
        add(preenchedor_vertical,g);
        
        for (int i=0;i<4;i++)
        {
            JButton btSelecionar = new JButton("Selecionar");
            if (!estaEmBatalha)
            {
                btSelecionar.setText("Remover");
            }
            btSelecionar.setName( Integer.toString(i) );
            btSelecionar.setPreferredSize(new Dimension(128,30));
            
            g.gridx = 20 + 148*i;
            g.gridwidth = 128;
            g.gridy = 224;
            g.gridheight = 30;
            add(btSelecionar,g);
            
            btSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if (lock == false)
                    {
                        if (estaEmBatalha)
                        {
                            SeletorHabilidades.this.dispose();
                            lock = true;
                            control.indice = Integer.parseInt( btSelecionar.getName() );
                            control.frame_a_exibir = FrameExibido.SKILL_SELECIONADA;
                            control.criarProximoFrame();
                        }
                        else
                        {
                            SeletorHabilidades.this.dispose();
                            lock = true;
                            control.indice = Integer.parseInt( btSelecionar.getName() );
                            control.frame_a_exibir = FrameExibido.SKILL_SUBSTITUIDA;
                            control.criarProximoFrame();
                        }
                    }
                }
            });
            
           
            
            CartaHabilidade carta_skill;
            if (i >= listaDeHabilidades.size())
            {
                carta_skill = new CartaHabilidade(null);
                btSelecionar.setEnabled(false);
            }
            else
            {
                carta_skill = new CartaHabilidade(listaDeHabilidades.get(i));
                if (!listaDeHabilidades.get(i).isNotOnCoolDown())
                {
                    btSelecionar.setEnabled(false);
                }
            }
            carta_skill.setPreferredSize(new Dimension(128,204));
            
            g.gridx = 20 + 148*i;
            g.gridwidth = 128;
            g.gridy = 20;
            g.gridheight = 204;
            add(carta_skill,g);
            
            JPanel preenchedor_vertical2 = new JPanel();
            preenchedor_vertical2.setBackground(Color.GRAY);
            preenchedor_vertical2.setPreferredSize(new Dimension(20,234));
            
            g.gridx = 20 + 148*i + 128;
            g.gridwidth = 20;
            g.gridy = 20;
            g.gridheight = 234;
            add(preenchedor_vertical2,g);
        }
        JPanel preenchedor_borda_inferior = new JPanel();
        preenchedor_borda_inferior.setBackground(Color.GRAY);
        preenchedor_borda_inferior.setPreferredSize(new Dimension(612,20));
        
        g.gridx = 0;
        g.gridwidth = 612;
        g.gridy = 254;
        g.gridheight = 20;
        add(preenchedor_borda_inferior,g);
        
        JButton btCancelar = new JButton("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelar();
            }
        });
        btCancelar.setPreferredSize(new Dimension(612,50));
        g.gridx = 0;
        g.gridwidth = 612;
        g.gridy = 274;
        g.gridheight = 50;
        add(btCancelar,g);
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.pack();
        ViewGlobal.centralizarJanela(this);
        this.setVisible(true);
        
    }
    
    private void cancelar()
    {
        if (estaEmBatalha)
        {
            this.dispose();
            controle.frame_a_exibir = FrameExibido.BATALHA_FRAME;
            controle.escolha = null;
            controle.criarProximoFrame();
        }
        else
        {
            this.dispose();
            controle.frame_a_exibir = FrameExibido.INVENTARIO;
            controle.escolha = null;
            controle.criarProximoFrame();
        }
    }
}
