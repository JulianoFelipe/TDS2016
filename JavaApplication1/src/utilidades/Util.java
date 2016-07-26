/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author FREE
 */
public class Util {

    /**
     * Constante que define acao de cancelar acao em um menu
     */
    public static final int BACK_PROTOCOL = -1532;

    /**
     * Metodo de interface que quando chamado asume que acao nao pode ser
     * cancelada
     *
     * @param min indice minimo
     * @param max indice maximo
     * @param msg mensagem usada
     * @return escolha do jogador
     */
    public static int get_player_choice(int min, int max, String msg) {
        return (get_player_choice(min, max, msg, false));
    }

    /**
     * Metodo com implementacao de fato que so retorna um valor
     *
     * @param min valor minimo que pode digitar
     * @param max valor maximo que pode digitar
     * @param msg mensagem que deve monstrar
     * @param is_canceleable true se acao pode ser cancelada false caso
     * contrario
     * @return -1 se o valor digitado não for numerico,Util.BACK_PROTOCOL se a
 acao puder ser cancelada e escolher o cancelamento ou um valor entre
 min(inclusivo) e max(inclusivo).
     */
    public static int get_player_choice(int min, int max, String msg, Boolean is_canceleable) {
        try {
            int min_valor = min;
            int max_valor = max;
            System.out.println(msg);
            int player_choice = min_valor;
            do {
                if (player_choice < min_valor || player_choice > max_valor) {
                    if (is_canceleable && player_choice == BACK_PROTOCOL) {
                        return (BACK_PROTOCOL);
                    }
                    System.out.println("Indice invalido!");
                }
                Scanner player_interaction = new Scanner(System.in);
                player_choice = player_interaction.nextInt();
            } while ((player_choice < min_valor || player_choice > max_valor));
            return (player_choice);
        } catch (Exception e) {
            System.out.println("erro e = " + e + "!");
            return (-1);
        }
    }

    /**
     * Metodo interface quando a acao get_player_choice deve ser cancelavel
     *
     * @param min Minimo valor que o usuario pode digitar.
     * @param max Maximo valor que o usuario pode digitar.
     * @param msg Mensagem que deve imprimir.
     * @return    Escolha do jogador.
     */
    public static int get_canceleable_player_choice(int min, int max, String msg) {
        StringBuilder s = new StringBuilder("Cancelar acao = " + BACK_PROTOCOL + "\n");
        s.append(msg);
        return (get_player_choice(min, max, s.toString(), true));
    }

    /**
     * Metodo interface quando get_and_display deve ser cancelavel
     *
     * @param array_of_choice Opcoes
     * @param custom_mensage  Mensagem que deve ser exibida
     * @return                Escolha do jogador.
     */
    public static int getcanceleable_and_display(ArrayList<? extends Descritivel> array_of_choice, String custom_mensage) {
        StringBuilder s = new StringBuilder("Cancelar acao = " + BACK_PROTOCOL + "\n");
        s.append(custom_mensage);
        return (get_and_display(array_of_choice, s.toString(), true));
    }

    /**
     * Metodo que monstra e depois espera o usuario selecionar uma opcao valida
     *
     * @param array_of_choice Opções possíveis.
     * @param custom_mensage  Mensagem a ser exibida ao usuario
     * @param is_canceleable  Parametro que define se a acao pode ou nao ser
     * cancelavel
     * @return               Escolhas do jogador.
     */
    public static int get_and_display(ArrayList<? extends Descritivel> array_of_choice, String custom_mensage, Boolean is_canceleable) {
        display_player_possibilities(array_of_choice, custom_mensage);
        return (get_player_array_choice(array_of_choice, is_canceleable));
    }

    /**
     * Metodo interface que chama get_and_display sem ser cancelavel.
     *
     * @param array_of_choice Opções.
     * @param custom_mensage  Mensagem que deve ser exibida
     * @return                Escolhas do jogador.
     */
    public static int get_and_display(ArrayList<? extends Descritivel> array_of_choice, String custom_mensage) {
        display_player_possibilities(array_of_choice, custom_mensage);
        return (get_player_array_choice(array_of_choice, false));
    }

    /**
     * Demonstra todos os itens em array_of_choice de acordo com metodo
 getDescricao implementado
     *
     * @param array_of_choice Opções.
     * @param custom_mensage  Mensagem exibida.
     */
    public static void display_player_possibilities(ArrayList<? extends Descritivel> array_of_choice, String custom_mensage) {
        if (custom_mensage != null) {
            System.out.println(custom_mensage);
        }
        if (array_of_choice.size() == 0) {
            System.out.println("The array is empty!!\n");
        } else {
            for (int i = 0; i < array_of_choice.size(); i++) {
                System.out.println("(" + i + ")->" + array_of_choice.get(i).getDescricao());
                //System.out.println('('+i+")->"+array_of_choice.get(i).getDescricao());
            }
        }
    }

    /**
     * Para selecionar uma opcao em um array em que voce nao pode cancelar a
     * escolha
     *
     * @param array_of_choice opcoes
     * @return indice possivel dentro de array_of_choice
     */
    public static int get_player_array_choice(ArrayList<? extends Descritivel> array_of_choice) {
        return (get_player_array_choice(array_of_choice, false));
    }

    /**
     * Para selecionar uma opcao de um array
     *
     * @param array_of_choice Opções de selecao.
     * @param is_canceleable  True se a acao pode ser cancelada false caso
     * contrario
     * @return                Escolhas do jogador.
     */
    public static int get_player_array_choice(ArrayList<? extends Descritivel> array_of_choice, Boolean is_canceleable) {
        int player_choice = -1;
        if (array_of_choice.size() == 0) {
            System.out.println("Nao tem opcoes....");
            return (BACK_PROTOCOL);
        }
        while (player_choice < 0 || player_choice >= array_of_choice.size()) {
            try {
                Scanner player_interaction = new Scanner(System.in);
                player_choice = player_interaction.nextInt();
                if ((player_choice < 0 || player_choice >= array_of_choice.size())) {
                    if (is_canceleable && player_choice == BACK_PROTOCOL) {
                        return (player_choice);
                    }
                    System.out.println("Indice invalido!?");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor digite um valor numerico!");
                return (-1);
            } catch (Exception e) {
                System.out.println("erro = " + e);
                return (-1);
            }
        }

        return (player_choice);
    }

}
