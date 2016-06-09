/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ItensPackage;

/**
 * Classe abstrata de itens equipaveis
 * 
 */
public abstract class BaseEquipableItem extends BaseItem{
    /**
     * Chamada ao equipar o item
     */
    abstract public void onEquip();
}
