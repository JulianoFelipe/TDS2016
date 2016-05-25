/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battle_math;
import javaapplication1.BaseCreature;
/**
 * Classe para calcular efeitos, ataques, etc 
 * de uma batalha.
 * @author Paulo Henrique
 * @author Juliano Felipe
 */
public class battle_math {
    /**
     * Calcula o dano atribuído à um defensor.
     * @param atacante Criatura que atacará.
     * @param defensor Criatura que defenderá.
     * @return         Dano que será atribuído ao defensor.
     */
    public static Double calculate_damage(BaseCreature atacante, BaseCreature defensor) {
        int atack_roll = atacante.getAttackRoll();
        if (defensor.willDodge(0,atack_roll))
        {
            return(0.00);
        }
        else
        {
            Double damage = atacante.getAttack()+atacante.getTemp_attack() - defensor.getDefense() - defensor.getTemp_defense();
            return(damage);
        }
    }
}
