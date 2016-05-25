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
    public void random_conflict(HeroClass hero) {
        int numero_de_inimigos = 1;//dps penso em mudar para > 1
        int monstro_level = 1;//pensar em uma logica pra isso tbm

        ArrayList< Monstro> monstro_array = new ArrayList<>();

        for (int i = 0; i < numero_de_inimigos; i++) {
            Monstro new_monstro = MonstroGenerator.gerarMonstro(monstro_level);
            monstro_array.add(new_monstro);
            new_monstro.setHit_points(new_monstro.getMax_hit_points());
        }

        while (!monstro_array.isEmpty()) {
            display_battle_info(hero, monstro_array); //todoodododododoododoodooodododoo ADD PRINTWRITER
            int action = get_player_choice();
            if (action == BaseCreature.ATTACK_PROTOCOL) {
                Double dmg = battle_math.calculate_damage(hero, monstro_array.get(0));
                monstro_array.get(0).takeDamage(dmg);
                if (dmg == 0) {
                    System.out.println("MISS!");
                } else {
                    System.out.println("Dano = " + dmg);
                }
            }

            for (int i = 0; i < monstro_array.size(); i++) {
                display_battle_info(hero, monstro_array);
                Monstro local_monstro = monstro_array.get(i);
                action = get_monstro_choice(local_monstro);
                if (action == BaseCreature.ATTACK_PROTOCOL) {
                    Double dmg = battle_math.calculate_damage(monstro_array.get(0), hero);
                    hero.takeDamage(dmg);
                    if (dmg == 0) {
                        System.out.println("MISS!");
                    } else {
                        System.out.println("Dano = " + dmg);
                    }
                }
            }
        }

    }

    /**
     * Escreve os dados da batalha, ora no STDOUT, ora em um arquivo.
     * @param hero          Herói presente na batalha.
     * @param monstro_array ArrayList de monstros presentes na batalha.
     * @param TODO          PrintWriter para imprimir os dados.
     */
    public void display_battle_info(HeroClass hero, ArrayList< Monstro> monstro_array, PrintWriter TODO) {
        //minimalista no momento
        System.out.println("\n\n");
        System.out.println("Hero stats:\n"
                + "Nome:" + hero.getNome() + "\n"
                + "HP:" + hero.getHit_points() + "\n"
                + "Attack:" + hero.getAttack() + "\n"
                + "Defense:" + hero.getDefense());

        for (int i = 0; i < monstro_array.size(); i++) {
            Monstro monstro_local = monstro_array.get(i);
            System.out.println("Monstro index " + i + "\n"
                    + "Nome:" + monstro_local.getNome() + "\n"
                    + "HP" + monstro_local.getHit_points() + "\n"
                    + "Attack" + monstro_local.getAttack() + "\n"
                    + "Defense" + monstro_local.getDefense());
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
