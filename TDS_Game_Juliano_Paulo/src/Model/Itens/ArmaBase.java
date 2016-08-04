/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Itens;

import Controller.ControleGeral;
import Model.Itens.Constantes.Armas;
import Model.Itens.Constantes.Modificador;
import Model.Itens.Constantes.Raridade;
import View.FrameExibido;
import java.io.File;

/**
 * Armas basicas
 *
 */
public class ArmaBase extends EquipavelBase {

//<editor-fold defaultstate="collapsed" desc="Banco de dados">
    private int armaId;
    
    public int getArmaId() {
        return armaId;
    }
    
    public void setArmaId(int armaId) {
        this.armaId = armaId;
    }
//</editor-fold>
    
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
    Double incrementoDano;

    /**
     * Raridade da arma
     */
    Raridade raridade;
    
    Modificador modificador = Modificador.Nenhum;
    
    public ArmaBase(Double incrementoDano)
    {
        super();
        this.incrementoDano = incrementoDano;
        setDescricao();
    }

    public Armas getTipo() {
        return tipo;
    }

    public void setTipo(Armas tipo) {
        this.tipo = tipo;
    }

    public Double getIncrementoDano() {
        return incrementoDano;
    }

    public void setIncrementoDano(Double incrementoDano) {
        this.incrementoDano = incrementoDano;
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

    public Modificador getModificador() {
        return modificador;
    }

    public void setModificador(Modificador modificador) {
        this.modificador = modificador;
    }

    @Override
    public void onEquip() {
        this.getHeroi().equipItem(this);
    }

    @Override
    public File getArquivoDeImagem() {
        return(new File(getClass().getResource("/View/Imagens/espada_icon.png").getFile()));
    }

    @Override
    public void aplicarEfeitosDeItem() {
        this.getHeroi().incAttack(this.getHeroi().getAtaque()*incrementoDano);
    }
    
    @Override
    public void setDescricao()
    {
        descricao = "Aumenta dano em " + incrementoDano.toString() + " vezes!";
    }

}
