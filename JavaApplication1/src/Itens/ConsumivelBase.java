/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Itens;

/**
 * Classe abstrata de itens consumiveis
 *
 */
public abstract class ConsumivelBase extends ItemBase {

    /**
     * Chamada ao consumir o item
     */
    abstract public void onConsume();

}
