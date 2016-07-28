/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Criaturas;

import utilidades.Descritivel;
import Model.Efeitos.Efeito;
import java.util.ArrayList;
import java.util.Random;
import Model.Habilidades.HabilidadeBase;
import java.io.File;

/**
 * Classe abstrata que dita uma criatura, monstro ou herói.
 *
 * @author Paulo Henrique
 * @author Juliano Felipe
 */
public abstract class CriaturaBase implements Comparable, Descritivel {

//<editor-fold defaultstate="collapsed" desc="Banco de dados">
    private int criaturaId;
    
    public int getCriaturaId() {
        return criaturaId;
    }
    
    public void setCriaturaId(int criaturaId) {
        this.criaturaId = criaturaId;
    }
//</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Atributos">

    /**
     * Valor que define velocidade minima
     */
    public static final double MIN_SPEED = 10;

    /**
     * Valor de ataque bar que deve ser alcançado para se mover
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
    protected Double pontos_vida = 0.00; //Pontos de ataque

    /**
     * Ataque da criatura
     */
    protected Double ataque = 0.00;//ataque da critura

    /**
     * Defesa da criatura
     */
    protected Double defesa = 0.00;//defesa da criatura

    /**
     * Max vida da criatura
     */
    protected Double max_pontos_vida = 0.00; //Vida da criatura

    /**
     * Ataque bar da criatura, quando {@code barra_ataque == attack_max} to move criatura
     * ganhara turno
     */
    protected Double barra_ataque = 0.00; // quando barra_ataque chegar a 100.00 então a criatura agirá

    //porcentagem de 0-100%
    /**
     * Dodge da criatura
     */
    protected Integer esquiva = 0;

    /**
     * Velocidade da criatura
     */
    protected Double velocidade = 0.00;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Atributos Temporários">
    
    //relacionado a buffs locais,status de armas e armaduras,ex skill que dobra ataque vai dobrar ataque na batalha mas nao o valor de ataque do personagem
    protected Double temp_hit_points = 0.00;
    protected Double temp_attack = 0.00;
    protected Double temp_defense = 0.00;
    protected Double temp_dodge = 0.00;
    protected Double temp_speed = 0.00;

    // </editor-fold>
    
    /**
     * Lista de habilidades de CriaturaBase
     */
    protected ArrayList< HabilidadeBase> lista_de_habilidades = new ArrayList<>();

    /**
     * Lista de efeitos que a CriaturaBase esta sofrendo
     */
    protected ArrayList< Efeito> lista_de_efeitos = new ArrayList<>();

    /**
     * Lista de efeitos instantaneos que a criatura sofrera instantaneamente
     */
    protected ArrayList< Efeito> lista_de_efeitos_instantaneos = new ArrayList<>();

    /**
     * Booleano que indica que criatura esta viva ou nao
     */
    volatile protected boolean isAlive;

    // <editor-fold defaultstate="collapsed" desc="Construtores">
    public CriaturaBase() {
        isAlive = true;
    }

    // </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="Getters & Setters">
    
    public boolean isAlive() {
        return (isAlive);
    }

    public Double getBarra_ataque() {
        return barra_ataque;
    }

    public void setBarra_ataque(Double barra_ataque) {
        this.barra_ataque = barra_ataque;
    }

    public Double getDefesa() {
        return defesa;
    }

    public Double getMax_pontos_vida() {
        return max_pontos_vida;
    }

    public void setMax_pontos_vida(Double max_pontos_vida) {
        this.max_pontos_vida = max_pontos_vida;
    }

    public void setDefesa(Double defesa) {
        this.defesa = defesa;
    }

    public Integer getEsquiva() {
        return esquiva;
    }

    public void setEsquiva(Integer esquiva) {
        this.esquiva = esquiva;
    }

    public Double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(Double velocidade) {
        this.velocidade = velocidade;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Efeito> getLista_de_efeitos() {
        return lista_de_efeitos;
    }

    public void setLista_de_efeitos(ArrayList<Efeito> lista_de_efeitos) {
        this.lista_de_efeitos = lista_de_efeitos;
    }

    public ArrayList<Efeito> getLista_de_efeitos_instantaneos() {
        return lista_de_efeitos_instantaneos;
    }

    public void setLista_de_efeitos_instantaneos(ArrayList<Efeito> lista_de_efeitos_instantaneos) {
        this.lista_de_efeitos_instantaneos = lista_de_efeitos_instantaneos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPontos_vida() {
        return pontos_vida;
    }

    public void setPontos_vida(Double pontos_vida) {
        this.pontos_vida = pontos_vida;
    }

    public Double getAtaque() {
        return ataque;
    }

    public void setAtaque(Double ataque) {
        this.ataque = ataque;
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

    public ArrayList<HabilidadeBase> getLista_de_habilidades() {
        return lista_de_habilidades;
    }

    public void setLista_de_habilidades(ArrayList<HabilidadeBase> lista_de_habilidades) {
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
     * @return velocidade usada em calculos
     */
    public Double getEffectiveSpeed() {
        Double retorno = this.velocidade + this.temp_speed;
        if (retorno <= MIN_SPEED) {
            return (MIN_SPEED);
        }
        return (retorno);
    }

    /**
     * Attack usada em calculos efetivamente
     *
     * @return velocidade usada em calculos
     */
    public Double getEffectiveAttack() {
        return (this.ataque + this.temp_attack);
    }

    /**
     * Defense usada em calculos efetivamente
     *
     * @return velocidade usada em calculos
     */
    public Double getEffectiveDefense() {
        return (this.defesa + this.temp_defense);
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
     * Aumenta velocidade
     *
     * @param increment o quanto aumenta
     */
    public void incSpeed(double increment) {
        double after_increment = this.temp_speed + increment;
        this.temp_speed = after_increment;
    }

    /**
     * Aumenta atkbar percentualmente
     *
     * @param percentage_increment o quanto aumenta percentual
     */
    public void incAttackBar(int percentage_increment) {
        double increment = CriaturaBase.ATTACK_BAR_TO_MOVE * ((percentage_increment+0.00) / (100.00));
        double after_increment = this.barra_ataque + increment;
        if (after_increment > CriaturaBase.ATTACK_BAR_TO_MOVE) {
            after_increment = CriaturaBase.ATTACK_BAR_TO_MOVE;
        }
        this.barra_ataque = after_increment;
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
     * Diminui velocidade
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
     * Diminui ataquebar percentualmente
     *
     * @param percentual_decrement O quanto diminui.
     */
    public void decAttackBar(int percentual_decrement) {
        double decrement = CriaturaBase.ATTACK_BAR_TO_MOVE * ((percentual_decrement+0.00) / (100.00));
        double after_decrement = this.barra_ataque - decrement;
        if (after_decrement < 0.00) {
            after_decrement = 0.00;
        }
        this.barra_ataque = after_decrement;
    }

    /**
     * cura criatura em valor_cura
     *
     * @param valor_cura valor da cura
     */
    public void heal(Double valor_cura) {
        Double vida_depois_da_cura = this.pontos_vida + valor_cura;
        if (vida_depois_da_cura > this.max_pontos_vida) {
            this.pontos_vida = this.max_pontos_vida;
        } else {
            this.pontos_vida = vida_depois_da_cura;
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
        this.temp_speed = 0.00;
    }

    /**
     * Aplica todos os efeitos
     */
    public void applyAllEffects() {
        for (Efeito effect : this.getLista_de_efeitos()) {
            effect.onTarget(this);
        }
        for (Efeito effect : this.lista_de_efeitos_instantaneos) {
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
            Efeito efeito = this.lista_de_efeitos.get(i);
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
        for (HabilidadeBase skill : this.lista_de_habilidades) {
            skill.setAvailable();
        }
    }

    /**
     * Diminui em um o cooldown de todas as skills
     */
    public void resetParcialySkillsCD() {
        for (HabilidadeBase skill : this.lista_de_habilidades) {
            skill.incCooldown();
        }
    }

    /**
     * Metodo chamado quando creatura inicia combate
     */
    public void onStart() {
        resetTotallySkillsCD();
        this.pontos_vida = this.max_pontos_vida;
    }

    /**
     * Metodo chamado toda vez que criatura ganha turno
     */
    public void everyTurn() {
        //do something
        resetParcialySkillsCD();
        removeOutdatedEffects();
    }

    /**
     * Metodo chamado toda vez fim de turno de qualquer criatura
     */
    public void everyTime() {
        //do something
        resetTempStats();
        applyAllEffects();
    }

    /**
     *
     * @return Array com as skills que o CriaturaBase poderá usar pois tem mana
 suficiente e as skills nao estao em tempo de recarga
     */
    public ArrayList<HabilidadeBase> getUsableSkillsArray() {
        ArrayList<HabilidadeBase> retorno = new ArrayList<>();
        for (HabilidadeBase skill : this.lista_de_habilidades) {
            if (skill.isNotOnCoolDown()) {
                retorno.add(skill);
            }
        }
        return (retorno);
    }

    // </editor-fold> 
    
    // <editor-fold defaultstate="collapsed" desc="Overrides">
    /**
     * Comparacao é feito por : todo vivo é maior que todo morto Caso os dois
        estejam vivos : o maior será quem tem maior barra_ataque Caso os dois
        possuam a mesma barra_ataque : o maior será quem tem o maior velocidade Se o
        objeto comparado nao for parte da classe CriaturaBase retorna 0
     *
     * @param o Objeto para comparação.
     * @return  Positivo se o objeto comparado for maior que esse objeto, 
     *          negativo se o objeto comparado for menor e 0 caso forem 
     *          iguais.
     */
    @Override
    public int compareTo(Object o) {
        if (o instanceof CriaturaBase) {
            CriaturaBase other_creature = (CriaturaBase) o;
            int comparacao_0 = 0;
            if (other_creature.isAlive) {
                comparacao_0++;
            }
            if (this.isAlive) {
                comparacao_0--;
            }
            double comparacao_1 = other_creature.barra_ataque - this.barra_ataque;
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
                + "HP: " + this.pontos_vida + '\n'
                + "AtkBar: " + (Math.floor(this.barra_ataque * 10000 / CriaturaBase.ATTACK_BAR_TO_MOVE) / 100) + '\n'
                + "Speed: " + this.getVelocidade() + "(" + this.getTemp_speed() + ")" + '\n'
                + "Atk: " + this.getAtaque() + "(" + this.getTemp_attack() + ")" + '\n'
                + "Defense: " + this.getDefesa() + "(" + this.getTemp_defense() + ")" + '\n'
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
    }

    /**
     * Se true então ataque será ignorado completamente, caso seja false não
     * ignorará
     *
     * @param dodge_penalty Se existir alguma penalidade para esquiva colocar aqui.
     * @param attack_roll   O valor que o atacante gerou.
     * @return              True se o attack_roll for maior ou igual que o
                      esquiva-dodge_penalty, false caso contrario.
     */
    public boolean willDodge(int dodge_penalty, int attack_roll) {
        //System.out.println("attack_roll = "+attack_roll+"\n"+"esquiva = "+((esquiva+temp_dodge)-dodge_penalty-effects_penalty));
        if (attack_roll >= (esquiva + temp_dodge)) {
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
        //System.err.println("EH nulo(takeDamge) = " + damage);
        if (damage > 0.00)
        {
            pontos_vida = pontos_vida - damage;
        }
    }

    /**
     * Metodo chamado quando a criatura morrer.
     */
    public void onDeath() {
        pontos_vida = 0.00;
        isAlive = false;
        System.out.println(this.nome + " morreu!, isalive = " + isAlive());
    }

    @Override
    public String getDescricao() {
        return (this.getNome());
    }
    
    /**
     * Metodo que especifica local onde imagem de heroi esta
     * @return file com imagem do heroi
     */
    public abstract File getImagemFile();

    // </editor-fold>  
}
