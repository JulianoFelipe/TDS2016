/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import ItensPackage.*;
import java.util.ArrayList;

/**
 * Classe abstrata que determina
 * atributos de um herói.
 * 
 * @author Paulo Tenório
 */
public abstract class HeroClass extends BaseCreature{
    /**
     * O quanto o xp_requirements irá aumentar por level
     */
    public static final Double XP_LV_MULTIPLIER=1.5;
    
    private ArrayList< BaseItem > inventario = new ArrayList<>();
    
    Double hp_multiplier = 1.00;
    Double mana_multiplier = 1.00;
    Double stamina_multiplier = 1.00;
    Double speed_multiplier = 1.00;
    Double attack_multiplier = 1.00;
    Double defense_multiplier = 1.00;
    
    Double hp_increment = 0.00;
    Double mana_increment = 0.00;
    Double stamina_increment = 0.00;
    Double speed_increment = 0.00;
    Double attack_increment = 0.00;
    Double defense_increment = 0.00;
    
    Double local_xp=0.00;
    Double xp_requirements=100.00;
    
    
    /**
     * Unidade monetaria usada
     */
    Integer gold=0;
    
    
    public void addMoney(int sum)
    {
        gold = gold + sum;
    }
    
    public void subMoney(int sum)
    {
        gold = gold - sum;
    }
    
    public void LevelUp()
    {
        this.max_hit_points = this.max_hit_points*hp_multiplier + hp_increment;
        hit_points = max_hit_points;
        this.attack = this.attack*attack_multiplier + attack_increment;
        this.speed = this.speed*speed_multiplier + speed_increment;
        defense = defense*defense_multiplier+defense_increment;
        mana = mana*mana_multiplier+mana_increment;
        this.level++;
        this.xp_requirements = this.xp_requirements * XP_LV_MULTIPLIER;
    }
    
    public String displayStatus()
    {
        return(
               this.toString() + '\n' +
               "level : " + this.level + '\n' +
               "mana : " + this.max_mana + '\n' +
               "mana_increment" + this.mana_increment + '\n' + 
               "gold : " + this.gold + '\n' +
               "xp : " + this.local_xp + '\n' +
               "xp necessaria para proximo level : " + (this.xp_requirements-this.local_xp) + '\n' +
               "Itens no inventairo : " + (this.getInventario().size()) + '\n' +
               "Skills aprendidas : "  + this.lista_de_habilidades.size() + '\n'
                
                
               );
    }
    
    /**
     * Adiciona xp e realiza levelup se xp ganha mais xp que tinha for mais que xp necessaria para aumentar nivel
     * @param xp xp ganha
     */
    public void addXP(Double xp)
    {
        Double xp_que_tinha = this.local_xp;
        Double xp_para_upar = this.xp_requirements-this.local_xp;
        if (xp>=xp_para_upar)
        {
            Double xp_restante = xp - xp_para_upar;
            this.LevelUp();
            this.addXP(xp_restante);
        }
        else
        {
            this.local_xp = this.local_xp + xp;
        }
    }

    public ArrayList<BaseItem> getInventario() {
        return inventario;
    }
    
    public void addItem(BaseItem item)
    {
        this.inventario.add(item);
    }
    
    public void removeItem(BaseItem item) {
        if (!inventario.remove(nome))
        {
            System.out.println("elemento nao encontrado :(");
        }
        else
        {
            System.out.println("Item:"+item+",removido!");
        }
    }

    public void setHp_multiplier(Double hp_multiplier) {
        this.hp_multiplier = hp_multiplier;
    }

    public void setMana_multiplier(Double mana_multiplier) {
        this.mana_multiplier = mana_multiplier;
    }

    public void setStamina_multiplier(Double stamina_multiplier) {
        this.stamina_multiplier = stamina_multiplier;
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

    public void setStamina_increment(Double stamina_increment) {
        this.stamina_increment = stamina_increment;
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

    public void setGold(Integer gold) {
        this.gold = gold;
    }
    
    
}
