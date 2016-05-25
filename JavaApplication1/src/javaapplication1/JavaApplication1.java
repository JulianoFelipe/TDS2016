/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import HeroesPackage.*;
import Geradores.*;
/**
 *
 * @author FREE
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        KnightClass mc = new KnightClass();
        BattleGenerator battle_arena = new BattleGenerator();
        
        battle_arena.random_conflict(mc);
    }
    
}
