/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utillities;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author FREE
 */
public class MyUtil {
    /**
     * Constante que define acao de cancelar acao em um menu
     */
    public static final int BACK_PROTOCOL = -1532;
    /**
     * A ação do player.
     * @return Ação a ser executara pelo player.
     */
    public static int get_player_choice(int min,int max,String msg) {
        try{
        int min_valor = min;
        int max_valor = max;
        System.out.println(msg);
        int player_choice = min_valor;
        do
        {
            if (player_choice<min_valor||player_choice>max_valor)
            {
                if (player_choice==BACK_PROTOCOL)
                {
                    return(BACK_PROTOCOL);
                }
                //System.out.println(player_choice);
                System.out.println("Indice invalido!");
            }
            Scanner player_interaction = new Scanner(System.in);
            player_choice = player_interaction.nextInt();
        }while((player_choice<min_valor||player_choice>max_valor));
        return (player_choice);
        }
        catch(Exception e)
        {
            System.out.println("erro e = "+e+"!");
            return (-1);
        }
    }
    
    /**
     * Uma acao do player que pode ser cancelada
     * @param min minimo valor que o usuario pode digitar
     * @param max maximo valor que o usuario pode digitar
     * @param msg mensagem que deve imprimir
     * @return 
     */
    public static int get_canceleable_player_choice(int min,int max,String msg)
    {
        StringBuilder s = new StringBuilder("Cancelar acao = " + BACK_PROTOCOL + "\n");
        s.append(msg);
        return (get_player_choice(min,max,s.toString()));
    }
    
    public static int getcanceleable_and_display(ArrayList<? extends Describable > array_of_choice,String custom_mensage)
    {
        StringBuilder s = new StringBuilder("Cancelar acao = " + BACK_PROTOCOL + "\n");
        s.append(custom_mensage);
        return (get_and_display(array_of_choice,s.toString()));
    }
    
    public static int get_and_display(ArrayList<? extends Describable > array_of_choice,String custom_mensage)
    {
        display_player_possibilities(array_of_choice,custom_mensage);
        return(get_player_array_choice(array_of_choice));
    }
    
    public static void display_player_possibilities(ArrayList<? extends Describable > array_of_choice,String custom_mensage)
    {
        if (custom_mensage!=null)
        {
            System.out.println(custom_mensage);
        }
        if (array_of_choice.size()==0)
        {
            System.out.println("The array is empty!!\n");
        }
        else
        {
            for (int i=0;i<array_of_choice.size();i++)
            {
                System.out.println("("+i+")->"+array_of_choice.get(i).getDescription());
                //System.out.println('('+i+")->"+array_of_choice.get(i).getDescription());
            }
        }
    }
    
    /**
     * Para selecionar uma opcao de um array
     * @param array_of_choice
     * @return 
     */
    public static int get_player_array_choice(ArrayList<? extends Describable> array_of_choice)
    {
        int player_choice = -1;
        if (array_of_choice.size()==0)
        {
            System.out.println("Nao tem opcoes....");
            return(BACK_PROTOCOL);
        }
        while (player_choice<0||player_choice >= array_of_choice.size())
        {
            try{
                Scanner player_interaction = new Scanner(System.in);
                player_choice = player_interaction.nextInt();
                if ((player_choice<0||player_choice >= array_of_choice.size()))
                {
                    if (player_choice==BACK_PROTOCOL)
                    {
                        return(player_choice);
                    }
                    System.out.println("Indice invalido!?");
                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("Por favor digite um valor numerico!");
                return(-1);
            }
            catch(Exception e)
            {
                System.out.println("erro = "+e);
                return(-1);
            }
        }
        
        return(player_choice);
    }
    
}
