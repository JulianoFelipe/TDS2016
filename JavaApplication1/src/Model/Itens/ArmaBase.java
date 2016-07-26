/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Itens;

/**
 * Armas basicas
 *
 */
public class ArmaBase extends EquipavelBase {

    /**
     * Indica se eh Sword,Staff,etc...
     */
    String tipo;

    /**
     * Nível da arma.
     */
    Integer level;

    /**
     * O quanto aumenta o ataque
     */
    Double damage_increase;

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

    public Double getDamage_increase() {
        return damage_increase;
    }

    public void setDamage_increase(Double damage_increase) {
        this.damage_increase = damage_increase;
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
    public String getDescricao() {
        StringBuilder usavel_por = new StringBuilder();
        if (this.getTipo().equals("Sword")) {
            usavel_por.append("Knight");
        } else if (this.getTipo().equals("Staff")) {
            usavel_por.append("Mage");
        } else {
            usavel_por.append("Erro");
        }

        return (this.getNome() + ",Multiplicacao de dano:" + damage_increase.toString());
    }

    @Override
    public void onEquip() {
        //
    }

}
