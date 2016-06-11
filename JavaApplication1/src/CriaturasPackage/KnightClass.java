/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CriaturasPackage;

import ItensPackage.BaseArmor;
import ItensPackage.BaseWeapon;
import SkillPackage.BaseSkill;

/**
 * Classe Knight, comportamento da classe de herois disponiveis Knigth com
 * limite de Armas,Armaduras o quanto os status aumentam com o level,etc
 *
 * @author Paulo Henrique
 * @author Juliano Felipe
 */
public class KnightClass extends HeroClass {

    /**
     * Construtor que define os atributos de um Cavaleiro. Por hora um cavaleiro
     * tem 10 skills disponiveis ao ser criado
     */
    public KnightClass() {
        this.setAttack(100.00);
        this.setDefense(200.00);
        this.setMax_hit_points(1000.00);
        this.setHit_points(1000.00);
        this.setDodge(20);
        this.reset_temporary_stats();
        this.setNome("Sr.Duke of Cornwall");
        this.setSpeed(100.00);
        this.setMana(0.00);
        this.setMax_mana(100.00);
        this.setMana_regain(1.00);
        this.setHp_multiplier(2.00);
        this.addGold(100000);

        for (int i = 0; i < 10; i++) {
            BaseSkill start_skill = Geradores.SkillGenerator.generate_skill();
            start_skill.setOwner(this);
            this.lista_de_habilidades.add(start_skill);
        }
    }

    /**
     * @inhericDoc
     * @param weapon
     * @return 
     */
    @Override
    public boolean canEquip(BaseWeapon weapon) {
        if (weapon.getTipo().equals("Sword")) {
            return (true);
        }
        return (false);
    }

    @Override
    public boolean canEquip(BaseArmor armor) {
        if (armor.getTipo().equals("Armor")) {
            return (true);
        }
        return (false);
    }
}
