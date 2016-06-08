/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import HeroesPackage.*;
import Geradores.*;
import java.util.ArrayList;
import java.util.Scanner;
import utillities.*;
/**
 *
 * @author Paulo Tenório
 * @author Juliano Felipe
 */
public class JavaApplication1 {


    public static void error()
    {
        System.out.println("Opa um erro ocorreu, tentando corrigir!");
    }
    
    public static void cancel()
    {
        System.out.println("Cancelando acao...");
    }
    
    public static void notyet()
    {
        System.out.println("Nao implementado ainda :D");
    }
    
    /**
     * Usado para sanity checks
     */
    public static void anomaly()
    {
        System.out.println("Essa msg nao deve aparecer");
    }
    
    /**
     * atalho para chamar getcancelable_player_choice
     * @return 
     */
    public static int choice(ArrayList<? extends Describable > lista,String msg)
    {
        int choice;
        do{
            choice = MyUtil.getcanceleable_and_display(lista, msg);
            if (choice==-1)
            {
                error();
            }
        }while(choice==-1);
        return(choice);
    }
    
    /**
     * Atalho para chamar MyUtil.getcanceleable_and_display
     * @param args 
     */
    public static int choice(int min,int max,String msg)
    {
        int choice;
        do{
            choice = MyUtil.get_player_choice(min, max, msg);
            if (choice==-1)
            {
                error();
            }
        }while(choice==-1);
        return(choice);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        KnightClass mc = new KnightClass();
        MageClass mc2 = new MageClass();
        BattleGenerator battle_arena = new BattleGenerator();
        
        ArrayList< HeroClass > lista_de_herois = new ArrayList<>();
        lista_de_herois.add(mc);
        lista_de_herois.add(mc2);
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while (true)
        {
            HeroClass local_hero = lista_de_herois.get(0);
            do{
                choice = MyUtil.get_player_choice(1, 2, "1-Batalhar\n2-Selecionar um heroi");
                if (choice==-1)
                {
                    error();
                }
            }while(choice==-1);
            
            if (choice==MyUtil.BACK_PROTOCOL)
            {
                cancel();
            }
            else
            {
                switch (choice)
                {
                    case 1:
                        battle_arena.random_conflict(lista_de_herois);
                        break;
                    case 2:
                        choice = choice(lista_de_herois, "Qual heroi deseja selecionar?");
                        if (choice==MyUtil.BACK_PROTOCOL)
                        {
                            cancel();
                        }
                        else
                        {
                            local_hero = lista_de_herois.get(choice);
                            System.out.println("Selecionado "+local_hero.getNome());
                            System.out.println(local_hero.displayStatus());
                            choice = choice(1, 3, "1-Ver inventario\n2-Ver Skills\n3-Ver o shop");
                            if (choice==MyUtil.BACK_PROTOCOL)
                            {
                                cancel();
                            }
                            else
                            {
                                switch(choice)
                                {
                                    case 1:
                                        break;
                                    case 2:
                                        choice = choice(1,3,"1-Ver habilidades\n2-Remover habilidades\n3-Upgrade em habilidades");
                                        if (choice==MyUtil.BACK_PROTOCOL)
                                        {
                                            cancel();
                                        }
                                        else
                                        {
                                            switch (choice)
                                            {
                                                case 1:
                                                    for (BaseSkill skill : local_hero.getLista_de_habilidades())
                                                    {
                                                        System.out.println(skill.getDescription());
                                                    }
                                                    break;
                                                case 2:
                                                    choice = choice(local_hero.getLista_de_habilidades(),"Qual habilidade deseja remover?");
                                                    if (choice==MyUtil.BACK_PROTOCOL)
                                                    {
                                                        cancel();
                                                    }
                                                    else
                                                    {
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

        }
    }
    
}
