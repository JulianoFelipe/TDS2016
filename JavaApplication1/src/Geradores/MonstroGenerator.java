/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geradores;
import java.util.ArrayList;
import javaapplication1.Monstro;
import java.util.Random;

/**
 *
 * @author FREE
 */
public class MonstroGenerator {
    final static int MAX_ELEMENTS=4;//aumentar enquando aumentar o numero de elementos
    public static String random_nome()
    {
        Random gerador = new Random();
        StringBuilder nome = new StringBuilder();
        for (int i=0;i<gerador.nextInt(20)+2;i++)
        {
            nome.append(gerador.nextInt(255));
        }
        return nome.toString();
    }
    
    public static String getElemento()
    {
        ArrayList< String > elementos_list = new ArrayList<>();
        Random gerador = new Random();
        
        elementos_list.add("Fogo");
        elementos_list.add("Agua");
        elementos_list.add("Vento");
        elementos_list.add("Terra");
       
        return(elementos_list.get(gerador.nextInt(MAX_ELEMENTS)));
    }
    
    public static Monstro gerarMonstro(int power_level)
    {
        //como gerar excecao para power_level <= 0 ?
        //gera monstro sem considerar : mana,range,stamina e skills. fazer dps se sobrar tempo e vontade.
        Random gerador = new Random();
        double hp_formulae = power_level*10 + gerador.nextDouble()*gerador.nextInt(power_level+5);
        double attack_formulae = power_level*5 + gerador.nextDouble()*gerador.nextInt(power_level+5);
        double speed_formulae = power_level*100;
        double defense_formulae = power_level*5 + gerador.nextDouble()*gerador.nextInt(power_level+5);
        
        int max_dodge = 50;//50%
        int dodge_formulae = gerador.nextInt(max_dodge);
        
        
        
        String element_formulae = getElemento();
        String nome = random_nome();
        
        Monstro monstro_de_retorno = new Monstro();
        monstro_de_retorno.setMax_hit_points(hp_formulae);
        monstro_de_retorno.setAttack(attack_formulae);
        monstro_de_retorno.setSpeed(speed_formulae);
        monstro_de_retorno.setDefense(defense_formulae);
        monstro_de_retorno.setDodge(dodge_formulae);
        monstro_de_retorno.setNome(nome);
        monstro_de_retorno.setElement(element_formulae);
        monstro_de_retorno.reset_temporary_stats();
        
        
        return(monstro_de_retorno);
    }
}
