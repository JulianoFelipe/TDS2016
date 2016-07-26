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

/**
 *
 * @author FREE
 */
public class BatalhaFrame extends JFrame{
    List< CriaturaBase > lista_de_criaturas;
    ControleArena control;
    public BatalhaFrame(List< CriaturaBase > lista,ControleArena control) throws IOException {
        
        lista_de_criaturas = lista;
        this.control = control;
        
        //25 + 200 + 25 + 200 + 25 + 200 + 25 + 200 + 25 = 25*5 + 200*4 = 125+800 = 925 = tamanho horizontal
        this.setLayout(new GridBagLayout());
        this.setResizable(false);
        
        GridBagConstraints c = new GridBagConstraints();
        
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.BLACK);
        panel1.setPreferredSize(new Dimension(925,25));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 37;
        c.gridheight = 1;
        this.add(panel1,c);
        
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.BLACK);
        panel2.setPreferredSize(new Dimension(25,400));
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 16;
        this.add(panel2,c);
        
        for (int i = 0;i<4;i++)
        {
            if (i >= lista_de_criaturas.size())
            {
                //System.out.println("label!");
                JLabel x_label = new JLabel();
                x_label.setIcon(new ImageIcon( ImageIO.read(new File(getClass().getResource("/View/Imagens/x_icon.png").getFile() ) ) ) );
                x_label.setPreferredSize(new Dimension(200,400));
                c.gridx = 1+i*9;
                c.gridy = 1;
                c.gridwidth = 8;
                c.gridheight = 16;
                this.add(x_label,c);
            }
            else
            {
                JPanel panel3 = new CartaCriatura(lista_de_criaturas.get(i));
                panel3.setPreferredSize(new Dimension(200,400));
                c.gridx = 1+i*9;
                c.gridy = 1;
                c.gridwidth = 8;
                c.gridheight = 16;
                this.add(panel3,c);
            }
            JPanel panel4 = new JPanel();
            panel4.setBackground(Color.BLACK);
            panel4.setPreferredSize(new Dimension(25,400));
            c.gridx = 9+i*9;
            c.gridy = 1;
            c.gridwidth = 1;
            c.gridheight = 16;
            this.add(panel4,c);
        }
        
        JPanel panel5 = new JPanel();
        panel5.setBackground(Color.BLACK);
        panel5.setPreferredSize(new Dimension(925,25));
        c.gridx = 0;
        c.gridy = 17;
        c.gridwidth = 37;
        c.gridheight = 1;
        this.add(panel5,c);
        
        JPanel panel6 = new JPanel();
        panel6.setBackground(Color.BLACK);
        panel6.setPreferredSize(new Dimension(250,25));
        c.gridx = 0;
        c.gridy = 18;
        c.gridwidth = 10;
        c.gridheight = 1;
        this.add(panel6,c);
        
        JButton btAction = new JButton("Agir");
        btAction.setPreferredSize(new Dimension(425,25));
        btAction.setEnabled(false);
        if (lista_de_criaturas.size() > 0)
        {
           if (lista_de_criaturas.get(0) instanceof Heroi)
           {
               btAction.setEnabled(true);
           }
        }
        btAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btActionActionPerformed(evt);
            }
        });
        c.gridx = 10;
        c.gridy = 18;
        c.gridwidth = 17;
        c.gridheight = 1;
        this.add(btAction,c);
        
        JPanel panel7 = new JPanel();
        panel7.setBackground(Color.BLACK);
        panel7.setPreferredSize(new Dimension(250,25));
        c.gridx = 27;
        c.gridy = 18;
        c.gridwidth = 10;
        c.gridheight = 1;
        this.add(panel7,c);
        
        JPanel panel8 = new JPanel();
        panel8.setBackground(Color.BLACK);
        panel8.setPreferredSize(new Dimension(925,25));
        c.gridx = 0;
        c.gridy = 19;
        c.gridwidth = 37;
        c.gridheight = 1;
        this.add(panel8,c);
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        //this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
    }
    
    private void btActionActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (control!=null)
        {
            EscolhaFrame frame = new EscolhaFrame(control);
            frame.setVisible(true);
            this.dispose();
        }
    }
    
    public static void main(String args[])
    {   
        try {
            BatalhaFrame frame = new BatalhaFrame(null,null);
        } catch (IOException ex) {
            Logger.getLogger(BatalhaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
