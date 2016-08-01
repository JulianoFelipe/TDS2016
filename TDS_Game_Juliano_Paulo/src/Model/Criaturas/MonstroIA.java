/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas;

import Controller.ControleArena;
import Model.Acao;
import Model.Geradores.ArenaBatalha;
import Model.Habilidades.HabilidadeBase;
import View.FrameExibido;
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
    private static List< HabilidadeBase > pegarListaDeHabilidades(List< HabilidadeBase > opcoes)
    {
        List< HabilidadeBase > retorno = new ArrayList<>();
        int maior_prioridade = 0;
        for (HabilidadeBase habilidade : opcoes)
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
    
    /**
     * 
     * @return Monstro com a menor quantidade de vida proporcional, ou seja o monstro que tomou mais dano proporcional a sua vida, se dois tiverem a mesma vida retorna o primeiro apenas
     */
    private static CriaturaBase pegarMonstroComMenorVida(List< CriaturaBase > opcoes)
    {
        CriaturaBase retorno = null;
        Integer maiorVidaPerdida = 0;
        for (CriaturaBase criatura : opcoes)
        {
            if (retorno == null)
            {
                retorno = criatura;
                maiorVidaPerdida = new Double((1 - (criatura.getPontosVida()/criatura.getMaxPontosVida()))*100).intValue();
            }
            else
            {
                Integer vidaPerdida = new Double((1 - (criatura.getPontosVida()/criatura.getMaxPontosVida()))*100).intValue();
                if (vidaPerdida > maiorVidaPerdida)
                {
                    retorno = criatura;
                    maiorVidaPerdida = vidaPerdida;
                }
            }
        }
        System.out.println("-------------MENOR VIDA = " + maiorVidaPerdida + "------------------");
        if (maiorVidaPerdida <= 10)
        {
            retorno = null;
        }
        return( retorno );
    }
    
    public static void decidirAcao(ArenaBatalha arena,CriaturaBase criaturaDecidindo,List< CriaturaBase > aliadosVivos,List< CriaturaBase > inimigosVivos)
    {
        List< HabilidadeBase > habilidadesIniciais = new ArrayList<>();
        for (HabilidadeBase habilidade : criaturaDecidindo.getUsableSkillsArray())
        {
            habilidadesIniciais.add(habilidade);
        }
        
        CriaturaBase aliadoComMenorVida = pegarMonstroComMenorVida( aliadosVivos );
        if (aliadoComMenorVida == null)
        {
            
            //significa que nao pode usar poderes de curar por que ninguem perdeu vida suficiente para justificar uma cura
            //tira efeitos de cura de habilidades consideradas
            for (HabilidadeBase habilidade : habilidadesIniciais)
            {
                if (habilidade.getTipo() == Acao.Cura)
                {
                    habilidadesIniciais.remove(habilidade);
                }
            }
        }
        
        List< HabilidadeBase > habilidadesDisponiveis = pegarListaDeHabilidades(habilidadesIniciais);
        
        Random gerador = new Random();
        int numeroRandom = 0;
        
        if (inimigosVivos.size() > 0)
        {
            numeroRandom = gerador.nextInt(inimigosVivos.size());
        }
        
        CriaturaBase alvoInimigo = inimigosVivos.get(numeroRandom);
        
        numeroRandom = 0;
        
        if (aliadosVivos.size() > 0)
        {
            numeroRandom = gerador.nextInt(aliadosVivos.size());
        }
        
        CriaturaBase alvoAliado = aliadosVivos.get(numeroRandom);
        
        //System.out.println("Tamanho habilidades disponiveis = " + habilidadesDisponiveis.size());
        
        if (habilidadesDisponiveis.isEmpty())
        {
            //so pode atacar algum alvo
            System.out.println("Monstro " + criaturaDecidindo.getNome() + " atacando " + alvoInimigo.getNome() + "!");
            Double dmg = battle_math.calculate_damage(criaturaDecidindo, alvoInimigo);
            Double[] vetor_parametros = new Double[5];
            vetor_parametros[0] = dmg;
            vetor_parametros[1] = new Double(0.00);
            vetor_parametros[2] = new Double(0.00);
            vetor_parametros[3] = new Double(0.00);
            vetor_parametros[4] = new Double(0.00);

            arena.modificarCriatura(vetor_parametros,criaturaDecidindo,alvoInimigo,true,0);
        }
        else
        {
            System.out.println("-----------------USANDO HABILIDADE------------------");
            numeroRandom = 0;
            if (habilidadesDisponiveis.size()>0)
            {
                //seleciona uma habilidade das disponiveis
                numeroRandom = gerador.nextInt(habilidadesDisponiveis.size());
            }
            HabilidadeBase habilidadeUtilizada = habilidadesDisponiveis.get(numeroRandom);
            ControleArena controle = ControleArena.ultimo_controle;
            if (controle != null)
            {
                if (habilidadeUtilizada.getTipo() == Acao.Ofensiva)
                {
                    controle.habilidade = habilidadeUtilizada;
                    controle.criatura_alvo = alvoInimigo;
                }
                else if (habilidadeUtilizada.getTipo() == Acao.Defensiva)
                {
                    controle.habilidade = habilidadeUtilizada;
                    controle.criatura_alvo = alvoAliado;
                }
                else if (habilidadeUtilizada.getTipo() == Acao.Cura)
                {
                    controle.habilidade = habilidadeUtilizada;
                    controle.criatura_alvo = aliadoComMenorVida;
                }
                else
                {
                    System.err.println("----------HABILIDADE NAO CATALOGADA-----------");
                }
                controle.frame_a_exibir = FrameExibido.SKILL_SELECIONADA_MONSTRO;
                controle.criarProximoFrame();
                        
                    
            }
            
            //habilidadeUtilizada.noUso(arena,alvo);
        }
    }
            
}
