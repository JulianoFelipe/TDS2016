/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Itens;

import Model.Habilidades.HabilidadeBase;
import java.io.File;

/**
 *  Classe que contem uma skill para ser aprendida pelo personagem
 * 
 */
public class PergaminhoHabilidade extends ConsumivelBase{

//<editor-fold defaultstate="collapsed" desc="Banco de dados">
    private int pergaminhoId;
    
    public int getPergaminhoId() {
        return pergaminhoId;
    }
    
    public void setPergaminhoId(int pergaminhoId) {
        this.pergaminhoId = pergaminhoId;
    }
//</editor-fold>  
    
    HabilidadeBase habilidade_associada;

    public PergaminhoHabilidade(HabilidadeBase habilidade_associada)
    {
        super();
        this.habilidade_associada = habilidade_associada;
        setDescricao();
        this.setNome("Pergaminho");
    }
    
    public HabilidadeBase getSkill_associada() {
        return habilidade_associada;
    }

    public void setSkill_associada(HabilidadeBase skill_associada) {
        this.habilidade_associada = skill_associada;
    }
    
    
    @Override
    public void onConsume() {
        if (this.getHeroi() ==null)
        {
            System.out.println("owner = null em SkillScroll on Consume");
        }
        else
        {
            this.getHeroi().getListaDeHabilidades().add(habilidade_associada);
            this.habilidade_associada.setDono(this.getHeroi());
            this.getHeroi().getJogador().removeItem(this);
        }
        //this.getOwner().getLista_de_habilidades().add(skill_associada);
        //System.out.println(this.getOwner().getNome() + " aprendeu a skill " + this.getSkill_associada().getDescricao());
    }

    @Override
    public File getArquivoDeImagem() {
        return(new File(getClass().getResource("/View/Imagens/scroll_icon.jpg").getFile()));
    }
    
    @Override
    public void setDescricao()
    {
        descricao = "Ensina habilidade " + habilidade_associada.getNome() + "\nQue faz :\n" + habilidade_associada.getDescricao();
    }
    
}
