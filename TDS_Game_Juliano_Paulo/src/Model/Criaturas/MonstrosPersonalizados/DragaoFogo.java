/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas.MonstrosPersonalizados;

import Model.Criaturas.Monstro;
import Model.Habilidades.HabilidadeBase;
import Model.Habilidades.HabilidadesPersonalizadas.Aniquilacao;
import Model.Habilidades.HabilidadesPersonalizadas.Conflagracao;
import Model.Habilidades.HabilidadesPersonalizadas.Encorajamento;
import Model.Habilidades.HabilidadesPersonalizadas.GolpeAtordoador;
import Model.Habilidades.HabilidadesPersonalizadas.InsigniaTerra;
import Model.Habilidades.HabilidadesPersonalizadas.Sobrecarga;
import java.io.File;
import java.util.Random;

/**
 *
 * @author Paulo
 */
public class DragaoFogo extends Monstro{
    public DragaoFogo()
    {
        this.setNome("DragaoFogo");
        
        this.setAtaque(1000.00);
        
        this.setDefesa(1000.00);
        
        this.setVelocidade(300.00);
        
        this.setMaxPontosVida(25000.00);
        this.setPontosVida(new Double( this.getMaxPontosVida() ));
        
        this.setLevel(10);
        
        HabilidadeBase habilidade1 = new Sobrecarga(this);
        habilidade1.setPrioridade(1);
        
        HabilidadeBase habilidade2 = new Conflagracao(this);
        habilidade2.setPrioridade(2);
        
        HabilidadeBase habilidade3 = new Aniquilacao(this);
        habilidade3.setPrioridade(3);
    }
    
    @Override
    public File getArquivoDeImagem() {
        return(new File(getClass().getResource("/View/Imagens/Monstros/homemleao.jpg").getFile()));
    }
}
