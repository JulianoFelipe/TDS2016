/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EffectsPackage;

import javaapplication1.BaseCreature;

/**
 *
 * @author FREE
 */
public abstract class EffectClass {
    Double percentage_power_level;
    Double const_power_level;

    public EffectClass(Double percentage_power_level, Double const_power_level) {
        this.percentage_power_level = percentage_power_level;
        this.const_power_level = const_power_level;
    }
    
    abstract public void onCaster(BaseCreature self);
    abstract public void onTarget(BaseCreature target);
}
