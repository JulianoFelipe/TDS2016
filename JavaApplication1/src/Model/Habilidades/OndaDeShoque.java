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
import math_package.battle_math;

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
                        CriaturaBase criatura = inimigos_vivos.get(i);
                        Efeito efeito_de_reducao_de_ataque = new EfeitoAtributos(50.00,0.00,0);
                        efeito_de_reducao_de_ataque.setDuration(2);
                        Double dmg = battle_math.calculate_damage(dono , criatura);
                        criatura.getLista_de_efeitos().add(efeito_de_reducao_de_ataque);
                        i++;
                        if (i >= inimigos_vivos.size())
                        {
                            arena.attackBaseCreature(dmg, dono , criatura, true);
                            System.out.println("timer stop!");
                            timer.stop();
                        }
                        else
                        {
                            arena.attackBaseCreature(dmg, dono , criatura, false);
                        }
                    }
                }
        );
        timer.setInitialDelay(5);
        timer.start();
    }
    
    @Override
    public void setDescricao() {
        descricao = "Da dano em todos os inimigos igual a 150% ataque e reduz ataques inimigos em 50%";
    }

    @Override
    protected void setCooldDown() {
        this.cooldown_time = this.local_cooldown = 3;
    }

    @Override
    protected void setNome() {
        this.nome = "OndaDeShoque";
    }
    
}
