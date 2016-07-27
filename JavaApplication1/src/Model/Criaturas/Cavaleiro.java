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
 * Classe Knight, comportamento da classe de herois disponiveis Knigth com
 * limite de Armas,Armaduras o quanto os status aumentam com o level,etc
 *
 * @author Paulo Henrique
 * @author Juliano Felipe
 */
public class Cavaleiro extends Heroi {

    /**
     * Construtor que define os atributos de um Cavaleiro. Por hora um cavaleiro
     * tem 10 skills disponiveis ao ser criado
     */
    public Cavaleiro() {
        this.setAtaque(400.00);
        this.setDefesa(200.00);
        this.setMax_pontos_vida(1000.00);
        this.setPontos_vida(1000.00);
        this.setEsquiva(20);
        this.reset_temporary_stats();
        this.setNome("Sr.Duke of Cornwall");
        this.setVelocidade(200.00);
        this.setHp_multiplier(2.00);

    }

    @Override
    public File getImagemFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}