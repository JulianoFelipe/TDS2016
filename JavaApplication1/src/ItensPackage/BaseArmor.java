/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ItensPackage;

/**
 * Armadura basica
 *
 */
public class BaseArmor extends BaseEquipableItem{
    /**
     * Indica se eh Sword,Staff,etc...
     */
    String tipo;
    
    /**
     * 
     */
    Integer level;
    
    /**
     * O quanto aumenta o ataque
     */
    Double defense_increase;
    
    /**
     * Raridade da arma
     */
    String raridade;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getDefense_increase() {
        return defense_increase;
    }

    public void setDefense_increase(Double defense_increase) {
        this.defense_increase = defense_increase;
    }



    public String getRaridade() {
        return raridade;
    }

    public void setRaridade(String raridade) {
        this.raridade = raridade;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    
    @Override
    public String getDescription()
    {
        StringBuilder usavel_por = new StringBuilder();
        if (this.getTipo().equals("Armor"))
        {
            usavel_por.append("Knight");
        }
        else if (this.getTipo().equals("Robe"))
        {
            usavel_por.append("Mage");
        }
        else
        {
            usavel_por.append("Erro");
        }
            
        return(this.getNome()+",Multiplicacao de defesa:"+defense_increase.toString()+" Usavel por classe:"+usavel_por.toString());
    }

    /**
     * Por enquanto nao usado
     */
    @Override
    public void onEquip() {
        //
    }
}
