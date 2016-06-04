/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EffectsPackage;

import javaapplication1.BaseCreature;

/**
 * Efeito Responsavel por curar, se cura for maior que 100% da vida de alvo cura 100%, 
 * se for negativo não faz nada, outros casos cura self em um valor entra 0-100%.
 * 
 * @author Paulo.Tenorio
 */
public class HealEffect extends EffectClass{

    public HealEffect(Double percentage_power_level, Double const_power_level) {
        super(percentage_power_level, const_power_level);
    }

    @Override
    public void onCaster(BaseCreature self) {
        Double quantia_de_cura_multiplicativa = self.getMax_hit_points()*percentage_power_level;
        Double quantia_de_cura_aditiva = const_power_level;
        Double cura_efetiva = quantia_de_cura_multiplicativa + quantia_de_cura_aditiva;
        if (cura_efetiva>=self.getMax_hit_points())
        {
            //se cura>=100% entao curar 100%
            self.setHit_points(self.getMax_hit_points());
        }
        else
        {
            if (cura_efetiva<0)
            {
                //do nothing
            }
            else
            {
                self.setHit_points(cura_efetiva);
            }
        }
    }

    @Override
    public void onTarget(BaseCreature target) {
        //does not have a target
    }
    
}
