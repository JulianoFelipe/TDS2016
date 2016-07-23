/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geradores;

import Control.ArenaControl;
import Enum.*;
import CriaturasPackage.BaseCreature;
import CriaturasPackage.HeroClass;
import CriaturasPackage.Monstro;
import ItensPackage.BaseItem;
import SkillPackage.BaseSkill;
import View.SeletorCriaturas;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import math_package.battle_math;
import math_package.turn_order_math;
import utillities.MyUtil;

/**
 *
 * @author FREE
 */
public class BattleArena extends Observable{
    private final List< BaseCreature > lista_criaturas;
    private final List< BaseCreature > lista_mortos;
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

    public BattleArena(List< HeroClass > lista_herois)
    {
        lista_criaturas = new ArrayList<>();
        lista_mortos = new ArrayList<>();
        for (HeroClass hero : lista_herois)
        {
            lista_criaturas.add(hero);
        }
        initArena();
    }
    
    public List< BaseCreature > getBattleArenaSituation()
    {
        return(lista_criaturas);
    }
    
    public List< BaseCreature > getMonstroVivosArray()
    {
        List< BaseCreature> retorno = new ArrayList<>();
        for (BaseCreature creature : lista_criaturas)
        {
            if (creature instanceof Monstro)
            {
                if (creature.isAlive())
                {
                    retorno.add(creature);
                }
            }
        }
        return(retorno);
    }
    
    public BaseCreature getBaseCreatureAt(int indice)
    {
        return( lista_criaturas.get(indice) );
    }
    
    /**
     * Inclue monstros na lista de criaturas
     */
    private void initArena()
    {
        Random generator = new Random();
        int numero_de_inimigos = MIN_NUMERO_DE_INIMIGOS + generator.nextInt(MAX_NUMERO_DE_INIMIGOS - MIN_NUMERO_DE_INIMIGOS);
        int monstro_level = 1;//pensar em uma logica pra isso tbm

        for (int i = 0; i < numero_de_inimigos; i++) {
            Monstro new_monstro = MonstroGenerator.gerarMonstro(monstro_level);
            new_monstro.setHit_points(new_monstro.getMax_hit_points());
            lista_criaturas.add(new_monstro);
        }
        onStart(lista_criaturas);
        Collections.sort(lista_criaturas);
    }
    
    public double attackBaseCreature(BaseCreature defensor)
    {
        BaseCreature atacante = lista_criaturas.get(0);
        
        System.out.println("Heroi " + atacante.getNome() + " atacando " + defensor.getNome() + "!");
        Double dmg = battle_math.calculate_damage(atacante, defensor);
        
        defensor.takeDamage(dmg);
        Double vida_depois = defensor.getHit_points();

        if (dmg == 0) {
            System.out.println("MISS!");
        } else {
            System.out.println("Dano = " + dmg);
        }
        
        Object array_object[] = new Object[3];
        array_object[0] = FrameExibido.ATACAR_DEFENDER_FRAME;
        array_object[1] = dmg;
        array_object[2] = defensor;
        
        setChanged();
        notifyObservers(array_object);
        
        whenGetTurn(atacante);
        if (defensor.getHit_points() < 0)
        {
            System.out.println("menor que 0 !");
        }
        else
        {
            
        }
        defensor.setHit_points(vida_depois);
        checkEveryTurn(lista_criaturas);
        
        return( dmg );
    }
    
    public void nextTurn()
    {
        if (!condicao_de_parada(lista_criaturas))
        {
            System.out.println("Nao terminou!");
            turn_order_math.nextTurn(lista_criaturas);
            Collections.sort(lista_criaturas);
            setChanged();
            notifyObservers( FrameExibido.BATALHA_FRAME );
            BaseCreature local_creature = lista_criaturas.get(0);
            System.out.println(local_creature.getNome() + " esta agindo!");
            ArrayList< BaseCreature> array_inimigo_vivo = new ArrayList<>();
            ArrayList< BaseCreature> array_aliado_vivo = new ArrayList<>();
            if (local_creature instanceof HeroClass) {
                //pegar acao
                
                if (0 == BaseCreature.SKILL_PROTOCOL) {
                    /*
                    ArrayList< BaseSkill> skill_usaveis = local_creature.getUsableSkillsArray();
                    System.out.println("Skill que nao podem ser usadas :");
                    System.out.println(local_creature.getUnusableSkills());
                    int skill_index = 0;
                    do {
                        skill_index = MyUtil.getcanceleable_and_display(skill_usaveis, "Qual skill deseja selecionar?");
                        if (skill_index == -1) {
                            erro();
                        }
                    } while (skill_index == -1);
                    if (skill_index == MyUtil.BACK_PROTOCOL) {
                        should_end_turn = false;
                    } else {
                        BaseSkill skill_usada = skill_usaveis.get(skill_index);
                        System.out.println("Usando skill->" + skill_usada.getDescription());
                        if (skill_usada.getTipo().equals("Ofensivo")) {
                            for (BaseCreature creature : array_inimigo_vivo) {
                                skill_usada.transferEffect(creature);
                                //System.out.println("creature afetada->"+creature);
                            }
                        } else if (skill_usada.getTipo().equals("Defensivo")) {
                            for (BaseCreature creature : array_aliado_vivo) {
                                skill_usada.transferEffect(creature);
                                //System.out.println("creature afetada->"+creature);
                            }
                        } else {
                            System.out.println("tipo = " + skill_usada.getTipo());
                            anomaly();
                        }
                        skill_usada.onUse();
                    */
                }
            }
            else if (local_creature instanceof Monstro) {
                System.out.println("monstro!");
                for (BaseCreature creature : lista_criaturas) {
                    if (creature instanceof HeroClass) {
                        array_inimigo_vivo.add(creature);
                    } else if (creature instanceof Monstro) {
                        array_aliado_vivo.add(creature);
                    } else {
                        
                    }

                }
                Monstro local_monstro = (Monstro) local_creature;
                EscolhaEnum action = get_monstro_choice(local_monstro);
                Random generator = new Random();
                if (action == EscolhaEnum.ATACAR) {
                    System.out.println("monstro atacando!");
                    ArrayList< HeroClass> possible_targets_list = new ArrayList<>();
                    for (int i = 0; i < lista_criaturas.size(); i++) {
                        if (!lista_criaturas.get(i).isAlive()) {
                            break;
                        }
                        if (lista_criaturas.get(i) instanceof HeroClass) {
                            HeroClass local_hero = (HeroClass) lista_criaturas.get(i);
                            possible_targets_list.add(local_hero);
                        }
                    }
                    if (possible_targets_list.size() == 0) {
                        System.out.println("?????");
                    } else {
                        System.out.println("Monstro " + local_creature.getNome() + " agindo!");
                        int index_alvo = generator.nextInt(possible_targets_list.size());
                        BaseCreature target = possible_targets_list.get(index_alvo);
                        attackBaseCreature(target);
                    }
                } else if (action == EscolhaEnum.SKILL) {
                    /*
                    //por enquanto escolhera skill aleatoriamente dentre as possibilidades
                    ArrayList< BaseSkill> possible_skills = local_monstro.getUsableSkillsArray();
                    int skill_indice = generator.nextInt(possible_skills.size());
                    BaseSkill skill_usada = possible_skills.get(skill_indice);

                    System.out.println("Monstro esta usando skill -> " + skill_usada.getDescription());
                    if (skill_usada.getTipo().equals("Ofensivo")) {
                        for (BaseCreature creature : array_inimigo_vivo) {
                            skill_usada.transferEffect(creature);
                            //System.out.println("creature afetada->"+creature);
                        }
                    } else if (skill_usada.getTipo().equals("Defensivo")) {
                        for (BaseCreature creature : array_aliado_vivo) {
                            skill_usada.transferEffect(creature);
                            //System.out.println("creature afetada->"+creature);
                        }
                    } else {
                        System.out.println("tipo = " + skill_usada.getTipo());
                        
                    }
                    skill_usada.onUse();
                    */
                }
            } else {
                System.out.println("Erro grave 3");
            }
        }
        else
        {
            System.out.println("fim da batalha!");
        }
    }

    /**
     * Chamada todo turno
     *
     * @param entry Collections de base creature que sera avaliada
     */
    public void checkEveryTurn(Collection<BaseCreature> entry) {
        //checar quem esta vivo e remover quem esta morto

        if (entry instanceof ArrayList) {
            ArrayList< BaseCreature> creature_array = (ArrayList) entry;

            for (int i = 0; i < creature_array.size(); i++) {
                BaseCreature local_creature = creature_array.get(i);
                local_creature.everyTime();
                if (!local_creature.isAlive()) {
                    break;
                }
                if (local_creature.getHit_points() <= 0) {
                    local_creature.onDeath();
                    //muda de fila de vivos para mortos
                    lista_criaturas.remove( local_creature );
                    lista_mortos.add( local_creature );
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
    public void whenGetTurn(BaseCreature creature_que_ganhou_turno) {
        //faz algo com ela :)
        creature_que_ganhou_turno.setAttack_bar(0.00);
        creature_que_ganhou_turno.everyTurn();
    }

    /**
     * Metodo chamada no comeco da batalha
     *
     * @param coll collection de BaseCreature
     */
    public void onStart(Collection< BaseCreature> coll) {
        for (BaseCreature creature : coll) {
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
    public int onEnd(Collection< BaseCreature> coll) {
        ArrayList< HeroClass> heroes = new ArrayList<>();
        ArrayList< Monstro> monstros = new ArrayList<>();
        for (BaseCreature c : coll) {
            if (c instanceof HeroClass) {
                HeroClass hero = (HeroClass) c;
                heroes.add(hero);
            } else if (c instanceof Monstro) {
                Monstro monstro = (Monstro) c;
                monstros.add(monstro);
            } else {
                
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
                if (will_get_new_item < ItemGenerator.CHANCE_OF_DROP) {
                    quantia_de_itens++;
                }
            }

            System.out.println("Os herois ganharam " + xp_pool + " Experience Points, " + gold_pool + " Pecas de ouro e " + quantia_de_itens + " itens!\n");

            for (HeroClass c : heroes) {
                if (c instanceof HeroClass) {
                    HeroClass local_hero = (HeroClass) c;
                    local_hero.addXP(xp_pool);

                    local_hero.addGold(gold_pool);
                } else {
                    
                }
            }

            System.out.println("");

            for (int i = 0; i < quantia_de_itens; i++) {
                BaseItem item = ItemGenerator.generateItem(AVERAGE_MONSTER_LEVEL);
                System.out.println("Voce recebeu o item " + item.getDescription());
                int item_for_who = 0;
                do {
                    item_for_who = MyUtil.get_and_display(heroes, "Quem deve receber o item?");
                } while (item_for_who == -1);
                HeroClass local_hero = heroes.get(item_for_who);
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
    public boolean condicao_de_parada(Collection<BaseCreature> creature_array) {
        int numero_de_herois = 0;
        int numero_de_monstros = 0;
        for (BaseCreature local_creature : creature_array) {
            if (local_creature.isAlive()) {
                System.out.println("BaseCreature nome = " + local_creature.getNome() + ", isalive = " + local_creature.isAlive());
                if (local_creature instanceof HeroClass) {
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
        System.out.println("heroi = "+numero_de_herois + ",monstro = "+numero_de_monstros);
        if (numero_de_herois == 0 || numero_de_monstros == 0) {
            return (true);
        }
        return (false);
    }

    /**
     * Versao simplificada de display_battle_info.
     *
     * @param creatures_array Array com criaturas.
     */
    public void display_battle_info_simplified(Collection<BaseCreature> creatures_array) {
        for (BaseCreature local_creature : creatures_array) {
            if (local_creature.isAlive()) {
                if (local_creature instanceof HeroClass) {
                    HeroClass hero = (HeroClass) local_creature;
                    System.out.println("Hero " + hero.getNome() + " HP:" + hero.getHit_points() + " Atk Bar:" + (Math.floor(hero.getAttack_bar() * 10000 / BaseCreature.ATTACK_BAR_TO_MOVE) / 100));
                } else {
                    if (local_creature instanceof Monstro) {
                        Monstro monstro = (Monstro) local_creature;
                        System.out.println("Monstro " + monstro.getNome() + " HP:" + monstro.getHit_points() + " Atk Bar:" + (Math.floor(monstro.getAttack_bar() * 10000 / BaseCreature.ATTACK_BAR_TO_MOVE) / 100));
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
    public void display_battle_info(Collection<BaseCreature> creatures_array, PrintWriter TODO) {
        //minimalista no momento
        for (BaseCreature local_creature : creatures_array) {
            if (local_creature.isAlive()) {
                if (local_creature instanceof HeroClass) {
                    HeroClass hero = (HeroClass) local_creature;
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
    public EscolhaEnum get_monstro_choice(Monstro local_monstro) {
        /*
        //se ele tem pelo menos uma skill para usar ele vai usar
        if (local_monstro.getUsableSkillsArray().size() > 0 || false) {
            return (BaseCreature.SKILL_PROTOCOL);
        } else {
            //System.out.println("Monstro nao pode usar skill pois "+local_monstro.getUnusableSkills());
        }
        */
        return (EscolhaEnum.ATACAR);
    }
}