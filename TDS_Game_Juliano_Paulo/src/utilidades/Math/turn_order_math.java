/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades.Math;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import Model.Criaturas.CriaturaBase;

/**
 * Classe que define o movimento de BaseCreatures em uma batalha de acorda com
 * suas propriedades
 *
 */
public class turn_order_math {

    /**
     * Retorna indice da proxima criatura que ira se mover
     *
     * @param collection collecao de criaturas
     * @return indice da proxima criatura a se mover
     */
    public static int nextToMove(Collection< CriaturaBase> collection) {
        int i = 0;
        int i_menor = 0;
        double menor_tempo = -1.00;
        boolean first = true;
        for (CriaturaBase local_creature : collection) {
            if (first) {
                first = false;
                menor_tempo = timeToMove(local_creature);
            } else {
                //System.out.println("menor tempo = "+menor_tempo+",timetomove= "+timeToMove(local_creature));
                if (timeToMove(local_creature) < menor_tempo && timeToMove(local_creature) >= 0) {
                    //System.out.println("if!");
                    menor_tempo = timeToMove(local_creature);
                    i_menor = i;
                }
            }
            i++;
        }
        return (i_menor);
    }

    /**
     * Retorna tempo ate uma criatura se mover
     *
     * @param creature criatura considerada
     * @return tempo ate ela se mover ou -1.00 se ela nao puder se mover
     */
    public static double timeToMove(CriaturaBase creature) {
        double necessary_attack_bar = CriaturaBase.ATTACK_BAR_TO_MOVE - creature.getBarraAtaque();
        if (creature.getEffectiveSpeed() == 0 || creature.isAlive() == false) {
            //System.out.println(creature.getNome()+" deu erro! velocidade= "+creature.getEffectiveSpeed() + " isAlive = "+creature.getIsAlive());
            return (-1.00);
        }
        double time_to_move = necessary_attack_bar / creature.getEffectiveSpeed();
        return (time_to_move);
    }

    /**
     * Move todos os ataque bares de todas as criaturas no Collection de acordo
 com a velocidade de dada Creature
     *
     * @param collection collection de CriaturaBase
     * @param time tempo de movimento
     */
    public static void moveAll(Collection< CriaturaBase> collection, double time) {
        ArrayList< CriaturaBase> coll = new ArrayList<>(collection);
        for (CriaturaBase cr : coll) {
            double attack_bar_after = cr.getBarraAtaque() + cr.getEffectiveSpeed() * time;
            cr.setBarraAtaque(attack_bar_after);
        }
        Collections.sort(coll);
    }

    /**
     * Rearranja collection de CriaturaBase de acordo com o proximo movimento
     *
     * @param coll collection de CriaturaBase
     */
    public static void nextTurn(Collection< CriaturaBase> coll) {
        ArrayList< CriaturaBase> aux = new ArrayList<>(coll);
        int indice_do_proximo_a_se_mover = nextToMove(aux);
        double tempo_se_movendo = timeToMove(aux.get(indice_do_proximo_a_se_mover));
        moveAll(coll, tempo_se_movendo);
    }
}
