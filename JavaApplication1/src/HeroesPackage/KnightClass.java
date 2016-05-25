/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HeroesPackage;
import javaapplication1.HeroClass;

/**
 *
 * @author FREE
 */
public class KnightClass extends HeroClass{
    
    public KnightClass()
    {
        this.setAttack(20.00);
        this.setDefense(10.00);
        this.setMax_hit_points(100.00);
        this.setHit_points(100.00);
        this.setDodge(20);
        this.reset_temporary_stats();
    }
}
