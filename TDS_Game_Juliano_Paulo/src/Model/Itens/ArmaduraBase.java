/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Itens;

import Model.Itens.Constantes.Armaduras;
import Model.Itens.Constantes.Modificador;
import Model.Itens.Constantes.Raridade;
import java.io.File;

/**
 * Armadura basica
 *
 */
public class ArmaduraBase extends EquipavelBase {

//<editor-fold defaultstate="collapsed" desc="Banco de dados">
    private int armaduraId;
    
    public int getArmaduraId() {
        return armaduraId;
    }
    
    public void setArmaduraId(int armaduraId) {
        this.armaduraId = armaduraId;
    }
    
//</editor-fold>
    
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
    
    Modificador modificador = Modificador.Nenhum;

    public ArmaduraBase(Double incrementoDefesa)
    {
        descricao = "Aumenta defesa em " + incrementoDefesa.toString();
        this.incrementoDefesa = incrementoDefesa;
    }
    
    public Modificador getModificador() {
        return modificador;
    }

    public void setModificador(Modificador modificador) {
        this.modificador = modificador;
    }

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

    /**
     * Por enquanto nao usado
     */
    @Override
    public void onEquip() {
        this.getHeroi().incDefense(incrementoDefesa);
    }

    @Override
    public File getArquivoDeImagem() {
        return(new File(getClass().getResource("/View/Imagens/shield_icon.png").getFile()));
    }
}
