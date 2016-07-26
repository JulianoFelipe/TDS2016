/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Efeitos;

import Model.Criaturas.CriaturaBase;
import utilidades.Descritivel;

/**
 * Classe base para efeitos.
 *
 * @author Paulo Henrique
 * @author Juliano Felipe
 */
public abstract class Efeito implements Descritivel, Cloneable {

    /**
     * Tipo pode ser ou Ofensivo ou Defensivo
     */
    protected String tipo;

    /**
     * Indica se o efeito eh instantaneo (eh aplicado e logo depois removido)
     */
    protected Boolean isInstantaneo = false;

    /**
     * Duracao do efeito se ele nao for instantaneo
     */
    protected Integer duracao;

    /**
     * A ser usado para cáculo de efeito multiplicativo. Ex.: Uma cura de 10%,
     * recuperaria 10% do {@link Model.Criaturas.CriaturaBase#max_pontos_vida}.
     */
    protected Double poder_percentual;
    /**
     * A ser usado para cáculo de efeito aditivo. Ex.: Um dano de 10 unidades,
     * reduziria, aritmeticamente, um número de acordo com defesas e skills do
     * {@link Model.Criaturas.CriaturaBase#max_pontos_vida}.
     */
    protected Double poder_constante;

    /**
     * Construtor default
     */
    public Efeito() {
        this.poder_percentual = 0.00;
        this.poder_constante = 0.00;
    }

    /**
     * Constutor de efeito.
     *
     * @param percentage_power_level Efeito multiplicativo.
     * @param const_power_level      Efeito aditivo.
     */
    public Efeito(Double percentage_power_level, Double const_power_level) {
        this.poder_percentual = percentage_power_level;
        this.poder_constante = const_power_level;
    }

    public Boolean isInstantaneo() {
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
     *
     * @param effect Para copiar.
     */
    public Efeito(Efeito effect) {
        this.poder_percentual = new Double(effect.poder_percentual);
        this.poder_constante = new Double(effect.poder_constante);
        this.duracao = new Integer(effect.duracao);
        this.tipo = effect.tipo;
        this.isInstantaneo = new Boolean(effect.isInstantaneo);
    }

    public int getDuration() {
        return duracao;
    }

    public void setDuration(int duration) {
        this.duracao = duration;
    }

    @Override
    public String getDescricao() {
        String string_1, string_2, string_3;
        switch (tipo) {
            case "Ofensivo":
                string_1 = "Em todos os inimigos,";
                break;
            case "Defensivo":
                string_1 = "Em todos os aliados,";
                break;
            default:
                string_1 = "ERRO DE TIPO!1!!";
                break;
        }

        if (isInstantaneo) {
            string_2 = "Instantaneamente,";
        } else {
            string_2 = "Durante " + this.duracao + " turnos,";
        }

        return (string_1 + string_2);
    }

    /**
     * Aplica efeito em uma criatura
     *
     * @param target Alvo do efeito
     */
    abstract public void onTarget(CriaturaBase target);
}
