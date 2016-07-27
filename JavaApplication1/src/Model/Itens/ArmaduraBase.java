/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Itens;

import Model.Armaduras;

/**
 * Armadura basica
 *
 */
public class ArmaduraBase extends EquipavelBase {

    /**
     * Indica tipo da armadura
     */
    Armaduras tipo;

    /**
     * Nível da armadura.
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

    public Armaduras getTipo() {
        return tipo;
    }

    public void setTipo(Armaduras tipo) {
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
    public String getDescricao() {
        StringBuilder usavel_por = new StringBuilder();
        if (this.getTipo() == Armaduras.Armadura) {
            usavel_por.append("Cavaleiro");
        } else if (this.getTipo() == Armaduras.Tunica) {
            usavel_por.append("Mago");
        } else {
            usavel_por.append("Erro");
        }

        return (this.getNome() + ", Multiplicacao de defesa:" + defense_increase.toString() + " Usavel por classe:" + usavel_por.toString());
    }

    /**
     * Por enquanto nao usado
     */
    @Override
    public void onEquip() {
        //
    }
}
