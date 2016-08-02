/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas;

import Model.Efeitos.Efeitos;
import Model.Itens.ArmaduraBase;
import Model.Itens.EquipavelBase;
import Model.Itens.ArmaBase;
import Model.Itens.ItemBase;

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
     * Jogador que tem controle sobre esse Heroi
     */
    Jogador jogador;
    
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
    
    Heroi(Jogador jogador)
    {
        this.jogador = jogador;
    }
    
    

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
        this.maxPontosVida = this.maxPontosVida * multiplicadorPontosVida + incrementoPV;
        pontosVida = maxPontosVida;
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
                + "Skills aprendidas : " + this.listaDeHabilidades.size() + '\n'
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

    @Override
    public void aplicarTodosOsEfeitos() {
        super.aplicarTodosOsEfeitos();
        if (arma!=null)
        {
            arma.aplicarEfeitosDeItem();
        }
        if (armadura!=null)
        {
            armadura.aplicarEfeitosDeItem();
        }
    }
    
    /**
     * Equipa item
     *
     * @param item item que sera equipado
     */
    public void equipItem(EquipavelBase item) {
        System.out.println("equipando item....");
        if (item instanceof ArmaduraBase) {
            ArmaduraBase local_armor = (ArmaduraBase) item;
            if (this.getArmadura() != null)
            {
                this.getJogador().addItem(this.getArmadura());
                this.getArmadura().setHeroi(null);
            }
            this.setArmadura(local_armor);
            this.getJogador().removeItem(item);
        } else if (item instanceof ArmaBase) {
            ArmaBase local_weapon = (ArmaBase) item;
            if (this.getArma() != null)
            {
                this.getJogador().addItem(this.getArma());
                this.getArma().setJogador(null);
            }
            this.setArma(local_weapon);
            this.getJogador().removeItem(item);
        } else {
            System.out.println("resultado anormal em equipItem");
        }
    }
    
    public void removerItem(ItemBase item)
    {
        if (arma!=null)
        {
            if (arma == item)
            {
                this.getJogador().getInventario().add(item);
                arma.setHeroi(null);
                arma = null;
            }
        }
        if (armadura!=null)
        {
            if (armadura == item)
            {
                this.getJogador().getInventario().add(item);
                armadura.setHeroi(null);
                armadura = null;
            }
        }
    }

    

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public static Double getXP_LV_MULTIPLIER() {
        return XP_LV_MULTIPLIER;
    }

    public void setXpAtual(Double xpAtual) {
        this.xpAtual = xpAtual;
    }

    public void setRequerimentoXp(Double requerimentoXp) {
        this.requerimentoXp = requerimentoXp;
    }
    
    public Jogador getJogador() {
        return jogador;
    }
    
    public Double getMultiplicadorPontosVida() {
        return multiplicadorPontosVida;
    }

    public Double getMultiplicadorMana() {
        return multiplicadorMana;
    }

    public Double getMultiplicadorVelocidade() {
        return multiplicadorVelocidade;
    }

    public Double getMultiplicadorAtaque() {
        return multiplicadorAtaque;
    }

    public Double getMultiplicadorDefesa() {
        return multiplicadorDefesa;
    }

    public Double getIncrementoPV() {
        return incrementoPV;
    }

    public Double getIncrementoMana() {
        return incrementoMana;
    }

    public Double getIncrementoVelocidade() {
        return incrementoVelocidade;
    }

    public Double getIncrementoAtaque() {
        return incrementoAtaque;
    }
    
    public Double getIncrementoDefesa() {
        return incrementoDefesa;
    }

    public Double getXpAtual() {
        return xpAtual;
    }

    public Double getRequerimentoXp() {
        return requerimentoXp;
    }
    
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
    
    // </editor-fold>
}
