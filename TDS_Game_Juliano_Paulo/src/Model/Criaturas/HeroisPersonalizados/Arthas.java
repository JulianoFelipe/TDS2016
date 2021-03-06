/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas.HeroisPersonalizados;

import Model.Criaturas.Heroi;
import Model.Criaturas.Jogador;
import Model.Habilidades.HabilidadeBase;
import Model.Habilidades.HabilidadesPersonalizadas.Nevasca;
import java.io.File;

/**
 * Um heroi customizado
 * @author Paulo
 */
public class Arthas extends Heroi {

    public Arthas(Jogador jogador)
    {
        super(jogador);
        this.setAtaque(100.00);
        this.setDefesa(100.00);
        this.setMaxPontosVida(1000.00);
        this.setPontosVida(1000.00);
        this.reset_temporary_stats();
        this.setNome("Arthas");
        this.setVelocidade(100.00);
        
        HabilidadeBase habilidade = new Nevasca(this);
    }
    
    @Override
    public File getArquivoDeImagem() {
        return(new File(getClass().getResource("/View/Imagens/Herois/arthas.jpg").getFile()));
    }
    
}
