/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Itens;

import Model.Armas;

/**
 * Armas basicas
 *
 */
public class ArmaBase extends EquipavelBase {

    /**
     * Indica tipo da arma.
     */
    Armas tipo;

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

    public Armas getTipo() {
        return tipo;
    }

    public void setTipo(Armas tipo) {
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
        if (this.getTipo() == Armas.Espada) {
            usavel_por.append("Cavaleiro");
        } else if (this.getTipo() == Armas.Cajado) {
            usavel_por.append("Mago");
        } else {
            usavel_por.append("Erro");
        }

        return (this.getNome() + ", Multiplicacao de dano:" + damage_increase.toString());
    }

    @Override
    public void onEquip() {
        //
    }

}
