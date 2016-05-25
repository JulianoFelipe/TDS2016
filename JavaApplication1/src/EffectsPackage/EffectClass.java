/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EffectsPackage;

import javaapplication1.BaseCreature;

/**
 * Classe base para efeitos.
 * @author Paulo Henrique
 * @author Juliano Felipe
 */
public abstract class EffectClass {
    /**
     * A ser usado para cáculo de efeito multiplicativo.
     * Ex.: Uma cura de 10%, recuperaria 10% do
     * {@link javaapplication1.BaseCreature#max_hit_points}.
     */
    Double percentage_power_level;
    /**
     * A ser usado para cáculo de efeito aditivo.
     * Ex.: Um dano de 10 unidades, reduziria, aritmeticamente,
     * um número de acordo com defesas e skills do
     * {@link javaapplication1.BaseCreature#max_hit_points}.
     */
    Double const_power_level;

    /**
     * Constutor de efeito.
     * @param percentage_power_level Efeito multiplicativo.
     * @param const_power_level      Efeito aditivo.
     */
    public EffectClass(Double percentage_power_level, Double const_power_level) {
        this.percentage_power_level = percentage_power_level;
        this.const_power_level = const_power_level;
    }
    
    /**
     * Cálcuo de Efeito em quem conjurou o ataque.
     * @param self Criatura que conjurou.
     */
    abstract public void onCaster(BaseCreature self);
    
    /**
     * Cálcuo de Efeito no alvo do ataque.
     * @param target Alvo do ataque.
     */
    abstract public void onTarget(BaseCreature target);
}
