/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Itens;

import Model.Itens.Constantes.Armaduras;
import Model.Itens.Constantes.Modificador;
import Model.Itens.Constantes.Raridade;

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
     * O quanto aumenta a defesa
     */
    Double incrementoDefesa;

    /**
     * Raridade da arma
     */
    Raridade raridade;
    
    Modificador modificador;

    public Armaduras getTipo() {
        return tipo;
    }

    public void setTipo(Armaduras tipo) {
        this.tipo = tipo;
    }

    public Double getIncrementoDefesa() {
        return incrementoDefesa;
    }

    public void setIncrementoDefesa(Double incrementoDefesa) {
        this.incrementoDefesa = incrementoDefesa;
    }

    public Raridade getRaridade() {
        return raridade;
    }

    public void setRaridade(Raridade raridade) {
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
        if (null != this.getTipo()) switch (this.getTipo()) {
            case Armadura:
                usavel_por.append("Cavaleiro");
                break;
            case Tunica:
                usavel_por.append("Mago");
                break;
            default:
                usavel_por.append("Erro");
                break;
        }

        return (this.getNome() + ", Multiplicacao de defesa:" + incrementoDefesa.toString() + " Usavel por classe:" + usavel_por.toString());
    }

    /**
     * Por enquanto nao usado
     */
    @Override
    public void onEquip() {
        //
    }
}
