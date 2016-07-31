/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas;

import Model.Habilidades.GolpeAtordoador;
import Model.Habilidades.HabilidadeBase;
import Model.Habilidades.OndaDeShoque;
import Model.Itens.ArmaBase;
import Model.Itens.ArmaduraBase;
import java.io.File;

/**
 * Um heroi customizado
 * @author Paulo
 */
public class Elesis extends Heroi{

    public Elesis(Jogador jogador)
    {
        super(jogador);
        this.setAtaque(1000.00);
        this.setDefesa(200.00);
        this.setMaxPontosVida(1000.00);
        this.setPontosVida(1000.00);
        this.setEsquiva(20);
        this.reset_temporary_stats();
        this.setNome("Elesis");
        this.setVelocidade(150.00);
        this.setMultiplicadorPontosVida(2.00);
        
        HabilidadeBase habilidade_inicial = new OndaDeShoque(this);
        HabilidadeBase habilidade_inicial2 = new GolpeAtordoador(this);
        
        
    }

    @Override
    public File getArquivoDeImagem() {
        return(new File(getClass().getResource("/View/Imagens/elesis_icon.png").getFile()));
    }
    
}