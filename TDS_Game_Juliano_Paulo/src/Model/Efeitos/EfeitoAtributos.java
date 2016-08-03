/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Efeitos;

import Model.Acao;
import Model.Criaturas.CriaturaBase;
import java.io.File;

/**
 * Classe que contem 7 diferentes tipos de possibilidades de efeito de acordo
 * com a variavel tipo_efeito
 *
 * @author Paulo
 */
public class EfeitoAtributos extends Efeitos {

    /**
     * Valor minimo que tipo_efeito pode assumir
     */
    public static final int MIN_RANGE = 0;

    /**
     * Valor maximo que tipo_efeito_pode assumir
     */
    public static final int MAX_RANGE = 8;

    /**
     * Define comportamente de onTarget
     */
    EfeitosBasicos tipo_efeito;

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
    public EfeitoAtributos(Double percentage_power_level, Double const_power_level, EfeitosBasicos tipo,int duracao_efeito) {
        super(percentage_power_level, const_power_level);
        this.tipo_efeito = tipo;
        this.duracao = duracao_efeito;
        setDescricao();
        switch (tipo) {
            case ATAQUE_DIMINUIR:
                this.tipo = Acao.Ofensiva;
                this.comportamento_efeito = ComportamentoEfeito.PADRAO;
                break;
            case ATAQUE_AUMENTAR:
                this.tipo = Acao.Defensiva;
                this.comportamento_efeito = ComportamentoEfeito.PADRAO;
                break;
            case DEFESA_DIMINUIR:
                this.tipo = Acao.Ofensiva;
                this.comportamento_efeito = ComportamentoEfeito.PADRAO;
                break;
            case DEFESA_AUMENTAR:
                this.tipo = Acao.Defensiva;
                this.comportamento_efeito = ComportamentoEfeito.PADRAO;
                break;
            case VELOCIDADE_DIMINUIR:
                this.tipo = Acao.Ofensiva;
                this.comportamento_efeito = ComportamentoEfeito.PADRAO;
                break;
            case VELOCIDADE_AUMENTAR:
                this.tipo = Acao.Defensiva;
                this.comportamento_efeito = ComportamentoEfeito.PADRAO;
                break;
            case BARRA_DE_ATAQUE_DIMINUIR:
                this.tipo = Acao.Ofensiva;
                this.isInstantaneo = true;
                this.comportamento_efeito = ComportamentoEfeito.INSTANTANEO;
                break;
            case BARRA_DE_ATAQUE_AUMENTAR:
                this.tipo = Acao.Defensiva;
                this.isInstantaneo = true;
                this.comportamento_efeito = ComportamentoEfeito.INSTANTANEO;
                break;
            case DANO_POR_TURNO:
                this.tipo = Acao.Ofensiva;
                this.comportamento_efeito = ComportamentoEfeito.TURNO;
                break;
            case CURA_POR_TURNO:
                this.tipo = Acao.Defensiva;
                this.comportamento_efeito = ComportamentoEfeito.TURNO;
                break;
            case ATORDOAMENTO:
                this.tipo = Acao.Ofensiva;
                this.comportamento_efeito = ComportamentoEfeito.PADRAO;
                break;
            case IMUNIDADE:
                this.tipo = Acao.Defensiva;
                this.comportamento_efeito = ComportamentoEfeito.PADRAO;
                break;
        }
    }

    public EfeitoAtributos(Efeitos efeito)
    {
        super(efeito);
        if (efeito instanceof EfeitoAtributos)
        {
            EfeitoAtributos outroefeito = (EfeitoAtributos)efeito;
            this.tipo_efeito = outroefeito.tipo_efeito;
        }
    }
    
    public EfeitosBasicos getTipo_efeito() {
        return tipo_efeito;
    }

    
    
    public void setTipo_efeito(EfeitosBasicos tipo_efeito) {
        this.tipo_efeito = tipo_efeito;
    }

    @Override
    public void setDescricao() {
        StringBuilder s = new StringBuilder();
        switch (tipo_efeito) {
            case ATAQUE_DIMINUIR:
                s.append("Reduz ataque em ").append(this.poder_percentual).append(" %.");
                break;
            case ATAQUE_AUMENTAR:
                s.append("Aumenta ataque em ").append(this.poder_percentual).append(" %.");
                break;
            case DEFESA_DIMINUIR:
                s.append("Reduz defesa em ").append(this.poder_percentual).append(" %.");
                break;
            case DEFESA_AUMENTAR:
                s.append("Aumenta defesa em ").append(this.poder_percentual).append(" %.");
                break;
            case VELOCIDADE_DIMINUIR:
                s.append("Reduz velocidade em ").append(this.poder_percentual).append(" %.");
                break;
            case VELOCIDADE_AUMENTAR:
                s.append("Aumenta velocidade em ").append(this.poder_percentual).append(" %.");
                break;
            case BARRA_DE_ATAQUE_DIMINUIR:
                s.append("Reduz atkbar em ").append(this.poder_percentual).append(" %.");
                break;
            case BARRA_DE_ATAQUE_AUMENTAR:
                s.append("Aumenta atkbar em ").append(this.poder_percentual).append(" %.");
                break;
            case DANO_POR_TURNO:
                s.append("Aplica dano por tuno igual a ").append(this.poder_percentual).append(" % da vida do alvo.");
                break;
            case CURA_POR_TURNO:
                s.append("Aplica cura por turno igual a ").append(this.poder_percentual).append(" % da vida do alvo.");
                break;
            case ATORDOAMENTO:
                s.append("Impossibilidade de fazer acao");
                break;
            case IMUNIDADE:
                s.append("Imune a efeitos negativos");
                break;
            default:
                s.append("ESSA MSG NAO DEVE APARECER");
                break;
        }
        s.append('\n');
        descricao = s.toString();
        //System.out.println("Descricao setada = " + descricao);
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
            case ATAQUE_DIMINUIR:
                decrement = target.getAtaque() * poder_percentual / 100 + poder_constante;
                target.decAttack(decrement);
                break;
            //Atk modifier increment
            case ATAQUE_AUMENTAR:
                increment = target.getAtaque() * poder_percentual / 100 + poder_constante;
                target.incAttack(increment);
                break;
            //Def modifier decrement
            case DEFESA_DIMINUIR:
                decrement = target.getDefesa() * poder_percentual / 100 + poder_constante;
                target.decDefense(decrement);
                break;
            //Def modifier increment
            case DEFESA_AUMENTAR:
                increment = target.getDefesa() * poder_percentual / 100 + poder_constante;
                target.incDefense(increment);
                break;
            //Speed modifier decrement
            case VELOCIDADE_DIMINUIR:
                decrement = target.getVelocidade() * poder_percentual / 100 + poder_constante;
                target.decSpeed(decrement);
                break;
            //Speed modifier increment
            case VELOCIDADE_AUMENTAR:
                increment = target.getVelocidade() * poder_percentual / 100 + poder_constante;
                target.incSpeed(increment);
                break;
            //Atk Bar Decrement
            case BARRA_DE_ATAQUE_DIMINUIR:
                decrement = poder_percentual;
                target.decAttackBar(decrement.intValue());
                break;
            //Atk Bar Increment
            case BARRA_DE_ATAQUE_AUMENTAR:
                System.out.println("--------AUMENTANDO ATK BAR DE " + target.getNome() + "----------------");
                System.out.println("atk bar antes = " + (target.getBarraAtaque()*100/CriaturaBase.ATTACK_BAR_TO_MOVE));
                increment = poder_percentual;
                target.incAttackBar(increment.intValue());
                System.out.println("atk bar depois = " + (target.getBarraAtaque()*100/CriaturaBase.ATTACK_BAR_TO_MOVE));
                break;
            case DANO_POR_TURNO :
                decrement = (target.getMaxPontosVida()* poder_percentual/100.00) + poder_constante;
                target.takeDamage(decrement);
                break;
            case CURA_POR_TURNO :
                increment = (target.getMaxPontosVida()* poder_percentual/100) + poder_constante;
                target.heal(increment);
                break;
            case ATORDOAMENTO :
                target.setEstaAtordoado(true);
                break;
            case IMUNIDADE :
                target.setEstaImune(true);
                break;
            default:
                break;
        }
    }
    
    @Override
    public File getArquivoDeImagem()
    {
        switch (tipo_efeito)
        {
            case ATAQUE_DIMINUIR : return(new File(getClass().getResource("/View/Imagens/Efeitos/ataque_diminuido_icon.png").getFile()));
            case ATAQUE_AUMENTAR : return(new File(getClass().getResource("/View/Imagens/Efeitos/ataque_aumentado_icon.png").getFile()));
            case ATORDOAMENTO : return(new File(getClass().getResource("/View/Imagens/Efeitos/atordoamento_icon.jpg").getFile()));
            case VELOCIDADE_AUMENTAR : return(new File(getClass().getResource("/View/Imagens/Efeitos/fast_icon.png").getFile()));
            case VELOCIDADE_DIMINUIR : return(new File(getClass().getResource("/View/Imagens/Efeitos/slow_icon.png").getFile()));
            case DANO_POR_TURNO : return(new File(getClass().getResource("/View/Imagens/Efeitos/dano_por_segundo_icon.png").getFile()));
            case CURA_POR_TURNO : return(new File(getClass().getResource("/View/Imagens/Efeitos/cura_por_segundo_icon.png").getFile()));
            case IMUNIDADE : return(new File(getClass().getResource("/View/Imagens/Efeitos/imunidadeIcone.png").getFile()));
            default : return(new File(getClass().getResource("/View/Imagens/Efeitos/ponto_interrogacao.png").getFile()));
                
        }
    }

}
