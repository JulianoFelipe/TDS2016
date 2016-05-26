/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ItensPackage;
import EffectsPackage.HealEffect;
import java.util.Objects;
/**
 *
 * @author Paulo.Tenorio
 */
public class HealingPotion extends BaseUsableItem implements UsableItensInterface {
    
    HealEffect efeito_de_cura;
    
    public HealingPotion(int potion_power)
    {
        efeito_de_cura = new HealEffect(0.00,potion_power*50.00);
        StringBuilder nome_potion = new StringBuilder();
        nome_potion.append("HealingPotion_"+Integer.toString(potion_power));
        setItem_name(nome_potion.toString());
    }

    @Override
    public void onUse() {
        efeito_de_cura.onCaster(item_owner);
    }

    @Override
    public void onDrop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onCollect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onSell() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof HealingPotion)
        {
            HealingPotion cast = (HealingPotion)obj;
            if (cast.getItem_name()==this.getItem_name())
            {
                return(true);
            }
        }
        return(false);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.efeito_de_cura);
        return hash;
    }
    
    
    
}
