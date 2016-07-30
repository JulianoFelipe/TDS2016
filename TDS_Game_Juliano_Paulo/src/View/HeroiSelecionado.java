/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Criaturas.Elesis;
import Model.Criaturas.Heroi;
import Model.Criaturas.Jogador;
import Model.Habilidades.HabilidadeBase;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author FREE
 */
public class HeroiSelecionado extends javax.swing.JFrame {

    /**
     * Creates new form HeroiSelecionado
     */
    public HeroiSelecionado(Heroi heroi) {
        initComponents();
        
        JPanel carta_heroi = new CartaCriatura(heroi,false);
        pHeroiCartao.setLayout(new FlowLayout(SwingConstants.LEADING,0,0));
        pHeroiCartao.add(carta_heroi);
        
        lbNivel.setText("Nivel : " + heroi.getLevel());
        
        lbVidaMaxima.setText( Integer.toString( heroi.getMaxPontosVida().intValue() ) );
        
        int tamanho_altura = pRolagemHabilidades.getSize().height;
        pRolagemHabilidades.setBackground(Color.DARK_GRAY);
        pRolagemHabilidades.setPreferredSize(new Dimension(0,tamanho_altura));
        pRolagemHabilidades.setLayout(new FlowLayout(SwingConstants.LEADING,10,0));
        
        for (HabilidadeBase habilidade : heroi.getListaDeHabilidades())
        {
            JPanel carta_habilidade = new CartaHabilidade(habilidade);
            pRolagemHabilidades.add(carta_habilidade);
            pRolagemHabilidades.setPreferredSize(new Dimension(pRolagemHabilidades.getPreferredSize().width + carta_habilidade.getPreferredSize().width,tamanho_altura));
        }
        pItemArma.setLayout(new FlowLayout(SwingConstants.LEADING,0,0));
        JPanel panel_arma = new CartaItens(heroi.getArma());
        pItemArma.add(panel_arma);
        
        pItemArmor.setLayout(new FlowLayout(SwingConstants.LEADING,0,0));
        JPanel panel_armor = new CartaItens(heroi.getArmadura());
        pItemArmor.add(panel_armor);
        
        JPanel panel_xp_possuida = new JPanel();
        panel_xp_possuida.setBackground(Color.RED);
        JPanel panel_xp_faltando = new JPanel();
        panel_xp_faltando.setBackground(Color.GREEN);
        int tamanho_total = pProgressoXP.getSize().width;
        int altura = pProgressoXP.getSize().height;
        int tamanho_xp_possuida;
        if (heroi.getXpAtual() >= 0.00)
        {
            tamanho_xp_possuida = new Double(heroi.getXpAtual()*(tamanho_total+0.00)/heroi.getRequerimentoXp()).intValue();
        }
        else
        {
            tamanho_xp_possuida = 0;
        }
        System.out.println("tamanho_");
        
        panel_xp_possuida.setPreferredSize(new Dimension(tamanho_xp_possuida,altura));
        panel_xp_faltando.setPreferredSize(new Dimension(tamanho_total - tamanho_xp_possuida,altura));
        
        pProgressoXP.setLayout(new FlowLayout(SwingConstants.LEADING,0,0));
        pProgressoXP.add(panel_xp_possuida);
        pProgressoXP.add(panel_xp_faltando);
        pProgressoXP.setBackground(Color.PINK);
        pack();
        System.out.println("panel_xp_po = " + panel_xp_possuida.getSize());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pRolagemHabilidades = new javax.swing.JPanel();
        pHeroiCartao = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pProgressoXP = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbNivel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbVidaMaxima = new javax.swing.JLabel();
        pItemArma = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        pItemArmor = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1270, 630));
        setResizable(false);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 0), 10, true));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Habilidades");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        pRolagemHabilidades.setPreferredSize(new java.awt.Dimension(271, 219));

        javax.swing.GroupLayout pRolagemHabilidadesLayout = new javax.swing.GroupLayout(pRolagemHabilidades);
        pRolagemHabilidades.setLayout(pRolagemHabilidadesLayout);
        pRolagemHabilidadesLayout.setHorizontalGroup(
            pRolagemHabilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pRolagemHabilidadesLayout.setVerticalGroup(
            pRolagemHabilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(pRolagemHabilidades);

        pHeroiCartao.setBackground(new java.awt.Color(153, 153, 153));
        pHeroiCartao.setPreferredSize(new java.awt.Dimension(280, 380));

        javax.swing.GroupLayout pHeroiCartaoLayout = new javax.swing.GroupLayout(pHeroiCartao);
        pHeroiCartao.setLayout(pHeroiCartaoLayout);
        pHeroiCartaoLayout.setHorizontalGroup(
            pHeroiCartaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        pHeroiCartaoLayout.setVerticalGroup(
            pHeroiCartaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );

        jLabel2.setText("Inicio Barra XP");

        pProgressoXP.setBackground(new java.awt.Color(204, 0, 153));

        jLabel3.setText("Proximo Nivel");

        lbNivel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbNivel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNivel.setText("Nivel");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel4.setText("Vida :");

        lbVidaMaxima.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbVidaMaxima.setText("jLabel5");

        pItemArma.setBackground(new java.awt.Color(255, 255, 255));
        pItemArma.setPreferredSize(new java.awt.Dimension(165, 369));

        javax.swing.GroupLayout pItemArmaLayout = new javax.swing.GroupLayout(pItemArma);
        pItemArma.setLayout(pItemArmaLayout);
        pItemArmaLayout.setHorizontalGroup(
            pItemArmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 163, Short.MAX_VALUE)
        );
        pItemArmaLayout.setVerticalGroup(
            pItemArmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel5.setText("Armadura");

        pItemArmor.setBackground(new java.awt.Color(204, 204, 0));
        pItemArmor.setPreferredSize(new java.awt.Dimension(165, 369));

        javax.swing.GroupLayout pItemArmorLayout = new javax.swing.GroupLayout(pItemArmor);
        pItemArmor.setLayout(pItemArmorLayout);
        pItemArmorLayout.setHorizontalGroup(
            pItemArmorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );
        pItemArmorLayout.setVerticalGroup(
            pItemArmorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 369, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel6.setText("Arma");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pHeroiCartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbVidaMaxima)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(jLabel6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(pItemArma, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(pItemArmor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel5)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(168, 168, 168)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lbNivel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pProgressoXP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pItemArmor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pItemArma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pHeroiCartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lbVidaMaxima))))
                .addGap(36, 36, 36)
                .addComponent(lbNivel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pProgressoXP, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HeroiSelecionado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HeroiSelecionado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HeroiSelecionado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HeroiSelecionado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Jogador jogador = new Jogador();

                Heroi mc = new Elesis();

                jogador.getLista_de_herois().add(mc);
                
                System.out.println(mc.getRequerimentoXp());
                
                mc.addXP(90.0);
                
                new HeroiSelecionado(mc).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbNivel;
    private javax.swing.JLabel lbVidaMaxima;
    private javax.swing.JPanel pHeroiCartao;
    private javax.swing.JPanel pItemArma;
    private javax.swing.JPanel pItemArmor;
    private javax.swing.JPanel pProgressoXP;
    private javax.swing.JPanel pRolagemHabilidades;
    // End of variables declaration//GEN-END:variables
}