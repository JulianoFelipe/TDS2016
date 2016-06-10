/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SkillPackage;

import CriaturasPackage.BaseCreature;
import EffectsPackage.AtributesEffect;
import utillities.Describable;
import EffectsPackage.EffectClass;

/**
 * Classe base para habilidades (skills).
 *
 * @author Paulo Tenório
 */
public class BaseSkill implements Describable {

    /**
     * Dono da skill
     */
    private BaseCreature owner = null;

    /**
     * Tipo de skill, ex:Ofensiva ou Defensiva
     */
    private String tipo = null;

    /**
     * efeito associado a skill
     */
    protected EffectClass effect;

    /**
     * nome da skill
     */
    protected String nome;

    /**
     * mana necessaria para usar a skill
     */
    protected Double mana;

    /**
     * variavel que se for igual a cooldown_time significa que a skill pode ser
     * usada
     */
    protected Integer local_cooldown;

    /**
     * tempo de recarga para poder usar a skill novamente em turnos
     */
    protected Integer cooldown_time;

    /**
     *
     * @param tipo pode ser "Ofensivo" ou "Defensivo", caso contrario skill será
     * ignorada
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public BaseCreature getOwner() {
        return owner;
    }

    public void setOwner(BaseCreature owner) {
        this.owner = owner;
    }

    public Integer getLocal_cooldown() {
        return local_cooldown;
    }

    /**
     *
     * @return retorna true se tiver mana disponivel para usar, false caso
     * contrario
     */
    public boolean isManaSufficient() {
        if (owner.getMana() >= this.getMana()) {
            return (true);
        }
        return (false);
    }

    /**
     *
     * @return true se a skill nao estive em tempo de recarga
     */
    public boolean isNotOnCoolDown() {
        if (this.local_cooldown == this.cooldown_time) {
            return (true);
        } else {
            return (false);
        }
    }

    /**
     *
     * @return 0 se puder usar 1 se faltar cooldown 2 se faltar mana e 3 se
     * faltar ambos
     */
    public int isReady() {
        if (isManaSufficient() && isNotOnCoolDown()) {
            return (0);
        } else {
            if (!isNotOnCoolDown() && !isManaSufficient()) {
                return (3);
            } else if (!isManaSufficient()) {
                return (2);
            } else {
                return (1);
            }
        }
    }

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

    public Integer getCooldown_time() {
        return cooldown_time;
    }

    public void setCooldown_time(Integer cooldown_time) {
        this.cooldown_time = cooldown_time;
    }

    public EffectClass getEffect() {
        return effect;
    }

    public void setEffect(EffectClass effect) {
        this.effect = effect;
    }

    /**
     * Transfere efeito de skill para uma criatura
     *
     * @param creature criatura que sofrera os efeitos
     */
    public void transferEffect(BaseCreature creature) {
        if (this.getEffect() instanceof AtributesEffect) {
            AtributesEffect local_effect = (AtributesEffect) this.getEffect();
            AtributesEffect new_effect = new AtributesEffect(local_effect);

            if (new_effect.isInstantaneo() == true) {
                creature.getLista_de_efeitos_instantaneos().add(new_effect);
            } else {
                creature.getLista_de_efeitos().add(new_effect);
            }
        }
    }

    /**
     * Diminui cooldown em 1 turno
     */
    public void incCooldown() {
        if (this.cooldown_time != this.local_cooldown) {
            this.local_cooldown = this.local_cooldown + 1;
        }
    }

    /**
     * Deixa skill disponivel em relacao ao cooldown
     */
    public void setAvailable() {
        this.local_cooldown = this.cooldown_time;
    }

    /**
     * Metodo que deve ser chamado quando skill for usada
     */
    public void onUse() {
        this.local_cooldown = 0;

    }

    @Override
    public String getDescription() {
        return (this.nome + '\n'
                + "Cooldown : " + this.cooldown_time + '\n'
                + "Mana necessaria : " + this.mana + '\n'
                + this.effect.getDescription() + '\n');
    }

}
