/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Itens;

import Model.Habilidades.HabilidadeBase;

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
    
    HabilidadeBase skill_associada;

    public HabilidadeBase getSkill_associada() {
        return skill_associada;
    }

    public void setSkill_associada(HabilidadeBase skill_associada) {
        this.skill_associada = skill_associada;
    }
    
    
    @Override
    public void onConsume() {
        if (this.getOwner() ==null)
        {
            System.out.println("owner = null em SkillScroll on Consume");
        }
        //this.getOwner().getLista_de_habilidades().add(skill_associada);
        //System.out.println(this.getOwner().getNome() + " aprendeu a skill " + this.getSkill_associada().getDescricao());
        this.onDrop();
    }
    
    @Override
    public String getDescricao()
    {
        return(this.getNome());
    }
    
}
