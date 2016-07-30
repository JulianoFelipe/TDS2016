/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Habilidades.HabilidadeBase;
import java.awt.Dimension;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author FREE
 */
public class CartaHabilidade extends javax.swing.JPanel {
    public boolean painel_informativo_ativado = true;
    HabilidadeBase skill;
    private JFrame father = null;
    /**
     * Creates new form CartaSkill
     */
    public CartaHabilidade(HabilidadeBase skill) {
        initComponents();
        btInformacao.setPreferredSize(new Dimension(128,30));
        if (skill!=null)
        {
            this.skill = skill;
            StringBuilder nome = new StringBuilder();
            if (skill.isNotOnCoolDown())
            {
                nome.append("(PRONTO)");
            }
            else
            {
                nome.append('(').append(skill.tempoAtePoderUsarDeNovo().toString()).append(')');
            }
            lbNome.setText( skill.getNome() );
            lbCoolDown.setText( nome.toString() );
            try{
                lbImagem.setIcon( new ImageIcon( ImageIO.read( skill.pegarArquivoImagem() ) ) );
            }
            catch(Exception e)
            {
                e.printStackTrace();
                System.out.println("erro ao carregar imagem!");
            }
        }
        else
        {
            btInformacao.setEnabled(false);
            try{
                lbImagem.setIcon( new ImageIcon( ImageIO.read( new File(getClass().getResource("/View/Imagens/x_icon_200x200.png").getFile()) ) ) );
            }
            catch(Exception e)
            {
                e.printStackTrace();
                System.out.println("erro ao carregar imagem!");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbImagem = new javax.swing.JLabel();
        lbNome = new javax.swing.JLabel();
        btInformacao = new javax.swing.JButton();
        lbCoolDown = new javax.swing.JLabel();

        lbImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Imagens/skilldefault_icon.jpg"))); // NOI18N
        lbImagem.setPreferredSize(new java.awt.Dimension(128, 128));

        lbNome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btInformacao.setText("?");
        btInformacao.setPreferredSize(new java.awt.Dimension(128, 30));
        btInformacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInformacaoActionPerformed(evt);
            }
        });

        lbCoolDown.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btInformacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCoolDown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbImagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNome, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbCoolDown, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btInformacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btInformacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInformacaoActionPerformed
        // TODO add your handling code here:
        if (painel_informativo_ativado)
        {
            CartaHabilidadeDetalhada novo_frame = new CartaHabilidadeDetalhada(skill);
            novo_frame.setVisible(true);
        }
        else
        {
            if (father != null)
            {
                father.dispose();
            }
        }
    }//GEN-LAST:event_btInformacaoActionPerformed

    public void setButtonText(String text)
    {
        btInformacao.setText(text);
    }
    
    public void setFather(JFrame father)
    {
        this.father = father;
    }
    
    public javax.swing.JButton getBotao()
    {
        return btInformacao;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btInformacao;
    private javax.swing.JLabel lbCoolDown;
    private javax.swing.JLabel lbImagem;
    private javax.swing.JLabel lbNome;
    // End of variables declaration//GEN-END:variables
}
