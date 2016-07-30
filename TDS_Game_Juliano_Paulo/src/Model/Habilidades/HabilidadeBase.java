/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Habilidades;

import Controller.ControleArena;
import Model.Acao;
import Model.Criaturas.CriaturaBase;
import Model.Criaturas.Monstro;
import Model.Efeitos.EfeitoAtributos;
import Model.Efeitos.Efeitos;
import Model.Geradores.ArenaBatalha;
import View.FrameExibido;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe base para habilidades (skills).
 *
 * @author Paulo Tenório
 */
public abstract class HabilidadeBase{
    public static ControleArena controle;
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
    protected Integer progressoRecarregamento;

    /**
     * tempo de recarga para poder usar a skill novamente
     * em turnos
     */
    protected Integer tempoRecarregamento;

    /**
     * Descricao do que a Skill faz
     */
    protected String descricao;
    
    
    public HabilidadeBase(CriaturaBase criatura_dono) {
        descricao = "";
        nome = "";
        dono = criatura_dono;
        dono.getListaDeHabilidades().add(this);
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

    public Integer getProgressoRecarregamento() {
        return progressoRecarregamento;
    }
    
    public Integer tempoAtePoderUsarDeNovo()
    {
        return( tempoRecarregamento - progressoRecarregamento );
    }

    /**
     *
     * @return true se a skill nao estive em tempo de recarga
     */
    public boolean isNotOnCoolDown() {
        if (this.progressoRecarregamento == this.tempoRecarregamento) {
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
    
    protected void solicitarIndice(HabilidadeBase habilidade_em_espera,List< CriaturaBase > lista_de_opcoes)
    {
        controle.habilidade = habilidade_em_espera;
        controle.opcoes_criaturas_alvos = lista_de_opcoes;
        controle.frame_a_exibir = FrameExibido.ESCOLHER_CRIATURA_SKILL;
        controle.criarProximoFrame();
    }
    
    protected List< CriaturaBase > pegarInimigosVivos(ArenaBatalha arena)
    {
        List< CriaturaBase > inimigos_vivos = new ArrayList<>();
        for (CriaturaBase criatura : arena.getListaDeVivos())
        {
            if (this.getDono() instanceof Monstro)
            {
                if (criatura instanceof CriaturaBase)
                {
                    inimigos_vivos.add(criatura);
                }
            }
            else
            {
                if (criatura instanceof Monstro)
                {
                    inimigos_vivos.add(criatura);
                }
            }
        }
        return( inimigos_vivos );
    }
    
    protected List< CriaturaBase > pegarInimigosMortos(ArenaBatalha arena)
    {
        List< CriaturaBase > inimigos_mortos = new ArrayList<>();
        for (CriaturaBase criatura : arena.getListaDeMortos())
        {
            if (this.getDono() instanceof Monstro)
            {
                if (criatura instanceof CriaturaBase)
                {
                    inimigos_mortos.add(criatura);
                }
            }
            else
            {
                if (criatura instanceof Monstro)
                {
                    inimigos_mortos.add(criatura);
                }
            }
        }
        return( inimigos_mortos );
    }
    
    protected List< CriaturaBase > pegarAliadosVivos(ArenaBatalha arena)
    {
        List< CriaturaBase > aliados_vivos = new ArrayList<>();
        for (CriaturaBase criatura : arena.getListaDeVivos())
        {
            if (this.getDono() instanceof Monstro)
            {
                if (criatura instanceof Monstro)
                {
                    aliados_vivos.add(criatura);
                }
            }
            else
            {
                if (criatura instanceof CriaturaBase)
                {
                    aliados_vivos.add(criatura);
                }
            }
        }
        return( aliados_vivos );
    }
    
    protected List< CriaturaBase > pegarAliadosMortos(ArenaBatalha arena)
    {
        List< CriaturaBase > aliados_mortos = new ArrayList<>();
        for (CriaturaBase criatura : arena.getListaDeMortos())
        {
            if (this.getDono() instanceof Monstro)
            {
                if (criatura instanceof Monstro)
                {
                    aliados_mortos.add(criatura);
                }
            }
            else
            {
                if (criatura instanceof CriaturaBase)
                {
                    aliados_mortos.add(criatura);
                }
            }
        }
        return( aliados_mortos );
    }
    
    
    

    /**
     * Diminui cooldown em 1 turno
     */
    public void incCooldown() {
        if (this.tempoRecarregamento != this.progressoRecarregamento) {
            this.progressoRecarregamento = this.progressoRecarregamento + 1;
        }
    }

    /**
     * Deixa skill disponivel em relacao ao cooldown
     */
    public void setAvailable() {
        this.progressoRecarregamento = this.tempoRecarregamento;
    }

    /**
     * Metodo que deve ser chamado quando skill for usada
     */
    abstract public void noUso(ArenaBatalha arena);
    
    abstract public void noUso(ArenaBatalha arena,CriaturaBase criatura);
    
    abstract protected void setDescricao();
    
    abstract protected void setNome();
    
    abstract protected void setCooldDown();
    
    abstract public File pegarArquivoImagem();
}
