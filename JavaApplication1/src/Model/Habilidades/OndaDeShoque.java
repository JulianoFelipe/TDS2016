/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Habilidades;

import Model.Criaturas.CriaturaBase;
import Model.Criaturas.Monstro;
import Model.Efeitos.Efeito;
import Model.Efeitos.EfeitoAtributos;
import Model.Geradores.ArenaBatalha;
import java.util.ArrayList;
import java.util.List;
import math_package.battle_math;

/**
 *
 * @author FREE
 */
public class OndaDeShoque extends HabilidadeBase{

    @Override
    public void noUso(ArenaBatalha arena) {
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
        
        for (CriaturaBase criatura : inimigos_vivos)
        {
            Efeito efeito_de_reducao_de_ataque = new EfeitoAtributos(50.00,0.00,0);
            Double dmg = battle_math.calculate_damage(this.getDono() , criatura);
            criatura.getLista_de_efeitos().add(efeito_de_reducao_de_ataque);
            arena.attackBaseCreature(dmg, this.getDono() , criatura);
        }
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
