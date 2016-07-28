/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Habilidades;

import Model.Acao;
import Model.Criaturas.CriaturaBase;
import Model.Efeitos.EfeitoAtributos;
import Model.Efeitos.Efeito;
import Model.Geradores.ArenaBatalha;
import java.util.List;

/**
 * Classe base para habilidades (skills).
 *
 * @author Paulo Tenório
 */
public abstract class HabilidadeBase{

//<editor-fold defaultstate="collapsed" desc="Banco de dados">
    private int habilidadeId;
    
    public int getHabilidadeId() {
        return habilidadeId;
    }
    
    public void setHabilidadeId(int habilidadeId) {
        this.habilidadeId = habilidadeId;
    }
//</editor-fold>
    
    
    /**
     * Dono da skill
     */
    private CriaturaBase dono = null;

    /**
     * Tipo de skill, ex:Ofensiva ou Defensiva
     */
    private Acao tipo = null;

    /**
     * nome da skill
     */
    protected String nome;

    /**
     * variavel que se for igual a tempoRecarregamento 
     * significa que a skill pode ser usada
     */
    protected Integer progessoRecarregamento;

    /**
     * tempo de recarga para poder usar a skill novamente
     * em turnos
     */
    protected Integer tempoRecarregamento;

    /**
     * Descricao do que a Skill faz
     */
    protected String descricao;
    
    
    public HabilidadeBase() {
        descricao = "";
        nome = "";
        setDescricao();
        setNome();
        setCooldDown();
    }
    
    /**
     *
     * @param tipo pode ser "Ofensivo" ou "Defensivo", caso contrario skill será
     * ignorada
     */
    public void setTipo(Acao tipo) {
        this.tipo = tipo;
    }

    public Acao getTipo() {
        return tipo;
    }

    public CriaturaBase getDono() {
        return dono;
    }

    public void setDono(CriaturaBase dono) {
        this.dono = dono;
    }

    public Integer getProgessoRecarregamento() {
        return progessoRecarregamento;
    }
    
    public Integer tempoAtePoderUsarDeNovo()
    {
        return( tempoRecarregamento - progessoRecarregamento );
    }

    /**
     *
     * @return true se a skill nao estive em tempo de recarga
     */
    public boolean isNotOnCoolDown() {
        if (this.progessoRecarregamento == this.tempoRecarregamento) {
            return (true);
        } else {
            return (false);
        }
    }

    public String getNome() {
        return nome;
    }

    public Integer getTempoRecarregamento() {
        return tempoRecarregamento;
    }

    public void setTempoRecarregamento(Integer tempoRecarregamento) {
        this.tempoRecarregamento = tempoRecarregamento;
    }

    public String getDescricao() {
        return descricao;
    }
    

    /**
     * Diminui cooldown em 1 turno
     */
    public void incCooldown() {
        if (this.tempoRecarregamento != this.progessoRecarregamento) {
            this.progessoRecarregamento = this.progessoRecarregamento + 1;
        }
    }

    /**
     * Deixa skill disponivel em relacao ao cooldown
     */
    public void setAvailable() {
        this.progessoRecarregamento = this.tempoRecarregamento;
    }

    /**
     * Metodo que deve ser chamado quando skill for usada
     */
    abstract public void noUso(ArenaBatalha arena);
    
    abstract protected void setDescricao();
    
    abstract protected void setNome();
    
    abstract protected void setCooldDown();
}
