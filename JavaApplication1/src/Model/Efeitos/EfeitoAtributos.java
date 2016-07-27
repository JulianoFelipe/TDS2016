/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Efeitos;

import Model.Acao;
import Model.Criaturas.CriaturaBase;

/**
 * Classe que contem 7 diferentes tipos de possibilidades de efeito de acordo
 * com a variavel tipo_efeito
 *
 * @author Paulo
 */
public class EfeitoAtributos extends Efeito {

    /**
     * Valor minimo que tipo_efeito pode assumir
     */
    public static final int MIN_RANGE = 0;

    /**
     * Valor maximo que tipo_efeito_pode assumir
     */
    public static final int MAX_RANGE = 7;

    /**
     * Define comportamente de onTarget
     */
    Integer tipo_efeito = 0;

    /**
     * Descricao do efeito
     */
    String desc = "";

    /**
     *
     * @param percentage_power_level poder percentual do efeito
     * @param const_power_level poder aditivo do efeito
     * @param tipo define comportamento do efeito
     */
    public EfeitoAtributos(Double percentage_power_level, Double const_power_level, int tipo) {
        super(percentage_power_level, const_power_level);
        this.tipo_efeito = tipo;
        switch (tipo) {
            case 0:
                this.tipo = Acao.Ofensiva;
                break;
            case 1:
                this.tipo = Acao.Defensiva;
                break;
            case 2:
                this.tipo = Acao.Ofensiva;
                break;
            case 3:
                this.tipo = Acao.Defensiva;
                break;
            case 4:
                this.tipo = Acao.Ofensiva;
                break;
            case 5:
                this.tipo = Acao.Defensiva;
                break;
            case 6:
                this.tipo = Acao.Ofensiva;
                this.isInstantaneo = true;
                break;
            case 7:
                this.tipo = Acao.Defensiva;
                this.isInstantaneo = true;
                break;
        }
    }

    /**
     * Construtor de copia.
     *
     * @param effect Efeito copiado.
     */
    public EfeitoAtributos(Efeito effect) {
        super(effect);
        if (effect instanceof EfeitoAtributos) {
            EfeitoAtributos local_effect = (EfeitoAtributos) effect;
            this.tipo_efeito = new Integer(local_effect.tipo_efeito);
        } else {
            //donothing
        }
    }

    public int getTipo_efeito() {
        return tipo_efeito;
    }

    public void setTipo_efeito(int tipo_efeito) {
        this.tipo_efeito = tipo_efeito;
    }

    @Override
    public String getDescricao() {
        String aux = super.getDescricao();
        StringBuilder s = new StringBuilder();
        switch (tipo_efeito) {
            case 0:
                s.append("Reduz ataque em ").append(this.poder_percentual).append(" %.");
                break;
            case 1:
                s.append("Aumenta ataque em ").append(this.poder_percentual).append(" %.");
                break;
            case 2:
                s.append("Reduz defesa em ").append(this.poder_percentual).append(" %.");
                break;
            case 3:
                s.append("Aumenta defesa em ").append(this.poder_percentual).append(" %.");
                break;
            case 4:
                s.append("Reduz velocidade em ").append(this.poder_percentual).append(" %.");
                break;
            case 5:
                s.append("Aumenta velocidade em ").append(this.poder_percentual).append(" %.");
                break;
            case 6:
                s.append("Reduz atkbar em ").append(this.poder_percentual).append(" %.");
                break;
            case 7:
                s.append("Aumenta atkbar em ").append(this.poder_percentual).append(" %.");
                break;
            default:
                s.append("ESSA MSG NAO DEVE APARECER");
                break;
        }
        s.append('\n');
        return (aux + s.toString());
    }

    /**
     * Oque fazer com alvo do efeito.
     *
     * @param target Alvo do efeito.
     */
    @Override
    public void onTarget(CriaturaBase target) {
        Double decrement = 0.00;
        Double increment = 0.00;
        switch (tipo_efeito) {
            //Atk modifier decrement
            case 0:
                decrement = target.getAtaque() * poder_percentual / 100 + poder_constante;
                target.decAttack(decrement);
                break;
            //Atk modifier increment
            case 1:
                increment = target.getAtaque() * poder_percentual / 100 + poder_constante;
                target.incAttack(increment);
                break;
            //Def modifier decrement
            case 2:
                decrement = target.getDefesa() * poder_percentual / 100 + poder_constante;
                target.decDefense(decrement);
                break;
            //Def modifier increment
            case 3:
                increment = target.getDefesa() * poder_percentual / 100 + poder_constante;
                target.incDefense(increment);
                break;
            //Speed modifier decrement
            case 4:
                decrement = target.getVelocidade() * poder_percentual / 100 + poder_constante;
                target.decSpeed(decrement);
                break;
            //Speed modifier increment
            case 5:
                increment = target.getVelocidade() * poder_percentual / 100 + poder_constante;
                target.incSpeed(increment);
                break;
            //Atk Bar Decrement
            case 6:
                decrement = poder_percentual;
                target.decAttackBar(decrement.intValue());
                break;
            //Atk Bar Increment
            case 7:
                increment = poder_percentual;
                target.incAttackBar(increment.intValue());
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return this.getDescricao() + "\nDuracao restante = " + this.duracao + "\n";
    }

}
