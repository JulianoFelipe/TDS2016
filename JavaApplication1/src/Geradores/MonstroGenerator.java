/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geradores;
import java.util.ArrayList;
import javaapplication1.Monstro;
import java.util.Random;
import javaapplication1.BaseSkill;

/**
 *
 * @author Paulo Henrique
 * @author Juliano Felipe
 */
public class MonstroGenerator {
    /**
     * O numero maximo de skill que um monstro pode ter
     */
    public static final int MAX_SKILL_NUMBER=2;
    
    /**
     * Ao gerar um monstro a chance uma skill se ele conseguir uma a mesma chance para a segunda ate o limite de MAX_SKILL_NUMBER
     */
    public static final int CHANCE_OF_SKILL_ROLL=100;
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
        double speed_formulae = power_level*100 - gerador.nextInt(20) + gerador.nextInt(20);
        double defense_formulae = power_level*5 + gerador.nextDouble()*gerador.nextInt(power_level+5);
        
        int max_dodge = 10;//50%
        int dodge_formulae = gerador.nextInt(max_dodge);
        
        
        
        String nome = random_nome();
        
        Monstro monstro_de_retorno = new Monstro();
        monstro_de_retorno.setMax_hit_points(hp_formulae);
        monstro_de_retorno.setAttack(attack_formulae);
        monstro_de_retorno.setSpeed(speed_formulae);
        monstro_de_retorno.setDefense(defense_formulae);
        monstro_de_retorno.setDodge(dodge_formulae);
        monstro_de_retorno.setNome(nome);
        monstro_de_retorno.reset_temporary_stats();
        monstro_de_retorno.setMax_mana(50*power_level+0.00);
        monstro_de_retorno.setMana_regain(5.00);
        
        int will_get_another_skill = gerador.nextInt(101);
        if (will_get_another_skill <= CHANCE_OF_SKILL_ROLL)
        {
            BaseSkill skill = SkillGenerator.generate_skill();
            skill.setOwner(monstro_de_retorno);
            monstro_de_retorno.getLista_de_habilidades().add(skill);
        }
        
        return(monstro_de_retorno);
    }
}
