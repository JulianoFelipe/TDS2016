/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ItensPackage;
import javaapplication1.BaseCreature;
/**
 *
 * @author Paulo.Tenorio
 */
public abstract class BaseUsableItem {
    protected BaseCreature item_owner;
    protected String item_name;

    public BaseCreature getItem_owner() {
        return item_owner;
    }

    public void setItem_owner(BaseCreature item_owner) {
        this.item_owner = item_owner;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }
    
    
}
