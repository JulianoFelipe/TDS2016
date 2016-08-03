/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades.Math;

import Model.Criaturas.CriaturaBase;

/**
 * Classe para calcular efeitos, ataques, etc de uma batalha.
 *
 * @author Paulo Henrique
 * @author Juliano Felipe
 */
public class battle_math {

    /**
     * Funcao auxiliar para transformar entradas Inteiras em Double
     */
    public static Double calculate_damage(CriaturaBase atacante, CriaturaBase defensor, int multiplicadorDoAtaque) {
        return( calculate_damage(atacante,defensor,multiplicadorDoAtaque+0.00) );
    }
    
    /**
     * Calcula o dano atribuído à um defensor.
     *
     * @param atacante Criatura que atacará.
     * @param defensor Criatura que defenderá.
     * @return Dano que será atribuído ao defensor.
     */
    public static Double calculate_damage(CriaturaBase atacante, CriaturaBase defensor, double multiplicadorDoAtaque) {
        int atack_roll = atacante.getAttackRoll();
        //verifica se o dano deve ser ignorado
        
        //regras - se a defesa for maior que ataque
        //para cada 2.5 unidades de defesa maior que o ataque diminue o dano em 1% ate um maximo de 90%
        //para cada 5 unidades de ataque maior que a defesa aumenta o dano em 1%
        
        Double ataque = atacante.getEffectiveAttack();
        Double defesa = defensor.getEffectiveDefense();
        
        System.out.println("ataque = " + ataque + ", defesa = " + defesa);
        
        if (ataque > defesa)
        {
            Double diferenca = ataque-defesa;
            Double porcentagemAumentada = (diferenca/5.00)/100;
            ataque = ataque + ataque*porcentagemAumentada;
            System.out.println("Dano final = " + ataque + "*" + multiplicadorDoAtaque);
            return(ataque*multiplicadorDoAtaque);
        }
        else
        {
            Double diferenca = defesa-ataque;
            Double porcentagemIgnorada = (diferenca/2.5)/100;
            if (porcentagemIgnorada > 90)
            {
                porcentagemIgnorada = 90.00;
            }
            ataque = ataque - ataque*porcentagemIgnorada;
            System.out.println("Dano final = " + ataque + "*" + multiplicadorDoAtaque);
            return(ataque*multiplicadorDoAtaque);
        }
    }
}
