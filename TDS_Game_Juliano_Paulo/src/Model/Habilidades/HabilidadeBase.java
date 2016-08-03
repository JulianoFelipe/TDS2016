/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Habilidades;

import Controller.ControleArena;
import Model.Acao;
import Model.Criaturas.CriaturaBase;
import Model.Criaturas.Heroi;
import Model.Criaturas.Monstro;
import Model.Geradores.ArenaBatalha;
import View.FrameExibido;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe base para habilidades (skills).
 *
 * @author Paulo Tenório
 */
public abstract class HabilidadeBase{
    
//<editor-fold defaultstate="collapsed" desc="Banco de Dados">
    @Override
    public String toString() {
        return "HabilidadeBase{" + "tipo=" + tipo + ", nome=" + nome + ", progressoRecarregamento=" + progressoRecarregamento + ", tempoRecarregamento=" + tempoRecarregamento + ", descricao=" + descricao + '}';
    }
    
    private int habilidadeId;
    
    public int getHabilidadeId() {
        return habilidadeId;
    }
    
    public void setHabilidadeId(int habilidadeId) {
        this.habilidadeId = habilidadeId;
    }
//</editor-fold>
    
    public static ControleArena controle;
    /**
     * Dono da skill
     */
    private CriaturaBase dono = null;

    /**
     * Tipo de skill, ex:Ofensiva ou Defensiva
     */
    protected Acao tipo = null;

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
    
    /**
     * Variavel para auxiliar monstros a tomarem decisao
     * Os monstros tentaram sempre usar a habilidade com mais prioridade
     * Se tiver varias com a mesma prioridade maxima ele vai pegar uma delas aleatoriamente
     * Ajuda a brever comportamento do monstro
     */
    private Integer prioridade = 0;
    
    
    public HabilidadeBase(CriaturaBase criatura_dono) {
        descricao = "";
        nome = "";
        dono = criatura_dono;
        dono.getListaDeHabilidades().add(this);
        setDescricao();
        setNome();
        setCoolDown();
        setTipo();
    }
    
    public HabilidadeBase() {
        descricao = "";
        nome = "";
        dono = null;
        setDescricao();
        setNome();
        setCoolDown();
        setTipo();
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

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setDescricao (String descricao){
        this.descricao = descricao;
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

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }
    
    
    
    protected void solicitarIndice(HabilidadeBase habilidade_em_espera,List< CriaturaBase > lista_de_opcoes)
    {
        controle.setHabilidade( habilidade_em_espera );
        controle.setOpcoes_criaturas_alvos( lista_de_opcoes );
        controle.setFrameParaExibir( FrameExibido.ESCOLHER_CRIATURA_SKILL );
        controle.criarProximoFrame();
    }
    
    protected List< CriaturaBase > pegarInimigosVivos(ArenaBatalha arena)
    {
        List< CriaturaBase > inimigos_vivos = new ArrayList<>();
        for (CriaturaBase criatura : arena.getListaDeVivos())
        {
            if (this.getDono() instanceof Monstro)
            {
                if (criatura instanceof Heroi)
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
                if (criatura instanceof Heroi)
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
                if (criatura instanceof Heroi)
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
                if (criatura instanceof Heroi)
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
    
    abstract protected void setCoolDown();
    
    abstract public File pegarArquivoImagem();
    
    abstract public void setTipo();
}
