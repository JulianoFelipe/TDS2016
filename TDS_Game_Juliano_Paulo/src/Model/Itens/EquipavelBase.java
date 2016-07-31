/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Itens;

/**
 * Classe abstrata de itens equipaveis
 *
 */
public abstract class EquipavelBase extends ItemBase {

    /**
     * Chamada ao equipar o item
     */
    abstract public void onEquip();
    
    abstract public void aplicarEfeitosDeItem();
}
