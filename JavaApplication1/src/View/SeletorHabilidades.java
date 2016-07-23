/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Habilidades.BaseSkill;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author FREE
 */
public class SeletorHabilidades extends JFrame{
    SeletorHabilidades(List< BaseSkill > lista_de_skills)
    {
        this.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        
        JPanel preenchedor_borda_superior = new JPanel();
        preenchedor_borda_superior.setBackground(Color.RED);
        preenchedor_borda_superior.setPreferredSize(new Dimension(900,20));
        
        g.gridx = 0;
        g.gridwidth = 90;
        g.gridy = 0;
        g.gridheight = 2;
        add(preenchedor_borda_superior,g);
        
        JPanel preenchedor_vertical = new JPanel();
        preenchedor_vertical.setBackground(Color.RED);
        preenchedor_vertical.setPreferredSize(new Dimension(20,370));
        
        g.gridx = 0;
        g.gridwidth = 2;
        g.gridy = 2;
        g.gridheight = 37;
        add(preenchedor_vertical,g);
        
        for (int i=0;i<4;i++)
        {
            JButton btSelecionar = new JButton("Selecionar");
            btSelecionar.setName( Integer.toString(i) );
            btSelecionar.setPreferredSize(new Dimension(200,70));
            
            g.gridx = 2 + 22*i;
            g.gridwidth = 20;
            g.gridy = 32;
            g.gridheight = 7;
            add(btSelecionar,g);
            
            CartaHabilidade carta_skill;
            if (i >= lista_de_skills.size())
            {
                carta_skill = new CartaHabilidade(null,this);
                btSelecionar.setEnabled(false);
            }
            else
            {
                carta_skill = new CartaHabilidade(lista_de_skills.get(i),this);
            }
            carta_skill.setPreferredSize(new Dimension(200,300));
            
            g.gridx = 2 + 22*i;
            g.gridwidth = 20;
            g.gridy = 2;
            g.gridheight = 30;
            add(carta_skill,g);
            
            JPanel preenchedor_vertical2 = new JPanel();
            preenchedor_vertical2.setBackground(Color.RED);
            preenchedor_vertical2.setPreferredSize(new Dimension(20,370));
            
            g.gridx = 2 + 22*i + 20;
            g.gridwidth = 2;
            g.gridy = 2;
            g.gridheight = 37;
            add(preenchedor_vertical2,g);
        }
        JPanel preenchedor_borda_inferior = new JPanel();
        preenchedor_borda_inferior.setBackground(Color.RED);
        preenchedor_borda_inferior.setPreferredSize(new Dimension(900,20));
        
        g.gridx = 0;
        g.gridwidth = 90;
        g.gridy = 39;
        g.gridheight = 2;
        add(preenchedor_borda_inferior,g);
        
    }
    
    public static void main(String args[])
    {
        BaseSkill skill_1 = new BaseSkill();
        skill_1.setNome("skill_1");
        
        BaseSkill skill_2 = new BaseSkill();
        skill_2.setNome("skill_2");
        
        List< BaseSkill > lista = new ArrayList<>();
        lista.add(skill_1);
        lista.add(skill_2);
        
        SeletorHabilidades teste = new SeletorHabilidades(lista);
        teste.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        teste.pack();
        teste.setVisible(true);
    }
}
