/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geradores;
import java.util.Random;
import javaapplication1.BaseSkill;
/**
 *  Gerador de Skills aleatorio, todas as skills sao geradas usando essa classe
 * 
 */
public class SkillGenerator {
    /**
     * Indica id da skill
     */
    private static int sequencial = 0;
    
    /**
     * Gera uma skill aleatoria
     * @return skill gerada
     */
    public static BaseSkill generate_skill()
    {
        Random generator = new Random();
        BaseSkill retorno = new BaseSkill();
        
        retorno.setNome("Skill_"+sequencial);
        sequencial++;
        retorno.setMana(10.00);
        retorno.setCooldown_time(generator.nextInt(5));
       
        retorno.setEffect(EffectGenerator.getNewEffect());
        
        if (retorno.getEffect().getTipo().equals("Ofensivo"))
        {
            retorno.setTipo(new String("Ofensivo"));
        }
        else if (retorno.getEffect().getTipo().equals("Defensivo"))
        {
            retorno.setTipo(new String("Defensivo"));
        }
        else
        {
            System.out.println("Misstyph de tipo, tipo != Ofensivo e tipo != Defensivo");
        }
        return(retorno);
    }
}
