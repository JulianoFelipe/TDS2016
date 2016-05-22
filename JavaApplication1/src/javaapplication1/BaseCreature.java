/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author FREE
 */
public class BaseCreature {
    protected String nome;
    protected String element;
    protected Double hit_points;
    protected Double attack;
    protected Double defense;
    protected Double max_hit_points;
    
    //porcentagem de 0-100%
    protected Double dodge;
    
    protected Double range;
    protected Double speed;
    
    protected Double temp_hit_points;
    protected Double temp_attack;
    protected Double temp_defense;
    protected Double temp_dodge;
    protected Double temp_range;
    protected Double temp_speed;
    
    
    public BaseCreature() {
    }
    
        public Double getDefense() {
        return defense;
    }

    public Double getMax_hit_points() {
        return max_hit_points;
    }

    public void setMax_hit_points(Double max_hit_points) {
        this.max_hit_points = max_hit_points;
    }
    
    public void setDefense(Double defense) {
        this.defense = defense;
    }

    public Double getDodge() {
        return dodge;
    }

    public void setDodge(Double dodge) {
        this.dodge = dodge;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getHit_points() {
        return hit_points;
    }

    public void setHit_points(Double hit_points) {
        this.hit_points = hit_points;
    }

    public Double getAttack() {
        return attack;
    }

    public void setAttack(Double attack) {
        this.attack = attack;
    }

    public Double getRange() {
        return range;
    }

    public void setRange(Double range) {
        this.range = range;
    }
    
    public void takeDamage(Double damage)
    {
        hit_points = hit_points - damage;
    }

    public Double getTemp_hit_points() {
        return temp_hit_points;
    }

    public void setTemp_hit_points(Double temp_hit_points) {
        this.temp_hit_points = temp_hit_points;
    }

    public Double getTemp_attack() {
        return temp_attack;
    }

    public void setTemp_attack(Double temp_attack) {
        this.temp_attack = temp_attack;
    }

    public Double getTemp_defense() {
        return temp_defense;
    }

    public void setTemp_defense(Double temp_defense) {
        this.temp_defense = temp_defense;
    }

    public Double getTemp_dodge() {
        return temp_dodge;
    }

    public void setTemp_dodge(Double temp_dodge) {
        this.temp_dodge = temp_dodge;
    }

    public Double getTemp_range() {
        return temp_range;
    }

    public void setTemp_range(Double temp_range) {
        this.temp_range = temp_range;
    }

    public Double getTemp_speed() {
        return temp_speed;
    }

    public void setTemp_speed(Double temp_speed) {
        this.temp_speed = temp_speed;
    }
    
    
}
