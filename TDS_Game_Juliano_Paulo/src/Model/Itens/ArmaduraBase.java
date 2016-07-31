/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Itens;

import Controller.ControleArena;
import Model.Itens.Constantes.Armaduras;
import Model.Itens.Constantes.Modificador;
import Model.Itens.Constantes.Raridade;
import View.FrameExibido;
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
        super();
        this.incrementoDefesa = incrementoDefesa;
        setDescricao();
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
            StringBuilder s = new StringBuilder();
            s.append(this.getHeroi().getNome()).append(" Equipou o item ").append(this.getNome());
        this.getHeroi().equipItem(this);
    }

    @Override
    public File getArquivoDeImagem() {
        return(new File(getClass().getResource("/View/Imagens/shield_icon.png").getFile()));
    }

    @Override
    public void aplicarEfeitosDeItem() {
        this.getHeroi().incDefense(this.getHeroi().getDefesa()*incrementoDefesa);
    }
    
    @Override
    public void setDescricao()
    {
        descricao = "Aumenta defesa em " + incrementoDefesa.toString() + " vezes!";
    }
}
