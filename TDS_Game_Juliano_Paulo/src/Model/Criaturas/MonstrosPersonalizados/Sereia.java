/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas.MonstrosPersonalizados;

import Model.Criaturas.Monstro;
import Model.Habilidades.HabilidadeBase;
import Model.Habilidades.HabilidadesPersonalizadas.Encorajamento;
import Model.Habilidades.HabilidadesPersonalizadas.EscudoDivino;
import Model.Habilidades.HabilidadesPersonalizadas.GolpeAtordoador;
import Model.Habilidades.HabilidadesPersonalizadas.InsigniaTerra;
import Model.Habilidades.HabilidadesPersonalizadas.Rejuvenacao;
import Model.Habilidades.HabilidadesPersonalizadas.Seducao;
import java.io.File;
import java.util.Random;

/**
 *
 * @author Paulo
 */
public class Sereia extends Monstro{
    public Sereia()
    {
        this.setNome("Sereia");
        Random gerador = new Random();
        
        int numeroRandom = gerador.nextInt(50);
        this.setAtaque(150 + numeroRandom + 0.00);
        
        numeroRandom = gerador.nextInt(100);
        this.setDefesa(250 + numeroRandom + 0.00);
        
        numeroRandom = gerador.nextInt(50);
        this.setVelocidade(180 + numeroRandom + 0.00);
        
        numeroRandom = gerador.nextInt(500);
        this.setMaxPontosVida(3500 + (numeroRandom + 0.00));
        this.setPontosVida(new Double( this.getMaxPontosVida() ));
        
        this.setLevel(level);
        
        HabilidadeBase habilidade1 = new EscudoDivino(this);
        habilidade1.setPrioridade(3);
        
        HabilidadeBase habilidade2 = new Rejuvenacao(this);
        habilidade2.setPrioridade(1);
        
        HabilidadeBase habilidade3 = new Seducao(this);
        habilidade3.setPrioridade(2);
    }
    
    @Override
    public File getArquivoDeImagem() {
        return(new File(getClass().getResource("/View/Imagens/Monstros/homemleao.jpg").getFile()));
    }
}
