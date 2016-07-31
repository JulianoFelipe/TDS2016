/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Itens;

import Model.Itens.Constantes.Armas;
import Model.Itens.Constantes.Modificador;
import Model.Itens.Constantes.Raridade;
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
        descricao = "Aumenta dano em " + incrementoDano.toString();
        this.incrementoDano = incrementoDano;
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
        this.getHeroi().incAttack(incrementoDano);
    }

    @Override
    public File getArquivoDeImagem() {
        return(new File(getClass().getResource("/View/Imagens/espada_icon.png").getFile()));
    }

}
