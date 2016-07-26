/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

import Model.Geradores.GeradorItem;
import Model.Geradores.GeradorBatalha;
import utilidades.Descritivel;
import utilidades.Util;
import Model.Habilidades.HabilidadeBase;
import Model.Criaturas.Mago;
import Model.Criaturas.Cavaleiro;
import Model.Criaturas.Heroi;
import Model.Itens.ConsumivelBase;
import Model.Itens.EquipavelBase;
import Model.Itens.ItemBase;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Paulo Tenório
 * @author Juliano Felipe
 */
public class JavaApplication1 {
// <editor-fold defaultstate="collapsed" desc="Stuff">
    // </editor-fold>  

    /**
     * Constante que define chance do shop atualizar o estoque(retirar produtos
     * velhos e adicionar novos)
     */
    public static final int SHOP_REFRESH_CHANCE = 100;

    /**
     * O quanto o shop aumentara os preços dos itens
     */
    public static final int SHOP_INTEREST_RATE = 3;

    /**
     * Ao fazer refresh determina a chance de um item ser gerado, se item for
     * gerado usa essa mesma chance para gerar outra ate falhar Nao pode ser 100
     * se nao loop não parará
     */
    public static final int NEW_ITEM_CHANCE = 90;

    /**
     * Gera novos itens para o shop e retira itens antigos
     *
     * @param level level dos itens gerados
     */
    private static void updateShop(int level) {
        System.out.println("O shop foi atualizado!");
        shop_itens.clear();
        Random generator = new Random();
        while (true) {
            int new_item = generator.nextInt(101);
            if (new_item <= NEW_ITEM_CHANCE) {
                ItemBase item = GeradorItem.generateItem(level);
                shop_itens.add(item);
            } else {
                break;
            }
        }

    }

    /**
     * Retorna o maior level do array de Heroi dado,usado para definir level
 dos itens gerados pelo shop
     *
     * @param heros array de Heroi
     * @return maior level
     */
    private static int getMaxLevel(ArrayList< Heroi> heros) {
        int maior = 0;
        for (Heroi hero : heros) {
            if (hero.getLevel() > maior) {
                maior = hero.getLevel();
            }
        }
        return (maior);
    }

    /**
     * Itens do shop
     */
    private static ArrayList< ItemBase> shop_itens = new ArrayList<>();

    /**
     * Um atalho de uma msg muito usada nos menus
     */
    public static void error() {
        System.out.println("Opa um erro ocorreu, tentando corrigir!");
    }

    /**
     * Um atalho de uma msg muito usada nos menus
     */
    public static void cancel() {
        System.out.println("Cancelando acao...");
    }

    /**
     * Um atalho de uma msg muito usada nos menus
     */
    public static void notyet() {
        System.out.println("Nao implementado ainda :D");
    }

    /**
     * Usado para sanity checks
     */
    public static void anomaly() {
        System.out.println("Essa msg nao deve aparecer");
    }

    /**
     * Interface entre chamadas de classe Util onde seleciona uma opcao entre
 um Array de opcoes e pode cancelar selecao
     *
     * @return indice da opcao escolhida
     * @param lista lista de opcoes
     * @param msg mensagem que deve ser exibida
     */
    public static int choice(ArrayList<? extends Descritivel> lista, String msg) {
        int choice;
        do {
            choice = Util.getcanceleable_and_display(lista, msg);
            if (choice == -1) {
                error();
            }
        } while (choice == -1);
        return (choice);
    }

    /**
     * Interface entre chamadas de classe Util onde seleciona uma opcao entre
 um valor minimo e um valor maximo, todas as opcoes entre min e max serão
 consideradas validas, uma acao cancelavel
     *
     * @return indice da opcao escolhida
     * @param min menor indice possivel(inclusivo)
     * @param max maior indice possivel(inclusivo)
     * @param msg mensagem que deve ser exibida
     */
    public static int choice(int min, int max, String msg) {
        int choice;
        do {
            choice = Util.get_canceleable_player_choice(min, max, msg);
            if (choice == -1) {
                error();
            }
        } while (choice == -1);
        return (choice);
    }

    public static void main(String[] args) {
        // TODO code application logic here

        //cria um heroi da classe Knight
        Cavaleiro mc = new Cavaleiro();

        //cria um heroi da classe Mage
        Mago mc2 = new Mago();

        //inicia o gerador de batalhas
        GeradorBatalha battle_arena = new GeradorBatalha();

        //array com todos os herois que o jogador possue
        ArrayList< Heroi> lista_de_herois = new ArrayList<>();
        lista_de_herois.add(mc);
        lista_de_herois.add(mc2);

        Scanner sc = new Scanner(System.in);

        //usada para menus
        int choice = 0;

        //quando o jogador sofrer gameover essa variavel mudara, caso contrario sempre tera esse valor
        int resultado = GeradorBatalha.CONTINUE_CODE;

        //indica quando uma batalha foi termina para ao termino da batalha tentar atualizar o shop
        boolean battle_start = false;

        //ao comecao atualiza o shop
        int max_level = getMaxLevel(lista_de_herois);
        updateShop(max_level);
        Random gerador = new Random();
        while (true) {
            for (Heroi hero : lista_de_herois) {
                hero.everyTime();//aplica efeito de armaduras e armas
            }
            Heroi local_hero = lista_de_herois.get(0);
            do {
                choice = Util.get_player_choice(1, 2, "1-Batalhar\n2-Selecionar um heroi");
                if (choice == -1) {
                    error();
                }
            } while (choice == -1);
            //essa msg provavelmente nao deve aparecer pois o menus acima nao eh cancelavel
            if (choice == Util.BACK_PROTOCOL) {
                cancel();
            } else {
                switch (choice) {
                    case 1:
                        //comecao uma batalha
                        battle_start = true;
                        resultado = battle_arena.random_conflict(lista_de_herois);
                        break;
                    case 2:
                        //selecionar um heroi
                        choice = choice(lista_de_herois, "Qual heroi deseja selecionar?");
                        if (choice == Util.BACK_PROTOCOL) {
                            cancel();
                        } else {
                            local_hero = lista_de_herois.get(choice);
                            System.out.println("Selecionado " + local_hero.getNome());
                            System.out.println(local_hero.displayStatus());
                            choice = choice(1, 3, "1-Ver inventario\n2-Ver Skills\n3-Ver o shop");
                            if (choice == Util.BACK_PROTOCOL) {
                                cancel();
                            } else {
                                switch (choice) {
                                    case 1:
                                        //Menu relacionado ao inventario
                                        if (local_hero.getInventario().size() == 0) {
                                            System.out.println("Inventario vazio!");
                                        } else {
                                            for (ItemBase item : local_hero.getInventario()) {
                                                System.out.println(item.getDescricao());
                                            }
                                            choice = choice(1, 3, "1-Utilizar um item\n2-Equipar um item\n3-Remover um item");
                                            if (choice == Util.BACK_PROTOCOL) {
                                                cancel();
                                            } else {
                                                switch (choice) {
                                                    case 1:
                                                        //utilizar um item
                                                        ArrayList< ConsumivelBase> consumable_itens = local_hero.getConsumableItensArray();
                                                        choice = choice(consumable_itens, "Qual item deseja utilizar?");
                                                        if (choice == Util.BACK_PROTOCOL) {
                                                            cancel();
                                                        } else {
                                                            ItemBase to_search = consumable_itens.get(choice);
                                                            ItemBase no_inventario = local_hero.getItem(to_search);
                                                            if (no_inventario == null) {
                                                                System.out.println("item nao encontrado???");
                                                                anomaly();
                                                            } else {
                                                                if (no_inventario instanceof ConsumivelBase) {
                                                                    ConsumivelBase item = (ConsumivelBase) no_inventario;
                                                                    item.onConsume();
                                                                } else {
                                                                    System.out.println("Outra instancia????");
                                                                    anomaly();
                                                                }
                                                            }
                                                        }
                                                        break;
                                                    case 2:
                                                        //equipar um item
                                                        ArrayList< EquipavelBase> equipable_itens = local_hero.getEquipableItensArray();
                                                        choice = choice(equipable_itens, "Qual item deseja equipar?");
                                                        if (choice == Util.BACK_PROTOCOL) {
                                                            cancel();
                                                        } else {
                                                            ItemBase to_search = equipable_itens.get(choice);
                                                            ItemBase no_inventario = local_hero.getItem(to_search);
                                                            if (no_inventario == null) {
                                                                System.out.println("item nao encontrado???");
                                                                anomaly();
                                                            } else {
                                                                if (no_inventario instanceof EquipavelBase) {
                                                                    EquipavelBase item = (EquipavelBase) no_inventario;
                                                                    item.onEquip();
                                                                    local_hero.equipItem(item);
                                                                } else {
                                                                    System.out.println("Outra instancia????");
                                                                    anomaly();
                                                                }
                                                            }
                                                        }
                                                        break;
                                                    case 3:
                                                        //remover um item
                                                        ArrayList< ItemBase> itens = local_hero.getInventario();
                                                        choice = choice(itens, "Qual item deseja remover?");
                                                        if (choice == Util.BACK_PROTOCOL) {
                                                            cancel();
                                                        } else {
                                                            ItemBase to_search = local_hero.getInventario().get(choice);
                                                            ItemBase no_inventario = local_hero.getItem(to_search);
                                                            if (no_inventario == null) {
                                                                System.out.println("item nao encontrado???");
                                                                anomaly();
                                                            } else {
                                                                no_inventario.onDrop();
                                                            }
                                                        }
                                                        break;
                                                    default:
                                                        anomaly();
                                                        break;

                                                }
                                            }
                                        }
                                        break;
                                    case 2:
                                        //Menu de skills
                                        choice = choice(1, 2, "1-Ver habilidades\n2-Remover habilidades\n");
                                        if (choice == Util.BACK_PROTOCOL) {
                                            cancel();
                                        } else {
                                            switch (choice) {
                                                case 1:
                                                    for (HabilidadeBase skill : local_hero.getLista_de_habilidades()) {
                                                        System.out.println(skill.getDescricao());
                                                    }
                                                    break;
                                                case 2:
                                                    //choice = choice(local_hero.getLista_de_habilidades(), "Qual habilidade deseja remover?");
                                                    choice = Util.BACK_PROTOCOL;
                                                    if (choice == Util.BACK_PROTOCOL) {
                                                        cancel();
                                                    } else {
                                                        System.out.println("Removendo....");
                                                        local_hero.getLista_de_habilidades().remove(choice);
                                                    }
                                                    break;
                                                case 3:
                                                    notyet();
                                                    break;
                                                default:
                                                    anomaly();
                                                    break;
                                            }
                                        }

                                        break;
                                    case 3:
                                        //Menu de itens
                                        System.out.println("O shop tem " + shop_itens.size() + " itens a venda!");
                                        choice = choice(1, 2, "Voce deseja \n1-Comprar Itens\n2-Vender Itens\n");
                                        if (choice == Util.BACK_PROTOCOL) {
                                            cancel();
                                        } else {
                                            switch (choice) {
                                                case 1:
                                                    //comprar um item
                                                    if (shop_itens.size() == 0) {
                                                        System.out.println("Sem itens para vender!....");
                                                        cancel();
                                                    } else {
                                                        ItemBase item = null;
                                                        System.out.println("Qual item deseja comprar?");
                                                        for (int i = 0; i < shop_itens.size(); i++) {
                                                            item = shop_itens.get(i);
                                                            System.out.println("(" + i + ")Preco(" + (item.getValor() * SHOP_INTEREST_RATE) + ")->" + item.getDescricao());
                                                        }
                                                        choice = choice(0, shop_itens.size() - 1, "Selecione um item");
                                                        if (choice == Util.BACK_PROTOCOL) {
                                                            cancel();
                                                        } else {
                                                            item = shop_itens.get(choice);
                                                            int valor_de_venda_do_item = item.getValor() * SHOP_INTEREST_RATE;
                                                            if (local_hero.getGold() >= valor_de_venda_do_item)//se tem dinheiro pra comprar
                                                            {
                                                                System.out.println(local_hero.getNome() + " comprou o item " + item.getNome());
                                                                System.out.println("Saldo antes da transacao : " + local_hero.getGold());
                                                                local_hero.subGold(valor_de_venda_do_item);
                                                                System.out.println("Saldo depois da transacao : " + local_hero.getGold());
                                                                item.setOwner(local_hero);
                                                                local_hero.getInventario().add(item);
                                                                shop_itens.remove(item);
                                                            } else {
                                                                System.out.println("Voce nao tem gold suficiente!");
                                                                System.out.println("Voce tem " + local_hero.getGold() + " e o item custa " + item.getValor() * SHOP_INTEREST_RATE);
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 2:
                                                    //vender um item
                                                    choice = choice(local_hero.getInventario(), "Qual item deseja vender");
                                                    if (choice == Util.BACK_PROTOCOL) {
                                                        cancel();
                                                    } else {
                                                        ItemBase item = local_hero.getInventario().get(choice);
                                                        item.onSell();
                                                    }
                                                    break;
                                                default:
                                                    anomaly();
                                                    break;
                                            }
                                        }
                                        break;
                                    default:
                                        anomaly();
                                        break;
                                }
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
            if (battle_start) {
                //se todos os herois morreram
                if (resultado == GeradorBatalha.GAME_OVER_CODE) {
                    break;
                } else {
                    //se sobreviveram atualiza shop
                    System.out.println("Voce ainda esta vivo!");
                    int will_refresh = gerador.nextInt(101);
                    if (will_refresh <= SHOP_REFRESH_CHANCE) {
                        max_level = getMaxLevel(lista_de_herois);
                        updateShop(max_level);
                    }
                }
                battle_start = false;
            }
        }
    }

}
