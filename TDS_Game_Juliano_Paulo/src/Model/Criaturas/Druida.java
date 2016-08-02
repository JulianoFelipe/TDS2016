/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas;

import Model.Habilidades.ForcaNatural;
import Model.Habilidades.HabilidadeBase;
import java.io.File;

/**
 *
 * @author FREE
 */
public class Druida extends Heroi{
    public Druida(Jogador jogador) {
        super(jogador);
        this.setAtaque(100.00);
        this.setDefesa(50.00);
        this.setMaxPontosVida(1000.00);
        this.setPontosVida(1000.00);
        this.reset_temporary_stats();
        this.setNome("Druida");
        this.setVelocidade(300.00);
        this.setMultiplicadorPontosVida(1.20);
        
        HabilidadeBase habilidadeInicial = new ForcaNatural(this);
    }

    @Override
    public File getArquivoDeImagem() {
        return(new File(getClass().getResource("/View/Imagens/healer_imagem_2.jpg").getFile()));
    }
}
