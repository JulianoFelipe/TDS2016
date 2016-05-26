/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.Random;

/**
 * Classe abstrata que dita uma criatura,
 * monstro ou herói.
 * @author Paulo Henrique
 * @author Juliano Felipe
 */
public abstract class BaseCreature implements Comparable{
    /**
     * Constante que define a ação de ataque.
     */
    public static final int ATTACK_PROTOCOL = 1;
    /**
     * Constante que define a ação de defesa.
     */
    public static final int DEFENSE_PROTOCOL = 2;
    
    protected String nome;
    protected String element;//elemento da criatura, ex:fogo,agua,etc
    protected Double hit_points; //Pontos de ataque
    protected Double attack;//ataque da critura
    protected Double defense;//defesa da criatura
    protected Double max_hit_points; //Vida da criatura
    protected Double attack_bar; // quando attack_bar chegar a 100.00 então a criatura agirá
    
    //porcentagem de 0-100%
    protected Integer dodge;
    
    protected Double range;
    protected Double speed;
    //mana,stamina,hp valores locais, ou seja max_hit_points em uma batalha nunca muda mais o hp pode cair
    protected Double mana;
    protected Double stamina;
    protected Double max_mana;
    protected Double max_stamina;
    
    //relacionado a buffs locais,ex skill que dobra ataque vai dobrar ataque na batalha mas nao o valor de ataque do personagem
    protected Double temp_hit_points;
    protected Double temp_attack;
    protected Double temp_defense;
    protected Double temp_dodge;
    protected Double temp_range;
    protected Double temp_speed;
    
    
    
    //CONSTRUTORES E OUTRAS COISAS CHATAS
    
    public BaseCreature() {
    }

    public Double getAttack_bar() {
        return attack_bar;
    }

    public void setAttack_bar(Double attack_bar) {
        this.attack_bar = attack_bar;
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

    public Double getMax_mana() {
        return max_mana;
    }

    public void setMax_mana(Double max_mana) {
        this.max_mana = max_mana;
    }

    public Double getMax_stamina() {
        return max_stamina;
    }

    public void setMax_stamina(Double max_stamina) {
        this.max_stamina = max_stamina;
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

    public Integer getDodge() {
        return dodge;
    }

    public void setDodge(Integer dodge) {
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
    
    @Override
    public int compareTo(Object o)
    {
        if (o instanceof BaseCreature)
        {
            BaseCreature other_creature = (BaseCreature)o;
            double comparacao_1 = other_creature.attack_bar-this.attack_bar;
            double comparacao_2 = other_creature.speed-this.speed;
            if (comparacao_1<0)
            {
                 return((int)Math.floor(comparacao_1));
            }
            else
            {
                if (comparacao_1>0)
                {
                    return((int)Math.ceil(comparacao_1));
                }
                else
                {
                    if (comparacao_2<0)
                    {
                        return((int)Math.floor(comparacao_2));
                    }
                    else
                    {
                        if (comparacao_2>0)
                        {
                            return((int)Math.ceil(comparacao_2));
                        }
                        else
                        {
                            return(0);
                        }
                    }
                }
            }
        }
        else
        {
            return(0);
        }
    }
    
    //FIM COISAS CHATAS
    
    
    
    //LOGICA DE JOGO
    public void reset_temporary_stats()
    {
        temp_hit_points=0.00;
        temp_attack=0.00;
        temp_defense=0.00;
        temp_dodge=0.00;
        temp_range=0.00;
        temp_speed=0.00;
    }
    
    protected int get_effects_dodge_penalty()
    {
        return(0);
    }
    
    public boolean willDodge(int dodge_penalty,int attack_roll)
    {
        int effects_penalty = get_effects_dodge_penalty();
        System.out.println("attack_roll = "+attack_roll+"\n"+"dodge = "+((dodge+temp_dodge)-dodge_penalty-effects_penalty));
        if (attack_roll >= ((dodge+temp_dodge)-dodge_penalty-effects_penalty))
        {
            return(false);
        }
        else
        {
            return(true);
        }
    }
    
    protected int get_effects_attack_penalty()
    {
        return(0);
    }
    
    public int getAttackRoll()
    {
        int effects_penalty = get_effects_attack_penalty();
        Random generator = new Random();
        int attack_roll = generator.nextInt(101)-effects_penalty;
        return(attack_roll);
    }
    
    public void takeDamage(Double damage)
    {
        hit_points = hit_points - damage;
    }
    
    public void onDeath()
    {
        System.out.println(this.nome+" morreu!");
    }
    
}
