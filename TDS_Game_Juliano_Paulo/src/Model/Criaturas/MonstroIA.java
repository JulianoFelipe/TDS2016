/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas;

import Model.Geradores.ArenaBatalha;
import Model.Habilidades.HabilidadeBase;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import utilidades.Math.battle_math;

/**
 * Decide que tipo de acao um monstro tomara dependendo da situacao
 * @author Paulo
 */
public class MonstroIA {
    
    /**
     * 
     * @param criatura criatura que sera filtrado habilidades
     * @return lista com habilidades disponiveis de maior prioridade
     */
    public static List< HabilidadeBase > pegarListaDeHabilidades(CriaturaBase criatura)
    {
        List< HabilidadeBase > retorno = new ArrayList<>();
        int maior_prioridade = 0;
        for (HabilidadeBase habilidade : criatura.getUsableSkillsArray())
        {
            if (retorno.size() == 0)
            {
                maior_prioridade = habilidade.getPrioridade();
                retorno.add(habilidade);
            }
            else
            {
                if (habilidade.getPrioridade() > maior_prioridade)
                {
                    retorno.clear();
                    maior_prioridade = habilidade.getPrioridade();
                    retorno.add(habilidade);
                }
                else if (habilidade.getPrioridade().equals(maior_prioridade))
                {
                    retorno.add(habilidade);
                }
            }
        }
        return(retorno);
    }
    
    public static void decidirAcao(ArenaBatalha arena,CriaturaBase criaturaDecidindo,List< CriaturaBase > aliadosVivos,List< CriaturaBase > inimigosVivos)
    {
        List< HabilidadeBase > habilidadesDisponiveis = pegarListaDeHabilidades(criaturaDecidindo);
        Random gerador = new Random();
        int numeroRandom = 0;
        
        if (inimigosVivos.size() > 0)
        {
            numeroRandom = gerador.nextInt(inimigosVivos.size());
        }
        
        CriaturaBase alvo = inimigosVivos.get(numeroRandom);
        
        if (habilidadesDisponiveis.size()==0)
        {
            //so pode atacar algum alvo
            System.out.println("Monstro " + criaturaDecidindo.getNome() + " atacando " + alvo.getNome() + "!");
            Double dmg = battle_math.calculate_damage(criaturaDecidindo, alvo);
            Double[] vetor_parametros = new Double[5];
            vetor_parametros[0] = dmg;
            vetor_parametros[1] = new Double(0.00);
            vetor_parametros[2] = new Double(0.00);
            vetor_parametros[3] = new Double(0.00);
            vetor_parametros[4] = new Double(0.00);

            arena.modificarCriatura(vetor_parametros,criaturaDecidindo,alvo,true);
        }
        else
        {
            numeroRandom = 0;
            if (habilidadesDisponiveis.size()>0)
            {
                numeroRandom = gerador.nextInt(habilidadesDisponiveis.size());
            }
            HabilidadeBase habilidadeUtilizada = habilidadesDisponiveis.get(numeroRandom);
            
            habilidadeUtilizada.noUso(arena,alvo);
        }
    }
            
}
