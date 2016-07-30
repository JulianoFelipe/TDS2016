/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControleArena;
import Model.Criaturas.CriaturaBase;
import Model.Criaturas.Heroi;
import Model.Criaturas.Monstro;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 *
 * @author FREE
 */
public class BatalhaFrame extends JFrame{
    List< CriaturaBase > lista_de_criaturas;
    ControleArena control;
    JScrollPane scroll_panel;
    public BatalhaFrame(List< CriaturaBase > lista,ControleArena control) throws IOException {
        
        lista_de_criaturas = lista;
        this.control = control;
        
        //25 + 200 + 25 + 200 + 25 + 200 + 25 + 200 + 25 = 25*5 + 200*4 = 125+800 = 925 = tamanho horizontal
        this.setLayout(new GridBagLayout());
        this.setResizable(false);
        
        GridBagConstraints c = new GridBagConstraints();
        
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.BLACK);
        panel1.setPreferredSize(new Dimension(1260,25));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1260;
        c.gridheight = 25;
        this.add(panel1,c);
        
        /*
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.BLACK);
        panel2.setPreferredSize(new Dimension(25,380));
        c.gridx = 0;
        c.gridy = 25;
        c.gridwidth = 25;
        c.gridheight = 380;
        this.add(panel2,c);
        */
        
        JPanel main_panel = new JPanel();
        main_panel.setBackground(Color.RED);
        //main_panel.setPreferredSize(new Dimension(1245,380));
        scroll_panel = new JScrollPane(main_panel);
        scroll_panel.setPreferredSize(new Dimension(1260,395));
        scroll_panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll_panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        c.gridx = 0;
        c.gridy = 25;
        c.gridwidth = 1260;
        c.gridheight = 395;
        this.add(scroll_panel,c);
        
        main_panel.setLayout(new FlowLayout(0,0,0));
        
        JPanel panel4_2 = new JPanel();
        panel4_2.setBackground(Color.BLACK);
        panel4_2.setPreferredSize(new Dimension(25,380));
        main_panel.add(panel4_2);
        
        for (int i = 0;i<lista.size()||i<4;i++)
        {
            if (i >= lista_de_criaturas.size())
            {
                //System.out.println("label!");
                JLabel x_label = new JLabel();
                x_label.setIcon(new ImageIcon( ImageIO.read(new File(getClass().getResource("/View/Imagens/x_icon.png").getFile() ) ) ) );
                x_label.setPreferredSize(new Dimension(280,380));
                main_panel.add(x_label);
            }
            else
            {
                JPanel panel3 = new CartaCriatura(lista_de_criaturas.get(i),false);
                panel3.setPreferredSize(new Dimension(280,380));
                main_panel.add(panel3);
            }
            JPanel panel4 = new JPanel();
            panel4.setBackground(Color.BLACK);
            panel4.setPreferredSize(new Dimension(25,380));
            main_panel.add(panel4);
        }
        
        JPanel panel5 = new JPanel();
        panel5.setBackground(Color.BLACK);
        panel5.setPreferredSize(new Dimension(1260,25));
        c.gridx = 0;
        c.gridy = 420;
        c.gridwidth = 1260;
        c.gridheight = 25;
        this.add(panel5,c);
        
        JPanel panel6 = new JPanel();
        panel6.setBackground(Color.BLACK);
        panel6.setPreferredSize(new Dimension(330,25));
        c.gridx = 0;
        c.gridy = 445;
        c.gridwidth = 330;
        c.gridheight = 25;
        this.add(panel6,c);
        
        JButton btAction = new JButton("Agir");
        btAction.setPreferredSize(new Dimension(585,25));
        btAction.setEnabled(false);
        if (lista_de_criaturas.size() > 0)
        {
           if (lista_de_criaturas.get(0) instanceof Heroi && !lista_de_criaturas.get(0).getEstaAtordoado())
           {
               btAction.setEnabled(true);
           }
        }
        btAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btActionActionPerformed(evt);
            }
        });
        c.gridx = 330;
        c.gridy = 445;
        c.gridwidth = 585;
        c.gridheight = 25;
        this.add(btAction,c);
        
        JPanel panel7 = new JPanel();
        panel7.setBackground(Color.BLACK);
        panel7.setPreferredSize(new Dimension(345,25));
        c.gridx = 915;
        c.gridy = 445;
        c.gridwidth = 345;
        c.gridheight = 25;
        this.add(panel7,c);
        
        JPanel panel8 = new JPanel();
        panel8.setBackground(Color.BLACK);
        panel8.setPreferredSize(new Dimension(1260,25));
        c.gridx = 0;
        c.gridy = 470;
        c.gridwidth = 1260;
        c.gridheight = 25;
        this.add(panel8,c);
        this.pack();
        //System.out.println("dim horizontal = " + scroll_panel.getHorizontalScrollBar().getSize());
        //System.out.println("dim vertical = " + scroll_panel.getVerticalScrollBar().getSize());
        ViewGlobal.centralizarJanela(this);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
    }
    
    private void btActionActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (control!=null)
        {
            this.dispose();
            control.frame_a_exibir = FrameExibido.ESCOLHER_ACAO;
            control.criarProximoFrame();
        }
    }
    
    public static void main(String args[])
    {   
        try {
            BatalhaFrame frame = new BatalhaFrame(new ArrayList<>(),null);
        } catch (IOException ex) {
            Logger.getLogger(BatalhaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
