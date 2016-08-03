/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Efeitos;

import Model.Acao;
import Model.Criaturas.CriaturaBase;
import View.Imageable;
import java.io.File;

/**
 * Classe base para efeitos.
 *
 * @author Paulo Henrique
 * @author Juliano Felipe
 */
public abstract class Efeitos implements Cloneable,Imageable {
    
//<editor-fold defaultstate="collapsed" desc="Banco de dados">
    private int efeitoID;
    
    public int getEfeitoID() {
        return efeitoID;
    }
    
    public void setEfeitoID(int efeitoID) {
        this.efeitoID = efeitoID;
    }
//</editor-fold>
    
    /**
     * Tipo pode ser ou Ofensivo ou Defensivo
     */
    protected Acao tipo;
    
    /**
     * Tipo de efeito PADRAO,INSTANTANEO,TURNO
     */
    protected ComportamentoEfeito comportamento_efeito;

    /**
     * Indica se o efeito eh instantaneo (eh aplicado e logo depois removido)
     */
    protected Boolean isInstantaneo = false;

    /**
     * Duracao do efeito se ele nao for instantaneo
     */
    protected Integer duracao;
    
    /**
     * A ser usado para cáculo de efeito multiplicativo. Ex.: Uma cura de 10%,
     * recuperaria 10% do {@link Model.Criaturas.CriaturaBase#max_pontos_vida}.
     */
    protected Double poder_percentual;
    /**
     * A ser usado para cáculo de efeito aditivo. Ex.: Um dano de 10 unidades,
     * reduziria, aritmeticamente, um número de acordo com defesas e skills do
     * {@link Model.Criaturas.CriaturaBase#max_pontos_vida}.
     */
    protected Double poder_constante;

    /**
     * Descricao do efeito
     */
    protected String descricao = "";
    
    /**
     * Campo usado para permitir que um efeito seja aplicado um turno depois
     */
    protected Boolean deveAtrasar = false;
    
    /**
     * Construtor default
     */
    public Efeitos() {
        this.poder_percentual = 0.00;
        this.poder_constante = 0.00;
    }

    /**
     * Constutor de efeito.
     *
     * @param percentage_power_level Efeito multiplicativo.
     * @param const_power_level      Efeito aditivo.
     */
    public Efeitos(Double percentage_power_level, Double const_power_level) {
        this.poder_percentual = percentage_power_level;
        this.poder_constante = const_power_level;
    }
    
    public Efeitos(Efeitos efeitos)
    {
        this.comportamento_efeito = efeitos.comportamento_efeito;
        this.descricao = efeitos.descricao;
        this.duracao = new Integer(efeitos.duracao);
        this.isInstantaneo = efeitos.isInstantaneo;
        this.poder_constante = efeitos.poder_constante;
        this.poder_percentual = efeitos.poder_percentual;
        this.tipo = efeitos.tipo;
    }

    public Boolean isInstantaneo() {
        return isInstantaneo;
    }

    public Acao getTipo() {
        return tipo;
    }

    public void setTipo(Acao tipo) {
        this.tipo = tipo;
    }

    public ComportamentoEfeito getComportamento_efeito() {
        return comportamento_efeito;
    }

    public void setComportamento_efeito(ComportamentoEfeito comportamento_efeito) {
        this.comportamento_efeito = comportamento_efeito;
    }
    
    public String getDescricao() {
        updateDescricao();
        return descricao;
    }

    public Boolean getIsInstantaneo() {
        return isInstantaneo;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public Double getPoder_percentual() {
        return poder_percentual;
    }

    public Double getPoder_constante() {
        return poder_constante;
    }

    public boolean isDeveAtrasar() {
        return deveAtrasar;
    }

    public void setDeveAtrasar(boolean shouldDelay) {
        this.deveAtrasar = shouldDelay;
    }

    private void updateDescricao() {
        setDescricao();
        StringBuilder s = new StringBuilder(descricao);
        s.append('\n').append("Duracao = ").append(Integer.toString( duracao ));
        descricao = s.toString();
    }

    public void setDuration(int duracao)
    {
        this.duracao = duracao;
    }

    /**
     * Aplica efeito em uma criatura
     *
     * @param target Alvo do efeito
     */
    abstract public void onTarget(CriaturaBase target);
    
    abstract public File getArquivoDeImagem();
    
    abstract public void setDescricao();
}
