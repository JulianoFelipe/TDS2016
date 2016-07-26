/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas;

import Model.Itens.ArmaduraBase;
import Model.Itens.EquipavelBase;
import Model.Itens.ConsumivelBase;
import Model.Itens.ArmaBase;
import Model.Itens.ItemBase;
import java.util.ArrayList;

/**
 * Classe abstrata que determina atributos de um herói.
 *
 * @author Paulo Tenório
 */
public abstract class Heroi extends CriaturaBase {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    
    /**
     * O quanto o xp_requirements irá aumentar por level
     */
    public static final Double XP_LV_MULTIPLIER = 1.5;

    /**
     * O quanto o hp aumentara por level multiplicamente
     */
    Double hp_multiplier = 1.00;

    /**
     * O quanto a mana aumentara por level multiplicamente
     */
    Double mana_multiplier = 1.00;

    /**
     * O quanto o velocidade aumentara por level multiplicamente
     */
    Double speed_multiplier = 1.00;

    /**
     * O quanto o ataque aumentara por level multiplicamente
     */
    Double attack_multiplier = 1.00;

    /**
     * O quanto a defesa aumentara por level multiplicamente
     */
    Double defense_multiplier = 1.00;

    /**
     * O quanto o hp aumentara por level aditivamente
     */
    Double hp_increment = 0.00;

    /**
     * O quanto a mana aumentara por level aditivamente
     */
    Double mana_increment = 0.00;

    /**
     * O quanto o velocidade aumentara por level aditivamente
     */
    Double speed_increment = 0.00;

    /**
     * O quanto o ataque aumentara por level aditivamente
     */
    Double attack_increment = 0.00;

    /**
     * O quanto a defesa aumentara por level aditivamente
     */
    Double defense_increment = 0.00;

    /**
     * O quanto de xp o heroi possue
     */
    Double local_xp = 0.00;

    /**
     * O quanto de xp o heroi necessita para evoluir de level
     */
    Double xp_requirements = 100.00;

    /**
     * Se tiver alguma, a armadura que o heroi possue
     */
    private ArmaduraBase armor = null;

    /**
     * Se tiver alguma, a arma que o heroi possue
     */
    private ArmaBase weapon = null;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Utilidades">
    
    /**
     * Aplica efeito de arma
     */
    @Override
    public void applyWeaponEffects() {
        if (weapon != null) {
            this.setTemp_attack(this.getAtaque() * weapon.getDamage_increase());
        }
    }

    /**
     * Aplica efeitos de armadura
     */
    @Override
    public void applyArmorEffects() {
        if (armor != null) {
            this.setTemp_defense(this.defesa * armor.getDefense_increase());
        }
    }

    /**
     * Equipa uma arma
     *
     * @param weapon arma que deve ser equipada
     */
    public void equipWeapon(ArmaBase weapon) {
        this.weapon = weapon;
    }

    /**
     * Metodo chamado toda vez que heroi subir de level
     */
    public void LevelUp() {
        this.max_pontos_vida = this.max_pontos_vida * hp_multiplier + hp_increment;
        pontos_vida = max_pontos_vida;
        this.ataque = this.ataque * attack_multiplier + attack_increment;
        this.velocidade = this.velocidade * speed_multiplier + speed_increment;
        defesa = defesa * defense_multiplier + defense_increment;
        mana = mana * mana_multiplier + mana_increment;
        this.level++;
        this.xp_requirements = this.xp_requirements * XP_LV_MULTIPLIER;
    }

    /**
     * funcao auxiliar a to.string que gera um relatorio com informacoes do
     * heroi
     *
     * @return String com informacoes
     */
    public String displayStatus() {
        return (this.toString() + '\n'
                + "level : " + this.level + '\n'
                + "mana : " + this.max_mana + '\n'
                + "mana_increment" + this.mana_increment + '\n'
                + "xp : " + this.local_xp + '\n'
                + "xp necessaria para proximo level : " + (this.xp_requirements - this.local_xp) + '\n'
                + "Skills aprendidas : " + this.lista_de_habilidades.size() + '\n'
                + "Weapon : " + this.getWeapon() + '\n'
                + "Armor : " + this.getArmor() + '\n');
    }

    /**
     * Adiciona xp e realiza levelup se xp ganha mais xp que tinha for mais que
     * xp necessaria para aumentar nivel
     *
     * @param xp xp ganha
     */
    public void addXP(Double xp) {
        Double xp_que_tinha = this.local_xp;
        Double xp_para_upar = this.xp_requirements - this.local_xp;
        if (xp >= xp_para_upar) {
            Double xp_restante = xp - xp_para_upar;
            this.LevelUp();
            this.addXP(xp_restante);
        } else {
            this.local_xp = this.local_xp + xp;
        }
    }

    /**
     * Equipa item
     *
     * @param item item que sera equipado
     */
    public void equipItem(EquipavelBase item) {
        System.out.println("equipando item....");
        if (canEquip(item)) {
            if (item instanceof ArmaduraBase) {
                ArmaduraBase local_armor = (ArmaduraBase) item;
                this.setArmor(local_armor);
            } else if (item instanceof ArmaBase) {
                ArmaBase local_weapon = (ArmaBase) item;
                this.setWeapon(local_weapon);
            } else {
                System.out.println("resultado anormal em equipItem");
            }
        }
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Getters & Setters">

    public ArmaduraBase getArmor() {
        return armor;
    }

    public void setArmor(ArmaduraBase armor) {
        this.armor = armor;
    }

    public ArmaBase getWeapon() {
        return weapon;
    }

    public void setWeapon(ArmaBase weapon) {
        this.weapon = weapon;
    }

    public void setHp_multiplier(Double hp_multiplier) {
        this.hp_multiplier = hp_multiplier;
    }

    public void setMana_multiplier(Double mana_multiplier) {
        this.mana_multiplier = mana_multiplier;
    }

    public void setSpeed_multiplier(Double speed_multiplier) {
        this.speed_multiplier = speed_multiplier;
    }

    public void setAttack_multiplier(Double attack_multiplier) {
        this.attack_multiplier = attack_multiplier;
    }

    public void setDefense_multiplier(Double defense_multiplier) {
        this.defense_multiplier = defense_multiplier;
    }

    public void setHp_increment(Double hp_increment) {
        this.hp_increment = hp_increment;
    }

    public void setMana_increment(Double mana_increment) {
        this.mana_increment = mana_increment;
    }

    public void setSpeed_increment(Double speed_increment) {
        this.speed_increment = speed_increment;
    }

    public void setAttack_increment(Double attack_increment) {
        this.attack_increment = attack_increment;
    }

    public void setDefense_increment(Double defense_increment) {
        this.defense_increment = defense_increment;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Booleanos">
    
    /**
     *
     * @param item A ser equipado
     * @return true se o item poder ser equipado, false caso contrario
     */
    public boolean canEquip(EquipavelBase item) {
        if (item instanceof ArmaBase) {
            ArmaBase local_weapon = (ArmaBase) item;
            return (this.canEquip(local_weapon));
        } else if (item instanceof ArmaduraBase) {
            ArmaduraBase local_armor = (ArmaduraBase) item;
            return (this.canEquip(local_armor));
        } else {
            System.out.println("Resultado nao esperado em HeroClass canEquip(BaseEquipableItem)");
            return (false);
        }
    }

    /**
     * Define o tipo de arma que pode ser equipada
     *
     * @param weapon Arma para tentar ser equipada.
     * @return true se puder equipar, false caso contrario
     */
    abstract public boolean canEquip(ArmaBase weapon);

    /**
     * Define o tipo de armor que pode ser equipada
     *
     * @param armor Armadura para tentar ser equipada.
     * @return true se puder equipar, false caso contrario
     */
    abstract public boolean canEquip(ArmaduraBase armor);
    
    // </editor-fold>
}
