/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas;

import Model.Itens.ArmaBase;
import Model.Itens.ArmaduraBase;
import java.io.File;

/**
 *
 * @author FREE
 */
public class Elesis extends Heroi{

    public Elesis()
    {
        this.setAtaque(1500.00);
        this.setDefesa(200.00);
        this.setMax_pontos_vida(1000.00);
        this.setPontos_vida(1000.00);
        this.setEsquiva(20);
        this.reset_temporary_stats();
        this.setNome("Elesis");
        this.setVelocidade(200.00);
        this.setHp_multiplier(2.00);
    }

    @Override
    public File getImagemFile() {
        return(new File(getClass().getResource("/View/Imagens/elesis_icon.png").getFile()));
    }
    
}
