/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Criaturas.Heroi;
import Model.Criaturas.CriaturaBase;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Paulo.Tenorio
 */
public class CartaCriatura extends javax.swing.JPanel {
    JPanel pAttackBarMovel;
    JPanel pVidaMovel;
    
    /**
     * Creates new form CartaCriatura2
     */
    public CartaCriatura(CriaturaBase creature) throws IOException {
        initComponents();
        
        //pAttackBarTotal.setLayout(null);
        
        pAttackBarMovel = new JPanel();
        pAttackBarMovel.setBackground(Color.ORANGE);
        Integer tamanho_attackbar = (new Double(200*( creature.getBarra_ataque() )/( CriaturaBase.ATTACK_BAR_TO_MOVE )) ).intValue();
        pAttackBarMovel.setSize(new Dimension(tamanho_attackbar,20));
        
        pAttackBarTotal.add(pAttackBarMovel);
        
        pVidaMovel = new JPanel();
        pVidaMovel.setBackground(Color.RED);
        Integer tamanho_vida = (new Double(200*( 1 - (creature.getPontos_vida() / creature.getMax_pontos_vida() ) ) ) ).intValue();
        pVidaMovel.setSize(new Dimension(tamanho_vida,20));
        
        pVidaTotal.add(pVidaMovel);
        
        lbNome.setText(creature.getNome());
        
        StringBuilder ataque_string = new StringBuilder().append(creature.getEffectiveAttack().toString());
        if (creature.getTemp_attack()<0)
        {
            ataque_string.append("(-");
        }
        else
        {
            ataque_string.append("(+");
        }
        ataque_string.append( String.format("%.2f)" , Math.abs( creature.getTemp_attack() ) ) );
        
        lbAtaque.setText( ataque_string.toString() );
        
        StringBuilder defesa_string = new StringBuilder().append(creature.getEffectiveDefense().toString());
        if (creature.getTemp_defense()<0)
        {
            defesa_string.append("(-");
        }
        else
        {
            defesa_string.append("(+");
        }
        defesa_string.append( String.format("%.2f)" , Math.abs( creature.getTemp_defense() ) ) );
        
        lbDefesa.setText( defesa_string.toString()  );
        
        StringBuilder velocidade_string = new StringBuilder().append(creature.getEffectiveSpeed().toString());
        if (creature.getTemp_speed()<0)
        {
            defesa_string.append("(-");
        }
        else
        {
            defesa_string.append("(+");
        }
        velocidade_string.append( String.format("%.2f)" , Math.abs( creature.getTemp_speed() ) ) );
        
        lbVelocidade.setText( velocidade_string.toString() );
        
        if (creature == null)
        {
            System.out.println("creature = null");
        }
        else
        {
            ImageIcon img_icon;
            File img_file;
            img_file = creature.getImagemFile();
            BufferedImage img = ImageIO.read(img_file);
            img_icon = new ImageIcon(img);
            imgCreature.setIcon(img_icon);
            imgCreature.setPreferredSize(new Dimension(200,200));
        }
        this.setPreferredSize(new Dimension(200,400));
    }
    
    public void updateMe( CriaturaBase creature ) throws IOException
    {
        Integer tamanho_attackbar = (new Double(200*( creature.getBarra_ataque() )/( CriaturaBase.ATTACK_BAR_TO_MOVE )) ).intValue();
        pAttackBarMovel.setSize(new Dimension(tamanho_attackbar,20));

        Integer tamanho_vida = (new Double(200*( 1 - (creature.getPontos_vida() / creature.getMax_pontos_vida() ) ) ) ).intValue();
        pVidaMovel.setSize(new Dimension(tamanho_vida,20));
        
        lbNome.setText(creature.getNome());
        StringBuilder ataque_string = new StringBuilder().append(creature.getEffectiveAttack().toString());
        if (creature.getTemp_attack()<0)
        {
            ataque_string.append("(-");
        }
        else
        {
            ataque_string.append("(+");
        }
        ataque_string.append( String.format("%.2f)" , Math.abs( creature.getTemp_attack() ) ) );
        
        lbAtaque.setText( ataque_string.toString() );
        
        StringBuilder defesa_string = new StringBuilder().append(creature.getEffectiveDefense().toString());
        if (creature.getTemp_defense()<0)
        {
            defesa_string.append("(-");
        }
        else
        {
            defesa_string.append("(+");
        }
        defesa_string.append( String.format("%.2f)" , Math.abs( creature.getTemp_defense() ) ) );
        
        lbDefesa.setText( defesa_string.toString()  );
        
        StringBuilder velocidade_string = new StringBuilder().append(creature.getEffectiveSpeed().toString());
        if (creature.getTemp_speed()<0)
        {
            defesa_string.append("(-");
        }
        else
        {
            defesa_string.append("(+");
        }
        velocidade_string.append( String.format("%.2f)" , Math.abs( creature.getTemp_speed() ) ) );
        
        lbVelocidade.setText( velocidade_string.toString() );
        
        if (creature == null)
        {
            System.out.println("creature = null");
        }
        else
        {
            ImageIcon img_icon;
            File img_file;
            img_file = creature.getImagemFile();
            BufferedImage img = ImageIO.read(img_file);
            img_icon = new ImageIcon(img);
            imgCreature.setIcon(img_icon);
            imgCreature.setPreferredSize(new Dimension(200,200));
        }
        this.setPreferredSize(new Dimension(200,400));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgCreature = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pAttackBarTotal = new javax.swing.JPanel();
        pVidaTotal = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lbNome = new javax.swing.JLabel();
        lbAtaque = new javax.swing.JLabel();
        lbDefesa = new javax.swing.JLabel();
        lbVelocidade = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        imgCreature.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Imagens/knight_icon.png"))); // NOI18N

        jLabel3.setText("Ataque :");

        jLabel4.setText("Defesa :");

        jLabel5.setText("Velocidade :");

        pAttackBarTotal.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout pAttackBarTotalLayout = new javax.swing.GroupLayout(pAttackBarTotal);
        pAttackBarTotal.setLayout(pAttackBarTotalLayout);
        pAttackBarTotalLayout.setHorizontalGroup(
            pAttackBarTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pAttackBarTotalLayout.setVerticalGroup(
            pAttackBarTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        pVidaTotal.setBackground(new java.awt.Color(102, 255, 51));

        jPanel4.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pVidaTotalLayout = new javax.swing.GroupLayout(pVidaTotal);
        pVidaTotal.setLayout(pVidaTotalLayout);
        pVidaTotalLayout.setHorizontalGroup(
            pVidaTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pVidaTotalLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pVidaTotalLayout.setVerticalGroup(
            pVidaTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pVidaTotalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lbNome.setText("Nome");

        lbAtaque.setText("jLabel1");

        lbDefesa.setText("jLabel1");

        lbVelocidade.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pAttackBarTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbDefesa, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(imgCreature, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pVidaTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbAtaque, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbVelocidade, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbNome))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imgCreature, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(lbNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pVidaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbAtaque))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbDefesa))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lbVelocidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pAttackBarTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imgCreature;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lbAtaque;
    private javax.swing.JLabel lbDefesa;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbVelocidade;
    private javax.swing.JPanel pAttackBarTotal;
    private javax.swing.JPanel pVidaTotal;
    // End of variables declaration//GEN-END:variables
}
