/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Itens;

/**
 * Potions que aumentam permanentemente o status de quem a consome
 *
 */
public class PocaoAumentoStatus extends ConsumivelBase {

    /**
     * Indica o numero de potes diferentes que pode ser gerada, usada para
     * generadores poderem ser atualizados ao apenas atualizar essa variavel
     */
    public static final int MAX_RANDOM_VALOR = 3;

    /**
     * Variavel que indica qual o efeito da Potion ao consumir
     */
    private int tipo;

    /**
     * O quanto o stauts vai aumentar ao consumir a pote
     */
    private Double potion_increase = 0.00;

    public PocaoAumentoStatus(int tipo, double potion_increase) {
        super();
        this.tipo = tipo;
        this.potion_increase = potion_increase;
    }

    /**
     * Automatizador de nome de acordo com os outros atributos
     */
    public void setAutomaticNome() {
        StringBuilder s = new StringBuilder();
        s.append("Potion de ");
        switch (this.tipo) {
            case 0://aumenta hp permanentemente
                s.append("HP");
                break;
            case 1://aumenta speed
                s.append("Speed");
                break;
            case 2://aumenta attack
                s.append("Attack");
                break;
            case 3://aumenta defesa
                s.append("Defesa");
                break;
            default:
                System.out.println("PotionBugada");
                break;
        }
        s.append('(' + this.potion_increase.toString() + ')');
        this.setNome(s.toString());
    }

    /**
     * Chamada ao consumir a pote
     */
    @Override
    public void onConsume() {
        switch (this.tipo) {
            case 0://aumenta hp permanentemente
                System.out.println("Aumenta hp de " + this.getOwner().getNome() + " em " + this.potion_increase + " unidades!");
                this.getOwner().setMax_pontos_vida(this.getOwner().getMax_pontos_vida() + potion_increase);
                break;
            case 1://aumenta speed
                System.out.println("Aumenta speed de " + this.getOwner().getNome() + " em " + this.potion_increase + " unidades!");
                this.getOwner().setVelocidade(this.getOwner().getVelocidade() + potion_increase);
                break;
            case 2://aumenta attack
                System.out.println("Aumenta attack de " + this.getOwner().getNome() + " em " + this.potion_increase + " unidades!");
                this.getOwner().setAtaque(this.getOwner().getAtaque() + potion_increase);
                break;
            case 3://aumenta defesa
                System.out.println("Aumenta defesa de " + this.getOwner().getNome() + " em " + this.potion_increase + " unidades!");
                this.getOwner().setDefesa(this.getOwner().getDefesa() + potion_increase);
                break;
            default:
                System.out.println("Essa msg nao deve aparecer em onConsume");
                break;
        }

    }

}
