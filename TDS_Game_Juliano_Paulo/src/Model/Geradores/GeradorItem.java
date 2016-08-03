/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Geradores;

import Model.Habilidades.HabilidadesPersonalizadas.Fortalecimento;
import Model.Itens.ArmaduraBase;
import Model.Itens.PocaoAumentoStatus;
import Model.Itens.PergaminhoHabilidade;
import Model.Itens.ArmaBase;
import Model.Itens.ItemBase;
import Model.Habilidades.HabilidadeBase;
import Model.Habilidades.HabilidadesPersonalizadas.MordidaVenenosa;
import Model.Habilidades.HabilidadesPersonalizadas.TeiaAranha;
import Model.Itens.Constantes.Pocoes;
import java.util.Random;

/**
 * Gerador de itens aleatorio, por hora todos os itens sao gerados aqui
 *
 */
public class GeradorItem {

    /**
     * Gera uma PocaoAumentoStatus aleatoria
     *
     * @param level Do item a ser retornado.
     * @return      Potion gerada.
     */
    public static PocaoAumentoStatus generateStatusIncreasePotion(int level) {
        Random generator = new Random();
        int tipo_potion = generator.nextInt(PocaoAumentoStatus.MAX_RANDOM_VALOR + 1);
        double potion_strenght = 0.00;
        if (Pocoes.porCodigo(tipo_potion) == Pocoes.Vida)
        {
            potion_strenght = 100 * level;
        }
        else if (Pocoes.porCodigo(tipo_potion) == Pocoes.Velocidade)
        {
            potion_strenght = 5 * level;
        }
        else
        {
            potion_strenght = 20 * level;
        }
        PocaoAumentoStatus retorno = new PocaoAumentoStatus(Pocoes.porCodigo( tipo_potion ), potion_strenght);
        retorno.setAutomaticNome();
        retorno.setValor(500 * level);
        return (retorno);
    }
    
    /**
     * 
     * @param level level do item, quanto maior melhor
     * @return uma arma que aumenta o dano, quanto maior o level da arma maior o aumento
     */
    public static ArmaBase gerarArma(int level)
    {
         ArmaBase retorno = new ArmaBase((level+0.00)/10.00);
         retorno.setNome("Arma");
         retorno.setValor(level*1500);
         return(retorno);
    }
    
    /**
     * 
     * @param level level do item, quanto maior melhor
     * @return uma arma que aumenta o dano, quanto maior o level da arma maior o aumento
     */
    public static ArmaduraBase gerarArmadura(int level)
    {
        ArmaduraBase retorno = new ArmaduraBase((level+0.00)/10.00);
        retorno.setNome("Armadura");
        retorno.setValor(level*1500);
        return(retorno);
    }
    
    /**
     * Gera ou uma arma ou armadura
     * @param level level da arma armadura
     * @return arma ou armadura
     */
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
    
    /**
     * Gera uma pergaminho associado a uma habilidade
     * @return pergaminho de habilidade usado para aprender uma habilidade
     */
    public static ItemBase gerarPergaminho()
    {
        int maiorHabilidade = 2;
        Random gerador = new Random();
        int rolada = gerador.nextInt(maiorHabilidade+1);
        HabilidadeBase habilidade;
        switch (rolada)
        {   
            case 0 :
                habilidade = new MordidaVenenosa();
                break;
            case 1 :
                habilidade = new TeiaAranha();
                break;
            case 2 :
                habilidade = new Fortalecimento();
                break;
            default :
                throw new UnsupportedOperationException();
        }
        ItemBase retorno = new PergaminhoHabilidade(habilidade);
        retorno.setValor(1250);
        return(retorno);
    }
    
}
