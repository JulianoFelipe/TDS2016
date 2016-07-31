/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Habilidades;

import Controller.ConfiguracoesDeTempo;
import Model.Criaturas.CriaturaBase;
import Model.Efeitos.EfeitoAtributos;
import Model.Efeitos.Efeitos;
import Model.Efeitos.EfeitosBasicos;
import Model.Geradores.ArenaBatalha;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Timer;
import utilidades.Math.battle_math;

/**
 * Implementa metodos estaticos que sao muito usados por diferentes tipos de habilidade centralizando codigo e evitando repitocoes
 * @author Paulo
 */
public class HabilidadesComportamentoPadrao {
    
    /**
     * Todas as operacoes basicas comuns sao feitas por esse metodo
     * @param habilidade 
     */
    private static void paraTodaHabilidade(HabilidadeBase habilidade)
    {
        habilidade.progressoRecarregamento = 0;
        
    }
    
    /**
     * Comportamento generico que habilidades que afetam todos os seus alvos possuem
     * @param habilidade habilidade que chama o metodo
     * @param arena arena que esta acontecendo a batalha
     * @param usuario_da_habilidade quem usou a habilidade(habilidade.getDono())
     * @param alvos lista de alvos da habilidade
     * @param efeitos_aplicados lista de efeitos aplicados aos alvos
     * @param multiplicador_do_ataque se for positivo é encarado como um ataque e todos os outros modificadores serao valores positivos relacionados a reducao de tal atributo, se for negativo é uma cura e todos os outros modificadores serao valores positivos relacionados a aumento de tal atributo
     * @param modificacao_ataque modificacao percentual do ataque
     * @param modificacao_defesa modificacao percentual da defesa
     * @param modificacao_velocidade modificacao percentual da velocidade
     * @param modificacao_barra_de_ataque modificacao percentual da barra de ataque
     */
    public static void afeteTodosOsAlvos(HabilidadeBase habilidade,ArenaBatalha arena,CriaturaBase usuario_da_habilidade,List<CriaturaBase > alvos,List< Efeitos > efeitos_aplicados,Double multiplicador_do_ataque,Integer modificacao_ataque,Integer modificacao_defesa,Integer modificacao_velocidade,Integer modificacao_barra_de_ataque)
    {
        paraTodaHabilidade(habilidade);
        final int delay = ConfiguracoesDeTempo.getInstance().getTempo_aproximado();
        final Timer timer = new Timer(delay,null);
        timer.addActionListener(new ActionListener()
                {
                    int i = 0;
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Double[] vetor_parametros = new Double[5];
                        CriaturaBase criatura = alvos.get(i);
                        
                        for (Efeitos efeito : efeitos_aplicados)
                        {
                            criatura.adicionarEfeito(efeito);
                        }
                        if (multiplicador_do_ataque >= 0)
                        {
                            Double ataque_antes = usuario_da_habilidade.getAtaque();
                            Double ataque_depois = usuario_da_habilidade.getAtaque()*multiplicador_do_ataque;
                            usuario_da_habilidade.setAtaque(ataque_depois);
                            Double dmg = battle_math.calculate_damage(usuario_da_habilidade, criatura);
                            Double ataque = criatura.getAtaque()*(modificacao_ataque+0.00)/100;
                            Double defesa = criatura.getDefesa()*(modificacao_defesa+0.00)/100;
                            Double velocidade=  criatura.getVelocidade()*(modificacao_velocidade+0.00)/100;
                            vetor_parametros[0] = dmg;
                            vetor_parametros[1] = ataque;
                            vetor_parametros[2] = defesa;
                            vetor_parametros[3] = velocidade;
                            vetor_parametros[4] = modificacao_barra_de_ataque + 0.00;
                            usuario_da_habilidade.setAtaque(ataque_antes);
                            i++;
                            if (i >= alvos.size())
                            {
                                arena.modificarCriatura(vetor_parametros, usuario_da_habilidade , criatura, true);
                                System.out.println("timer stop!");
                                timer.stop();
                            }
                            else
                            {
                                arena.modificarCriatura(vetor_parametros, usuario_da_habilidade , criatura, false);
                            }
                        }
                        else
                        {
                            throw new UnsupportedOperationException();
                        }
                    }
                }
        );
        timer.setInitialDelay(0);
        timer.start();
    }
    
    /**
     * Comportamento generico que habilidades que afetam um inimigo
     * @param habilidade habilidade que chama o metodo
     * @param arena arena que esta acontecendo a batalha
     * @param usuario_da_habilidade quem usou a habilidade(habilidade.getDono())
     * @param alvo alvo da habilidade
     * @param efeitos_aplicados lista de efeitos aplicados ao alvo
     * @param multiplicador_do_ataque se for positivo é encarado como um ataque e todos os outros modificadores serao valores positivos relacionados a reducao de tal atributo, se for negativo é uma cura e todos os outros modificadores serao valores positivos relacionados a aumento de tal atributo
     * @param modificacao_ataque modificacao percentual do ataque
     * @param modificacao_defesa modificacao percentual da defesa
     * @param modificacao_velocidade modificacao percentual da velocidade
     * @param modificacao_barra_de_ataque modificacao percentual da barra de ataque
     */
    public static void afeteUmInimigo(HabilidadeBase habilidade,ArenaBatalha arena,CriaturaBase usuario_da_habilidade,CriaturaBase alvo,List< Efeitos > efeitos_aplicados,Double multiplicador_do_ataque,Integer modificacao_ataque,Integer modificacao_defesa,Integer modificacao_velocidade,Integer modificacao_barra_de_ataque)
    {
        paraTodaHabilidade(habilidade);
        Double[] vetor_parametros = new Double[5];

        if (multiplicador_do_ataque >= 0.00)
        {
            Double ataque_antes = usuario_da_habilidade.getAtaque();
            Double ataque_depois = usuario_da_habilidade.getAtaque()*multiplicador_do_ataque;

            
            
            usuario_da_habilidade.setAtaque(ataque_depois);
            Double dmg = battle_math.calculate_damage(usuario_da_habilidade , alvo);
            
            for (Efeitos efeito : efeitos_aplicados)
            {
                alvo.adicionarEfeito(efeito);
            }
            Double ataque = alvo.getAtaque()*(modificacao_ataque+0.00)/100;
            Double defesa = alvo.getDefesa()*(modificacao_defesa+0.00)/100;
            Double velocidade=  alvo.getVelocidade()*(modificacao_velocidade+0.00)/100;
            vetor_parametros[0] = dmg;
            vetor_parametros[1] = ataque;
            vetor_parametros[2] = defesa;
            vetor_parametros[3] = velocidade;
            vetor_parametros[4] = modificacao_barra_de_ataque+0.00;
            arena.modificarCriatura(vetor_parametros, usuario_da_habilidade , alvo, true);
            usuario_da_habilidade.setAtaque(ataque_antes);
        }
    }
}
