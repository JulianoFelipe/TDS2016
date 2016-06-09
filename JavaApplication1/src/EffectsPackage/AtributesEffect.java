/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EffectsPackage;

import CriaturasPackage.BaseCreature;

/**
 *  Classe que contem 7 diferentes tipos de possibilidades de efeito de acordo com a variavel tipo_efeito
 * @author FREE
 */
public class AtributesEffect extends EffectClass{
    /**
     * Valor minimo que tipo_efeito pode assumir
     */
    public static final int MIN_RANGE = 0;
    
    /**
     * Valor maximo que tipo_efeito_pode assumir
     */
    public static final int MAX_RANGE = 7;
    
    /**
     * Define comportamente de onTarget
     */
    Integer tipo_efeito = 0;
    
    /**
     * Descricao do efeito
     */
    String desc = "";
    
    /**
     * 
     * @param percentage_power_level poder percentual do efeito
     * @param const_power_level poder aditivo do efeito
     * @param tipo define comportamento do efeito
     */
    public AtributesEffect(Double percentage_power_level, Double const_power_level,int tipo) {
        super(percentage_power_level, const_power_level);
        this.tipo_efeito = tipo;
        switch (tipo)
        {
            case 0:
                this.tipo = "Ofensivo";
                break;
            case 1:
                this.tipo = "Defensivo";
                break;
            case 2:
                this.tipo = "Ofensivo";
                break;
            case 3:
                this.tipo = "Defensivo";
                break;
            case 4:
                this.tipo = "Ofensivo";
                break;
            case 5:
                this.tipo = "Defensivo";
                break;
            case 6:
                this.tipo = "Ofensivo";
                this.isInstantaneo = true;
                break;
            case 7:
                this.tipo = "Defensivo";
                this.isInstantaneo = true;
                break;
        }
    }
    
    /**
     * Construtor de copia
     * @param effect efeito copiado
     */
    public AtributesEffect(EffectClass effect) {
        super(effect);
        if (effect instanceof AtributesEffect)
        {
            AtributesEffect local_effect = (AtributesEffect)effect;
            this.tipo_efeito = new Integer(local_effect.tipo_efeito);
        }
        else
        {
            //donothing
        }
    }

    public int getTipo_efeito() {
        return tipo_efeito;
    }

    public void setTipo_efeito(int tipo_efeito) {
        this.tipo_efeito = tipo_efeito;
    }
    
    
    
    @Override
    public String getDescription()
    {
        String aux = super.getDescription();
        StringBuilder s = new StringBuilder();
        switch (tipo_efeito){
            case 0:
                s.append("Reduz ataque em "+this.percentage_power_level+" %.");
                break;
            case 1:
                s.append("Aumenta ataque em "+this.percentage_power_level+" %.");
                break;
            case 2:
                s.append("Reduz defesa em "+this.percentage_power_level+" %.");
                break;
            case 3:
                s.append("Aumenta defesa em "+this.percentage_power_level+" %.");
                break;
            case 4:
                s.append("Reduz velocidade em "+this.percentage_power_level+" %.");
                break;
            case 5:
                s.append("Aumenta velocidade em "+this.percentage_power_level+" %.");
                break;
            case 6:
                s.append("Reduz atkbar em "+this.percentage_power_level+" %.");
                break;
            case 7:
                s.append("Aumenta atkbar em "+this.percentage_power_level+" %."+"is istantaneo = " + this.isInstantaneo.toString());
                break;
            default:
                s.append("ESSA MSG NAO DEVE APARECER");
                break;
        }
        s.append('\n');
        return(aux + s.toString());
    }

    /**
     * Oque fazer com alvo do efeito
     * @param target alvo do efeito
     */
    @Override
    public void onTarget(BaseCreature target) {
        Double decrement = 0.00;
        Double increment = 0.00;
        switch (tipo_efeito) {
        //Atk modifier decrement
            case 0:
                decrement = target.getAttack()*percentage_power_level/100+const_power_level;
                target.decAttack(decrement);
                break;
        //Atk modifier increment
            case 1:
                increment = target.getAttack()*percentage_power_level/100+const_power_level;
                target.incAttack(increment);
                break;
        //Def modifier decrement
            case 2:
                decrement = target.getDefense()*percentage_power_level/100+const_power_level;
                target.decDefense(decrement);
                break;
        //Def modifier increment
            case 3:
                increment = target.getDefense()*percentage_power_level/100+const_power_level;
                target.incDefense(increment);
                break;
        //Speed modifier decrement
            case 4:
                decrement = target.getSpeed()*percentage_power_level/100+const_power_level;
                target.decSpeed(decrement);
                break;
        //Speed modifier increment
            case 5:
                increment = target.getSpeed()*percentage_power_level/100+const_power_level;
                target.incSpeed(increment);
                break;
        //Atk Bar Decrement
            case 6:
                decrement = percentage_power_level;
                target.decAttackBar(decrement.intValue());
                break;
        //Atk Bar Increment
            case 7:
                increment = percentage_power_level;
                target.incAttackBar(increment.intValue());
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return this.getDescription() + "\nDuracao restante = " + this.duration + "\n";
    }
    
    
    
}
