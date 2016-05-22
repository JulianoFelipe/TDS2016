/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.ArrayList;

/**
 *
 * @author FREE
 */
public abstract class HeroClass extends BaseCreature{
    Double hp_multiplier;
    Double mana_multiplier;
    Double stamina_multiplier;
    Double speed_multiplier;
    Double attack_multiplier;
    Double defense_multiplier;
    
    Double hp_increment;
    Double mana_increment;
    Double stamina_increment;
    Double speed_increment;
    Double attack_increment;
    Double defense_increment;
    
    Integer level;
    Double xp_requirements;
    
    Double mana;
    Double stamina;
    
    Integer money;
    
    ArrayList< SkillClass > Lista_de_habilidades;
    
    public void LevelUp()
    {
        this.max_hit_points = this.max_hit_points*hp_multiplier + hp_increment;
        hit_points = max_hit_points;
        this.attack = this.attack*attack_multiplier + attack_increment;
        this.speed = this.speed*speed_multiplier + speed_increment;
        this.stamina = this.stamina*stamina_multiplier + stamina_increment;
        defense = defense*defense_multiplier+defense_increment;
        mana = mana*mana_multiplier+mana_increment;
    }
}
