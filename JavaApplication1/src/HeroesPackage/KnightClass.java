/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HeroesPackage;
import javaapplication1.BaseSkill;
import javaapplication1.HeroClass;

/**
 * Classe herói para Cavaleiro.
 * @author Paulo Henrique
 * @author Juliano Felipe 
 */
public class KnightClass extends HeroClass{
    
    /**
     * Construtor que define os atributos de um Cavaleiro.
     */
    public KnightClass()    {
        this.setAttack(100.00);
        this.setDefense(10.00);
        this.setMax_hit_points(1000.00);
        this.setHit_points(1000.00);
        this.setDodge(20);
        this.reset_temporary_stats();
        this.setNome("Sr.Duke of Cornwall");
        this.setSpeed(100.00);
        this.setMana(0.00);
        this.setMax_mana(100.00);
        this.setMana_regain(1.00);
        
        
        for (int i=0;i<10;i++)
        {
            BaseSkill start_skill = Geradores.SkillGenerator.generate_skill();
            start_skill.setOwner(this);
            this.lista_de_habilidades.add(start_skill);
        }
    }
}
