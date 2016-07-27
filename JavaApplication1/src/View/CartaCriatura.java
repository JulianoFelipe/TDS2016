/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Criaturas.CriaturaBase;
import Model.Efeitos.Efeito;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author FREE
 */
public class CartaCriatura extends JPanel{
    JLabel imagemLabel;
    JLabel lbNome;
    JLabel lbAtaque;
    JLabel lbDefesa;
    JLabel lbVelocidade;
    JPanel pVida;
    JPanel pVidaRestante;
    JPanel pVidaPerdida;
    JPanel pAtaqueBar;
    JPanel pAtaqueBarFaltando;
    JPanel pAtaqueBarGanho;
    JPanel pEfeitosSuperPanel;
    JButton btAcimaEfeito;
    JButton btBaixoEfeito;
    PanelEfeitos[] pEfeitos;
    CriaturaBase criatura;
    
    int ponteiro_de_efeitos = 1;
    
    public CartaCriatura(CriaturaBase criatura)
    {
        this.criatura = criatura;
        this.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        
        imagemLabel = new JLabel("");
        imagemLabel.setPreferredSize( new Dimension(200,200) );
        g.gridx = 0;
        g.gridwidth = 200;
        g.gridy = 0;
        g.gridheight = 200;
        if (criatura == null)
        {
            System.out.println("criatura = null");
        }
        else
        {
            ImageIcon img_icon;
            File img_file;
            img_file = criatura.getImagemFile();
            BufferedImage img = null;
            try {
                img = ImageIO.read(img_file);
            } catch (IOException ex) {
                Logger.getLogger(CartaCriatura.class.getName()).log(Level.SEVERE, null, ex);
            }
            img_icon = new ImageIcon(img);
            imagemLabel.setIcon(img_icon);
        }
        this.add(imagemLabel,g);
        
        lbNome = new JLabel("");
        lbNome.setText(criatura.getNome());
        lbNome.setPreferredSize(new Dimension(200,20));
        lbNome.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridx = 0;
        g.gridwidth = 200;
        g.gridy = 200;
        g.gridheight = 20;
        this.add(lbNome,g);
        
        pVida = new JPanel();
        pVida.setPreferredSize( new Dimension(200,50) );
        pVida.setBackground(Color.PINK);
        pVida.setLayout(new GridBagLayout());
        GridBagConstraints g2 = new GridBagConstraints();
        g.gridx = 0;
        g.gridwidth = 200;
        g.gridy = 220;
        g.gridheight = 50;
        this.add(pVida,g);
        
        Integer tamanho_vida_faltando = (new Double(200*( 1 - (criatura.getPontos_vida() / criatura.getMax_pontos_vida() ) ) ) ).intValue();
        if (tamanho_vida_faltando > 200)
        {
            tamanho_vida_faltando = 200;
        }
        pVidaRestante = new JPanel();
        pVidaRestante.setPreferredSize( new Dimension(200-tamanho_vida_faltando,50) );
        pVidaRestante.setBackground(Color.GREEN);
        g2.gridx = tamanho_vida_faltando;
        g2.gridwidth = 200-tamanho_vida_faltando;
        g2.gridy = 0;
        g2.gridheight = 50;
        pVida.add(pVidaRestante,g2);
        
        pVidaPerdida = new JPanel();
        pVidaPerdida.setPreferredSize( new Dimension(tamanho_vida_faltando,50) );
        pVidaPerdida.setBackground(Color.RED);
        g2.gridx = 0;
        g2.gridwidth = tamanho_vida_faltando;
        g2.gridy = 0;
        g2.gridheight = 50;
        pVida.add(pVidaPerdida,g2);
        
        lbAtaque = new JLabel("Ataque : ");
        lbAtaque.setPreferredSize( new Dimension(200,20) );
        g.gridx = 0;
        g.gridwidth = 200;
        g.gridy = 270;
        g.gridheight = 20;
        this.add(lbAtaque,g);
                
        lbDefesa = new JLabel("Defesa : ");
        lbDefesa.setPreferredSize( new Dimension(200,20) );
        g.gridx = 0;
        g.gridwidth = 200;
        g.gridy = 290;
        g.gridheight = 20;
        this.add(lbDefesa,g);
        
        lbVelocidade = new JLabel("Velocidade : ");
        lbVelocidade.setPreferredSize( new Dimension(200,20) );
        g.gridx = 0;
        g.gridwidth = 200;
        g.gridy = 310;
        g.gridheight = 20;
        this.add(lbVelocidade,g);
        
        pAtaqueBar = new JPanel();
        pAtaqueBar.setPreferredSize(new Dimension(200,50));
        //pAtaqueBar.setBackground(Color.PINK);
        pAtaqueBar.setLayout(new GridBagLayout());
        g.gridx = 0;
        g.gridwidth = 200;
        g.gridy = 330;
        g.gridheight = 50;
        this.add(pAtaqueBar,g);
        
        Integer tamanho_attackbar = (new Double(200*( criatura.getBarra_ataque() )/( CriaturaBase.ATTACK_BAR_TO_MOVE )) ).intValue();
        if (tamanho_attackbar > 200)
        {
            tamanho_attackbar = 200;
        }
        pAtaqueBarFaltando = new JPanel();
        pAtaqueBarFaltando.setBackground(Color.GRAY);
        pAtaqueBarFaltando.setPreferredSize( new Dimension(200-tamanho_attackbar,50) );
        g2.gridx = 0;
        g2.gridwidth = 200-tamanho_attackbar;
        g2.gridy = 100;
        g2.gridheight = 50;
        pAtaqueBar.add(pAtaqueBarFaltando,g2);
        
        pAtaqueBarGanho = new JPanel();
        pAtaqueBarGanho.setBackground(Color.ORANGE);
        pAtaqueBarGanho.setPreferredSize( new Dimension(tamanho_attackbar,50) );
        g2.gridx = 200-tamanho_attackbar;
        g2.gridwidth = tamanho_attackbar;
        g2.gridy = 100;
        g2.gridheight = 50;
        pAtaqueBar.add(pAtaqueBarGanho,g2);
        
        StringBuilder ataque_string = new StringBuilder("Ataque : ").append(criatura.getEffectiveAttack().toString());
        if (criatura.getTemp_attack()<0)
        {
            ataque_string.append("(-");
        }
        else
        {
            ataque_string.append("(+");
        }
        ataque_string.append( String.format("%.2f)" , Math.abs( criatura.getTemp_attack() ) ) );
        
        lbAtaque.setText( ataque_string.toString() );
        
        StringBuilder defesa_string = new StringBuilder("Defesa : ").append(criatura.getEffectiveDefense().toString());
        if (criatura.getTemp_defense()<0)
        {
            defesa_string.append("(-");
        }
        else
        {
            defesa_string.append("(+");
        }
        defesa_string.append( String.format("%.2f)" , Math.abs( criatura.getTemp_defense() ) ) );
        
        lbDefesa.setText( defesa_string.toString()  );
        
        StringBuilder velocidade_string = new StringBuilder("Velocidade : ").append(criatura.getEffectiveSpeed().toString());
        if (criatura.getTemp_speed()<0)
        {
            velocidade_string.append("(-");
        }
        else
        {
            velocidade_string.append("(+");
        }
        velocidade_string.append( String.format("%.2f)" , Math.abs( criatura.getTemp_speed() ) ) );
        
        lbVelocidade.setText( velocidade_string.toString() );
        
        pEfeitosSuperPanel = new JPanel();
        pEfeitosSuperPanel.setBackground(Color.BLACK);
        pEfeitosSuperPanel.setPreferredSize(new Dimension(80,380));
        pEfeitosSuperPanel.setLayout(new GridBagLayout());
        g.gridx = 200;
        g.gridwidth = 80;
        g.gridy = 0;
        g.gridheight = 380;
        
        this.add(pEfeitosSuperPanel,g);
        
        btAcimaEfeito = new JButton();
        btAcimaEfeito.setPreferredSize(new Dimension(80,70));
        try {
            btAcimaEfeito.setIcon( new ImageIcon( ImageIO.read( new File(getClass().getResource("/View/Imagens/flecha_cima_icon.png").getFile() ) ) ) );
        } catch (IOException ex) {
            Logger.getLogger(CartaCriatura.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ponteiro_de_efeitos == 1)
        {
            btAcimaEfeito.setEnabled(false);
        }
        btAcimaEfeito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incPonteiroEfeitos();
            }
        });
        g2.gridx = 0;
        g2.gridwidth = 80;
        g2.gridy = 0;
        g2.gridheight = 70;
        pEfeitosSuperPanel.add(btAcimaEfeito,g2);
        pEfeitos = new PanelEfeitos[3];
        for (int i=0;i<3;i++)
        {
            Efeito efeito;
            
            if (i + ponteiro_de_efeitos*3 >= criatura.getLista_de_efeitos().size())
            {
                efeito = null;
            }
            else
            {
                efeito = criatura.getLista_de_efeitos().get(i + ponteiro_de_efeitos);
            }
            
            pEfeitos[i] = new PanelEfeitos(efeito);
            pEfeitos[i].setPreferredSize(new Dimension(80,80));
            g2.gridx = 0;
            g2.gridwidth = 80;
            g2.gridy = 70 + 80*i;
            g2.gridheight = 80;
            pEfeitosSuperPanel.add(pEfeitos[i],g2);
        }
        
        btBaixoEfeito = new JButton();
        btBaixoEfeito.setPreferredSize(new Dimension(80,70));
        try {
            btBaixoEfeito.setIcon( new ImageIcon( ImageIO.read( new File(getClass().getResource("/View/Imagens/flecha_baixo_icon.png").getFile() ) ) ) );
        } catch (IOException ex) {
            Logger.getLogger(CartaCriatura.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ponteiro_de_efeitos*3 >= criatura.getLista_de_efeitos().size())
        {
            btBaixoEfeito.setEnabled(false);
        }
        btBaixoEfeito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decPonteiroEfeitos();
            }
        });
        g2.gridx = 0;
        g2.gridwidth = 80;
        g2.gridy = 70 + 80*3;
        g2.gridheight = 70;
        pEfeitosSuperPanel.add(btBaixoEfeito,g2);
        
        this.setBackground(Color.PINK);
        
        this.setPreferredSize(new Dimension(280,380));
    }
    
    private void incPonteiroEfeitos()
    {
        ponteiro_de_efeitos++;
        for (int i=0;i<3;i++)
        {
            Efeito efeito;
            if (i + ponteiro_de_efeitos*3 >= criatura.getLista_de_efeitos().size())
            {
                efeito = null;
            }
            else
            {
                efeito = criatura.getLista_de_efeitos().get(i + ponteiro_de_efeitos);
            }
            pEfeitos[i].update(efeito);
        }
    }
    
    private void decPonteiroEfeitos()
    {
        ponteiro_de_efeitos--;
        for (int i=0;i<3;i++)
        {
            Efeito efeito;
            if (i + ponteiro_de_efeitos*3 >= criatura.getLista_de_efeitos().size())
            {
                efeito = null;
            }
            else
            {
                efeito = criatura.getLista_de_efeitos().get(i + ponteiro_de_efeitos);
            }
            pEfeitos[i].update(efeito);
        }
    }
    
    public void updateMe(CriaturaBase criatura) throws IOException
    {
        this.criatura = criatura;
        ponteiro_de_efeitos = 1;
        
        for (int i=0;i<3;i++)
        {
            Efeito efeito;
            if (i + ponteiro_de_efeitos*3 >= criatura.getLista_de_efeitos().size())
            {
                efeito = null;
            }
            else
            {
                efeito = criatura.getLista_de_efeitos().get(i + ponteiro_de_efeitos);
            }
            pEfeitos[i].update(efeito);
        }
        
        GridBagConstraints g2 = new GridBagConstraints();
        
        Integer tamanho_vida_faltando = (new Double(200*( 1 - (criatura.getPontos_vida() / criatura.getMax_pontos_vida() ) ) ) ).intValue();
        if (tamanho_vida_faltando > 200)
        {
            tamanho_vida_faltando = 200;
        }
        pVidaRestante.setPreferredSize( new Dimension(200-tamanho_vida_faltando,50) );
        pVidaRestante.setBackground(Color.GREEN);
        g2.gridx = tamanho_vida_faltando;
        g2.gridwidth = 200-tamanho_vida_faltando;
        g2.gridy = 0;
        g2.gridheight = 50;
        pVida.add(pVidaRestante,g2);
        
        pVidaPerdida.setPreferredSize( new Dimension(tamanho_vida_faltando,50) );
        pVidaPerdida.setBackground(Color.RED);
        g2.gridx = 0;
        g2.gridwidth = tamanho_vida_faltando;
        g2.gridy = 0;
        g2.gridheight = 50;
        pVida.add(pVidaPerdida,g2);
        
        Integer tamanho_attackbar = (new Double(200*( criatura.getBarra_ataque() )/( CriaturaBase.ATTACK_BAR_TO_MOVE )) ).intValue();
        if (tamanho_attackbar > 200)
        {
            tamanho_attackbar = 200;
        }
        pAtaqueBarFaltando.setBackground(Color.GRAY);
        pAtaqueBarFaltando.setPreferredSize( new Dimension(200-tamanho_attackbar,50) );
        g2.gridx = 0;
        g2.gridwidth = 200-tamanho_attackbar;
        g2.gridy = 100;
        g2.gridheight = 50;
        pAtaqueBar.add(pAtaqueBarFaltando,g2);
        
        pAtaqueBarGanho.setBackground(Color.ORANGE);
        pAtaqueBarGanho.setPreferredSize( new Dimension(tamanho_attackbar,50) );
        g2.gridx = 200-tamanho_attackbar;
        g2.gridwidth = tamanho_attackbar;
        g2.gridy = 100;
        g2.gridheight = 50;
        pAtaqueBar.add(pAtaqueBarGanho,g2);
        
        lbNome.setText(criatura.getNome());
        StringBuilder ataque_string = new StringBuilder("Ataque : ").append(criatura.getEffectiveAttack().toString());
        if (criatura.getTemp_attack()<0)
        {
            ataque_string.append("(-");
        }
        else
        {
            ataque_string.append("(+");
        }
        ataque_string.append( String.format("%.2f)" , Math.abs( criatura.getTemp_attack() ) ) );
        
        lbAtaque.setText( ataque_string.toString() );
        
        StringBuilder defesa_string = new StringBuilder("Defesa : ").append(criatura.getEffectiveDefense().toString());
        if (criatura.getTemp_defense()<0)
        {
            defesa_string.append("(-");
        }
        else
        {
            defesa_string.append("(+");
        }
        defesa_string.append( String.format("%.2f)" , Math.abs( criatura.getTemp_defense() ) ) );
        
        lbDefesa.setText( defesa_string.toString()  );
        
        StringBuilder velocidade_string = new StringBuilder("Velocidade : ").append(criatura.getEffectiveSpeed().toString());
        if (criatura.getTemp_speed()<0)
        {
            velocidade_string.append("(-");
        }
        else
        {
            velocidade_string.append("(+");
        }
        velocidade_string.append( String.format("%.2f)" , Math.abs( criatura.getTemp_speed() ) ) );
        
        lbVelocidade.setText( velocidade_string.toString() );
        
        if (criatura == null)
        {
            System.out.println("criatura = null");
        }
        else
        {
            ImageIcon img_icon;
            File img_file;
            img_file = criatura.getImagemFile();
            BufferedImage img = ImageIO.read(img_file);
            img_icon = new ImageIcon(img);
            imagemLabel.setIcon(img_icon);
        }
    }
}
