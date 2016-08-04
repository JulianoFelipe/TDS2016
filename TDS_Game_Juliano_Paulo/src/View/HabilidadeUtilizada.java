/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControleGeral;
import Model.Criaturas.CriaturaBase;
import Model.Criaturas.Escolha;
import Model.Habilidades.HabilidadeBase;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Tela exibida que informa que uma criatura esta usando uma habilidade
 * @author Paulo
 */
public class HabilidadeUtilizada extends JFrame {
    final private ControleGeral control;
    final private JFrame eu_mesmo;
    public HabilidadeUtilizada(ControleGeral control,CriaturaBase atacante,HabilidadeBase habilidade,boolean deve_fechar_sozinho,boolean bloquearBotaoCancelar) throws IOException
    {
        this.eu_mesmo = this;
        this.control = control;
        System.out.println("inicio!");
        this.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        
        JPanel panel_criatura = new CartaCriatura(atacante,false);
        panel_criatura.setPreferredSize(new Dimension(280,380));
        g.gridx = 0;
        g.gridwidth = 280;
        g.gridy = 0;
        g.gridheight = 380;
        this.add(panel_criatura,g);
        
        CartaHabilidade panel_habilidade = new CartaHabilidade(habilidade);
        panel_habilidade.setPreferredSize(new Dimension(128,204));
        g.gridx = 280;
        g.gridwidth = 128;
        g.gridy = 0;
        g.gridheight = 204;
        this.add(panel_habilidade,g);
        
        JButton btConfirmar = new JButton("Confimar");
        btConfirmar.setPreferredSize(new Dimension(128,60));
        g.gridx = 280;
        g.gridwidth = 128;
        g.gridy = 204;
        g.gridheight = 60;
        this.add(btConfirmar,g);
        
        btConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                control.setFrameParaExibir( FrameExibido.SKILL_USADA );
                control.setHabilidade( habilidade );
                eu_mesmo.dispose();
                control.criarProximoFrame();
            }
        });
        
        /*
        JCheckBox boxDePular = new JCheckBox("Nao faz nada :D");
        boxDePular.setPreferredSize(new Dimension(128,40));
        g.gridx = 280;
        g.gridwidth = 128;
        g.gridy = 264;
        g.gridheight = 40;
        this.add(boxDePular,g);
        */
        
        JButton botaoVoltar = new JButton("Cancelar");
        if (bloquearBotaoCancelar)
        {
            botaoVoltar.setEnabled(false);
        }
        botaoVoltar.setBackground(Color.GRAY);
        botaoVoltar.setPreferredSize(new Dimension(128,96));
        botaoVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cancelar();
                }
            });
        g.gridx = 280;
        g.gridwidth = 128;
        g.gridy = 304;
        g.gridheight = 76;
        this.add(botaoVoltar,g);
        
        if (deve_fechar_sozinho)
        {
            panel_habilidade.getBotao().setEnabled(false);
            btConfirmar.setEnabled(false);
        }
        this.setResizable(false);
        this.pack();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ViewGlobal.centralizarJanela(this);
        this.setVisible(true);
    }
    
    private void cancelar()
    {
        if (control != null)
        {
            this.dispose();
            control.setFrameParaExibir( FrameExibido.BATALHA_FRAME );
            control.setEscolha( Escolha.CANCELAR );
            control.criarProximoFrame();
        }
    }
}
