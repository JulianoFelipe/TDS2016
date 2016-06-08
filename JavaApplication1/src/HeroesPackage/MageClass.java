/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HeroesPackage;

import javaapplication1.*;

/**
 *
 * @author FREE
 */
public class MageClass extends HeroClass{
        public MageClass()
        {
            this.setAttack(100.00);
            this.setDefense(10.00);
            this.setMax_hit_points(1000.00);
            this.setHit_points(1000.00);
            this.setDodge(20);
            this.reset_temporary_stats();
            this.setNome("Galdalf");
            this.setSpeed(50.00);
            this.setMax_mana(200.00);
            this.setMana_regain(10.00);

            BaseSkill start_skill = Geradores.SkillGenerator.generate_skill();
            start_skill.setOwner(this);
            this.lista_de_habilidades.add(start_skill);
        }
        
}
