/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Itens;

import Model.Itens.Constantes.Pocoes;
import java.io.File;

/**
 * Potions que aumentam permanentemente o status de quem a consome
 *
 */
public class PocaoAumentoStatus extends ConsumivelBase {

//<editor-fold defaultstate="collapsed" desc="Banco de dados">
    private int pocaoId;
    
    public int getPocaoId() {
        return pocaoId;
    }
    
    public void setPocaoId(int pocaoId) {
        this.pocaoId = pocaoId;
    }
//</editor-fold>
    
    /**
     * Indica o numero de potes diferentes que pode ser gerada, usada para
     * generadores poderem ser atualizados ao apenas atualizar essa variavel
     */
    public static final int MAX_RANDOM_VALOR = 3;

    /**
     * Variavel que indica qual o efeito da Potion ao consumir
     */
    private final Pocoes tipo;

    /**
     * O quanto o stauts vai aumentar ao consumir a pote
     */
    private Double aumento = 0.00;

    public PocaoAumentoStatus(Pocoes tipo, double potion_increase) {
        super();
        this.tipo = tipo;
        this.aumento = potion_increase;
    }

    /**
     * Automatizador de nome de acordo com os outros atributos
     */
    public void setAutomaticNome() {
        StringBuilder s = new StringBuilder();
        s.append("Potion de ").append(tipo.getDescricao());
        this.setNome(s.toString());
    }

    /**
     * Chamada ao consumir a pote
     */
    @Override
    public void onConsume() {
        StringBuilder s = new StringBuilder();
        s.append("Aumenta ").append(tipo.getDescricao()).append(" de ");
        s.append(this.getOwner().getNome()).append(" em ").append(this.aumento);
        s.append(" unidades!");
        System.out.println(s.toString());
        switch (this.tipo) {
            case Vida://aumenta hp permanentemente
                this.getOwner().setMaxPontosVida(this.getOwner().getMaxPontosVida() + aumento);
                break;
            case Velocidade://aumenta speed
                this.getOwner().setVelocidade(this.getOwner().getVelocidade() + aumento);
                break;
            case Ataque://aumenta attack
                this.getOwner().setAtaque(this.getOwner().getAtaque() + aumento);
                break;
            case Defesa://aumenta defesa
                this.getOwner().setDefesa(this.getOwner().getDefesa() + aumento);
                break;
            default:
                System.err.println("Essa msg nao deve aparecer em onConsume");
                break;
        }

    }

    @Override
    public File getArquivoDeImagem() {
        return(new File(getClass().getResource("/View/Imagens/potion_icon.png").getFile()));
    }

}
