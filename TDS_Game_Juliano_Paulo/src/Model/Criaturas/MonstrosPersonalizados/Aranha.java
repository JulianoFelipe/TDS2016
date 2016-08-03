/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas.MonstrosPersonalizados;

import Model.Criaturas.Monstro;
import Model.Habilidades.HabilidadeBase;
import Model.Habilidades.HabilidadesPersonalizadas.MordidaVenenosa;
import Model.Habilidades.HabilidadesPersonalizadas.TeiaAranha;
import java.io.File;
import java.util.Random;

/**
 * Um monstro personalizado que é uma aranha
 * @author Paulo
 */
public class Aranha extends Monstro{
    public Aranha(int level)
    {
        this.setNome("Aranha");
        Random gerador = new Random();
        
        int numeroRandom = gerador.nextInt(20);
        this.setAtaque(100 + (level-1)*50 + numeroRandom + 0.00);
        
        numeroRandom = gerador.nextInt(20);
        this.setDefesa(100 + (level-1)*50 + numeroRandom + 0.00);
        
        numeroRandom = gerador.nextInt(20);
        this.setVelocidade(90 + (level-1)*25 + numeroRandom + 0.00);
        
        numeroRandom = gerador.nextInt(200);
        this.setMaxPontosVida(500 + (level-1)*250 + (numeroRandom + 0.00));
        this.setPontosVida(new Double( this.getMaxPontosVida() ));
        
        this.setLevel(level);
        
        HabilidadeBase habilidade1 = new TeiaAranha(this);
        habilidade1.setPrioridade(1);
        
        HabilidadeBase habilidade2 = new MordidaVenenosa(this);
        habilidade2.setPrioridade(1);
    }
    
    @Override
    public File getArquivoDeImagem() {
        return(new File(getClass().getResource("/View/Imagens/Monstros/aranha_icon.png").getFile()));
    }
}
