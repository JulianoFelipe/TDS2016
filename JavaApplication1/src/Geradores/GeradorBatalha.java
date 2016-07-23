/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geradores;

import utilidades.Util;
import Itens.ItemBase;
import Criaturas.CriaturaBase;
import Criaturas.Heroi;
import Criaturas.Monstro;
import math_package.*;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import Habilidades.BaseSkill;

/**
 * Classe que gera batalhas,gerencia status da batalha e devolve recompensas.
 *
 * @author Paulo Henrique
 * @author Juliano Felipe
 */
public class GeradorBatalha {

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

    /**
     * Por hora constante nao faz nada alem de definir qualidade dos drops
     */
    public static final int AVERAGE_MONSTER_LEVEL = 1;

    /**
     * atalho para comando bastante utilizado
     */
    private static void anulando() {
        System.out.println("Anulando acao!....");
    }

    /**
     * atalho usado para comando bastante utilizado
     */
    private static void erro() {
        System.out.println("Ops um erro aconteceu, tentando de novo....");
    }

    /**
     * Usado para sanity checks
     */
    private static void anomaly() {
        System.out.println("Essa msg nao deve aparecer");
    }

    /**
     * Gera um conflito aleatório Monstros são gerados internamente.
     *
     * @param hero_list Lista com herois batalhando
     * @return          Condição ao parar.
     *
     */
    public int random_conflict(ArrayList<Heroi> hero_list) {
        Random generator = new Random();
        int numero_de_inimigos = MIN_NUMERO_DE_INIMIGOS + generator.nextInt(MAX_NUMERO_DE_INIMIGOS - MIN_NUMERO_DE_INIMIGOS);
        int monstro_level = 1;//pensar em uma logica pra isso tbm

        ArrayList<CriaturaBase> criaturas_array = new ArrayList<>();

        //coloca Herois e Monstros em um unico array do tipo ArrayList < CriaturaBase >
        for (Heroi hero1 : hero_list) {
            criaturas_array.add(hero1);
        }

        for (int i = 0; i < numero_de_inimigos; i++) {
            Monstro new_monstro = GeradorMonstro.gerarMonstro(monstro_level);
            criaturas_array.add(new_monstro);
            new_monstro.setHit_points(new_monstro.getMax_hit_points());
        }
        onStart(criaturas_array);
        while (!condicao_de_parada(criaturas_array)) {
            turn_order_math.nextTurn(criaturas_array);
            Collections.sort(criaturas_array);
            CriaturaBase local_creature = criaturas_array.get(0);
            System.out.println(local_creature.getNome() + " esta movendo!");
            ArrayList< CriaturaBase> array_inimigo_vivo = new ArrayList<>();
            ArrayList< CriaturaBase> array_aliado_vivo = new ArrayList<>();
            if (!local_creature.isAlive()) {
                break;
            }
            if (local_creature instanceof Heroi) {
                for (CriaturaBase creature : criaturas_array) {
                    if (creature instanceof Monstro) {
                        array_inimigo_vivo.add(creature);
                    } else if (creature instanceof Heroi) {
                        array_aliado_vivo.add(creature);
                    } else {
                        anomaly();
                    }

                }
                boolean should_end_turn = true;
                do {
                    should_end_turn = true;
                    display_battle_info_simplified(criaturas_array); //todoodododododoododoodooodododoo ADD PRINTWRITER
                    System.out.println("Heroi " + local_creature.getNome() + " agindo!");
                    int action = -1;
                    do {
                        action = Util.get_player_choice(1, 4, "1 - atacar 2 - usar skill 3-ignorar round 4 - exibir status detalhados de combatentes");
                        if (action == -1) {
                            erro();
                        }
                    } while (action == -1);
                    if (action == Util.BACK_PROTOCOL) {
                        should_end_turn = false;
                    } else if (action == CriaturaBase.ATTACK_PROTOCOL) {
                        ArrayList< Monstro> possible_targets_list = new ArrayList<>();
                        for (int i = 0; i < criaturas_array.size(); i++) {
                            if (!criaturas_array.get(i).isAlive()) {
                                break;
                            }
                            if (criaturas_array.get(i) instanceof Monstro) {
                                Monstro local_monstro = (Monstro) criaturas_array.get(i);
                                possible_targets_list.add(local_monstro);
                            }
                        }

                        int target_index = -1;
                        while (target_index == -1) {
                            target_index = Util.getcanceleable_and_display(possible_targets_list, "Qual monstro deseja atacar?");
                            if (target_index == -1) {
                                System.out.println("Ocorreu um erro!, tentando corrigir....");
                            }
                        }

                        if (target_index == Util.BACK_PROTOCOL) {
                            should_end_turn = false;
                            anulando();
                        } else {
                            CriaturaBase target = possible_targets_list.get(target_index);
                            System.out.println("Heroi " + local_creature.getNome() + " atacando " + target.getNome() + "!");
                            Double dmg = battle_math.calculate_damage(local_creature, target);
                            target.takeDamage(dmg);

                            if (dmg == 0) {
                                System.out.println("MISS!");
                            } else {
                                System.out.println("Dano = " + dmg);
                            }
                        }
                    } else if (action == CriaturaBase.SKILL_PROTOCOL) {
                        ArrayList< BaseSkill> skill_usaveis = local_creature.getUsableSkillsArray();
                        System.out.println("Skill que nao podem ser usadas :");
                        System.out.println(local_creature.getUnusableSkills());
                        int skill_index = 0;
                        do {
                            //skill_index = Util.getcanceleable_and_display(skill_usaveis, "Qual skill deseja selecionar?");
                            skill_index = 0;
                            if (skill_index == -1) {
                                erro();
                            }
                        } while (skill_index == -1);
                        if (skill_index == Util.BACK_PROTOCOL) {
                            should_end_turn = false;
                        } else {
                            BaseSkill skill_usada = skill_usaveis.get(skill_index);
                            System.out.println("Usando skill->" + skill_usada.getDescricao());
                            if (skill_usada.getTipo().equals("Ofensivo")) {
                                for (CriaturaBase creature : array_inimigo_vivo) {
                                    skill_usada.transferEffect(creature);
                                    //System.out.println("creature afetada->"+creature);
                                }
                            } else if (skill_usada.getTipo().equals("Defensivo")) {
                                for (CriaturaBase creature : array_aliado_vivo) {
                                    skill_usada.transferEffect(creature);
                                    //System.out.println("creature afetada->"+creature);
                                }
                            } else {
                                System.out.println("tipo = " + skill_usada.getTipo());
                                anomaly();
                            }
                            skill_usada.onUse();

                        }
                    } else if (action == CriaturaBase.IGNORE_ROUND_PROTOCOL) {
                        System.out.println("ignorando round.....");
                    } else if (action == CriaturaBase.REPORT_PROTOCOL) {

                        display_battle_info(criaturas_array, null);
                        should_end_turn = false;//acao nao deve terminar turno
                    }
                } while (!should_end_turn);
            } else {
                if (local_creature instanceof Monstro) {
                    for (CriaturaBase creature : criaturas_array) {
                        if (creature instanceof Heroi) {
                            array_inimigo_vivo.add(creature);
                        } else if (creature instanceof Monstro) {
                            array_aliado_vivo.add(creature);
                        } else {
                            anomaly();
                        }

                    }
                    Monstro local_monstro = (Monstro) local_creature;
                    int action = get_monstro_choice(local_monstro);

                    if (action == CriaturaBase.ATTACK_PROTOCOL) {
                        ArrayList< Heroi> possible_targets_list = new ArrayList<>();
                        for (int i = 0; i < criaturas_array.size(); i++) {
                            if (!criaturas_array.get(i).isAlive()) {
                                break;
                            }
                            if (criaturas_array.get(i) instanceof Heroi) {
                                Heroi local_hero = (Heroi) criaturas_array.get(i);
                                possible_targets_list.add(local_hero);
                            }
                        }
                        if (possible_targets_list.size() == 0) {
                            System.out.println("?????");
                        } else {
                            System.out.println("Monstro " + local_creature.getNome() + " agindo!");
                            int index_alvo = generator.nextInt(possible_targets_list.size());
                            CriaturaBase target = possible_targets_list.get(index_alvo);
                            System.out.println("Monstro " + local_monstro.getNome() + " atacando " + target.getNome());
                            Double dmg = battle_math.calculate_damage(local_creature, target);
                            target.takeDamage(dmg);
                            System.out.println("Dano = " + dmg);
                        }
                    } else if (action == CriaturaBase.SKILL_PROTOCOL) {
                        //por enquanto escolhera skill aleatoriamente dentre as possibilidades
                        ArrayList< BaseSkill> possible_skills = local_monstro.getUsableSkillsArray();
                        int skill_indice = generator.nextInt(possible_skills.size());
                        BaseSkill skill_usada = possible_skills.get(skill_indice);

                        System.out.println("Monstro esta usando skill -> " + skill_usada.getDescricao());
                        if (skill_usada.getTipo().equals("Ofensivo")) {
                            for (CriaturaBase creature : array_inimigo_vivo) {
                                skill_usada.transferEffect(creature);
                                //System.out.println("creature afetada->"+creature);
                            }
                        } else if (skill_usada.getTipo().equals("Defensivo")) {
                            for (CriaturaBase creature : array_aliado_vivo) {
                                skill_usada.transferEffect(creature);
                                //System.out.println("creature afetada->"+creature);
                            }
                        } else {
                            System.out.println("tipo = " + skill_usada.getTipo());
                            anomaly();
                        }
                        skill_usada.onUse();

                    }
                } else {
                    System.out.println("Erro grave 3");
                }
            }
            checkEveryTurn(criaturas_array);
            if (condicao_de_parada(criaturas_array)) {
                break;
            }
        }
        return (onEnd(criaturas_array));

    }

    /**
     * Chamada todo turno
     *
     * @param entry Collections de base creature que sera avaliada
     */
    public void checkEveryTurn(Collection<CriaturaBase> entry) {
        //checar quem esta vivo e remover quem esta morto

        if (entry instanceof ArrayList) {
            ArrayList< CriaturaBase> creature_array = (ArrayList) entry;

            CriaturaBase criatura_que_agiu = creature_array.get(0);
            whenGetTurn(criatura_que_agiu);

            for (int i = 0; i < creature_array.size(); i++) {
                CriaturaBase local_creature = creature_array.get(i);
                local_creature.everyTime();
                if (!local_creature.isAlive()) {
                    break;
                }
                if (local_creature.getHit_points() <= 0) {
                    local_creature.onDeath();
                    //empurra monstros mortos pro final do vetor
                    for (int j = i; j < creature_array.size() - 1; j++) {
                        CriaturaBase copy = creature_array.get(j);
                        creature_array.set(j, creature_array.get(j + 1));
                        creature_array.set(j + 1, copy);
                    }
                }
            }
        } else {
            throw new UnsupportedOperationException("Logica implementada somente sobre ArrayList no momento!");
        }
    }

    /**
     * Metodo chamada para criatura que ganhou o turno
     *
     * @param creature_que_ganhou_turno creature que ganhou o turno
     */
    public void whenGetTurn(CriaturaBase creature_que_ganhou_turno) {
        //faz algo com ela :)
        creature_que_ganhou_turno.setAttack_bar(0.00);
        creature_que_ganhou_turno.everyTurn();
    }

    /**
     * Metodo chamada no comeco da batalha
     *
     * @param coll collection de CriaturaBase
     */
    public void onStart(Collection< CriaturaBase> coll) {
        for (CriaturaBase creature : coll) {
            creature.onStart();
        }
        //display_battle_info_simplified(coll);

    }

    /**
     * Metoda chamando sempre que a batalha termina
     *
     * @param coll Collections com criaturas que participaram da batalha
     * @return     {@link BattleGenerator#CONTINUE_CODE} Se os herois ganharam
     *             (pelo menos um heroi vivo). {@link BattleGenerator#GAME_OVER_CODE},
     *             caso todos os herois morreram
     */
    public int onEnd(Collection< CriaturaBase> coll) {
        ArrayList< Heroi> heroes = new ArrayList<>();
        ArrayList< Monstro> monstros = new ArrayList<>();
        for (CriaturaBase c : coll) {
            if (c instanceof Heroi) {
                Heroi hero = (Heroi) c;
                heroes.add(hero);
            } else if (c instanceof Monstro) {
                Monstro monstro = (Monstro) c;
                monstros.add(monstro);
            } else {
                anomaly();
            }
        }

        //indica se há um monstro vivo pelo menos, ou seja herois perderam e deve-se executar gameover
        boolean someoneAlive = false;
        for (Monstro c : monstros) {
            if (c.isAlive()) {
                someoneAlive = true;
                break;
            }
        }
        if (someoneAlive) {
            return (onGameOver());
        } else {
            Random generator = new Random();
            double xp_pool = 0;
            int gold_pool = 0;
            int quantia_de_itens = 0;
            for (Monstro c : monstros) {
                xp_pool = xp_pool + c.getLevel() * 100;
                gold_pool = gold_pool + c.getLevel() * 10;
                int will_get_new_item = generator.nextInt(101);
                if (will_get_new_item < GeradorItem.CHANCE_OF_DROP) {
                    quantia_de_itens++;
                }
            }

            System.out.println("Os herois ganharam " + xp_pool + " Experience Points, " + gold_pool + " Pecas de ouro e " + quantia_de_itens + " itens!\n");

            for (Heroi c : heroes) {
                if (c instanceof Heroi) {
                    Heroi local_hero = (Heroi) c;
                    local_hero.addXP(xp_pool);

                    local_hero.addGold(gold_pool);
                } else {
                    anomaly();
                }
            }

            System.out.println("");

            for (int i = 0; i < quantia_de_itens; i++) {
                ItemBase item = GeradorItem.generateItem(AVERAGE_MONSTER_LEVEL);
                System.out.println("Voce recebeu o item " + item.getDescription());
                int item_for_who = 0;
                do {
                    item_for_who = Util.get_and_display(heroes, "Quem deve receber o item?");
                } while (item_for_who == -1);
                Heroi local_hero = heroes.get(item_for_who);
                System.out.println("O item vai para " + local_hero.getNome());
                local_hero.addItem(item);
                item.setOwner(local_hero);
            }
            return (CONTINUE_CODE);
        }

    }

    /**
     * Se prescissar algum tratamento adicional quando o jogo terminar em game
     * over ele é feito aqui
     *
     * @return {@link BattleGenerator#GAME_OVER_CODE}
     */
    public int onGameOver() {
        System.out.println("VOCE PERDEU HAHAHAHAHAHAHAHAHAHAHAHAHA");
        return (GAME_OVER_CODE);
    }

    /**
     * Checa se a batalha já chegou ao fim, ou seja todos os herois estao mortos
     * ou todos os monstros estao mortos.
     *
     * @param creature_array Collection com criaturas parcipando da batalha.
     * @return               True se batalha terminou, False caso contrario.
     */
    public boolean condicao_de_parada(Collection<CriaturaBase> creature_array) {
        int numero_de_herois = 0;
        int numero_de_monstros = 0;
        for (CriaturaBase local_creature : creature_array) {
            if (local_creature.isAlive()) {
                if (local_creature instanceof Heroi) {
                    numero_de_herois++;
                } else {
                    if (local_creature instanceof Monstro) {
                        numero_de_monstros++;
                    } else {
                        System.out.println("Erro Grave 2");
                    }
                }
            }
        }
        if (numero_de_herois == 0 || numero_de_monstros == 0) {
            //System.out.println("heroi = "+numero_de_herois + ",monstro = "+numero_de_monstros);
            return (true);
        }
        return (false);
    }

    /**
     * Versao simplificada de display_battle_info.
     *
     * @param creatures_array Array com criaturas.
     */
    public void display_battle_info_simplified(Collection<CriaturaBase> creatures_array) {
        for (CriaturaBase local_creature : creatures_array) {
            if (local_creature.isAlive()) {
                if (local_creature instanceof Heroi) {
                    Heroi hero = (Heroi) local_creature;
                    System.out.println("Hero " + hero.getNome() + " HP:" + hero.getHit_points() + " Atk Bar:" + (Math.floor(hero.getAttack_bar() * 10000 / CriaturaBase.ATTACK_BAR_TO_MOVE) / 100));
                } else {
                    if (local_creature instanceof Monstro) {
                        Monstro monstro = (Monstro) local_creature;
                        System.out.println("Monstro " + monstro.getNome() + " HP:" + monstro.getHit_points() + " Atk Bar:" + (Math.floor(monstro.getAttack_bar() * 10000 / CriaturaBase.ATTACK_BAR_TO_MOVE) / 100));
                    } else {
                        System.out.println("Erro grave!\n");
                    }
                }
            } else {
                System.out.println(local_creature.getNome() + " is dead."); //Typo here. "Death" is subject, and "dead" is adjective.
            }
        }
    }

    /**
     * Escreve os dados da batalha
     *
     * @param creatures_array Criaturas presentes na batalha; tanto heróis como
     * monstros.
     *
     * @param TODO Por enquanto inutilizavel mas no futuro poderá ser ajustado
     * para detalhes da batalha serem escritos em arquivos por exemplo.
     * 
     * Ou redireconar o System.out. para outro outputStream?
     */
    public void display_battle_info(Collection<CriaturaBase> creatures_array, PrintWriter TODO) {
        //minimalista no momento
        for (CriaturaBase local_creature : creatures_array) {
            if (local_creature.isAlive()) {
                if (local_creature instanceof Heroi) {
                    Heroi hero = (Heroi) local_creature;
                    System.out.println("Hero stats:\n"
                            + hero + '\n');
                } else {
                    if (local_creature instanceof Monstro) {
                        Monstro monstro_local = (Monstro) local_creature;
                        System.out.println("Monstro stats:" + "\n"
                                + monstro_local + '\n');
                    } else {
                        System.out.println("Erro grave!\n");
                    }
                }
            } else {
                System.out.println(local_creature.getNome() + " is dead."); //Typo here. "Death" is subject, and "dead" is adjective.
            }
        }
    }

    /**
     * A ação do monstro
     *
     * @param local_monstro Que executará a ação.
     * @return              Ação que o monstro executará.
     */
    public int get_monstro_choice(Monstro local_monstro) {
        //se ele tem pelo menos uma skill para usar ele vai usar
        if (local_monstro.getUsableSkillsArray().size() > 0) {
            return (CriaturaBase.SKILL_PROTOCOL);
        } else {
            //System.out.println("Monstro nao pode usar skill pois "+local_monstro.getUnusableSkills());
        }
        return (CriaturaBase.ATTACK_PROTOCOL);
    }
}
