/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControleGeral;
import Model.Criaturas.CriaturaBase;
import Model.Criaturas.Monstro;
import Model.Criaturas.Escolha;
import Model.Criaturas.Heroi;
import Model.Criaturas.Jogador;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Tela com a finalidade de permitir a selecao uma opcoes de um conjunto de CriaturasBase
 * @author Paulo
 */
public class SeletorCriaturas extends JFrame{
    
    ControleGeral control = null;
    List< CriaturaBase > lista;
    private int ponteiro = 0;
    private volatile boolean block = false;
    
    private final JButton btDireita;
    private final JButton btEsquerda;
    private final JButton btAtacar;
    private final JButton btCancelar;
    private CartaCriatura panel_criatura;
    
    public Escolha escolha;
    
    public SeletorCriaturas(List< CriaturaBase > lista,ControleGeral controlador,Escolha escolha) throws IOException
    {
        super();
        this.escolha = escolha;
        control = controlador;
        this.lista = lista;
        
        setLayout(new GridBagLayout());
        
        GridBagConstraints g = new GridBagConstraints();
        
        for (int i=0;i<4;i++)
        {
            JPanel panel_preenchedor_esquerda = new JPanel();
            if (i%2==0)
            {
                panel_preenchedor_esquerda.setBackground(Color.DARK_GRAY);
            }
            else
            {
                panel_preenchedor_esquerda.setBackground(Color.BLACK);
            }
            panel_preenchedor_esquerda.setPreferredSize(new Dimension(200,95));
            g.gridwidth = 200;
            g.gridx = 0;
            g.gridheight = 95;
            g.gridy = 0 + i*95;
            add(panel_preenchedor_esquerda,g);
        }
        
        for (int i=0;i<4;i++)
        {
            JPanel panel_preenchedor_direita = new JPanel();
            if (i%2==0)
            {
                panel_preenchedor_direita.setBackground(Color.DARK_GRAY);
            }
            else
            {
                panel_preenchedor_direita.setBackground(Color.BLACK);
            }
            panel_preenchedor_direita.setPreferredSize(new Dimension(200,95));
            g.gridwidth = 200;
            g.gridx = 480;
            g.gridheight = 100;
            g.gridy = 0 + i*95;
            add(panel_preenchedor_direita,g);
        }
        
        panel_criatura = new CartaCriatura(lista.get(ponteiro),false);
        panel_criatura.setPreferredSize(new Dimension(280,380));
        g.gridwidth = 280;
        g.gridx = 200;
        g.gridheight = 380;
        g.gridy = 0;
        add(panel_criatura,g);
        
        btEsquerda = new JButton("Esquerda");
        //carregar imagem seta esquerda
        btEsquerda.setPreferredSize(new Dimension(200,200));
        btEsquerda.setIcon( new ImageIcon( ImageIO.read( new File(getClass().getResource("/View/Imagens/arrow_left_icon.png").getFile()) ) ) );
        btEsquerda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btEsquerdaActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(SeletorCriaturas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        g.gridwidth = 200;
        g.gridx = 0;
        g.gridheight = 200;
        g.gridy = 380;
        add(btEsquerda,g);
        
        btDireita = new JButton("Direita");
        //carregar imagem seta direita
        btDireita.setPreferredSize(new Dimension(200,200));
        btDireita.setIcon( new ImageIcon( ImageIO.read( new File(getClass().getResource("/View/Imagens/arrow_right_icon.png").getFile()) ) ) );
        btDireita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btDireitaActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(SeletorCriaturas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        g.gridwidth = 200;
        g.gridx = 480;
        g.gridheight = 200;
        g.gridy = 380;
        add(btDireita,g);
        
        btAtacar = new JButton("Escolher");
        btAtacar.setPreferredSize(new Dimension(280,100));
        btAtacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btAtacarActionPerformed(evt);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SeletorCriaturas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        g.gridwidth = 280;
        g.gridx = 200;
        g.gridheight = 100;
        g.gridy = 380;
        add(btAtacar,g);
        
        btCancelar = new JButton("Cancelar");
        btCancelar.setPreferredSize(new Dimension(280,100));
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btCancelarActionPerformed(evt);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SeletorCriaturas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        g.gridwidth = 280;
        g.gridx = 200;
        g.gridheight = 100;
        g.gridy = 480;
        add(btCancelar,g);
        
        setResizable(false);
        checkButtonStatus();
        pack();
        
        ViewGlobal.centralizarJanela(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }
    
    private void btAtacarActionPerformed(java.awt.event.ActionEvent evt) throws InterruptedException
    {
        if (control != null)
        {
            if (escolha != null)
            {
                if (escolha == Escolha.ATACAR)
                {
                    this.dispose();
                    control.setFrameParaExibir( FrameExibido.ATACAR_DEFENDER_FRAME );
                    control.setEscolha( Escolha.INDICE_ESCOLHIDO );
                    control.setIndice( ponteiro );
                    //System.out.println("ponteiro = " + ponteiro);
                    control.criarProximoFrame();
                }
                else if (escolha == Escolha.SKILL)
                {
                    this.dispose();
                    control.setCriatura_alvo( lista.get(ponteiro) );
                    control.setFrameParaExibir( FrameExibido.INDICE_CRIATURA_ALVO_SKILL_ESCOLHIDA );
                    control.criarProximoFrame();
                }
            }
            else
            {
                this.dispose();
                control.setEscolha( Escolha.INDICE_ESCOLHIDO );
                control.setIndice( ponteiro );
                control.criarProximoFrame();
            }
        }
    }
    
    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) throws InterruptedException
    {
        if (control != null)
        {
            if (escolha != null)
            {
                this.dispose();
                control.setFrameParaExibir( FrameExibido.BATALHA_FRAME );
                control.setEscolha( Escolha.CANCELAR );
                control.criarProximoFrame();
            }
            else
            {
                this.dispose();
                control.setFrameParaExibir( FrameExibido.TELA_INICIAL );
                control.setEscolha(null);
                control.criarProximoFrame();
            }
        }
    }
    
    private void btEsquerdaActionPerformed(java.awt.event.ActionEvent evt) throws IOException
    {
        if (!block)
        {
            block = true;
            ponteiro--;
            update();
        }
    }
    
    private void btDireitaActionPerformed(java.awt.event.ActionEvent evt) throws IOException
    {
        if (!block)
        {
            block = true;
            ponteiro++;
            update();
        }
    }
    
    private void onEnd()
    {
        
    }
    
    private void update() throws IOException
    {
        panel_criatura.updateMe( lista.get( ponteiro ) );
        checkButtonStatus();
    }
    
    private void checkButtonStatus()
    {
        System.out.println("chamado!");
        if (lista == null)
        {
            btDireita.setEnabled(false);
            btEsquerda.setEnabled(false);
        }
        else
        {
            if (ponteiro == 0)
            {
                btEsquerda.setEnabled(false);
            }
            else
            {
                btEsquerda.setEnabled(true);
            }
            if (ponteiro == lista.size()-1)
            {
                btDireita.setEnabled(false);
            }
            else
            {
                btDireita.setEnabled(true);
            }
        }
        block = false;
    }
   

    
    
    
    
    
}
