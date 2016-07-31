/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Geradores;

import Model.Itens.ArmaduraBase;
import Model.Itens.PocaoAumentoStatus;
import Model.Itens.PergaminhoHabilidade;
import Model.Itens.ArmaBase;
import Model.Itens.ItemBase;
import Model.Habilidades.HabilidadeBase;
import Model.Itens.Constantes.Pocoes;
import java.util.Random;
import jdk.nashorn.internal.codegen.CompilerConstants;

/**
 * Gerador de itens aleatorio, por hora todos os itens sao gerados aqui
 *
 */
public class GeradorItem {

    /**
     * constante que indica chance de um drop ocorrer por monstro morto ao final
     * de uma batalha
     */
    public static final int CHANCE_OF_DROP = 100;

    /**
     * Gera uma PocaoAumentoStatus aleatoria
     *
     * @param level Do item a ser retornado.
     * @return      Potion gerada.
     */
    public static PocaoAumentoStatus generateStatusIncreasePotion(int level) {
        Random generator = new Random();
        int tipo_potion = generator.nextInt(PocaoAumentoStatus.MAX_RANDOM_VALOR + 1);
        //por enquanto potion strenght eh fixo em 10
        double potion_strenght = 10.00 * level;
        PocaoAumentoStatus retorno = new PocaoAumentoStatus(Pocoes.porCodigo( tipo_potion ), potion_strenght);
        retorno.setAutomaticNome();
        return (retorno);
    }
    
    public static ArmaBase gerarArma(int level)
    {
         return(new ArmaBase(1 + (level+0.00/10.00)));
    }
    
    public static ArmaduraBase gerarArmadura(int level)
    {
        return(new ArmaduraBase(1 + (level+0.00)/10.00));
    }
    
    public static ItemBase gerarArmaArmadura(int level)
    {
        Random generator = new Random();
        int rolada = generator.nextInt(100);
        if (rolada>49)
        {
            return(gerarArmadura(level));
        }
        else
        {
            return(gerarArma(level));
        }
    }
    
}
