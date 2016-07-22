/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CriaturasPackage;

import EffectsPackage.EffectClass;
import java.util.ArrayList;
import utillities.*;
import java.util.Random;
import SkillPackage.BaseSkill;

/**
 * Classe abstrata que dita uma criatura, monstro ou herói.
 *
 * @author Paulo Henrique
 * @author Juliano Felipe
 */
public abstract class BaseCreature implements Comparable, Describable {

    // <editor-fold defaultstate="collapsed" desc="Atributos">

    /**
     * Valor que define speed minima
     */
    public static final double MIN_SPEED = 10;

    /**
     * Valor de attack bar que deve ser alcançado para se mover
     */
    public static final double ATTACK_BAR_TO_MOVE = 1000000;

    /**
     * Constante que define a ação de ataque.
     */
    public static final int ATTACK_PROTOCOL = 1;
    /**
     * Constante que define a ação de defesa.
     */
    public static final int SKILL_PROTOCOL = 2;

    /**
     * Constante que define a acao de ignorar um round
     */
    public static final int IGNORE_ROUND_PROTOCOL = 3;

    /**
     * Constante que indica que deve ser mostrado um relatorio
     */
    public static final int REPORT_PROTOCOL = 4;

    /**
     * Nome da criatura
     */
    protected String nome;

    /**
     * level da criatura
     */
    protected int level = 1;

    /**
     * Vida da criatura
     */
    protected Double hit_points = 0.00; //Pontos de ataque

    /**
     * Ataque da criatura
     */
    protected Double attack = 0.00;//ataque da critura

    /**
     * Defesa da criatura
     */
    protected Double defense = 0.00;//defesa da criatura

    /**
     * Max vida da criatura
     */
    protected Double max_hit_points = 0.00; //Vida da criatura

    /**
     * Ataque bar da criatura, quando {@code attack_bar == attack_max} to move criatura
     * ganhara turno
     */
    protected Double attack_bar = 0.00; // quando attack_bar chegar a 100.00 então a criatura agirá

    //porcentagem de 0-100%
    /**
     * Dodge da criatura
     */
    protected Integer dodge = 0;

    /**
     * Velocidade da criatura
     */
    protected Double speed = 0.00;

    /**
     * Mana da criatura
     */
    protected Double mana = 0.00;

    /**
     * MaxMana da criatura
     */
    protected Double max_mana = 0.00;

    /**
     * Mana recuperado por turno
     */
    protected Double mana_regain = 0.00;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Atributos Temporários">
    
    //relacionado a buffs locais,status de armas e armaduras,ex skill que dobra ataque vai dobrar ataque na batalha mas nao o valor de ataque do personagem
    protected Double temp_hit_points = 0.00;
    protected Double temp_attack = 0.00;
    protected Double temp_defense = 0.00;
    protected Double temp_dodge = 0.00;
    protected Double temp_speed = 0.00;
    protected Double temp_mana_regain = 0.00;

    // </editor-fold>
    
    /**
     * Lista de habilidades de BaseCreature
     */
    protected ArrayList< BaseSkill> lista_de_habilidades = new ArrayList<>();

    /**
     * Lista de efeitos que a BaseCreature esta sofrendo
     */
    protected ArrayList< EffectClass> lista_de_efeitos = new ArrayList<>();

    /**
     * Lista de efeitos instantaneos que a criatura sofrera instantaneamente
     */
    protected ArrayList< EffectClass> lista_de_efeitos_instantaneos = new ArrayList<>();

    /**
     * Booleano que indica que criatura esta viva ou nao
     */
    protected boolean isAlive;

    // <editor-fold defaultstate="collapsed" desc="Construtores">
    public BaseCreature() {
        isAlive = true;
    }

    // </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="Getters & Setters">
    
    public boolean isAlive() {
        return (isAlive);
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

    public Double getMax_mana() {
        return max_mana;
    }

    public void setMax_mana(Double max_mana) {
        this.max_mana = max_mana;
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

    public String getNome() {
        return nome;
    }

    public ArrayList<EffectClass> getLista_de_efeitos() {
        return lista_de_efeitos;
    }

    public void setLista_de_efeitos(ArrayList<EffectClass> lista_de_efeitos) {
        this.lista_de_efeitos = lista_de_efeitos;
    }

    public ArrayList<EffectClass> getLista_de_efeitos_instantaneos() {
        return lista_de_efeitos_instantaneos;
    }

    public void setLista_de_efeitos_instantaneos(ArrayList<EffectClass> lista_de_efeitos_instantaneos) {
        this.lista_de_efeitos_instantaneos = lista_de_efeitos_instantaneos;
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

    public Double getMana_regain() {
        return mana_regain;
    }

    public void setMana_regain(Double mana_regain) {
        this.mana_regain = mana_regain;
    }

    public Double getTemp_mana_regain() {
        return temp_mana_regain;
    }

    public void setTemp_mana_regain(Double temp_mana_regain) {
        this.temp_mana_regain = temp_mana_regain;
    }

    public ArrayList<BaseSkill> getLista_de_habilidades() {
        return lista_de_habilidades;
    }

    public void setLista_de_habilidades(ArrayList<BaseSkill> lista_de_habilidades) {
        this.lista_de_habilidades = lista_de_habilidades;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public int getLevel() {
        return level;
    }

    public Double getTemp_dodge() {
        return temp_dodge;
    }

    public void setTemp_dodge(Double temp_dodge) {
        this.temp_dodge = temp_dodge;
    }

    public Double getTemp_speed() {
        return temp_speed;
    }

    public void setTemp_speed(Double temp_speed) {
        this.temp_speed = temp_speed;
    }

    /**
     * Speed usada em calculos efetivamente
     *
     * @return speed usada em calculos
     */
    public Double getEffectiveSpeed() {
        Double retorno = this.speed + this.temp_speed;
        if (retorno <= MIN_SPEED) {
            return (MIN_SPEED);
        }
        return (retorno);
    }

    /**
     * Attack usada em calculos efetivamente
     *
     * @return speed usada em calculos
     */
    public Double getEffectiveAttack() {
        return (this.attack + this.temp_attack);
    }

    /**
     * Defense usada em calculos efetivamente
     *
     * @return speed usada em calculos
     */
    public Double getEffectiveDefense() {
        return (this.defense + this.temp_defense);
    }

    /**
     * Mana regain usado em calculos
     *
     * @return valor que deve ser usado em calculos
     */
    public Double getEffectiveManaRegain() {
        return (this.mana_regain + this.temp_mana_regain);
    }

    /**
     * Aumenta ataque
     *
     * @param increment o quanto aumenta
     */
    public void incAttack(double increment) {
        double after_increment = this.temp_attack + increment;
        this.temp_attack = after_increment;
    }

    /**
     * Aumenta defensa
     *
     * @param increment o quanto aumenta
     */
    public void incDefense(double increment) {
        double after_increment = this.temp_defense + increment;
        this.temp_defense = after_increment;
    }

    /**
     * Aumenta speed
     *
     * @param increment o quanto aumenta
     */
    public void incSpeed(double increment) {
        double after_increment = this.temp_speed + increment;
        this.temp_speed = after_increment;
    }

    /**
     * Aumenta mana regain
     *
     * @param increment o quanto aumenta
     */
    public void incManaRegain(double increment) {
        double after_increment = this.temp_mana_regain + increment;
        this.temp_mana_regain = after_increment;
    }

    /**
     * Aumenta atkbar percentualmente
     *
     * @param percentage_increment o quanto aumenta percentual
     */
    public void incAttackBar(int percentage_increment) {
        double increment = BaseCreature.ATTACK_BAR_TO_MOVE * ((percentage_increment+0.00) / (100.00));
        double after_increment = this.attack_bar + increment;
        if (after_increment > BaseCreature.ATTACK_BAR_TO_MOVE) {
            after_increment = BaseCreature.ATTACK_BAR_TO_MOVE;
        }
        this.attack_bar = after_increment;
    }

    /**
     * Diminui ataque
     *
     * @param decrement O quanto diminui.
     */
    public void decAttack(double decrement) {
        double after_decrement = this.temp_attack - decrement;
        this.temp_attack = after_decrement;
    }

    /**
     * Diminui speed
     *
     * @param decrement O quanto diminui.
     */
    public void decSpeed(double decrement) {
        double after_decrement = this.temp_speed - decrement;
        this.temp_speed = after_decrement;
    }

    /**
     * Diminui defesa
     *
     * @param decrement O quanto diminui.
     */
    public void decDefense(double decrement) {
        double after_decrement = this.temp_defense - decrement;
        this.temp_defense = after_decrement;
    }

    /**
     * Diminui mana regain
     *
     * @param decrement O quanto diminui.
     */
    public void decManaRegain(double decrement) {
        double after_decrement = this.temp_mana_regain - decrement;
        this.temp_mana_regain = after_decrement;
    }

    /**
     * Diminui ataquebar percentualmente
     *
     * @param percentual_decrement O quanto diminui.
     */
    public void decAttackBar(int percentual_decrement) {
        double decrement = BaseCreature.ATTACK_BAR_TO_MOVE * ((percentual_decrement+0.00) / (100.00));
        double after_decrement = this.attack_bar - decrement;
        if (after_decrement < 0.00) {
            after_decrement = 0.00;
        }
        this.attack_bar = after_decrement;
    }

    /**
     * cura criatura em valor_cura
     *
     * @param valor_cura valor da cura
     */
    public void heal(Double valor_cura) {
        Double vida_depois_da_cura = this.hit_points + valor_cura;
        if (vida_depois_da_cura > this.max_hit_points) {
            this.hit_points = this.max_hit_points;
        } else {
            this.hit_points = vida_depois_da_cura;
        }
    }

    public void regainMana() {
        double mana_after_regain = this.mana + this.getEffectiveManaRegain();
        if (mana_after_regain > this.max_mana) {
            this.mana = this.max_mana;
        } else {
            this.mana = mana_after_regain;
        }
    }

    /**
     * reseta status temporarios
     */
    public void resetTempStats() {
        this.temp_attack = 0.00;
        this.temp_defense = 0.00;
        this.temp_dodge = 0.00;
        this.temp_hit_points = 0.00;
        this.temp_mana_regain = 0.00;
        this.temp_speed = 0.00;
    }

    /**
     * Chamada para verificar bonus de Weapons(armas)
     */
    abstract public void applyWeaponEffects();

    /**
     * Chamada para verificar bonus de Armor(armadura)
     */
    abstract public void applyArmorEffects();

    /**
     * Aplica todos os efeitos
     */
    public void applyAllEffects() {
        for (EffectClass effect : this.getLista_de_efeitos()) {
            effect.onTarget(this);
        }
        for (EffectClass effect : this.lista_de_efeitos_instantaneos) {
            effect.onTarget(this);
        }
        this.lista_de_efeitos_instantaneos.clear();
    }

    /**
     * Os efeitos que chegarem ao fim sao removidos os outros suas duracao sao
     * decrementadas
     */
    public void removeOutdatedEffects() {
        int i = 0;
        while (true) {
            if (i >= this.lista_de_efeitos.size()) {
                break;
            }
            EffectClass efeito = this.lista_de_efeitos.get(i);
            if (efeito.getDuration() == 0) {
                //remove efeito
                System.out.println("effect clear!");
                this.lista_de_efeitos.remove(i);
                i--;
            } else {
                int new_duration = 0;
                new_duration = efeito.getDuration() - 1;
                efeito.setDuration(new_duration);
            }
            i++;
        }
    }

    /**
     * Deixa todas as skill disponiveis em relacao ao cooldown(tempo de recarga)
     */
    public void resetTotallySkillsCD() {
        for (BaseSkill skill : this.lista_de_habilidades) {
            skill.setAvailable();
        }
    }

    /**
     * Diminui em um o cooldown de todas as skills
     */
    public void resetParcialySkillsCD() {
        for (BaseSkill skill : this.lista_de_habilidades) {
            skill.incCooldown();
        }
    }

    /**
     * Chamada pra utilizar mana
     *
     * @param amount quanto de mana foi utilizada
     */
    public void useMana(double amount) {
        double mana_after_decrease = this.mana - amount;
        if (mana_after_decrease < 0)//nao decrementa mais que zero
        {
            this.mana = 0.00;
        } else {
            this.mana = mana_after_decrease;
        }
    }

    /**
     * Metodo chamado quando creatura inicia combate
     */
    public void onStart() {
        resetTotallySkillsCD();
        this.hit_points = this.max_hit_points;
        this.mana = this.max_mana;
    }

    /**
     * Metodo chamado toda vez que criatura ganha turno
     */
    public void everyTurn() {
        //do something
        resetParcialySkillsCD();
        regainMana();
        removeOutdatedEffects();
    }

    /**
     * Metodo chamado toda vez fim de turno de qualquer criatura
     */
    public void everyTime() {
        //do something
        resetTempStats();
        applyAllEffects();
        applyWeaponEffects();
        applyArmorEffects();
    }

    /**
     *
     * @return Array com as skills que o BaseCreature poderá usar pois tem mana
     * suficiente e as skills nao estao em tempo de recarga
     */
    public ArrayList<BaseSkill> getUsableSkillsArray() {
        ArrayList<BaseSkill> retorno = new ArrayList<>();
        for (BaseSkill skill : this.lista_de_habilidades) {
            if (skill.isReady() == 0) {
                retorno.add(skill);
            }
        }
        return (retorno);
    }

    /**
     * Gera string que informa as skill que nao podem ser usadas e o motivo
     *
     * @return string com informações
     */
    public String getUnusableSkills() {
        StringBuilder s = new StringBuilder();
        for (BaseSkill skill : this.lista_de_habilidades) {
            switch (skill.isReady()) {
                case 1:
                    s.append("COOLDOWN(" + (skill.getCooldown_time() - skill.getLocal_cooldown()) + ")->" + skill.getDescription() + '\n');
                    break;
                case 2:
                    System.out.println("owner mana = " + this.getMana());
                    s.append("MANAINSUFFICIENT(" + (skill.getMana() - this.getMana()) + ")->" + skill.getDescription() + "\n");
                    break;
                case 3:
                    s.append("COOLDOWN(" + (skill.getCooldown_time() - skill.getLocal_cooldown()) + "MANAINSUFFICIENT(" + (skill.getMana() - this.getMana()) + ")->" + skill.getDescription() + "\n");
                    break;
                default:
                    //donothing
                    break;
            }
        }
        return (s.toString());
    }

    // </editor-fold> 
    
    // <editor-fold defaultstate="collapsed" desc="Overrides">
    /**
     * Comparacao é feito por : todo vivo é maior que todo morto Caso os dois
     * estejam vivos : o maior será quem tem maior attack_bar Caso os dois
     * possuam a mesma attack_bar : o maior será quem tem o maior speed Se o
     * objeto comparado nao for parte da classe BaseCreature retorna 0
     *
     * @param o Objeto para comparação.
     * @return  Positivo se o objeto comparado for maior que esse objeto, 
     *          negativo se o objeto comparado for menor e 0 caso forem 
     *          iguais.
     */
    @Override
    public int compareTo(Object o) {
        if (o instanceof BaseCreature) {
            BaseCreature other_creature = (BaseCreature) o;
            int comparacao_0 = 0;
            if (other_creature.isAlive) {
                comparacao_0++;
            }
            if (this.isAlive) {
                comparacao_0--;
            }
            double comparacao_1 = other_creature.attack_bar - this.attack_bar;
            double comparacao_2 = other_creature.getEffectiveSpeed() - this.getEffectiveSpeed();
            if (comparacao_0 != 0) {
                return (comparacao_0);
            } else {
                if (comparacao_1 < 0) {
                    return ((int) Math.floor(comparacao_1));
                } else {
                    if (comparacao_1 > 0) {
                        return ((int) Math.ceil(comparacao_1));
                    } else {
                        if (comparacao_2 < 0) {
                            return ((int) Math.floor(comparacao_2));
                        } else {
                            if (comparacao_2 > 0) {
                                return ((int) Math.ceil(comparacao_2));
                            } else {
                                return (0);
                            }
                        }
                    }
                }
            }
        } else {
            return (0);
        }
    }

    @Override
    public String toString() {
        return ("Nome: " + this.nome + '\n'
                + "HP: " + this.hit_points + '\n'
                + "AtkBar: " + (Math.floor(this.attack_bar * 10000 / BaseCreature.ATTACK_BAR_TO_MOVE) / 100) + '\n'
                + "Speed: " + this.getSpeed() + "(" + this.getTemp_speed() + ")" + '\n'
                + "Atk: " + this.getAttack() + "(" + this.getTemp_attack() + ")" + '\n'
                + "Defense: " + this.getDefense() + "(" + this.getTemp_defense() + ")" + '\n'
                + "Effects: " + this.getLista_de_efeitos());
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Lógica de Jogo">
    /**
     * Reseta status temporarios devido a efeitos e a status de armas e
     * armaduras
     */
    public void reset_temporary_stats() {
        temp_hit_points = 0.00;
        temp_attack = 0.00;
        temp_defense = 0.00;
        temp_dodge = 0.00;
        temp_speed = 0.00;
        temp_mana_regain = 0.00;
    }

    /**
     * Se true então ataque será ignorado completamente, caso seja false não
     * ignorará
     *
     * @param dodge_penalty Se existir alguma penalidade para dodge colocar aqui.
     * @param attack_roll   O valor que o atacante gerou.
     * @return              True se o attack_roll for maior ou igual que o
     *                      dodge-dodge_penalty, false caso contrario.
     */
    public boolean willDodge(int dodge_penalty, int attack_roll) {
        //System.out.println("attack_roll = "+attack_roll+"\n"+"dodge = "+((dodge+temp_dodge)-dodge_penalty-effects_penalty));
        if (attack_roll >= (dodge + temp_dodge)) {
            return (false);
        } else {
            return (true);
        }
    }

    /**
     * Se existir alguma logica que afeta a chance de atacar ela vem aqui.
     *
     * @return valor que afeta chance de ataque
     */
    protected int get_effects_attack_penalty() {
        return (0);
    }

    /**
     * Gera um valor de ataque que decide se o ataque deverá ser ignorado.
     *
     * @return valor de ataque.
     */
    public int getAttackRoll() {
        int effects_penalty = get_effects_attack_penalty();
        Random generator = new Random();
        int attack_roll = generator.nextInt(101) - effects_penalty;
        return (attack_roll);
    }

    /**
     * Metodo chamado quando a criatura sofrer dano.
     *
     * @param damage Sofrido pela criatura.
     */
    public void takeDamage(Double damage) {
        hit_points = hit_points - damage;
    }

    /**
     * Metodo chamado quando a criatura morrer.
     */
    public void onDeath() {
        hit_points = 0.00;
        isAlive = false;
        System.out.println(this.nome + " morreu!");
    }

    @Override
    public String getDescription() {
        return (this.getNome());
    }

    // </editor-fold>  
}
