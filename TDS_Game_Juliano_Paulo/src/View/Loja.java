/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControleGeral;
import Model.Criaturas.Heroi;
import Model.Criaturas.Jogador;
import Model.Geradores.GeradorDeHeroi;
import Model.Itens.ItemBase;
import Model.Geradores.GeradorItem;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Tela com informações da loja onde o jogador pode vender e comprar itens,personagens,etc
 * @author Paulo
 */
public class Loja extends javax.swing.JFrame {
    ControleGeral controlador;
    /**
     * Jogador acessando a loja
     */
    Jogador jogador = null;
    
    /**
     * Creates new form Loja
     * @param jogador que está jogando.
     * @param controle interno.
     */
    public Loja(Jogador jogador,ControleGeral controle) {
        initComponents();
        controlador = controle;
        this.jogador = jogador;
        atualizarComponentes();
        atualizarAbaDeVendas();
        pack();
        ViewGlobal.centralizarJanela(this);
        setVisible(true);
    }

    /**
     * Atualizar valores de acordo com parametros dados
     */
    private void atualizarComponentes()
    {
        int nivel_arma = (Integer)sNivelArma.getValue();
        lbCustoArma.setText(new Integer(nivel_arma*5000).toString());
        
        int nivel_armadura = (Integer)sNivelArmadura.getValue();
        lbCustoArmadura.setText(new Integer(nivel_armadura*5000).toString());
        
        if (jogador != null)
        {
            lbSaldoJogador.setText(jogador.getGold().toString());
        }
    }
    
    /**
     * Preenche barra de vendas com itens do inventario do jogador
     */
    private void atualizarAbaDeVendas()
    {
        int numero_de_itens = jogador.getInventario().size();
        pRolagem.setLayout(new FlowLayout(SwingConstants.LEADING,0,0));
        pRolagem.removeAll();
        pRolagem.revalidate();
        pRolagem.repaint();
        
        GridBagConstraints g = new GridBagConstraints();
        
        //CartaItens tem dimensao 165 x 369
        //Adicionar um label com dimensao 165 x ?
        //Adicionar um botao com dimensao 165 x ?
        //a altura dependera da fonte
        pRolagem.setPreferredSize(new Dimension(165*numero_de_itens+15,469+15));
        for (int i=0;i<numero_de_itens;i++)
        {
            ItemBase itemConsiderado = jogador.getInventario().get(i);
            
            JPanel panel_principal = new JPanel();
            panel_principal.setLayout(new GridBagLayout());
            
            CartaItens carta_item = new CartaItens(itemConsiderado,null,false);
            g.gridx = 0 + i*165;
            g.gridwidth = 165;
            g.gridy = 0;
            g.gridheight = 369;
            panel_principal.add(carta_item,g);
            carta_item.mudarEstadoDoBotao(false);
            
            JLabel label = new JLabel(itemConsiderado.getValor().toString());
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setPreferredSize(new Dimension(165,50));
            g.gridx = 0 + i*165;
            g.gridwidth = 165;
            g.gridy = 369;
            g.gridheight = 50;
            panel_principal.add(label,g);
            
            JButton btVender = new JButton("VENDER");
            btVender.setName(Integer.toString(i));
            btVender.setPreferredSize(new Dimension(165,50));
            g.gridx = 0 + i*165;
            g.gridwidth = 165;
            g.gridy = 419;
            g.gridheight = 50;
            panel_principal.add(btVender,g);
            btVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                    vender(jogador.getInventario().get(Integer.parseInt(btVender.getName())));
                }
            });
            
            pRolagem.add(panel_principal);
            
        }
        
    }
    
    private void vender(ItemBase item)
    {
        if (jogador!=null && item!=null)
        {
            jogador.removeItem(item);
            jogador.addGold(item.getValor());
            atualizarComponentes();
            atualizarAbaDeVendas();
            JOptionPane.showMessageDialog(this, "Item vendido!");
            
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbSaldoJogador = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        lbCustoHeroi = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btComprarPocao = new javax.swing.JButton();
        lbCustoPocao = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        sNivelArma = new javax.swing.JSpinner();
        lbCustoArma = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btComprarArma = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lbCustoArmadura = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btComprarArmaduras = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        sNivelArmadura = new javax.swing.JSpinner();
        jPanel6 = new javax.swing.JPanel();
        lbCustoHabilidade = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btComprarHabilidade = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        sPane = new javax.swing.JScrollPane();
        pRolagem = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 51), 10));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Saldo :");

        lbSaldoJogador.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lbSaldoJogador.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbSaldoJogador.setText("Saldoaki");

        jTabbedPane1.setBackground(new java.awt.Color(51, 51, 255));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jButton1.setText("Comprar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lbCustoHeroi.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lbCustoHeroi.setText("25000");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Imagens/coin_icon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(279, 279, 279)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lbCustoHeroi)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(450, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(120, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lbCustoHeroi)
                        .addGap(39, 39, 39)))
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111))
        );

        jTabbedPane1.addTab("Herois", jPanel2);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        btComprarPocao.setText("Comprar");
        btComprarPocao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btComprarPocaoActionPerformed(evt);
            }
        });

        lbCustoPocao.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lbCustoPocao.setText("2500");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Imagens/coin_icon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(lbCustoPocao)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addComponent(btComprarPocao, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(454, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(lbCustoPocao)
                        .addGap(26, 26, 26))
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btComprarPocao, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
        );

        jTabbedPane1.addTab("Pocoes", jPanel3);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        sNivelArma.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        sNivelArma.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        sNivelArma.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sNivelArmaStateChanged(evt);
            }
        });

        lbCustoArma.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lbCustoArma.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCustoArma.setText("2500");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Imagens/coin_icon.png"))); // NOI18N

        btComprarArma.setText("Comprar");
        btComprarArma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btComprarArmaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setText("Nivel da Arma");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel10))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(lbCustoArma, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 273, Short.MAX_VALUE)
                        .addComponent(sNivelArma, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(88, 88, 88))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(263, 263, 263)
                .addComponent(btComprarArma, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(114, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(sNivelArma, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(259, 259, 259))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(lbCustoArma)
                                .addGap(37, 37, 37))
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btComprarArma, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jTabbedPane1.addTab("Armas", jPanel4);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        lbCustoArmadura.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lbCustoArmadura.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCustoArmadura.setText("2500");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Imagens/coin_icon.png"))); // NOI18N

        btComprarArmaduras.setText("Comprar");
        btComprarArmaduras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btComprarArmadurasActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel13.setText("Nivel da Armadura");

        sNivelArmadura.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        sNivelArmadura.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        sNivelArmadura.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sNivelArmaduraStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btComprarArmaduras, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(221, 221, 221)
                .addComponent(sNivelArmadura, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lbCustoArmadura, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(73, 73, 73))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbCustoArmadura, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(7, 7, 7)
                        .addComponent(sNivelArmadura, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel11)
                        .addGap(38, 38, 38)
                        .addComponent(btComprarArmaduras, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Armaduras", jPanel5);

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        lbCustoHabilidade.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lbCustoHabilidade.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCustoHabilidade.setText("5000");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Imagens/coin_icon.png"))); // NOI18N

        btComprarHabilidade.setText("Comprar");
        btComprarHabilidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btComprarHabilidadeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lbCustoHabilidade, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(btComprarHabilidade, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(485, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(lbCustoHabilidade))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jLabel12)))
                .addGap(18, 18, 18)
                .addComponent(btComprarHabilidade, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Habilidades", jPanel6);

        pRolagem.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pRolagemLayout = new javax.swing.GroupLayout(pRolagem);
        pRolagem.setLayout(pRolagemLayout);
        pRolagemLayout.setHorizontalGroup(
            pRolagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 970, Short.MAX_VALUE)
        );
        pRolagemLayout.setVerticalGroup(
            pRolagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 449, Short.MAX_VALUE)
        );

        sPane.setViewportView(pRolagem);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sPane, javax.swing.GroupLayout.PREFERRED_SIZE, 972, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(sPane)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Vender", jPanel7);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Imagens/coin_icon.png"))); // NOI18N

        btSair.setText("Sair");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbSaldoJogador, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(521, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addGap(307, 307, 307)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbSaldoJogador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLabel5)
                    .addContainerGap(471, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sNivelArmaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sNivelArmaStateChanged
        // TODO add your handling code here:
        atualizarComponentes();
    }//GEN-LAST:event_sNivelArmaStateChanged

    private void sNivelArmaduraStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sNivelArmaduraStateChanged
        // TODO add your handling code here:
        atualizarComponentes();
    }//GEN-LAST:event_sNivelArmaduraStateChanged

    private void btComprarPocaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btComprarPocaoActionPerformed
        // TODO add your handling code here:
        ItemBase pocao = GeradorItem.generateStatusIncreasePotion(1);
        int valor = Integer.parseInt(lbCustoPocao.getText());
        tentarComprar(pocao,valor);
    }//GEN-LAST:event_btComprarPocaoActionPerformed

    private void btComprarArmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btComprarArmaActionPerformed
        // TODO add your handling code here:
        int nivel_arma = (Integer)sNivelArma.getValue();
        ItemBase arma = GeradorItem.gerarArma(nivel_arma);
        int valor = Integer.parseInt(lbCustoArma.getText());
        tentarComprar(arma,valor);
    }//GEN-LAST:event_btComprarArmaActionPerformed

    private void btComprarArmadurasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btComprarArmadurasActionPerformed
        // TODO add your handling code here:
        int nivel_armadura = (Integer)sNivelArmadura.getValue();
        ItemBase armadura = GeradorItem.gerarArmadura(nivel_armadura);
        int valor = Integer.parseInt(lbCustoArmadura.getText());
        tentarComprar(armadura,valor);
    }//GEN-LAST:event_btComprarArmadurasActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        // TODO add your handling code here:
        dispose();
        controlador.setFrameParaExibir( FrameExibido.TELA_INICIAL );
        controlador.criarProximoFrame();
    }//GEN-LAST:event_btSairActionPerformed

    private void btComprarHabilidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btComprarHabilidadeActionPerformed
        // TODO add your handling code here:
        ItemBase pergaminho = GeradorItem.gerarPergaminho();
        int valor = Integer.parseInt(lbCustoHabilidade.getText());
        tentarComprar(pergaminho,valor);
    }//GEN-LAST:event_btComprarHabilidadeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Heroi heroiComprado = GeradorDeHeroi.gerarHeroi();
        int valor = Integer.parseInt(lbCustoHeroi.getText());
        tentarComprar(heroiComprado,valor);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tentarComprar(Heroi heroi,int valor)
    {
        if (jogador != null)
        {
            if (jogador.getGold() >= valor)
            {
                StringBuilder mensagem = new StringBuilder();
                mensagem.append("Voce adquiriu o heroi ").append(heroi.getNome());
                JOptionPane.showMessageDialog(this, mensagem.toString());
                jogador.setGold(jogador.getGold()-valor);
                heroi.setJogador(jogador);
                jogador.getLista_de_herois().add(heroi);
            }
            else
            {
                StringBuilder mensagem = new StringBuilder();
                mensagem.append("Voce nao tem moedas suficiente!");
                JOptionPane.showMessageDialog(this, mensagem.toString());
            }
            atualizarComponentes();
            atualizarAbaDeVendas();
        }
    }
    
    private void tentarComprar(ItemBase item,int valor)
    {
        if (jogador != null)
        {
            if (jogador.getGold() >= valor)
            {
                StringBuilder mensagem = new StringBuilder();
                mensagem.append("Voce comprou um ").append(item.getNome());
                JOptionPane.showMessageDialog(this, mensagem.toString());
                jogador.setGold(jogador.getGold()-valor);
                jogador.getInventario().add(item);
            }
            else
            {
                StringBuilder mensagem = new StringBuilder();
                mensagem.append("Voce nao tem moedas suficiente!");
                JOptionPane.showMessageDialog(this, mensagem.toString());
            }
            atualizarComponentes();
            atualizarAbaDeVendas();
        }
    }
    
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
            java.util.logging.Logger.getLogger(Loja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Loja(null,null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btComprarArma;
    private javax.swing.JButton btComprarArmaduras;
    private javax.swing.JButton btComprarHabilidade;
    private javax.swing.JButton btComprarPocao;
    private javax.swing.JButton btSair;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbCustoArma;
    private javax.swing.JLabel lbCustoArmadura;
    private javax.swing.JLabel lbCustoHabilidade;
    private javax.swing.JLabel lbCustoHeroi;
    private javax.swing.JLabel lbCustoPocao;
    private javax.swing.JLabel lbSaldoJogador;
    private javax.swing.JPanel pRolagem;
    private javax.swing.JSpinner sNivelArma;
    private javax.swing.JSpinner sNivelArmadura;
    private javax.swing.JScrollPane sPane;
    // End of variables declaration//GEN-END:variables
}
