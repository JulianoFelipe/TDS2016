/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas;

import Model.Itens.ArmaduraBase;
import Model.Itens.EquipavelBase;
import Model.Itens.ConsumivelBase;
import Model.Itens.ArmaBase;
import Model.Itens.ItemBase;
import java.util.ArrayList;

/**
 * Classe abstrata que determina atributos de um herói.
 *
 * @author Paulo Tenório
 */
public abstract class Heroi extends CriaturaBase {

//<editor-fold defaultstate="collapsed" desc="Banco de dados">
    private int heroiId;
    
    public int getHeroiId() {
        return heroiId;
    }
    
    public void setHeroiId(int heroiId) {
        this.heroiId = heroiId;
    }
//</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Atributos">
    
    /**
     * O quanto o requerimentoXp irá aumentar por level
     */
    public static final Double XP_LV_MULTIPLIER = 1.5;

    /**
     * O quanto o hp aumentara por level multiplicamente
     */
    Double multiplicadorPontosVida = 1.00;

    /**
     * O quanto a mana aumentara por level multiplicamente
     */
    Double multiplicadorMana = 1.00;

    /**
     * O quanto o velocidade aumentara por level multiplicamente
     */
    Double multiplicadorVelocidade = 1.00;

    /**
     * O quanto o ataque aumentara por level multiplicamente
     */
    Double multiplicadorAtaque = 1.00;

    /**
     * O quanto a defesa aumentara por level multiplicamente
     */
    Double multiplicadorDefesa = 1.00;

    /**
     * O quanto o PV aumentara por level aditivamente
     * PV - PontosVida
     */
    Double incrementoPV = 0.00;

    /**
     * O quanto a mana aumentara por level aditivamente
     */
    Double incrementoMana = 0.00;

    /**
     * O quanto o velocidade aumentara por level aditivamente
     */
    Double incrementoVelocidade = 0.00;

    /**
     * O quanto o ataque aumentara por level aditivamente
     */
    Double incrementoAtaque = 0.00;

    /**
     * O quanto a defesa aumentara por level aditivamente
     */
    Double incrementoDefesa = 0.00;

    /**
     * O quanto de xp o heroi possue
     */
    Double xpAtual = 0.00;

    /**
     * O quanto de xp o heroi necessita para evoluir de level
     */
    Double requerimentoXp = 100.00;

    /**
     * Se tiver alguma, a armadura que o heroi possue
     */
    private ArmaduraBase armadura = null;

    /**
     * Se tiver alguma, a arma que o heroi possue
     */
    private ArmaBase arma = null;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Utilidades">


    /**
     * Equipa uma arma
     *
     * @param weapon arma que deve ser equipada
     */
    public void equipWeapon(ArmaBase weapon) {
        this.arma = weapon;
    }

    /**
     * Metodo chamado toda vez que heroi subir de level
     */
    public void LevelUp() {
        this.max_pontos_vida = this.max_pontos_vida * multiplicadorPontosVida + incrementoPV;
        pontos_vida = max_pontos_vida;
        this.ataque = this.ataque * multiplicadorAtaque + incrementoAtaque;
        this.velocidade = this.velocidade * multiplicadorVelocidade + incrementoVelocidade;
        defesa = defesa * multiplicadorDefesa + incrementoDefesa;
        this.level++;
        this.requerimentoXp = this.requerimentoXp * XP_LV_MULTIPLIER;
    }

    /**
     * funcao auxiliar a to.string que gera um relatorio com informacoes do
     * heroi
     *
     * @return String com informacoes
     */
    public String displayStatus() {
        return (this.toString() + '\n'
                + "level : " + this.level + '\n'
                + "xp : " + this.xpAtual + '\n'
                + "xp necessaria para proximo level : " + (this.requerimentoXp - this.xpAtual) + '\n'
                + "Skills aprendidas : " + this.lista_de_habilidades.size() + '\n'
                + "Weapon : " + this.getArma() + '\n'
                + "Armor : " + this.getArmadura() + '\n');
    }

    /**
     * Adiciona xp e realiza levelup se xp ganha mais xp que tinha for mais que
     * xp necessaria para aumentar nivel
     *
     * @param xp xp ganha
     */
    public void addXP(Double xp) {
        Double xp_que_tinha = this.xpAtual;
        Double xp_para_upar = this.requerimentoXp - this.xpAtual;
        if (xp >= xp_para_upar) {
            Double xp_restante = xp - xp_para_upar;
            this.LevelUp();
            this.addXP(xp_restante);
        } else {
            this.xpAtual = this.xpAtual + xp;
        }
    }

    /**
     * Equipa item
     *
     * @param item item que sera equipado
     */
    public void equipItem(EquipavelBase item) {
        System.out.println("equipando item....");
        if (canEquip(item)) {
            if (item instanceof ArmaduraBase) {
                ArmaduraBase local_armor = (ArmaduraBase) item;
                this.setArmadura(local_armor);
            } else if (item instanceof ArmaBase) {
                ArmaBase local_weapon = (ArmaBase) item;
                this.setArma(local_weapon);
            } else {
                System.out.println("resultado anormal em equipItem");
            }
        }
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Getters & Setters">

    public ArmaduraBase getArmadura() {
        return armadura;
    }

    public void setArmadura(ArmaduraBase armadura) {
        this.armadura = armadura;
    }

    public ArmaBase getArma() {
        return arma;
    }

    public void setArma(ArmaBase arma) {
        this.arma = arma;
    }

    public void setMultiplicadorPontosVida(Double multiplicadorPontosVida) {
        this.multiplicadorPontosVida = multiplicadorPontosVida;
    }

    public void setMultiplicadorMana(Double multiplicadorMana) {
        this.multiplicadorMana = multiplicadorMana;
    }

    public void setMultiplicadorVelocidade(Double multiplicadorVelocidade) {
        this.multiplicadorVelocidade = multiplicadorVelocidade;
    }

    public void setMultiplicadorAtaque(Double multiplicadorAtaque) {
        this.multiplicadorAtaque = multiplicadorAtaque;
    }

    public void setMultiplicadorDefesa(Double multiplicadorDefesa) {
        this.multiplicadorDefesa = multiplicadorDefesa;
    }

    public void setIncrementoPV(Double incrementoPV) {
        this.incrementoPV = incrementoPV;
    }

    public void setIncrementoMana(Double incrementoMana) {
        this.incrementoMana = incrementoMana;
    }

    public void setIncrementoVelocidade(Double incrementoVelocidade) {
        this.incrementoVelocidade = incrementoVelocidade;
    }

    public void setIncrementoAtaque(Double incrementoAtaque) {
        this.incrementoAtaque = incrementoAtaque;
    }

    public void setIncrementoDefesa(Double incrementoDefesa) {
        this.incrementoDefesa = incrementoDefesa;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Booleanos">
    
    /**
     *
     * @param item A ser equipado
     * @return true se o item poder ser equipado, false caso contrario
     */
    public boolean canEquip(EquipavelBase item) {
        if (item instanceof ArmaBase) {
            ArmaBase local_weapon = (ArmaBase) item;
            return (this.canEquip(local_weapon));
        } else if (item instanceof ArmaduraBase) {
            ArmaduraBase local_armor = (ArmaduraBase) item;
            return (this.canEquip(local_armor));
        } else {
            System.out.println("Resultado nao esperado em HeroClass canEquip(BaseEquipableItem)");
            return (false);
        }
    }
    
    // </editor-fold>
}
