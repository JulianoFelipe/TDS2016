/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas.MonstrosPersonalizados;

import Model.Criaturas.Monstro;
import Model.Habilidades.HabilidadeBase;
import Model.Habilidades.HabilidadesPersonalizadas.AtaqueEmGrupo;
import Model.Habilidades.HabilidadesPersonalizadas.EscudoDivino;
import Model.Habilidades.HabilidadesPersonalizadas.Nevasca;
import Model.Habilidades.HabilidadesPersonalizadas.Sobrecarga;
import Model.Habilidades.HabilidadesPersonalizadas.ZeroAbsoluto;
import java.io.File;

/**
 *
 * @author FREE
 */
public class DragaoVento extends Monstro{
    public DragaoVento()
    {
        this.setNome("DragaoVento");
        
        this.setAtaque(450.00);
        
        this.setDefesa(1000.00);
        
        this.setVelocidade(500.00);
        
        this.setMaxPontosVida(25000.00);
        this.setPontosVida(new Double( this.getMaxPontosVida() ));
        
        this.setLevel(10);
        
        HabilidadeBase habilidade1 = new Sobrecarga(this);
        habilidade1.setPrioridade(1);
        
        HabilidadeBase habilidade2 = new EscudoDivino(this);
        habilidade2.setPrioridade(2);
        
        HabilidadeBase habilidade3 = new AtaqueEmGrupo(this);
        habilidade3.setPrioridade(3);
    }
    
    @Override
    public File getArquivoDeImagem() {
        return(new File(getClass().getResource("/View/Imagens/Monstros/dragondevento.jpg").getFile()));
    }
}
