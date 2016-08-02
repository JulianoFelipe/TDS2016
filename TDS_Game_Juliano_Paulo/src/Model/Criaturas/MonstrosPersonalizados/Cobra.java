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
 *
 * @author FREE
 */
public class Cobra extends Monstro{
    public Cobra()
    {
        this.setNome("Cobra");
        Random gerador = new Random();
        
        int numeroRandom = gerador.nextInt(20);
        this.setAtaque(100 + numeroRandom + 0.00);
        
        numeroRandom = gerador.nextInt(20);
        this.setDefesa(100 + numeroRandom + 0.00);
        
        numeroRandom = gerador.nextInt(20);
        this.setVelocidade(90 + numeroRandom + 0.00);
        
        numeroRandom = gerador.nextInt(200);
        this.setMaxPontosVida(500 + numeroRandom + 0.00);
        
        HabilidadeBase habilidade1 = new TeiaAranha(this);
        habilidade1.setPrioridade(1);
        
        HabilidadeBase habilidade2 = new MordidaVenenosa(this);
        habilidade2.setPrioridade(1);
    }
    
    @Override
    public File getArquivoDeImagem() {
        return(new File(getClass().getResource("/View/Imagens/Herois/elesis.jpg").getFile()));
        //return(new File(getClass().getResource("/View/Imagens/aranha_icon.png").getFile()));
    }
}
