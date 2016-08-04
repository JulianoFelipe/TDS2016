/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas;

import Model.Itens.Constantes.Armaduras;
import Model.Itens.Constantes.Armas;
import java.io.File;

/**
 * Classe Knight, comportamento da classe de herois disponiveis Knigth com
 * limite de Armas,Armaduras o quanto os status aumentam com o level,etc
 *
 * @author Paulo Henrique
 * @author Juliano Felipe
 */
public class Cavaleiro extends Heroi {

    private static final Armas ARMA = Armas.Espada;
    private static final Armaduras ARMADURA = Armaduras.Armadura;
    
    /**
     * Construtor que define os atributos de um Cavaleiro. Por hora um cavaleiro
     * tem 10 skills disponiveis ao ser criado
     * @param jogador Que possui o herói.
     */
    public Cavaleiro(Jogador jogador) {
        super(jogador);
        this.setAtaque(400.00);
        this.setDefesa(200.00);
        this.setMaxPontosVida(1000.00);
        this.setPontosVida(1000.00);
        this.setEsquiva(20);
        this.reset_temporary_stats();
        this.setNome("Sr.Duke of Cornwall");
        this.setVelocidade(200.00);
        this.setMultiplicadorPontosVida(2.00);

    }

    @Override
    public File getArquivoDeImagem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param arma
     * @inhericDoc
     * @return 
     *//*
    @Override
    public boolean canEquip(ArmaBase arma) {
        return arma.getTipo() == Cavaleiro.ARMA;
    }

    @Override
    public boolean canEquip(ArmaduraBase armadura) {
        return armadura.getTipo() == Cavaleiro.ARMADURA;
    }*/
}
