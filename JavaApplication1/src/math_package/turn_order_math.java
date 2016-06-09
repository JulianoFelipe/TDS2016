/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math_package;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javaapplication1.BaseCreature;

/**
 * Classe que define o movimento de BaseCreatures em uma batalha de acorda com suas propriedades
 * 
 */
public class turn_order_math {
    /**
     * Retorna indice da proxima criatura que ira se mover
     * @param collection collecao de criaturas
     * @return indice da proxima criatura a se mover
     */
    public static int nextToMove(Collection< BaseCreature > collection)
    {
        int i = 0;
        int i_menor = 0;
        double menor_tempo = -1.00;
        boolean first = true;
        for (BaseCreature local_creature : collection)
        {
            if (first)
            {
                first = false;
                menor_tempo = timeToMove(local_creature);
            }
            else
            {
                //System.out.println("menor tempo = "+menor_tempo+",timetomove= "+timeToMove(local_creature));
                if (timeToMove(local_creature)<menor_tempo&&timeToMove(local_creature)>=0)
                {
                    //System.out.println("if!");
                    menor_tempo = timeToMove(local_creature);
                    i_menor = i;
                }
            }
            i++;
        }
        return(i_menor);
    }
    
    /**
     * Retorna tempo ate uma criatura se mover
     * @param creature criatura considerada
     * @return tempo ate ela se mover ou -1.00 se ela nao puder se mover
     */
    public static double timeToMove(BaseCreature creature)
    {
        double necessary_attack_bar = BaseCreature.ATTACK_BAR_TO_MOVE - creature.getAttack_bar();
        if (creature.getEffectiveSpeed() == 0||creature.getIsAlive()==false)
        {
            //System.out.println(creature.getNome()+" deu erro! speed= "+creature.getEffectiveSpeed() + " isAlive = "+creature.getIsAlive());
            return(-1.00);
        }
        double time_to_move = necessary_attack_bar/creature.getEffectiveSpeed();
        return(time_to_move);
    }
    
    /**
     * Move todos os attack bares de todas as criaturas no Collection de acordo com a speed de dada Creature
     * @param collection  collection de BaseCreature
     * @param time  tempo de movimento
     */
    public static void moveAll(Collection< BaseCreature > collection,double time)
    {
        ArrayList< BaseCreature > coll = new ArrayList<>(collection);
        for (BaseCreature cr : coll)
        {
            double attack_bar_after  = cr.getAttack_bar() + cr.getEffectiveSpeed()*time;
            cr.setAttack_bar(attack_bar_after);
        }
        Collections.sort(coll);
    }
    
    /**
     * Rearranja collection de BaseCreature de acordo com o proximo movimento
     * @param coll collection  de BaseCreature
     */
    public static void nextTurn(Collection< BaseCreature > coll)
    {
        ArrayList< BaseCreature > aux = new ArrayList<>(coll);
        int indice_do_proximo_a_se_mover = nextToMove(aux);
        double tempo_se_movendo =  timeToMove(aux.get(indice_do_proximo_a_se_mover));
        moveAll(coll,tempo_se_movendo);
    }
}
