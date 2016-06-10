/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ItensPackage;

/**
 * Classe abstrata de itens consumiveis
 *
 */
public abstract class BaseConsumableItem extends BaseItem {

    /**
     * Chamada ao consumir o item
     */
    abstract public void onConsume();

}
