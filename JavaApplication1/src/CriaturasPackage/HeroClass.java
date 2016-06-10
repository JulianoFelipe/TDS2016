/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CriaturasPackage;

import ItensPackage.*;
import java.util.ArrayList;

/**
 * Classe abstrata que determina atributos de um herói.
 *
 * @author Paulo Tenório
 */
public abstract class HeroClass extends BaseCreature {

    // <editor-fold defaultstate="collapsed" desc="Atributos">
    
    /**
     * O quanto o xp_requirements irá aumentar por level
     */
    public static final Double XP_LV_MULTIPLIER = 1.5;

    /**
     * Inventario do heroi ou seja itens que ele possue
     */
    private ArrayList< BaseItem> inventario = new ArrayList<>();

    /**
     * O quanto o hp aumentara por level multiplicamente
     */
    Double hp_multiplier = 1.00;

    /**
     * O quanto a mana aumentara por level multiplicamente
     */
    Double mana_multiplier = 1.00;

    /**
     * O quanto o speed aumentara por level multiplicamente
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
     * O quanto o speed aumentara por level aditivamente
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
    private BaseArmor armor = null;

    /**
     * Se tiver alguma, a arma que o heroi possue
     */
    private BaseWeapon weapon = null;

    /**
     * Unidade monetaria usada
     */
    Integer gold = 0;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Utilidades">
    
    /**
     * Aplica efeito de arma
     */
    @Override
    public void applyWeaponEffects() {
        if (weapon != null) {
            this.setTemp_attack(this.getAttack() * weapon.getDamage_increase());
        }
    }

    /**
     * Aplica efeitos de armadura
     */
    @Override
    public void applyArmorEffects() {
        if (armor != null) {
            this.setTemp_defense(this.defense * armor.getDefense_increase());
        }
    }

    /**
     * Adiciona gold para o heroi
     *
     * @param sum o quanto adicionara
     */
    public void addGold(int sum) {
        gold = gold + sum;
    }

    /**
     * Retira gold do heroi
     *
     * @param sum o quanto retirará
     */
    public void subGold(int sum) {
        gold = gold - sum;
    }

    /**
     * Equipa uma arma
     *
     * @param weapon arma que deve ser equipada
     */
    public void equipWeapon(BaseWeapon weapon) {
        this.weapon = weapon;
    }

    /**
     * Metodo chamado toda vez que heroi subir de level
     */
    public void LevelUp() {
        this.max_hit_points = this.max_hit_points * hp_multiplier + hp_increment;
        hit_points = max_hit_points;
        this.attack = this.attack * attack_multiplier + attack_increment;
        this.speed = this.speed * speed_multiplier + speed_increment;
        defense = defense * defense_multiplier + defense_increment;
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
                + "gold : " + this.gold + '\n'
                + "xp : " + this.local_xp + '\n'
                + "xp necessaria para proximo level : " + (this.xp_requirements - this.local_xp) + '\n'
                + "Itens no inventairo : " + (this.getInventario().size()) + '\n'
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
     * Adiciona item ao inventario
     *
     * @param item item adicionado
     */
    public void addItem(BaseItem item) {
        this.inventario.add(item);
    }

    /**
     * Remove item do inventario
     *
     * @param item item removido
     */
    public void removeItem(BaseItem item) {
        if (!inventario.remove(item)) {
            System.out.println("elemento nao encontrado :(");
        } else {
            System.out.println("Item:" + item + ",removido!");
        }
    }

    /**
     *
     * @return Array com itens no inventario que podem ser consumidos
     */
    public ArrayList< BaseConsumableItem> getConsumableItensArray() {
        ArrayList< BaseConsumableItem> retorno = new ArrayList<>();
        for (BaseItem item : this.inventario) {
            if (item instanceof BaseConsumableItem) {
                BaseConsumableItem item_consumable = (BaseConsumableItem) item;
                retorno.add(item_consumable);
            }
        }
        return (retorno);
    }

    /**
     * Equipa item
     *
     * @param item item que sera equipado
     */
    public void equipItem(BaseEquipableItem item) {
        System.out.println("equipando item....");
        if (canEquip(item)) {
            if (item instanceof BaseArmor) {
                BaseArmor local_armor = (BaseArmor) item;
                this.setArmor(local_armor);
            } else if (item instanceof BaseWeapon) {
                BaseWeapon local_weapon = (BaseWeapon) item;
                this.setWeapon(local_weapon);
            } else {
                System.out.println("resultado anormal em equipItem");
            }
        }
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Getters & Setters">
    
    public ArrayList<BaseItem> getInventario() {
        return inventario;
    }
    
    /**
     *
     * @return ArrayList com itens que sao do tipo BaseEquipableItem(itens que
     * podem ser equipados)
     */
    public ArrayList< BaseEquipableItem> getEquipableItensArray() {
        ArrayList< BaseEquipableItem> retorno = new ArrayList<>();
        for (BaseItem item : this.inventario) {
            if (item instanceof BaseEquipableItem) {
                if (item instanceof BaseArmor) {
                    BaseArmor armor = (BaseArmor) item;
                    if (this.canEquip(armor)) {
                        retorno.add(armor);
                    }
                } else if (item instanceof BaseWeapon) {
                    BaseWeapon weapon = (BaseWeapon) item;
                    if (this.canEquip(weapon)) {
                        retorno.add(weapon);
                    }
                }
            }
        }
        return (retorno);
    }

    /**
     * Se existir encontra item no inventario e o retorna
     *
     * @param to_search Item a procurar.
     * @return          Item retornado (ou null, caso não existir).
     */
    public BaseItem getItem(BaseItem to_search) {
        BaseItem retorno = null;
        for (BaseItem item : this.inventario) {
            if (item.equals(to_search)) {
                retorno = item;
                break;
            }
        }
        return (retorno);
    }

    public Integer getGold() {
        return gold;
    }

    public BaseArmor getArmor() {
        return armor;
    }

    public void setArmor(BaseArmor armor) {
        this.armor = armor;
    }

    public BaseWeapon getWeapon() {
        return weapon;
    }

    public void setWeapon(BaseWeapon weapon) {
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

    private void setGold(Integer gold) {
        this.gold = gold;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Booleanos">
    
    /**
     *
     * @param item A ser equipado
     * @return true se o item poder ser equipado, false caso contrario
     */
    public boolean canEquip(BaseEquipableItem item) {
        if (item instanceof BaseWeapon) {
            BaseWeapon local_weapon = (BaseWeapon) item;
            return (this.canEquip(local_weapon));
        } else if (item instanceof BaseArmor) {
            BaseArmor local_armor = (BaseArmor) item;
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
    abstract public boolean canEquip(BaseWeapon weapon);

    /**
     * Define o tipo de armor que pode ser equipada
     *
     * @param armor Armadura para tentar ser equipada.
     * @return true se puder equipar, false caso contrario
     */
    abstract public boolean canEquip(BaseArmor armor);
    
    // </editor-fold>
}
