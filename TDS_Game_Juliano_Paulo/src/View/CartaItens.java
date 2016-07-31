/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControleArena;
import Model.Criaturas.Escolha;
import Model.Itens.ArmaBase;
import Model.Itens.ArmaduraBase;
import Model.Itens.ItemBase;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author FREE
 */
public class CartaItens extends javax.swing.JPanel {
    
    /**
     * Referencia ao controle para comunicacao
     */
    private ControleArena controle;
    
    /**
     * Item que a carta esta exibindo
     */
    private ItemBase item;
    
    /**
     * Comportamento ao clicar eh diferente se o pai era seletor ou nao
     */
    boolean paiEhSeletor = false;
    
    /**
     * Variavel usada para manipular eventos mais complexos
     */
    int tipo;
    
    public CartaItens(ItemBase item,ControleArena controle,boolean paiEhSeletor) {
        initComponents();
        this.paiEhSeletor = paiEhSeletor;
        this.item = item;
        this.controle = controle;
        if (item != null )
        {
            lbNome.setText(item.getNome());
            try {
                lbImagem.setIcon(new ImageIcon( ImageIO.read( item.getArquivoDeImagem() ) ));
            } catch (IOException ex) {
                System.out.println("falha em carregar imagem!");
                Logger.getLogger(CartaItens.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (item.getHeroi() == null)
            {
                btUsar.setText("Usar");
            }
            else
            {
                btUsar.setText("Remover");
            }
        }
        else
        {
            lbNome.setText("Sem item!");
            btUsar.setText("Equipar");
            btUsar.setEnabled(true);
            btInformacoes.setEnabled(false);
        }
    }
    
    /**
     * Muda informaçoes da carta de acordo com novo item passado
     */
    public void update(ItemBase item)
    {
        this.item = item;
        if (item != null )
        {
            lbNome.setText(item.getNome());
            try {
                lbImagem.setIcon(new ImageIcon( ImageIO.read( item.getArquivoDeImagem() ) ));
            } catch (IOException ex) {
                System.out.println("falha em carregar imagem!");
                Logger.getLogger(CartaItens.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (item.getHeroi() == null)
            {
                btUsar.setText("Usar");
            }
            else
            {
                btUsar.setText("Remover");
            }
        }
        else
        {
            lbNome.setText("Sem item!");
            btUsar.setText("Equipar");
            btUsar.setEnabled(true);
            btInformacoes.setEnabled(false);
        }
    }
    
    /**
     * Quando por alguma razao o botao deve ou nao ser desabilitado pode mudar usando esse metodo
     * @param novo_estado novo estado do botao
     */
    public void mudarEstadoDoBotao(boolean novo_estado)
    {
        btUsar.setEnabled(novo_estado);
    }
    
    public void desabilitarTodosBotoes()
    {
        btUsar.setEnabled(false);
        btInformacoes.setEnabled(false);
    }
    
    /**
     * Se tipo == 0 entao o inventario encara esse panel nulo como destinado a armas
     * Se tipo == 1 entao o inventario encara esse panel nulo como destinado a armaduras
     */
    public void mudarTipo(int tipo)
    {
        this.tipo = tipo;
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
        btUsar = new javax.swing.JButton();
        btInformacoes = new javax.swing.JButton();

        lbImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Imagens/x_icon165x165.png"))); // NOI18N
        lbImagem.setText("jLabel1");

        lbNome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbNome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNome.setText("Nome");

        btUsar.setText("Usar");
        btUsar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUsarActionPerformed(evt);
            }
        });

        btInformacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Imagens/ponto_interrogacao.png"))); // NOI18N
        btInformacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInformacoesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btUsar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btInformacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbNome, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btUsar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btInformacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btUsarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUsarActionPerformed
        // TODO add your handling code here:
        if (controle != null)
        {
            if (!paiEhSeletor)
            {
                if (item != null)
                {
                    System.out.println("item existe!");
                    if (item.getHeroi()==null)
                    {
                        controle.escolha = Escolha.ITEM_ESCOLHIDO;
                        controle.item = item;
                        System.out.println("Item nao tem dono!");
                    }
                    else
                    {
                        System.out.println("Item tem dono!");
                        controle.frame_a_exibir = FrameExibido.ESCOLHER_UM_HEROI;
                        controle.escolha = Escolha.INDICE_ESCOLHIDO;
                        item.getHeroi().removerItem(item);
                    }
                }
                else
                {
                    if (tipo == 0)
                    {
                        controle.frame_a_exibir = FrameExibido.PROCURANDO_ARMADURA_PARA_CRIATURA;
                        System.out.println("ARMADURA");
                    }
                    else if (tipo == 1)
                    {
                        controle.frame_a_exibir = FrameExibido.PROCURANDO_ARMA_PARA_CRIATURA;
                        System.out.println("ARMA");
                    }
                    else
                    {
                        System.err.println("TERCEIRO EXCLUIDO LOL");
                    }
                }
            }
            else
            {
                if (item != null)
                {
                    controle.escolha = Escolha.ITEM_ESCOLHIDO;
                    controle.item = item;
                    System.out.println("item existe!");
                }
            }
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.dispose();
            controle.criarProximoFrame();
        }
        else
        {
            System.err.println("CONTROLE NULO FUUUUUUUUUUUUUUUUU");
        }
    }//GEN-LAST:event_btUsarActionPerformed

    private void btInformacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInformacoesActionPerformed
        // TODO add your handling code here:
        CartaItemDetalhada informacoes = new CartaItemDetalhada(item);
    }//GEN-LAST:event_btInformacoesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btInformacoes;
    private javax.swing.JButton btUsar;
    private javax.swing.JLabel lbImagem;
    private javax.swing.JLabel lbNome;
    // End of variables declaration//GEN-END:variables
}
