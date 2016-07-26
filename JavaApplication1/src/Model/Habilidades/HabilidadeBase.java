/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Habilidades;

import Model.Criaturas.CriaturaBase;
import Model.Efeitos.EfeitoAtributos;
import Model.Efeitos.Efeito;
import java.util.List;

/**
 * Classe base para habilidades (skills).
 *
 * @author Paulo Tenório
 */
public abstract class HabilidadeBase{

    /**
     * Dono da skill
     */
    private CriaturaBase dono = null;

    /**
     * Tipo de skill, ex:Ofensiva ou Defensiva
     */
    private String tipo = null;

    /**
     * nome da skill
     */
    protected String nome;

    /**
     * variavel que se for igual a cooldown_time significa que a skill pode ser
     * usada
     */
    protected Integer local_cooldown;

    /**
     * tempo de recarga para poder usar a skill novamente em turnos
     */
    protected Integer cooldown_time;

    /**
     * Descricao do que a Skill faz
     */
    protected String descricao;
    
    
    /**
     *
     * @param tipo pode ser "Ofensivo" ou "Defensivo", caso contrario skill será
     * ignorada
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public CriaturaBase getDono() {
        return dono;
    }

    public void setDono(CriaturaBase dono) {
        this.dono = dono;
    }

    public Integer getLocal_cooldown() {
        return local_cooldown;
    }

    /**
     *
     * @return true se a skill nao estive em tempo de recarga
     */
    public boolean isNotOnCoolDown() {
        if (this.local_cooldown == this.cooldown_time) {
            return (true);
        } else {
            return (false);
        }
    }

    public HabilidadeBase() {
        descricao = "Default descricao";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCooldown_time() {
        return cooldown_time;
    }

    public void setCooldown_time(Integer cooldown_time) {
        this.cooldown_time = cooldown_time;
    }

    public String getDescricao() {
        return descricao;
    }

    /**
     * Diminui cooldown em 1 turno
     */
    public void incCooldown() {
        if (this.cooldown_time != this.local_cooldown) {
            this.local_cooldown = this.local_cooldown + 1;
        }
    }

    /**
     * Deixa skill disponivel em relacao ao cooldown
     */
    public void setAvailable() {
        this.local_cooldown = this.cooldown_time;
    }

    /**
     * Metodo que deve ser chamado quando skill for usada
     */
    abstract public void noUso(List< CriaturaBase > aliados_vivos,List< CriaturaBase > aliados_mortos,List< CriaturaBase > inimigos_vivos,List< CriaturaBase > inimigos_mortos);
    
    abstract public void setDescricao();
}
