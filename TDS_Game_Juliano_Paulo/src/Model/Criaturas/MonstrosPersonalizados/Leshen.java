/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas.MonstrosPersonalizados;

import Model.Criaturas.Monstro;
import Model.Habilidades.HabilidadeBase;
import Model.Habilidades.HabilidadesPersonalizadas.InsigniaTerra;
import Model.Habilidades.HabilidadesPersonalizadas.Rejuvenacao;
import java.io.File;
import java.util.Random;

/**
 *
 * @author Paulo
 */
public class Leshen extends Monstro{
    public Leshen(int level)
    {
        this.setNome("Spriggan");
        Random gerador = new Random();
        
        int numeroRandom = gerador.nextInt(20);
        this.setAtaque(80 + (level-1)*50 + numeroRandom + 0.00);
        
        numeroRandom = gerador.nextInt(20);
        this.setDefesa(150 + (level-1)*50 + numeroRandom + 0.00);
        
        numeroRandom = gerador.nextInt(20);
        this.setVelocidade(90 + (level-1)*25 + numeroRandom + 0.00);
        
        numeroRandom = gerador.nextInt(200);
        this.setMaxPontosVida(900 + (level-1)*500 + (numeroRandom + 0.00));
        this.setPontosVida(new Double( this.getMaxPontosVida() ));
        
        this.setLevel(level);
        
        HabilidadeBase habilidade1 = new Rejuvenacao(this);
        habilidade1.setPrioridade(3);
        
        HabilidadeBase habilidade2 = new InsigniaTerra(this);
        habilidade2.setPrioridade(1);
    }
    
    @Override
    public File getArquivoDeImagem() {
        return(new File(getClass().getResource("/View/Imagens/Monstros/leshen.png").getFile()));
    }
}
