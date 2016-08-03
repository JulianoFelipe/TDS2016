/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Geradores;

import Model.Criaturas.Monstro;
import Model.Criaturas.MonstrosPersonalizados.Aranha;
import Model.Criaturas.MonstrosPersonalizados.Ave;
import Model.Criaturas.MonstrosPersonalizados.Barbaro;
import Model.Criaturas.MonstrosPersonalizados.Cobra;
import Model.Criaturas.MonstrosPersonalizados.HomemLeao;
import java.util.Random;
import Model.Habilidades.HabilidadeBase;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gerador de monstros
 *
 * @author Paulo Henrique
 * @author Juliano Felipe
 */
public class GeradorMonstro {

    /**
     * O numero maximo de skill que um monstro pode ter
     */
    public static final int MAX_SKILL_NUMBER = 2;

    /**
     * Ao gerar um monstro a chance uma skill se ele conseguir uma a mesma
     * chance para a segunda ate o limite de MAX_SKILL_NUMBER
     */
    public static final int CHANCE_OF_SKILL_ROLL = 100;

    /**
     * Por enquanto o nome sempre sera Monstro_+Id_do_monstro.
     *
     * @return String de nome para um monstro.
     */
    public static String random_nome() {
        Random gerador = new Random();
        StringBuilder nome = new StringBuilder();
        for (int i = 0; i < gerador.nextInt(20) + 2; i++) {
            nome.append(gerador.nextInt(255));
        }
        return ("Monstro_" + Monstro.getNumero_monstros());
    }
    
        /**
     * Seleciona um nome aleatório do gerador.
     * @param type Tipo a ser gerado. 0 para Random normal,
     *             1 para melhorado e 2 para especial.
     * @return     Nome gerado.
     */
    public static String generated_nome (int type){
        GeradorNomeMonstro gen = new GeradorNomeMonstro(3, false, true);
        String generated = null;
        
        switch (type){ //Adicionado aqui por enquanto, depois, fazer algo para passar isso em janela para o usuário
            case 0:
                    try {
                       generated = gen.generateRandomName(true);
                    } catch (IOException ex) {
                       Logger.getLogger(GeradorMonstro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                break;
            case 1:
                    try {
                       generated = gen.generateImprovedName(true);
                    } catch (IOException ex) {
                       Logger.getLogger(GeradorMonstro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                break;
            case 2:
                    try {
                       generated = gen.generateSpecialName(false);
                    } catch (IOException ex) {
                       Logger.getLogger(GeradorMonstro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                break;
            default:
                throw new IllegalArgumentException("Tipo inesperado.");
        }
        
        return generated;
    }

    /**
     * Gera um montstro aleatorio dos disponiveis do andar 1 com exceção do chefe
     *
     * @param level Escala de poder do monstro a ser gerado.
     * @return Monstro gerado.
     */
    public static Monstro gerarMonstro(int level,int andar) {
        Random gerador = new Random();
        Monstro monstroDeRetorno = null;
         
        if (andar == 1)
        {
            int numeroRandom = gerador.nextInt(3);
            switch (numeroRandom)
            {
                case 0 : 
                    monstroDeRetorno = new Aranha(level);
                    break;
                case 1 :
                    monstroDeRetorno = new Cobra(level);
                    break;
                case 2 :
                    monstroDeRetorno = new Ave(level);
                    break;
            }
        }
        else if (andar == 2)
        {
            int numeroRandom = gerador.nextInt(3);
            switch (numeroRandom)
            {
                case 0 : 
                    monstroDeRetorno = new Barbaro();
                    break;
                case 1 :
                    monstroDeRetorno = new HomemLeao();
                    break;
                case 2 :
                    monstroDeRetorno = new Ave(level);
                    break;
            }
        }
        

        return (monstroDeRetorno);
    }
    
}
