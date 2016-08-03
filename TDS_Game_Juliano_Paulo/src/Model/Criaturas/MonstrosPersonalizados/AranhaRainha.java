/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas.MonstrosPersonalizados;

import Model.Criaturas.Monstro;
import Model.Habilidades.HabilidadeBase;
import Model.Habilidades.HabilidadesPersonalizadas.MordidaVenenosa;
import Model.Habilidades.HabilidadesPersonalizadas.Proliferacao;
import Model.Habilidades.HabilidadesPersonalizadas.SuperficiePegajosa;
import Model.Habilidades.HabilidadesPersonalizadas.TeiaAranha;
import java.io.File;
import java.util.Random;

/**
 * Monstro chefe do primeiro andar
 * @author Paulo
 */
public class AranhaRainha extends Monstro{
    public AranhaRainha()
    {
        this.setNome("AranhaRainha");
        Random gerador = new Random();
        
        this.setAtaque(250.00);
        
        this.setDefesa(250.00);
        
        this.setVelocidade(150.00);
        
        this.setMaxPontosVida(5000.00);
        this.setPontosVida(5000.00);
        
        this.setLevel(3);
        
        HabilidadeBase habilidade1 = new TeiaAranha(this);
        habilidade1.setPrioridade(1);
        
        HabilidadeBase habilidade2 = new MordidaVenenosa(this);
        habilidade2.setPrioridade(1);
        
        HabilidadeBase habilidade3 = new Proliferacao(this);
        habilidade3.setPrioridade(3);
        
        HabilidadeBase habilidade4 = new SuperficiePegajosa(this);
        habilidade4.setPrioridade(2);
    }
    
    @Override
    public File getArquivoDeImagem() {
        return(new File(getClass().getResource("/View/Imagens/Monstros/aranharainha.jpg").getFile()));
    }
}
