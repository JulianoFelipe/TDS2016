/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas;

import Model.Itens.ArmaduraBase;
import Model.Itens.ArmaBase;
import Model.Habilidades.HabilidadeBase;
import java.io.File;

/**
 * Classe Mage, comportamento da classe de herois disponiveis Mago com limite de
 * Armas,Armaduras o quanto os status aumentam com o level,etc
 *
 */
public class Mago extends Heroi {

    /**
     * Construtor default, pode ser alterado para fazer testes. Por hora ele
     * gera uma skill para a classe mage
     */
    public Mago() {
        this.setAtaque(200.00);
        this.setDefesa(100.00);
        this.setMax_pontos_vida(1000.00);
        this.setPontos_vida(1000.00);
        this.setEsquiva(20);
        this.reset_temporary_stats();
        this.setNome("Galdalf");
        this.setVelocidade(75.00);
    }

    @Override
    public File getImagemFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}