/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 * Classe base para habilidades (skills).
 * 
 * @author Paulo  Tenório
 */
public class BaseSkill {
    protected String nome;
    
    //mana necessaria para usa-la
    protected Double mana;
    
    //stamina necessaria para usa-la
    protected Double stamina;
    
    //tempo ate a skill ser usada de novo
    protected Double cooldown_time;

    public BaseSkill() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getMana() {
        return mana;
    }

    public void setMana(Double mana) {
        this.mana = mana;
    }

    public Double getStamina() {
        return stamina;
    }

    public void setStamina(Double stamina) {
        this.stamina = stamina;
    }

    public Double getCooldown_time() {
        return cooldown_time;
    }

    public void setCooldown_time(Double cooldown_time) {
        this.cooldown_time = cooldown_time;
    }
    
    
}
