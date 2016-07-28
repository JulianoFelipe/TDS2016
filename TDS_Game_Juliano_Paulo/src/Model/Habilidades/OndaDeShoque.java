/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Habilidades;

import Controller.ConfiguracoesDeTempo;
import Model.Criaturas.CriaturaBase;
import Model.Criaturas.Monstro;
import Model.Efeitos.Efeito;
import Model.Efeitos.EfeitoAtributos;
import Model.Geradores.ArenaBatalha;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import utilidades.Math.battle_math;

/**
 *
 * @author FREE
 */
public class OndaDeShoque extends HabilidadeBase{

    @Override
    public void noUso(ArenaBatalha arena) {
        System.out.println("inicio skill noUso");
        List< CriaturaBase > inimigos_vivos = new ArrayList<>();
        for (CriaturaBase criatura : arena.getListaDeVivos())
        {
            if (this.getDono() instanceof Monstro)
            {
                if (criatura instanceof CriaturaBase)
                {
                    inimigos_vivos.add(criatura);
                }
            }
            else
            {
                if (criatura instanceof Monstro)
                {
                    inimigos_vivos.add(criatura);
                }
            }
        }
        final CriaturaBase dono = this.getDono();
        final int delay = ConfiguracoesDeTempo.getInstance().getTempo_aproximado();
        final Timer timer = new Timer(delay,null);
        timer.addActionListener(new ActionListener()
                {
                    int i = 0;
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        System.out.println("timer start!");
                        Double[] vetor_parametros = new Double[4];
                        CriaturaBase criatura = inimigos_vivos.get(i);
                        Double ataque = -criatura.getAtaque()*0.5;
                        
                        
                        Efeito efeito_de_reducao_de_ataque = new EfeitoAtributos(50.00,0.00,0);
                        efeito_de_reducao_de_ataque.setDuration(2);
                        Double heroi_dano = dono.getAtaque();
                        dono.incAttack(heroi_dano);
                        Double dmg = battle_math.calculate_damage(dono , criatura);
                        dono.decAttack(heroi_dano);
                        criatura.getListaDeEfeitos().add(efeito_de_reducao_de_ataque);
                        vetor_parametros[0] = dmg;
                        vetor_parametros[1] = ataque;
                        vetor_parametros[2] = new Double(0.00);
                        vetor_parametros[3] = new Double(0.00);
                        i++;
                        if (i >= inimigos_vivos.size())
                        {
                            arena.attackBaseCreature(vetor_parametros, dono , criatura, true);
                            System.out.println("timer stop!");
                            timer.stop();
                        }
                        else
                        {
                            arena.attackBaseCreature(vetor_parametros, dono , criatura, false);
                        }
                    }
                }
        );
        timer.setInitialDelay(0);
        timer.start();
    }
    
    @Override
    public void setDescricao() {
        descricao = "Da dano em todos os inimigos igual a 200% ataque e reduz ataques inimigos em 50%";
    }

    @Override
    protected void setCooldDown() {
        this.tempoRecarregamento = this.progessoRecarregamento = 3;
    }

    @Override
    protected void setNome() {
        this.nome = "OndaDeShoque";
    }
    
}
