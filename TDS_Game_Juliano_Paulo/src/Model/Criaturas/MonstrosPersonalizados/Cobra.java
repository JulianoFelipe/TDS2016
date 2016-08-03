/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas.MonstrosPersonalizados;

import Model.Criaturas.Monstro;
import Model.Habilidades.HabilidadeBase;
import Model.Habilidades.HabilidadesPersonalizadas.MordidaVenenosa;
import Model.Habilidades.HabilidadesPersonalizadas.NuvemVenenosa;
import java.io.File;
import java.util.Random;

/**
 *
 * @author FREE
 */
public class Cobra extends Monstro{
    public Cobra(int level)
    {
        this.setNome("Cobra");
        Random gerador = new Random();
        
        int numeroRandom = gerador.nextInt(20);
        this.setAtaque(120 + (level*60) + numeroRandom + 0.00);
        
        numeroRandom = gerador.nextInt(20);
        this.setDefesa(100 + (level*50) + numeroRandom + 0.00);
        
        numeroRandom = gerador.nextInt(20);
        this.setVelocidade(80 + (level*20) + numeroRandom + 0.00);
        
        numeroRandom = gerador.nextInt(200);
        this.setMaxPontosVida(600 + (level*400) + numeroRandom + 0.00);
        
        HabilidadeBase habilidade1 = new NuvemVenenosa(this);
        habilidade1.setPrioridade(1);
        
        HabilidadeBase habilidade2 = new MordidaVenenosa(this);
        habilidade2.setPrioridade(1);
    }
    
    @Override
    public File getArquivoDeImagem() {
        return(new File(getClass().getResource("/View/Imagens/Monstros/cobra.jpg").getFile()));
    }
}
