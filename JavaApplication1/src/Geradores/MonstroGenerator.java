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
 * @author Paulo Henrique
 * @author Juliano Felipe
 */
public class MonstroGenerator {
    /**
     * Gera um nome aleatório para o monstro.
     * IDEIA: Um monte de nomes aleatórios, fazer 
     * loops para concatenar nomes aleatórios em ordem aleatória.
     * @return String de nome para um monstro.
     */
    public static String random_nome()
    {
        Random gerador = new Random();
        StringBuilder nome = new StringBuilder();
        for (int i=0;i<gerador.nextInt(20)+2;i++)
        {
            nome.append(gerador.nextInt(255));
        }
        return ("Monstro_"+Monstro.getNumero_monstros());
    }
    
    /**
     * Gera elementos aleatórios.
     * @return um elemento da tabela de
     *         elementos gerada.
     */
    public static String getElemento()
    {
        ArrayList< String > elementos_list = new ArrayList<>();
        Random gerador = new Random();
        
        elementos_list.add("Fogo");
        elementos_list.add("Agua");
        elementos_list.add("Vento");
        elementos_list.add("Terra");
       
        return(elementos_list.get(gerador.nextInt(elementos_list.size())));
    }
    
    /**
     * Gera um montstro aleatório.
     * @param power_level Escala de poder do monstro a ser gerado.
     * @return            Monstro gerado.
     */
    public static Monstro gerarMonstro(int power_level)
    {
        //como gerar excecao para power_level <= 0 ?
        //gera monstro sem considerar : mana,range,stamina e skills. fazer dps se sobrar tempo e vontade.
        Random gerador = new Random();
        double hp_formulae = power_level*50 + gerador.nextDouble()*gerador.nextInt(power_level+5);
        double attack_formulae = power_level*15 + gerador.nextDouble()*gerador.nextInt(power_level+5);
        double speed_formulae = power_level*100;
        double defense_formulae = power_level*5 + gerador.nextDouble()*gerador.nextInt(power_level+5);
        
        int max_dodge = 10;//50%
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
