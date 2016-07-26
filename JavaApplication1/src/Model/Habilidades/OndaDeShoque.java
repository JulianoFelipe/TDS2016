/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Habilidades;

import Model.Criaturas.CriaturaBase;
import Model.Efeitos.Efeito;
import Model.Efeitos.EfeitoAtributos;
import Model.Geradores.ArenaBatalha;
import java.util.List;
import math_package.battle_math;

/**
 *
 * @author FREE
 */
public class OndaDeShoque extends HabilidadeBase{

    @Override
    public void noUso(ArenaBatalha arena, List<CriaturaBase> aliados_vivos, List<CriaturaBase> aliados_mortos, List<CriaturaBase> inimigos_vivos, List<CriaturaBase> inimigos_mortos) {
        for (CriaturaBase criatura : inimigos_vivos)
        {
            Efeito efeito_de_reducao_de_ataque = new EfeitoAtributos(50.00,0.00,0);
            Double dmg = battle_math.calculate_damage(this.getDono() , criatura);
            criatura.getLista_de_efeitos().add(efeito_de_reducao_de_ataque);
            arena.attackBaseCreature(dmg, this.getDono() , criatura);
        }
        init();
    }
    
    @Override
    public void setDescricao() {
        descricao = "Da dano em todos os inimigos igual a 150% ataque e reduz ataques inimigos em 50%";
    }

    @Override
    protected void init() {
        this.cooldown_time = this.local_cooldown = 3;
    }
    
}
