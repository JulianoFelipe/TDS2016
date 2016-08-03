/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas.MonstrosPersonalizados;

import Model.Criaturas.Monstro;
import Model.Habilidades.HabilidadeBase;
import Model.Habilidades.HabilidadesPersonalizadas.Encorajamento;
import Model.Habilidades.HabilidadesPersonalizadas.InsigniaFogo;
import Model.Habilidades.HabilidadesPersonalizadas.MordidaVenenosa;
import Model.Habilidades.HabilidadesPersonalizadas.Rugido;
import Model.Habilidades.HabilidadesPersonalizadas.TeiaAranha;
import java.io.File;
import java.util.Random;

/**
 *
 * @author FREE
 */
public class HomemLeao extends Monstro{
    public HomemLeao()
    {
        this.setNome("HomemLeao");
        Random gerador = new Random();
        
        int numeroRandom = gerador.nextInt(50);
        this.setAtaque(200 + numeroRandom + 0.00);
        
        numeroRandom = gerador.nextInt(50);
        this.setDefesa(200 + numeroRandom + 0.00);
        
        numeroRandom = gerador.nextInt(50);
        this.setVelocidade(140 + numeroRandom + 0.00);
        
        numeroRandom = gerador.nextInt(500);
        this.setMaxPontosVida(1500 + (numeroRandom + 0.00));
        this.setPontosVida(new Double( this.getMaxPontosVida() ));
        
        this.setLevel(level);
        
        HabilidadeBase habilidade1 = new Encorajamento(this);
        habilidade1.setPrioridade(1);
        
        HabilidadeBase habilidade2 = new InsigniaFogo(this);
        habilidade2.setPrioridade(2);
        
        HabilidadeBase habilidade3 = new Rugido(this);
        habilidade3.setPrioridade(3);
    }
    
    @Override
    public File getArquivoDeImagem() {
        return(new File(getClass().getResource("/View/Imagens/Monstros/homemleao.jpg").getFile()));
    }
}
