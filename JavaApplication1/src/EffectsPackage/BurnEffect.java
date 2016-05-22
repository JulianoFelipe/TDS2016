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
public class BurnEffect extends EffectClass{

    public BurnEffect(Double percentage_power_level, Double const_power_level) {
        super(percentage_power_level, const_power_level);
    }

    @Override
    public void onCaster(BaseCreature self) {
        //faz nada
    }

    @Override
    public void onTarget(BaseCreature target) {
        Double aux = target.getDefense();
        aux = (aux-aux*(percentage_power_level/100)-const_power_level);
    }
    
}
