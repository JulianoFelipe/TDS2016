/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geradores;

import ItensPackage.BaseItem;
import javaapplication1.BaseCreature;
import javaapplication1.HeroClass;
import javaapplication1.Monstro;
import math_package.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import javaapplication1.BaseSkill;
import utillities.*;

/**
 * Classe para gerar batalhas entre heróis e monstros.
 *
 * @author Paulo Henrique
 * @author Juliano Felipe
 */
public class BattleGenerator {
    /**
     * Constante que indica condicao de game over
     */
    public static final int GAME_OVER_CODE = 1;
    
    /**
     * Constante que indica continuacao, o contrario do game_over_code
     */
    public static final int CONTINUE_CODE = 2;
    
    /**
     * Constante que define o numero maximo de inimigos
     */
    public static final int MAX_NUMERO_DE_INIMIGOS = 5;
    
    /**
     * Constante que define o numero minimo de inimigos
     */
    public static final int MIN_NUMERO_DE_INIMIGOS = 1;

    public static void anulando()
    {
        System.out.println("Anulando acao!....");
    }
    
    public static void erro()
    {
        System.out.println("Ops um erro aconteceu, tentando de novo....");
    }
    
    /**
     * Usado para sanity checks
     */
    public static void anomaly()
    {
        System.out.println("Essa msg nao deve aparecer");
    }
    
    /**
     * Gera um conflito aleatório, para testes.
     * Monstros são gerados internamente.
     * @param hero_list Heróis para testar em 
     *        um conflito.
     */
    public int random_conflict(ArrayList<HeroClass> hero_list) {
        Random generator = new Random();
        int numero_de_inimigos = MIN_NUMERO_DE_INIMIGOS+generator.nextInt(MAX_NUMERO_DE_INIMIGOS-MIN_NUMERO_DE_INIMIGOS);
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
        onStart(criaturas_array);
        while (!condicao_de_parada(criaturas_array)) {
            turn_order_math.nextTurn(criaturas_array);
            Collections.sort(criaturas_array);
            BaseCreature local_creature = criaturas_array.get(0);
            System.out.println(local_creature.getNome()+" esta movendo!");
            ArrayList< BaseCreature > array_inimigo_vivo = new ArrayList<>();
            ArrayList< BaseCreature > array_aliado_vivo = new ArrayList<>();
            if (!local_creature.getIsAlive())
            {
                break;
            }
            if (local_creature instanceof HeroClass)
            {
                for (BaseCreature creature : criaturas_array)
                {
                    if (creature instanceof Monstro)
                    {
                        array_inimigo_vivo.add(creature);
                    }
                    else if (creature instanceof HeroClass)
                    {
                        array_aliado_vivo.add(creature);
                    }
                    else
                    {
                        anomaly();
                    }
                    
                }
                boolean should_end_turn = true;
                do
                {
                    should_end_turn = true;
                    display_battle_info(criaturas_array,null); //todoodododododoododoodooodododoo ADD PRINTWRITER
                    System.out.println("Heroi "+local_creature.getNome()+" agindo!");
                    int action = -1;
                    do{
                        action = MyUtil.get_player_choice(1,2,"1 - atacar 2 - usar skill");
                        if (action==-1)
                        {
                            erro();
                        }
                    }while(action==-1);
                    if (action == MyUtil.BACK_PROTOCOL)
                    {
                        should_end_turn = false;
                    }
                    else if (action == BaseCreature.ATTACK_PROTOCOL) {
                        ArrayList< Monstro > possible_targets_list = new ArrayList<>();
                        for (int i=0;i<criaturas_array.size();i++)
                        {
                            if (!criaturas_array.get(i).getIsAlive())
                            {
                                break;
                            }
                            if (criaturas_array.get(i) instanceof Monstro)
                            {
                                Monstro local_monstro = (Monstro) criaturas_array.get(i);
                                possible_targets_list.add(local_monstro);
                            }
                        }


                        int target_index = -1;
                        while (target_index==-1)
                        {
                            target_index=MyUtil.getcanceleable_and_display(possible_targets_list, "Qual monstro deseja atacar?");
                            if (target_index==-1)
                            {
                                System.out.println("Ocorreu um erro!, tentando corrigir....");
                            }
                        }
                        
                        if (target_index==MyUtil.BACK_PROTOCOL)
                        {
                            should_end_turn = false;
                            anulando();
                        }
                        else
                        {
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
                    else if (action==BaseCreature.SKILL_PROTOCOL)
                    {
                        ArrayList< BaseSkill > skill_usaveis = local_creature.getUsableSkillsArray();
                        System.out.println("Skill que nao podem ser usadas :");
                        System.out.println(local_creature.getUnusableSkills());
                        int skill_index = 0;
                        do{
                            skill_index = MyUtil.get_and_display(skill_usaveis, "Qual skill deseja selecionar?");
                            if (skill_index==-1)
                            {
                                erro();
                            }
                        }while(skill_index==-1);
                        if (skill_index==MyUtil.BACK_PROTOCOL)
                        {
                            should_end_turn = false;
                        }
                        else
                        {
                            BaseSkill skill_usada = skill_usaveis.get(skill_index);
                            System.out.println("Usando skill->"+skill_usada.getDescription());
                            if (skill_usada.getTipo().equals("Ofensivo"))
                            {
                                for (BaseCreature creature : array_inimigo_vivo)
                                {
                                    skill_usada.transferEffect(creature);
                                    System.out.println("creature afetada->"+creature);
                                }
                            }
                            else if (skill_usada.getTipo().equals("Defensivo"))
                            {
                                for (BaseCreature creature : array_aliado_vivo)
                                {
                                    skill_usada.transferEffect(creature);
                                    System.out.println("creature afetada->"+creature);
                                }
                            }
                            else
                            {
                                System.out.println("tipo = " + skill_usada.getTipo());
                                anomaly();
                            }
                            skill_usada.onUse();
                            
                        }
                    }
                }while(!should_end_turn);
            }
            else
            {
                if (local_creature instanceof Monstro)
                {
                    for (BaseCreature creature : criaturas_array)
                        {
                            if (creature instanceof HeroClass)
                            {
                                array_inimigo_vivo.add(creature);
                            }
                            else if (creature instanceof Monstro)
                            {
                                array_aliado_vivo.add(creature);
                            }
                            else
                            {
                                anomaly();
                            }

                        }
                    Monstro local_monstro = (Monstro) local_creature;
                    int action = get_monstro_choice(local_monstro);

                    if (action == BaseCreature.ATTACK_PROTOCOL) {
                        ArrayList< HeroClass > possible_targets_list = new ArrayList<>();
                        for (int i=0;i<criaturas_array.size();i++)
                        {
                            if (!criaturas_array.get(i).getIsAlive())
                            {
                                break;
                            }
                            if (criaturas_array.get(i) instanceof HeroClass)
                            {
                                HeroClass local_hero = (HeroClass) criaturas_array.get(i);
                                possible_targets_list.add(local_hero);
                            }
                        }
                        if (possible_targets_list.size()==0)
                        {
                            System.out.println("?????");
                        }
                        else
                        {
                            System.out.println("Monstro "+local_creature.getNome()+" agindo!");
                            BaseCreature target = possible_targets_list.get(0);
                            System.out.println("Monstro "+local_monstro.getNome()+" atacando "+target.getNome());
                            Double dmg = battle_math.calculate_damage(local_creature, target);
                            target.takeDamage(dmg);
                            System.out.println("Dano = " + dmg);
                        }
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
        return(onEnd(criaturas_array));

    }
    
    public void checkEveryTurn(Collection<BaseCreature> entry) {
        //checar quem esta vivo e remover quem esta morto
        
        if (entry instanceof ArrayList)
        {
            ArrayList< BaseCreature > creature_array = (ArrayList)entry;
            
            BaseCreature criatura_que_agiu = creature_array.get(0);
            whenGetTurn(criatura_que_agiu);
            
            for (int i=0;i<creature_array.size();i++)
            {
                BaseCreature local_creature = creature_array.get(i);
                local_creature.everyTime();
                if (!local_creature.getIsAlive())
                {
                    break;
                }
                if (local_creature.getHit_points()<=0)
                {
                    local_creature.onDeath();
                    //empurra monstros mortos pro final do vetor
                    for (int j=i;j<creature_array.size()-1;j++)
                    {
                        BaseCreature copy = creature_array.get(j);
                        creature_array.set(j, creature_array.get(j+1));
                        creature_array.set(j+1, copy);
                    }
                }
            }
        }
        else
        {
            throw new UnsupportedOperationException("Logica implementada somente sobre ArrayList no momento!.");
        }
    }
    
    /**
     * Metodo chamada para criatura que ganhou o turno
     * @param creature_que_ganhou_turno creature que ganhou o turno
     */
    public void whenGetTurn(BaseCreature creature_que_ganhou_turno)
    {
        //faz algo com ela :)
        creature_que_ganhou_turno.setAttack_bar(0.00);
        creature_que_ganhou_turno.everyTurn();
    }
    
    /**
     * Metodo chamada no comeco da batalha
     * @param coll collection de BaseCreature
     */
    public void onStart(Collection< BaseCreature > coll)
    {
        display_battle_info(coll,null); //todoodododododoododoodooodododoo ADD PRINTWRITER
        for (BaseCreature creature : coll)
        {
            creature.onStart();
        }
    }
    
    public int onEnd(Collection< BaseCreature > coll)
    {
        ArrayList< HeroClass > heroes = new ArrayList<>();
        ArrayList< Monstro > monstros = new ArrayList<>();
        for (BaseCreature c : coll)
        {
            if (c instanceof HeroClass)
            {
                HeroClass hero = (HeroClass)c;
                heroes.add(hero);
            }
            else if (c instanceof Monstro)
            {
                Monstro monstro = (Monstro)c;
                monstros.add(monstro);
            }
            else
            {
                anomaly();
            }
        }
        
        //indica se há um monstro vivo pelo menos, ou seja herois perderam e deve-se executar gameover
        boolean someoneAlive = false;
        for (Monstro c : monstros)
        {
            if (c.isIsAlive())
            {
                someoneAlive = true;
                break;
            }
        }
        if (someoneAlive)
        {
            return(onGameOver());
        }
        else
        {
            Random generator = new Random();
            double xp_pool=0;
            int quantia_de_itens=0;
            for (Monstro c : monstros)
            {
                xp_pool = xp_pool + c.getLevel()*100;
                int will_get_new_item = generator.nextInt(101);
                if (will_get_new_item<ItemGenerator.CHANCE_OF_DROP)
                {
                    quantia_de_itens++;
                }
            }
            
            System.out.println("Os herois ganharam "+xp_pool+" Experience Points e "+quantia_de_itens+" itens!\n");
            
            for (HeroClass c : heroes)
            {
                if (c instanceof HeroClass)
                {
                    HeroClass local_hero = (HeroClass)c;
                    local_hero.addXP(xp_pool);
                }
                else
                {
                    anomaly();
                }
            }
            
            System.out.println("");
            
            for (int i=0;i<quantia_de_itens;i++)
            {
                BaseItem item = ItemGenerator.generateItem();
                System.out.println("Voce recebeu o item "+item.getDescription());
                int item_for_who = 0;
                do{
                    item_for_who = MyUtil.get_and_display(heroes, "Quem deve receber o item?");
                }while(item_for_who==-1);
                HeroClass local_hero = heroes.get(item_for_who);
                System.out.println("O item vai para " + local_hero.getNome());
                local_hero.addItem(item);
            }
            return(CONTINUE_CODE);
        }
        
    }
    
    public int onGameOver()
    {
        System.out.println("VOCE PERDEU HAHAHAHAHAHAHAHAHAHAHAHAHA");
        return(GAME_OVER_CODE);
    }

    public boolean condicao_de_parada(Collection<BaseCreature> creature_array) {
        int numero_de_herois=0;
        int numero_de_monstros=0;
        for (BaseCreature local_creature : creature_array)
        {
            if (local_creature.getIsAlive())
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
        }
        if (numero_de_herois==0||numero_de_monstros==0)
        {
            //System.out.println("heroi = "+numero_de_herois + ",monstro = "+numero_de_monstros);
            return(true);
        }
        return(false);
    }
    
    /**
     * Escreve os dados da batalha, ora no STDOUT, ora em um arquivo.
     * @param creatures_array Criaturas presentes na batalha; tanto
     *                        heróis como monstros.
     * 
     * @param TODO            PrintWriter para imprimir os dados.
     */
    public void display_battle_info(Collection<BaseCreature> creatures_array, PrintWriter TODO) {
        //minimalista no momento
        for (BaseCreature local_creature : creatures_array)
        {
            if (local_creature.getIsAlive())
            {
                if (local_creature instanceof HeroClass)
                {
                    HeroClass hero = (HeroClass)local_creature;
                    System.out.println("Hero stats:\n"
                            + hero + '\n');
                }
                else
                {
                    if (local_creature instanceof Monstro)
                    {
                        Monstro monstro_local = (Monstro)local_creature;
                        System.out.println("Monstro stats:"+"\n"
                            + monstro_local + '\n');
                    }
                    else
                    {
                        System.out.println("Erro grave!\n");
                    }
                }
            }
            else
            {
                System.out.println(local_creature.getNome()+" is dead."); //Typo here. "Death" is subject, and "dead" is adjective.
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
}
