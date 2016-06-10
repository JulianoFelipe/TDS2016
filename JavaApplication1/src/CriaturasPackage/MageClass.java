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
 * Classe Mage, comportamento da classe de herois disponiveis Mago com limite de
 * Armas,Armaduras o quanto os status aumentam com o level,etc
 *
 */
public class MageClass extends HeroClass {

    /**
     * Construtor default, pode ser alterado para fazer testes. Por hora ele
     * gera uma skill para a classe mage
     */
    public MageClass() {
        this.setAttack(200.00);
        this.setDefense(100.00);
        this.setMax_hit_points(1000.00);
        this.setHit_points(1000.00);
        this.setDodge(20);
        this.reset_temporary_stats();
        this.setNome("Galdalf");
        this.setSpeed(75.00);
        this.setMax_mana(200.00);
        this.setMana_regain(10.00);
        this.addGold(100000);

        BaseSkill start_skill = Geradores.SkillGenerator.generate_skill();
        start_skill.setOwner(this);
        this.lista_de_habilidades.add(start_skill);
    }

    @Override
    public boolean canEquip(BaseWeapon weapon) {
        if (weapon.getTipo().equals("Staff")) {
            return (true);
        }
        return (false);
    }

    @Override
    public boolean canEquip(BaseArmor armor) {
        if (armor.getTipo().equals("Robe")) {
            return (true);
        }
        return (false);
    }

}
