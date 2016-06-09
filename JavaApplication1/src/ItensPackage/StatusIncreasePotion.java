/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ItensPackage;

/**
 *
 * @author FREE
 */
public class StatusIncreasePotion extends BaseConsumableItem{
    /**
     * Indica o numero de potes diferentes que pode ser gerada, usada para generadores poderem ser atualizados ao apenas atualizar essa variavel
     */
    public static final int MAX_RANDOM_VALOR = 4;
    
    
    /**
     * Variavel que indica qual o efeito da Potion ao consumir
     */
    private int tipo;
    
    private Double potion_increase = 0.00;
    
    public StatusIncreasePotion(int tipo,double potion_increase)
    {
        super();
        this.tipo = tipo;
        this.potion_increase = potion_increase;
    }
    
    public void setAutomaticNome()
    {
        StringBuilder s = new StringBuilder();
        s.append("Potion de ");
        switch (this.tipo)
        {
            case 0://aumenta hp permanentemente
                s.append("HP");
                break;
            case 1://aumenta mana permanentemente
                s.append("Mana");
                break;
            case 2://aumenta speed
                s.append("Speed");
                break;
            case 3://aumenta attack
                s.append("Attack");
                break;
            case 4://aumenta defesa
                s.append("Defesa");
                break;
            default:
                System.out.println("PotionBugada");
                break;
        }
        s.append('('+this.potion_increase.toString()+')');
        this.setNome(s.toString());
    }

    @Override
    public void onConsume() {
        switch (this.tipo)
        {
            case 0://aumenta hp permanentemente
                System.out.println("Aumenta hp de "+this.getOwner().getNome() + " em "+this.potion_increase+" unidades!");
                this.getOwner().setMax_hit_points(this.getOwner().getMax_hit_points() + potion_increase); 
                break;
            case 1://aumenta mana permanentemente
                System.out.println("Aumenta mana de "+this.getOwner().getNome() + " em "+this.potion_increase+" unidades!");
                this.getOwner().setMax_mana(this.getOwner().getMax_mana() + potion_increase);
                break;
            case 2://aumenta speed
                System.out.println("Aumenta speed de "+this.getOwner().getNome() + " em "+this.potion_increase+" unidades!");
                this.getOwner().setSpeed(this.getOwner().getSpeed() + potion_increase);
                break;
            case 3://aumenta attack
                System.out.println("Aumenta attack de "+this.getOwner().getNome() + " em "+this.potion_increase+" unidades!");
                this.getOwner().setAttack(this.getOwner().getAttack() + potion_increase);
                break;
            case 4://aumenta defesa
                System.out.println("Aumenta defesa de "+this.getOwner().getNome() + " em "+this.potion_increase+" unidades!");
                this.getOwner().setDefense(this.getOwner().getDefense() + potion_increase);
                break;
            default:
                System.out.println("Essa msg nao deve aparecer em onConsume");
                break;
        }
        
    }
    
}
