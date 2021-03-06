/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas.MonstrosPersonalizados;

import Model.Criaturas.Monstro;
import Model.Habilidades.HabilidadeBase;
import Model.Habilidades.HabilidadesPersonalizadas.Encorajamento;
import Model.Habilidades.HabilidadesPersonalizadas.ExplorandoFraqueza;
import java.io.File;
import java.util.Random;

/**
 *
 * Monstro personalizado
 */
public class Ave extends Monstro{
    public Ave(int level)
    {
        this.setNome("Ave");
        Random gerador = new Random();
        
        int numeroRandom = gerador.nextInt(20);
        this.setAtaque(110 + (level-1)*50 + numeroRandom + 0.00);
        
        numeroRandom = gerador.nextInt(20);
        this.setDefesa(80 + (level-1)*40 + numeroRandom + 0.00);
        
        numeroRandom = gerador.nextInt(20);
        this.setVelocidade(120 + (level-1)*35 + numeroRandom + 0.00);
        
        numeroRandom = gerador.nextInt(200);
        this.setMaxPontosVida(500 + (level-1)*250 + numeroRandom + 0.00);
        
        setLevel(level);
        
        HabilidadeBase habilidade1 = new ExplorandoFraqueza(this);
        habilidade1.setPrioridade(1);
        
        HabilidadeBase habilidade2 = new Encorajamento(this);
        habilidade2.setPrioridade(1);
        
    }
    
    @Override
    public File getArquivoDeImagem() {
        return(new File(getClass().getResource("/View/Imagens/Monstros/ave.jpg").getFile()));
    }
}
