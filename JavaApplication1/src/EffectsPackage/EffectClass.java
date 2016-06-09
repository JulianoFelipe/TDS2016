/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EffectsPackage;

import CriaturasPackage.BaseCreature;
import utillities.Describable;
/**
 * Classe base para efeitos.
 * @author Paulo Henrique
 * @author Juliano Felipe
 */
public abstract class EffectClass implements Describable,Cloneable{
    /**
     * Tipo pode ser ou Ofensivo ou Defensivo
     */
    protected String tipo;
    
    /**
     * Indica se o efeito eh instantaneo(eh aplicado e logo depois removido)
     */
    protected Boolean isInstantaneo = false;
    
    /**
     * Duracao do efeito se ele nao for instantaneo
     */
    protected Integer duration;
    
    /**
     * A ser usado para cáculo de efeito multiplicativo.
     * Ex.: Uma cura de 10%, recuperaria 10% do
     * {@link javaapplication1.BaseCreature#max_hit_points}.
     */
    protected Double percentage_power_level;
    /**
     * A ser usado para cáculo de efeito aditivo.
     * Ex.: Um dano de 10 unidades, reduziria, aritmeticamente,
     * um número de acordo com defesas e skills do
     * {@link javaapplication1.BaseCreature#max_hit_points}.
     */
    protected Double const_power_level;

    /**
     * Construtor default
     */
    public EffectClass()
    {
        this.percentage_power_level = 0.00;
        this.const_power_level = 0.00;
    }
    
    /**
     * Constutor de efeito.
     * @param percentage_power_level Efeito multiplicativo.
     * @param const_power_level      Efeito aditivo.
     */
    public EffectClass(Double percentage_power_level, Double const_power_level) {
        this.percentage_power_level = percentage_power_level;
        this.const_power_level = const_power_level;
    }

    public Boolean getIsInstantaneo() {
        return isInstantaneo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
    
    
    /**
     * Construtor de copia
     * @param effect 
     */
    public EffectClass(EffectClass effect) {
        this.percentage_power_level = new Double(effect.percentage_power_level);
        this.const_power_level = new Double(effect.const_power_level);
        this.duration = new Integer(effect.duration);
        this.tipo = effect.tipo;
        this.isInstantaneo = new Boolean(effect.isInstantaneo);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    
    
    @Override
    public String getDescription()
    {
        String string_1,string_2,string_3;
        if (tipo.equals("Ofensivo"))
        {
            string_1 = "Em todos os inimigos,";
        }
        else if (tipo.equals("Defensivo"))
        {
            string_1 = "Em todos os aliados,";
        }
        else
        {
            string_1 = "ERRO DE TIPO!1!!";
        }
        
        if (isInstantaneo)
        {
            string_2 = "Instantaneamente,";
        }
        else
        {
            string_2 = "Durante " + this.duration + " turnos,";
        }
           
        return(
                string_1 + string_2
                
                
                );
    }

    /**
     * Aplica efeito em uma criatura
     * @param target Alvo do efeito
     */
    abstract public void onTarget(BaseCreature target);
}
