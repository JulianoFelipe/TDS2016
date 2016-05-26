/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geradores;

import javaapplication1.BaseCreature;
import javaapplication1.HeroClass;
import javaapplication1.Monstro;
import battle_math.battle_math;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;

/**
 * Classe para gerar batalhas entre heróis e monstros.
 *
 * @author Paulo Henrique
 * @author Juliano Felipe
 */
public class BattleGenerator {

    /**
     * Gera um conflito aleatório, para testes.
     * Monstros são gerados internamente.
     * @param hero Herói para testar em um conflito.
     */
    public void random_conflict(ArrayList<HeroClass> hero_list) {
        int numero_de_inimigos = 1;//dps penso em mudar para > 1
        int monstro_level = 1;//pensar em uma logica pra isso tbm

        ArrayList<BaseCreature> criaturas_array = new ArrayList<>();
        
        for (HeroClass hero1 : hero_list)
        {
            criaturas_array.add(hero1);
        }

        for (int i = 0; i < numero_de_inimigos; i++) {
            Monstro new_monstro = MonstroGenerator.gerarMonstro(monstro_level);
            criaturas_array.add(new_monstro);
            new_monstro.setHit_points(new_monstro.getMax_hit_points());
        }
        while (!condicao_de_parada(criaturas_array)) {
            display_battle_info(criaturas_array,null); //todoodododododoododoodooodododoo ADD PRINTWRITER
            for (BaseCreature local_creature : criaturas_array)
            {
                if (local_creature instanceof HeroClass)
                {
                    System.out.println("Heroi "+local_creature.getNome()+" agindo!");
                    int action = get_player_choice();
                    if (action == BaseCreature.ATTACK_PROTOCOL) {
                        ArrayList< Monstro > possible_targets_list = new ArrayList<>();
                        for (int i=0;i<criaturas_array.size();i++)
                        {
                            if (criaturas_array.get(i) instanceof Monstro)
                            {
                                Monstro local_monstro = (Monstro) criaturas_array.get(i);
                                possible_targets_list.add(local_monstro);
                            }
                        }
                        
                        System.out.println("Qual monstro voce quer atacar?");
                        for (int i=0;i<possible_targets_list.size();i++)
                        {
                            System.out.println("("+i+")"+"->"+possible_targets_list.get(i).getNome());
                        }
                        
                        int target_index = -1;
                        Scanner scam = new Scanner(System.in);
                        
                        while (target_index<0||target_index>=possible_targets_list.size())
                        {
                            target_index = scam.nextInt();
                        }
                        
                        BaseCreature target = possible_targets_list.get(target_index);
                        System.out.println("Heroi "+local_creature.getNome()+" atacando "+target.getNome()+"!");
                        Double dmg = battle_math.calculate_damage(local_creature, target);
                        target.takeDamage(dmg);
                       
                        if (dmg == 0) {
                            System.out.println("MISS!");
                        } else {
                            System.out.println("Dano = " + dmg);
                        }
                    }
                }
                else
                {
                    if (local_creature instanceof Monstro)
                    {
                        
                        Monstro local_monstro = (Monstro) local_creature;
                        int action = get_monstro_choice(local_monstro);
                        
                        if (action == BaseCreature.ATTACK_PROTOCOL) {
                            ArrayList< HeroClass > possible_targets_list = new ArrayList<>();
                            for (int i=0;i<criaturas_array.size();i++)
                            {
                                if (criaturas_array.get(i) instanceof HeroClass)
                                {
                                    HeroClass local_hero = (HeroClass) criaturas_array.get(i);
                                    possible_targets_list.add(local_hero);
                                }
                            }
                            System.out.println("Monstro "+local_creature.getNome()+" agindo!");
                            BaseCreature target = possible_targets_list.get(0);
                            System.out.println("Monstro "+local_monstro.getNome()+" atacando "+target.getNome());
                            Double dmg = battle_math.calculate_damage(local_creature, target);
                            target.takeDamage(dmg);
                            System.out.println("Dano = " + dmg);
                        }
                    }
                    else
                    {
                        System.out.println("Erro grave 3");
                    }
                }
                checkEveryTurn(criaturas_array);
                if (condicao_de_parada(criaturas_array))
                {
                    break;
                }
            }
        }

    }
    
    public void checkEveryTurn(ArrayList<BaseCreature> creature_array)
    {
        //checar quem esta vivo e remover quem esta morto
        for (int i=0;i<creature_array.size();i++)
        {
            BaseCreature local_creature = creature_array.get(i);
            if (local_creature.getHit_points()<=0)
            {
                local_creature.onDeath();
                creature_array.remove(i);
                i--;
            }
        }
    }

    public boolean condicao_de_parada(ArrayList<BaseCreature> creature_array)
    {
        int numero_de_herois=0;
        int numero_de_monstros=0;
        for (BaseCreature local_creature : creature_array)
        {
            if (local_creature instanceof HeroClass)
            {
                numero_de_herois++;
            }
            else
            {
                if (local_creature instanceof Monstro)
                {
                    numero_de_monstros++;
                }
                else
                {
                    System.out.println("Erro Grave 2");
                }
            }
        }
        if (numero_de_herois==0||numero_de_monstros==0)
        {
            return(true);
        }
        return(false);
    }
    
    /**
     * Escreve os dados da batalha, ora no STDOUT, ora em um arquivo.
     * @param hero          Herói presente na batalha.
     * @param monstro_array ArrayList de monstros presentes na batalha.
     * @param TODO          PrintWriter para imprimir os dados.
     */
    public void display_battle_info(ArrayList<BaseCreature> creatures_array, PrintWriter TODO) {
        //minimalista no momento
        for (BaseCreature local_creature : creatures_array)
        {
            if (local_creature instanceof HeroClass)
            {
                HeroClass hero = (HeroClass)local_creature;
                System.out.println("Hero stats:\n"
                        + "Nome:" + hero.getNome() + "\n"
                        + "HP:" + hero.getHit_points() + "\n"
                        + "Attack:" + hero.getAttack() + "\n"
                        + "Defense:" + hero.getDefense());
            }
            else
            {
                if (local_creature instanceof Monstro)
                {
                    Monstro monstro_local = (Monstro)local_creature;
                    System.out.println("Monstro stats:"+"\n"
                        + "Nome:" + monstro_local.getNome() + "\n"
                        + "HP" + monstro_local.getHit_points() + "\n"
                        + "Attack" + monstro_local.getAttack() + "\n"
                        + "Defense" + monstro_local.getDefense());
                }
                else
                {
                    System.out.println("Erro grave!\n");
                }
            }
        }
    }

    /**
     * A ação do monstro.
     * @param local_monstro que executará a ação.
     * @return              Ação que o monstro executará.
     */
    public int get_monstro_choice(Monstro local_monstro) {
        return (BaseCreature.ATTACK_PROTOCOL);
    }

    /**
     * A ação do player.
     * @return Ação a ser executara pelo player.
     */
    public int get_player_choice() {
        System.out.println("1 - atacar 2 - defender");
        Scanner player_interaction = new Scanner(System.in);
        int player_choise = player_interaction.nextInt();
        return (player_choise);
    }
}
