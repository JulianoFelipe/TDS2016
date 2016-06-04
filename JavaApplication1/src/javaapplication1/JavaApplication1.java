/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import HeroesPackage.*;
import Geradores.*;
import java.util.ArrayList;
/**
 *
 * @author Paulo Tenório
 * @author Juliano Felipe
 */
public class JavaApplication1 {

    /**
     * This random battle tester. This da stuff, bruv.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        KnightClass mc = new KnightClass();
        BattleGenerator battle_arena = new BattleGenerator();
        
        ArrayList< HeroClass > lista_de_herois = new ArrayList<>();
        lista_de_herois.add(mc);
        
        battle_arena.random_conflict(lista_de_herois);
    }
    
}
