/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 * Classe que determina atributos
 * base de uma arma.
 * 
 * @author Paulo Tenório
 */
public class BaseWeapon {
    protected String nome;
    protected Double range;
    protected Double attack_power;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getRange() {
        return range;
    }

    public void setRange(Double range) {
        this.range = range;
    }

    public Double getAttack_power() {
        return attack_power;
    }

    public void setAttack_power(Double attack_power) {
        this.attack_power = attack_power;
    }
    
}
